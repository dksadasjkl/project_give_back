from selenium import webdriver
from selenium.webdriver.common.by import By
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options

import pymysql
import time

# MySQL 연결
connection = pymysql.connect(
    host="mysql-db.cz6i24w6m9m3.ap-northeast-2.rds.amazonaws.com",
    port=3306,
    user="본인 아이디",
    password="본인 비밀번호",
    database="해당 테이블",
    charset="utf8mb4"
)
cursor = connection.cursor()

#  Chrome 설정
options = Options()
options.add_argument("--start-maximized")
options.add_argument("--disable-blink-features=AutomationControlled")

service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=options)
wait = WebDriverWait(driver, 10)

#  펀딩 페이지 접속
driver.get("https://happybean.naver.com/fundings/home")
time.sleep(2)

#  더보기 버튼
def click_more():
    try:
        btn = wait.until(EC.element_to_be_clickable(
            (By.CSS_SELECTOR, "button.FundingHomeContent_button_more__1oXF1")
        ))
        driver.execute_script("arguments[0].scrollIntoView(true);", btn)
        time.sleep(0.3)
        driver.execute_script("arguments[0].click();", btn)
        time.sleep(1)
        return True
    except:
        return False

# 카드 로드 보장
def ensure_card_loaded(index):
    while True:
        cards = driver.find_elements(By.CSS_SELECTOR,
            "li.FundingHomeContent_item__1zEcG a.FundingCard_wrap__3sCFN"
        )
        if len(cards) > index:
            return cards

        print(f"{len(cards)}개 → 부족 → 더보기 클릭")
        if not click_more():
            return cards

#  상세 스크롤
def scroll_to_bottom():
    last_height = driver.execute_script("return document.body.scrollHeight")
    for _ in range(10):
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        time.sleep(0.7)
        new_height = driver.execute_script("return document.body.scrollHeight")
        if new_height == last_height:
            break
        last_height = new_height

#  Summary 이미지 + 상세내용 크롤링
MAX_COUNT = 150
insert_count = 0

summary_images = []   # ⭐ 여기 summary 이미지만 모음
funding_details = []

for project_idx in range(MAX_COUNT):

    print(f"\n===== {project_idx + 1}번 펀딩 상세 페이지 =====")

    cards = ensure_card_loaded(project_idx)
    if project_idx >= len(cards):
        print("카드 없음 → 종료")
        break

    card = cards[project_idx]
    driver.execute_script("arguments[0].scrollIntoView(true);", card)
    time.sleep(0.4)
    driver.execute_script("arguments[0].click();", card)
    time.sleep(2)

    scroll_to_bottom()
    time.sleep(1)

    #  Summary 이미지 수집
    try:
        summary_div = wait.until(EC.presence_of_element_located(
            (By.CSS_SELECTOR, "div.FundingDetailSummary_image__qODvx")
        ))
        style_attr = summary_div.get_attribute("style")

        # style="background-image: url("...");" 에서 URL 추출
        summary_url = style_attr.split('url("')[1].split('")')[0]

    except:
        summary_url = None

    summary_images.append({
        "project_id": project_idx + 151,
        "summary_image_url": summary_url
    })

    # 기존 상세 제목 / 내용 / 이미지 수집
    titles = driver.find_elements(By.CSS_SELECTOR,
        "strong.FundingDetailEditorParagraph_title__2o5M9"
    )
    texts = driver.find_elements(By.CSS_SELECTOR,
        "p.FundingDetailEditorParagraph_content__3l1rd"
    )
    imgs = driver.find_elements(By.CSS_SELECTOR,
        "img.FundingDetailEditorRollingImage_image__1Kr_s"
    )

    max_len = max(len(titles), len(texts), len(imgs), 1)

    project_id = project_idx + 151
    order_no = 1

    for i in range(max_len):
        subtitle = titles[i].text if i < len(titles) else ""
        content = texts[i].text if i < len(texts) else ""
        img_url = imgs[i].get_attribute("src") if i < len(imgs) else ""

        if subtitle.strip() == "" and content.strip() == "":
            continue

        # DB 저장 (기존과 동일)
        sql = """
            INSERT INTO donation_project_detail_tb
            (donation_project_id, donation_project_detail_subtitle,
             donation_project_detail_image_url, donation_project_detail_content,
             donation_project_detail_order_no, create_date, update_date)
            VALUES (%s, %s, %s, %s, %s, NOW(), NOW())
        """

        cursor.execute(sql, (
            project_id, subtitle, img_url, content, order_no
        ))
        connection.commit()
        insert_count += 1
        order_no += 1

    driver.back()
    time.sleep(1.5)


driver.quit()
cursor.close()
connection.close()

print("\n=========== Summary 이미지 ===========")
for row in summary_images:
    print(row)

print("\n=========== 총 DETAIL INSERT ===========")
print(insert_count)
