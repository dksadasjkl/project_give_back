import time
import requests
from bs4 import BeautifulSoup

HEADERS = {
    "User-Agent": (
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
        "AppleWebKit/537.36 (KHTML, like Gecko) "
        "Chrome/120.0.0.0 Safari/537.36"
    )
}

def get_soup(url: str) -> BeautifulSoup | None:
    """URL에서 HTML 받아서 BeautifulSoup 객체로 변환"""
    try:
        resp = requests.get(url, headers=HEADERS, timeout=10)
        resp.raise_for_status()
    except requests.RequestException as e:
        print(f"[ERROR] {url} 요청 실패: {e}")
        return None

    return BeautifulSoup(resp.text, "html.parser")


# =========================
# 1) 펀딩 페이지 자동 감지
# =========================
def is_funding_page(soup: BeautifulSoup) -> bool:
    """
    이 페이지가 '펀딩' 페이지인지 자동 감지하는 함수.
    - 예시 기준:
      1) 상단에 '펀딩', '리워드', '후원 금액 선택' 같은 키워드가 존재
      2) 리워드 박스용 공통 클래스가 존재 (예: .reward-item, .reward-card 등)
    """

    text = soup.get_text(separator=" ", strip=True)

    # 1) 텍스트 기반 감지 (자주 나오는 문구를 키워드로)
    funding_keywords = [
        "펀딩", "리워드", "후원 금액 선택", "리워드 선택", "펀딩 참여",
        "리워드 구성", "펀딩 리워드"
    ]
    if any(kw in text for kw in funding_keywords):
        return True

    # 2) DOM 구조 기반 감지 (사이트 구조에 맞게 수정)
    reward_selectors = [
        "div.reward-item",
        "li.reward-item",
        "div.reward-card",
        "div.reward_box",
        "div[class*=reward]",
    ]

    for sel in reward_selectors:
        if soup.select_one(sel):
            return True

    return False


# =========================
# 2) 리워드 정보 추출
# =========================
def extract_rewards(soup: BeautifulSoup) -> list[dict]:
    """
    펀딩 페이지에서 리워드 정보(금액, 제목, 설명 등)를 크롤링.
    사이트 구조에 맞게 selector 부분만 수정해 사용.
    """

    rewards = []

    # 예시: 여러 가지 가능성 고려해서 우선순위로 찾기
    reward_containers = (
        soup.select("div.reward-item")
        or soup.select("li.reward-item")
        or soup.select("div.reward-card")
        or soup.select("div.reward_box")
    )

    if not reward_containers:
        print("[WARN] 리워드 박스를 찾지 못했습니다.")
        return rewards

    for box in reward_containers:
        # 이하 selector들은 실제 사이트에 맞게 수정
        # 금액
        price_el = box.select_one(".price, .reward-price, .amount, .rewardPrice")
        # 리워드 이름/제목
        title_el = box.select_one(".title, .reward-title, .name, .rewardName")
        # 상세 설명
        desc_el = box.select_one(".desc, .description, .reward-desc, .rewardDescription")
        # 제한 수량, 남은 수량
        limit_el = box.select_one(".limit, .stock, .reward-limit, .left-quantity")

        price = price_el.get_text(strip=True) if price_el else ""
        title = title_el.get_text(strip=True) if title_el else ""
        description = desc_el.get_text(strip=True) if desc_el else ""
        limit = limit_el.get_text(strip=True) if limit_el else ""

        rewards.append(
            {
                "title": title,
                "price": price,
                "description": description,
                "limit": limit,
            }
        )

    return rewards


# =========================
# 3) 여러 URL 돌면서 펀딩 리워드만 크롤링
# =========================
def crawl_funding_rewards(url_list: list[str]) -> list[dict]:
    """
    여러 프로젝트 URL 돌면서
    - 펀딩 페이지인지 자동 감지
    - 펀딩이면 리워드 정보만 수집
    """
    result = []

    for url in url_list:
        print(f"\n[INFO] 크롤링 시작: {url}")
        soup = get_soup(url)
        if soup is None:
            continue

        if not is_funding_page(soup):
            print("  → 펀딩 페이지가 아니라고 판단, 스킵")
            continue

        rewards = extract_rewards(soup)
        if not rewards:
            print("  → 리워드 정보 없음")
            continue

        for r in rewards:
            result.append(
                {
                    "url": url,
                    **r,
                }
            )

        # 너무 빠르게 요청 보내지 않도록 딜레이
        time.sleep(0.5)

    return result


if __name__ == "__main__":
    # 예시: 이미 수집해 둔 상세 페이지 URL 리스트
    project_urls = [
        "https://example.com/project/1",
        "https://example.com/project/2",
        "https://example.com/project/3",
    ]

    data = crawl_funding_rewards(project_urls)

    print("\n===== 크롤링 결과 =====")
    for row in data:
        print(row)
