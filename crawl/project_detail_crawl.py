from selenium import webdriver
from selenium.webdriver.common.by import By
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options

import pymysql
import time

#  MySQL 연결
connection = pymysql.connect(
    host="mysql-db.cz6i24w6m9m3.ap-northeast-2.rds.amazonaws.com",
    port=3306,
    user="aws",
    password="1q2w3e!!",     # 본인 비밀번호
    database="give_db",
    charset="utf8mb4"
)
cursor = connection.cursor()


# Chrome 설정
options = Options()
options.add_argument("--start-maximized")
options.add_argument("--disable-blink-features=AutomationControlled")

service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=options)
wait = WebDriverWait(driver, 10)


#  페이지 접속
driver.get("https://happybean.naver.com/donation/DonateHomeMain")
time.sleep(2)


#  더보기 클릭 함수
def click_more():
    try:
        btn = wait.until(EC.element_to_be_clickable((By.ID, "btn_more")))
        driver.execute_script("arguments[0].scrollIntoView(true);", btn)
        time.sleep(0.3)
        driver.execute_script("arguments[0].click();", btn)
        time.sleep(1)
        return True
    except:
        return False


#  카드 로드 보장
def ensure_card_loaded(index):
    while True:
        cards = driver.find_elements(By.CSS_SELECTOR, "a.card")
        if len(cards) > index:
            return cards

        print(f"{len(cards)}개 → 부족 → 더보기 클릭")
        if not click_more():
            return cards


#  상세 페이지 스크롤 최하단까지 내리는 함수
def scroll_to_bottom():
    last_height = driver.execute_script("return document.body.scrollHeight")

    for _ in range(8):  # 5~8번 정도 스크롤 → 모든 컨텐츠 로딩됨
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        time.sleep(0.8)

        new_height = driver.execute_script("return document.body.scrollHeight")
        if new_height == last_height:
            break
        last_height = new_height



# 상세 페이지 전체 크롤링
MAX_COUNT = 150

detail_results = []
insert_count = 0

for project_idx in range(MAX_COUNT):

    print(f"\n======== {project_idx + 1}번 상세 페이지 ========")

    cards = ensure_card_loaded(project_idx)
    if project_idx >= len(cards):
        print("카드 없음 → 종료")
        break

    # 상세로 이동
    card = cards[project_idx]
    driver.execute_script("arguments[0].scrollIntoView(true);", card)
    time.sleep(0.4)
    driver.execute_script("arguments[0].click();", card)
    time.sleep(2)

    # 상세 페이지 스크롤 최하단까지
    scroll_to_bottom()
    time.sleep(1)

    # ============================================
    #  기관 로고
    # ============================================
    try:
        logo = wait.until(
            EC.presence_of_element_located(
                (By.CSS_SELECTOR, "img.AsideBanner_image__2kWsw")
            )
        ).get_attribute("src")
    except:
        logo = None

    # ============================================
    # 상세 텍스트/이미지 수집
    # ============================================
    subtitles = driver.find_elements(By.CSS_SELECTOR, "strong.DonationEditorParagraph_title__3vjyU")
    contents = driver.find_elements(By.CSS_SELECTOR, "p.DonationEditorParagraph_text__hcuoX")
    images = driver.find_elements(By.CSS_SELECTOR, "img.DonationEditorRollingImage_image__xf40t")

    max_len = max(len(subtitles), len(contents), len(images), 1)

    project_id = project_idx + 1
    order_no = 1

    for i in range(max_len):

        subtitle = subtitles[i].text if i < len(subtitles) else ""
        content = contents[i].text if i < len(contents) else ""
        img_url = images[i].get_attribute("src") if i < len(images) else ""

        # 제목/내용 둘 다 없으면 슬라이드 이미지이므로 제외
        if subtitle.strip() == "" and content.strip() == "":
            continue

        # 결과 저장
        row = {
            "project_id": project_id,
            "subtitle": subtitle,
            "image_url": img_url,
            "content": content,
            "order_no": order_no,
            "logo": logo
        }
        detail_results.append(row)

        # ============================================
        #  DB INSERT
        # ============================================
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

    # 뒤로가기
    driver.back()
    time.sleep(2)

# 종료
driver.quit()
cursor.close()
connection.close()

#  최종 결과 출력
print("\n=========== 최종 결과 ===========")
for idx, item in enumerate(detail_results, 1):
    print(f"{idx}번 =", item)

print("\n======== 총 INSERT :", insert_count, "개 ========")
