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

# Chrome 설정
options = Options()
options.add_argument("--start-maximized")

service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=options)

# 사이트 접속
driver.get("https://happybean.naver.com/donation/DonateHomeMain")
time.sleep(2)

wait = WebDriverWait(driver, 10)


# 더보기 버튼 클릭 함수
def click_more():
    try:
        btn = wait.until(EC.element_to_be_clickable((By.ID, "btn_more")))
        driver.execute_script("arguments[0].scrollIntoView(true);", btn)
        time.sleep(0.3)
        driver.execute_script("arguments[0].click();", btn)
        return True
    except:
        return False

# 스크롤 + 더보기 실행 150개
MAX_COUNT = 150

while True:
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
    time.sleep(1)

    cards = driver.find_elements(By.CSS_SELECTOR, "a.card")
    print("현재 카드:", len(cards))

    if len(cards) >= MAX_COUNT:
        print("150개 도달 → 수집 종료")
        break

    if not click_more():
        print("더 이상 더보기 없음 → 종료")
        break

cards = cards[:MAX_COUNT]
print("최종 카드 개수:", len(cards))


# 카드 데이터 수집
values = []
today = datetime.now().date()

for c in cards:
    try:
        title = c.find_element(By.CSS_SELECTOR, ".card_title").text.strip()
    except:
        title = ""

    try:
        org = c.find_element(By.CSS_SELECTOR, ".card_organization").text.strip()
    except:
        org = ""

    try:
        img = c.find_element(By.CSS_SELECTOR, "img.card_img").get_attribute("src")
    except:
        img = ""

    # 데이터 구성
    category_id = random.randint(1, 10)
    target_amount = random.randint(10, 50) * 100000
    current_amount = random.randint(1, target_amount - 1)

    end_date = today + timedelta(days=random.randint(30, 90))
    project_type = "DONATION"
    org_image_url = "https://example.com/default-org.png"

    values.append((title, org, org_image_url, img, category_id,
                   current_amount, target_amount, today, end_date, project_type))

driver.quit()


# MySQL DB 연결
connection = pymysql.connect(
    host="mysql-db.cz6i24w6m9m3.ap-northeast-2.rds.amazonaws.com",
    port=3306,
    user="",
    password="",  
    database="give_db",
)
cursor = connection.cursor()


# INSERT SQL
sql = """
INSERT INTO donation_project_tb
(donation_project_title,
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
 update_date)
VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, NOW(), NOW())
"""

cursor.executemany(sql, values)
connection.commit()

print("저장 완료! 총", len(values), "건 저장됨")
connection.close()
