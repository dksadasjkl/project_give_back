from selenium import webdriver
from selenium.webdriver.common.by import By
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
import pymysql
import random

# ----------------------------
# Chrome 설정
# ----------------------------
options = Options()
options.add_argument("--start-maximized")

service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=options)
wait = WebDriverWait(driver, 10)

# ----------------------------
# 스토어 페이지 접속
# ----------------------------
URL = "https://happybean.naver.com/flower/product"
driver.get(URL)
time.sleep(2)

# ----------------------------
# ⭐ 더보기 버튼 클릭 (텍스트 기반 → 100% 안정적)
# ----------------------------
def click_more():
    try:
        btn = wait.until(
            EC.element_to_be_clickable(
                (By.XPATH, "//button[.//span[contains(text(), '더보기')]]")
            )
        )
        driver.execute_script("arguments[0].scrollIntoView(true);", btn)
        time.sleep(0.5)
        btn.click()
        print("👉 더보기 클릭됨")
        return True
    except Exception as e:
        print("더보기 없음/클릭 실패:", e)
        return False

# ----------------------------
# ⭐ 스크롤 + 더보기 반복하여 150개 확보
# ----------------------------
MAX_COUNT = 150

while True:
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
    time.sleep(1)

    cards = driver.find_elements(
        By.CSS_SELECTOR,
        "ul.FlowerProduct_list__Z8ryl li.FlowerProductCard_list_item__3oGlV"
    )
    print("현재 카드 수:", len(cards))

    if len(cards) >= MAX_COUNT:
        print("목표 달성 → 150개 확보 완료")
        break

    if not click_more():
        print("더보기 버튼 없음 → 종료")
        break

cards = cards[:MAX_COUNT]
print("최종 상품 수:", len(cards))

# ----------------------------
# 상품 정보 수집
# ----------------------------
values = []

for c in cards:
    try:
        name = c.find_element(By.CSS_SELECTOR, "strong.FlowerProductCard_product_title__1t1Mx").text.strip()
    except:
        name = ""

    try:
        img = c.find_element(By.CSS_SELECTOR, "img.FlowerProductCard_product_image__3csSg").get_attribute("src")
    except:
        img = ""

    try:
        price_text = c.find_element(By.CSS_SELECTOR, "span.FlowerProductCard_product_price__3Qqyr").text
        price = int(price_text.replace(",", "").strip())
    except:
        price = 0

    # seller_id는 요구대로 0 고정
    seller_id = 0  

    # 품절 여부
    try:
        c.find_element(By.CSS_SELECTOR, "span.FlowerProductCard_product_soldout__3Dk00")
        is_active = 0
    except:
        is_active = 1

    category_id = random.randint(1, 8)
    original_price = int(price * random.uniform(1.1, 1.4))
    stock = random.randint(20, 300)

    values.append((
        category_id,
        seller_id,
        name,
        "",
        price,
        original_price,
        stock,
        img,
        "",
        is_active
    ))

driver.quit()

# ----------------------------
# DB INSERT
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
INSERT INTO store_product_tb
(
    category_id,
    seller_id,
    product_name,
    product_description,
    product_price,
    product_original_price,
    product_stock,
    product_image_url,
    product_image_detail_url,
    is_active,
    create_date,
    update_date
)
VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, NOW(), NOW())
"""

cursor.executemany(sql, values)
connection.commit()

print("\n🎉 총", len(values), "개의 스토어 상품 저장 완료!")
connection.close()
