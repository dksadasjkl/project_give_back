from selenium import webdriver
from selenium.webdriver.common.by import By
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options

import time
import pymysql

# 크롬 설정
options = Options()
options.add_argument("--start-maximized")

service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=options)

wait = WebDriverWait(driver, 10)

# 페이지 접속
driver.get("https://happybean.naver.com/donation/DonateHomeMain")
time.sleep(2)

# 더보기 함수
def click_more():
    try:
        btn = wait.until(
            EC.element_to_be_clickable((By.ID, "btn_more"))
        )
        driver.execute_script("arguments[0].scrollIntoView(true);", btn)
        time.sleep(0.3)
        driver.execute_script("arguments[0].click();", btn)
        time.sleep(1.2)
        return True
    except:
        return False

# 원하는 index까지 보장해주는 함수
def ensure_card_loaded(target_index):
    """target_index(예: 0~149)가 존재할 때까지 더보기 누름"""
    while True:
        cards = driver.find_elements(By.CSS_SELECTOR, "a.card")

        if len(cards) > target_index:
            return cards  

        print(f"카드 {len(cards)}개 → 부족함 → 더보기 클릭")
        if not click_more():
            print("더보기 없음 → 더 이상 확보 불가")
            return cards

# 로고 150개 수집
MAX_COUNT = 150
detail_results = []

for idx in range(MAX_COUNT):

    print(f"\n===== {idx+1}번 프로젝트 처리 중 =====")

    # 현재 idx번째 카드를 위한 준비
    cards = ensure_card_loaded(idx)

    if idx >= len(cards):
        print("카드를 더 이상 확보할 수 없습니다. 종료.")
        break

    card = cards[idx]

    # 클릭
    driver.execute_script("arguments[0].scrollIntoView(true);", card)
    time.sleep(0.3)
    driver.execute_script("arguments[0].click();", card)

    # 상세 페이지 로딩
    time.sleep(1.5)

    # 단체 로고 수집
    try:
        logo = wait.until(
            EC.presence_of_element_located(
                (By.CSS_SELECTOR, "img.AsideBanner_image__2kWsw")
            )
        ).get_attribute("src")
    except:
        logo = None

    print("단체 로고:", logo)
    detail_results.append(logo)

    # 뒤로가기
    driver.back()
    time.sleep(1.5)

# 수집 결과 출력
print("\n=========== 최종 결과 ===========")
for i, v in enumerate(detail_results, 1):
    print(f"{i}번 =", v)

driver.quit()


# DB 데이터 입력
connection = pymysql.connect(
    host="mysql-db.cz6i24w6m9m3.ap-northeast-2.rds.amazonaws.com",
    port=3306,
    user="본인 아이디",
    password="본인 비밀번호",
    database="해당 테이블",
    charset="utf8mb4",
    autocommit=True
)

cursor = connection.cursor()

sql = """
UPDATE donation_project_tb
SET donation_project_organization_image_url = %s
WHERE donation_project_id = %s
"""

update_list = []

for idx, logo in enumerate(detail_results, start=1):
    update_list.append((logo, idx))

cursor.executemany(sql, update_list)

print(f"DB 업데이트 완료! 총 {len(update_list)}건 반영됨")

cursor.close()
connection.close()

