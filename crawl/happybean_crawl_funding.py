from selenium import webdriver
from selenium.webdriver.common.by import By
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options

import time
import pymysql
import random
from datetime import datetime, timedelta

# ----------------------------
# Chrome 설정
# ----------------------------
options = Options()
options.add_argument("--start-maximized")

service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=options)

wait = WebDriverWait(driver, 10)

# ----------------------------
# 펀딩 페이지 접속
# ----------------------------
driver.get("https://happybean.naver.com/fundings/home")
time.sleep(2)

# ----------------------------
# 더보기 버튼 클릭 함수
# ----------------------------
def click_more():
    try:
        btn = wait.until(
            EC.element_to_be_clickable(
                (By.CSS_SELECTOR, "button[class^='FundingHomeContent_button_more']")
            )
        )
        driver.execute_script("arguments[0].scrollIntoView(true);", btn)
        time.sleep(0.3)
        driver.execute_script("arguments[0].click();", btn)
        return True
    except Exception as e:
        print("더보기 클릭 실패:", e)
        return False


# ----------------------------
# 스크롤 + 더보기 반복 → 150개 확보
# ----------------------------
MAX_COUNT = 150

while True:
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
    time.sleep(1)

    cards = driver.find_elements(By.CSS_SELECTOR, "li.FundingHomeContent_item__1zEcG a.FundingCard_wrap__3sCFN")
    print("현재 카드:", len(cards))

    if len(cards) >= MAX_COUNT:
        print("150개 도달 → 종료")
        break

    if not click_more():
        print("더보기 없음 → 종료")
        break

cards = cards[:MAX_COUNT]
print("최종 카드:", len(cards))


# ----------------------------
# 펀딩 데이터 수집
# ----------------------------
values = []
today = datetime.now().date()

for c in cards:

    try:
        title = c.find_element(By.CSS_SELECTOR, "strong.FundingCard_title__2hI5-").text.strip()
    except:
        title = ""

    try:
        org = c.find_element(By.CSS_SELECTOR, "div.FundingCard_organization__2MYka").text.strip()
    except:
        org = ""

    try:
        img = c.find_element(By.CSS_SELECTOR, "img.FundingCard_img__2oL8b").get_attribute("src")
    except:
        img = ""

    # 🔥 랜덤 데이터 생성
    category_id = random.randint(11, 17)
    target_amount = random.randint(20, 80) * 100000
    current_amount = random.randint(1, target_amount - 1)
    end_date = today + timedelta(days=random.randint(20, 60))
    project_type = "FUNDING"
    org_image_url = "https://example.com/default-org.png"

    values.append((
        title, org, org_image_url, img, category_id,
        current_amount, target_amount, today, end_date, project_type
    ))

driver.quit()


# ----------------------------
# MySQL 저장
# ----------------------------
connection = pymysql.connect(
    host="mysql-db.cz6i24w6m9m3.ap-northeast-2.rds.amazonaws.com",
    port=3306,
    user="본인 아이디",
    password="본인 비밀번호",
    database="해당 테이블",
)
cursor = connection.cursor()

sql = """
INSERT INTO donation_project_tb
(
 donation_project_title,
 donation_project_organization,
 donation_project_organization_image_url,
 donation_project_image_url,
 donation_category_id,
 donation_project_current_amount,
 donation_project_target_amount,
 donation_project_start_date,
 donation_project_end_date,
 donation_project_type,
 create_date,
 update_date
)
VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, NOW(), NOW())
"""

cursor.executemany(sql, values)
connection.commit()

print("저장 완료! 총", len(values), "건 저장됨")
connection.close()
