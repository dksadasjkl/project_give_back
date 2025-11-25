from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options

import pymysql
import time
import json

# ----------------------------
# DB 연결
# ----------------------------
connection = pymysql.connect(
    host="mysql-db.cz6i24w6m9m3.ap-northeast-2.rds.amazonaws.com",
    port=3306,
    user="본인 아이디",
    password="본인 비밀번호",
    database="해당 테이블",
    charset="utf8mb4"
)
cursor = connection.cursor()

# ----------------------------
# Chrome 설정
# ----------------------------
options = Options()
options.add_argument("--start-maximized")
options.add_argument("--disable-blink-features=AutomationControlled")

service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=options)
wait = WebDriverWait(driver, 10)

driver.get("https://happybean.naver.com/flower/product")
time.sleep(2)

# ----------------------------
# 더보기 버튼
# ----------------------------
def click_more():
    try:
        btn = wait.until(EC.element_to_be_clickable(
            (By.XPATH, "//button[.//span[contains(text(),'더보기')]]")
        ))
        driver.execute_script("arguments[0].scrollIntoView(true);", btn)
        time.sleep(0.3)
        btn.click()
        time.sleep(1)
        return True
    except:
        return False

# ----------------------------
# 카드 보장
# ----------------------------
def ensure_card_loaded(index):
    while True:
        cards = driver.find_elements(
            By.CSS_SELECTOR,
            "li.FlowerProductCard_list_item__3oGlV a[class^='FlowerProductCard_product_link__']"
        )
        print("현재 카드 수:", len(cards))

        if len(cards) > index:
            return cards

        if not click_more():
            return cards

# ----------------------------
# 상세 스크롤
# ----------------------------
def scroll_detail_page():
    last_height = driver.execute_script("return document.body.scrollHeight")
    for _ in range(10):
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        time.sleep(0.5)
        new_height = driver.execute_script("return document.body.scrollHeight")
        if new_height == last_height:
            break
        last_height = new_height


# ======================================================
# 🔥🔥 메인 로직 (가장 큰 해상도 이미지 자동 선택) 🔥🔥
# ======================================================
MAX_COUNT = 150

for idx in range(MAX_COUNT):
    print(f"\n===== {idx + 1} 번째 상품 처리 =====")

    cards = ensure_card_loaded(idx)
    if idx >= len(cards):
        print("카드 부족 → 종료")
        break

    card = cards[idx]

    driver.execute_script("arguments[0].scrollIntoView(true);", card)
    time.sleep(0.5)
    driver.execute_script("arguments[0].click();", card)
    time.sleep(2)

    # 상세정보 펼쳐보기
    try:
        more_btn = wait.until(EC.element_to_be_clickable(
            (By.CSS_SELECTOR, "button[data-shp-area='detailitm.more']")
        ))
        driver.execute_script("arguments[0].scrollIntoView(true);", more_btn)
        time.sleep(0.3)
        more_btn.click()
        time.sleep(1.2)
    except:
        print("상세정보 펼쳐보기 없음")

    scroll_detail_page()

    # ----------------------------------------------
    # ⭐ SmartEditor 이미지 중 ‘가장 큰 해상도’ 자동 선택
    # ----------------------------------------------
    best_img_url = None
    best_area = 0

    try:
        # 모든 이미지 컨테이너 가져오기
        modules = driver.find_elements(
            By.CSS_SELECTOR, "div.se-module.se-module-image a.se-module-image-link"
        )

        for m in modules:
            linkdata = m.get_attribute("data-linkdata")
            if not linkdata:
                continue

            try:
                data = json.loads(linkdata.replace("&quot;", "\""))
                w = int(data.get("originalWidth", 0))
                h = int(data.get("originalHeight", 0))
                area = w * h
                src = data.get("src")

                if src and area > best_area:
                    best_area = area
                    best_img_url = src

            except:
                continue

    except Exception as e:
        print("이미지 분석 오류:", e)

    print("📌 선택된 최고 해상도 이미지:", best_img_url)

    # DB 저장
    if best_img_url:
        sql = """
            UPDATE store_product_tb
            SET product_image_detail_url = %s
            WHERE product_id = %s
        """
        cursor.execute(sql, (best_img_url, idx + 1))
        connection.commit()

    driver.back()
    time.sleep(1.5)

cursor.close()
connection.close()
driver.quit()

print("\n🎉 product_image_detail_url 150개 업데이트 완료 (최고 해상도 이미지 적용)! 🎉")
