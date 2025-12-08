# 🎁 GIVE – Donation · Funding · Store Platform  
> **기부·펀딩·스토어·Admin·무중단 배포까지 포함한 실서비스 수준의 풀스택 사회공헌 플랫폼**  
> Donation · Funding · Store · Admin · Blue/Green Deploy · OAuth2 · PortOne 결제 · Kakao Map

<br/>

---

# 🔖 목차
- [✨ 프로젝트 소개](#-프로젝트-소개)  
- [💼 작업 역할](#-작업-역할)  
- [📚 프로젝트 진행 현황](#-프로젝트-진행-현황)  
- [✔ 개발 컨벤션](#-개발-컨벤션)  
- [🔍 배포 구조](#-배포-구조)  
- [🛠 기술 스택](#-기술-스택)  
- [📆 프로젝트 일정](#-프로젝트-일정)  
- [📄 API 명세서 & ERD](#-api-명세서--erd)  
- [📋 메뉴 구조도](#-메뉴-구조도)  
- [🖥 화면 구현](#-화면-구현)  
- [💡 프로젝트 회고](#-프로젝트-회고)  

<br/><br/>

---

# ✨ 프로젝트 소개

### 🌟 제목  
**GIVE – Donation · Funding · Store 통합 사회공헌 플랫폼**

### 🚀 목적  
기부(Donation), 펀딩(Funding), 스토어(Store) 기능을 하나의 통합 서비스로 제공하여  
사용자는 **후원 + 소비 + 리뷰 관리**,  
관리자는 **전체 운영(Admin)** 을 수행할 수 있도록 개발한 플랫폼입니다.

또한 AWS 기반의 **Blue/Green 무중단 배포 인프라**,  
Back-End 전반의 비즈니스 로직, Front-End UI/UX까지  
**실서비스 수준의 아키텍처 구축을 목표로 개발**하였습니다.

### 📆 제작 기간  
**2025.09 ~ 2025.11**

---

## 🔎 주요 기능 요약 (테이블 버전)

| 구분 | 주요 기능 |
|------|-----------|
| 🎁 사용자 기능 | 프로젝트 조회/후원, 검색·필터, 결제, 지도 기반 기부처 조회, 리뷰/댓글/신고, 장바구니·포인트, 마이페이지 |
| 🛒 스토어 기능 | 상품 구매, 장바구니, 결제, 리뷰 관리, 찜하기 |
| 🛡 관리자 기능 | 기부/펀딩 CRUD, 스토어(상품/리뷰/주문/배송/신고), 결제관리, 통계 Dashboard |
| ⚙ 시스템 기능 | Blue/Green 무중단 배포, Scheduler 배송 자동 업데이트, Firebase 이미지 업로드, OAuth2 소셜 로그인 |

<br/><br/>

---

# 💼 작업 역할  
<h3>🛩 서창현 (Full-Stack Developer)</h3>

---

## 🔧 Back-End 개발

- Spring Security + JWT 기반 인증/인가 전면 개발  
- OAuth2(Google·Kakao·Naver) 소셜 로그인 연동  
- 기부/펀딩/스토어/주문/결제/배송 등 **전체 API 개발**  
- 프로젝트/댓글/리뷰/신고/통계·대시보드 API 개발  
- MyBatis 기반 SQL 설계 및 복잡 통계 쿼리 작성  
- SMTP 기반 비밀번호 초기화 메일 발송  
- Scheduler를 통한 배송 자동 상태 업데이트  
- Firebase Storage 이미지 업로드 API 개발  
- Spring Profiles 기반 Blue/Green 서버 분리 운영  
- AWS EC2 + Docker + Nginx 무중단 배포 인프라 구축  

---

## 🖥 Front-End 개발

- React 기반 전체 페이지 UI 개발  
- React Query 상태 관리 및 캐싱 최적화  
- Recoil 전역 상태 관리  
- PortOne 결제 기능 구현  
- Kakao Maps API 기반 지도 기능 개발  
- Firebase 이미지 업로드 UI 구축  
- 관리자/사용자 전체 페이지 UI/UX 구현  
- Recharts 기반 Dashboard 통계 시각화  
- Emotion CSS 기반 반응형 UI 설계  

<br/><br/>

---

# 📚 프로젝트 진행 현황

- 📌 **Front-End Repository**  
  https://github.com/dksadasjkl/project_give_front/commits/main  

- 📌 **Back-End Repository**  
  https://github.com/dksadasjkl/project_give_back/commits/main  

<br/><br/>

---

# ✔ 개발 컨벤션

### 🔗 Git Commit & Code Convention 링크  
- **Git Commit Convention:**  
  https://mangrove-comic-a17.notion.site/Git-Commit-Message-Convention-8e5410868b7d4bb6a9e61e976d638963  

- **Code Convention:**  
  https://mangrove-comic-a17.notion.site/Code-Convention-ed0bf7dc4b974f17acae503ba05efc24?pvs=74  

<br/><br/>

---

# 🔍 배포 구조

### 🚀 AWS 기반 Blue/Green 무중단 배포

**Infra 구성**
- AWS EC2 (Ubuntu 20.04 + Docker Engine)  
- Docker Blue(8001) / Green(8002) 서버 운영  
- Nginx Reverse Proxy  
- GitHub Actions CI/CD 자동 배포  
- CloudFront + S3 정적 호스팅  
- Route53 도메인 연결  
- RDS MySQL 운영 DB  

---

### 📦 배포 과정

1. GitHub main push  
2. GitHub Actions → Build & Docker Image 생성  
3. Docker Hub에 이미지 업로드  
4. EC2에서 Deploy Script 실행  
5. 신규 Green 서버 실행 후 Health Check  
6. Nginx Upstream 자동 전환(무중단)  
7. Old Blue 서버 종료  

---

### 📊 서비스 아키텍처  
<img width="1471" height="1027" alt="Image" src="https://github.com/user-attachments/assets/25643049-b0d4-4dce-9494-9015b0891bfe" />

<br/><br/>

---

# 🛠 기술 스택 (아이콘 포함)

## ✔ Back-End  
![Java](https://img.shields.io/badge/Java-11-blue) 
![SpringBoot](https://img.shields.io/badge/SpringBoot-2.7-brightgreen)
![Security](https://img.shields.io/badge/Security-JWT-yellow)
![OAuth2](https://img.shields.io/badge/OAuth2-Google%20%7C%20Kakao%20%7C%20Naver-lightblue)
![MyBatis](https://img.shields.io/badge/MyBatis-Mapper-red)
![MySQL](https://img.shields.io/badge/MySQL-RDS-blue)
![Firebase](https://img.shields.io/badge/Firebase-Storage-orange)
![AWS](https://img.shields.io/badge/AWS-EC2%20S3%20RDS-orange)
![Docker](https://img.shields.io/badge/Docker-Blue%2FGreen-lightgrey)

## ✔ Front-End  
![React](https://img.shields.io/badge/React-18-blue)
![Query](https://img.shields.io/badge/React%20Query-Cache-red)
![Recoil](https://img.shields.io/badge/Recoil-State-lightblue)
![Emotion](https://img.shields.io/badge/Emotion-CSS-purple)
![PortOne](https://img.shields.io/badge/PortOne-Payment-yellow)
![Kakao](https://img.shields.io/badge/Kakao-Map-black)
![Firebase](https://img.shields.io/badge/Firebase-Upload-orange)
![Recharts](https://img.shields.io/badge/Recharts-Chart-pink)


<br/><br/>

---

# 📆 프로젝트 일정
<img width="1568" height="879" alt="Image" src="https://github.com/user-attachments/assets/64cb4939-b3a4-4d74-ab3c-29f4174e1821" />

<br/><br/>

---

# 📄 API 명세서 & ERD

### 🛰 API 명세서  
[Postman 문서 보기](https://documenter.getpostman.com/view/32355208/2sB3dMxWwB)

### 📐 ERD 설계도  
(유저 / 기부·펀딩 / 주문결제 / 스토어)

![User](https://github.com/user-attachments/assets/d38a86d3-c484-4ad5-b141-4e5b3dd63766)  
![DonationFunding](https://github.com/user-attachments/assets/28ebb952-3994-4514-bbc8-2b815c7bfc62)  
![OrderPayment](https://github.com/user-attachments/assets/50d41869-2bdd-411c-97b8-3131046b81b5)  
![Store](https://github.com/user-attachments/assets/c7096a6a-dcf2-4355-9801-c07c4a31f810)

<br/><br/>

---

# 📋 메뉴 구조도
<img width="2052" height="1066" alt="Image" src="https://github.com/user-attachments/assets/5db7ca5c-7db6-45d0-ad94-e8d2885ef5e0" />

<br/><br/>

---

# 🖥 화면 구현  
> 사용자 · 관리자 전체 기능을 **실제 동작 화면(GIF)** 으로 정리했습니다.  
> UI/UX 구조, 결제 흐름, 지도 기반 기능, 리뷰/신고, 배송·포인트 운영까지  
> **서비스 전반의 기능 흐름을 한눈에 파악할 수 있습니다.**

---

## 🎁 공감가게(Store) – 사용자 화면
<details>
<summary><b>클릭하여 펼치기</b></summary>
<br>

#### Q&A 작성  
![QnA](https://github.com/user-attachments/assets/d38e108e-1c33-4c04-bc63-f451de133814)

#### 결제 + 마이페이지 이동  
![결제](https://github.com/user-attachments/assets/5c679927-0619-42bf-88fc-f1b8f54306fc)

#### 댓글 작성 · 수정 · 삭제 + 리뷰 신고  
![댓글](https://github.com/user-attachments/assets/6aeae8ac-357e-4595-9c33-1fc3abaa0849)

#### 사진 확대 기능  
![사진확대](https://github.com/user-attachments/assets/91c1b14a-6105-4156-997e-179c47be9063)

#### 장바구니 + 마이페이지 이동  
![장바구니](https://github.com/user-attachments/assets/dc16bce1-9c15-4c44-b870-b0663bb0989f)

#### 찜하기  
![찜하기](https://github.com/user-attachments/assets/cbbf8946-787a-4c8c-89e7-27f375453e0a)

#### 리스트 페이지  
![리스트](https://github.com/user-attachments/assets/41d3fc12-9ef1-4a95-a387-f7269e2487df)

#### 상세페이지 전체  
![상세](https://github.com/user-attachments/assets/9b024f68-59f2-48ca-9ac6-ae7df43e6e6f)

</details>

---

## 🏪 공감가게(Store) – 관리자(Admin)
<details>
<summary><b>클릭하여 펼치기</b></summary>
<br>

![QnA관리](https://github.com/user-attachments/assets/0ec98d5b-f6fe-4467-af70-985e34abb6ba)
![결제관리](https://github.com/user-attachments/assets/12450823-4a98-4654-9520-e393d7d32519)
![리뷰신고](https://github.com/user-attachments/assets/9d7cdc09-09d0-48f9-acc3-d15959d700f5)
![리뷰관리](https://github.com/user-attachments/assets/9a41b423-59a2-41ca-9f54-16480933fab3)
![배송관리](https://github.com/user-attachments/assets/83a1dfa2-624d-460c-b2a7-fee293439cfa)
![주문관리](https://github.com/user-attachments/assets/c6e4c9b4-78eb-48c9-bc61-487bc7143ee9)
![포인트조회](https://github.com/user-attachments/assets/ca28c91e-c3a1-4fca-bf08-52faf7d9dc1a)
![프로젝트조회](https://github.com/user-attachments/assets/07fc11ad-c6db-4ec2-94ff-5417a9081683)
![프로젝트생성](https://github.com/user-attachments/assets/76c47682-f666-42f4-a5be-888eed25882c)
![프로젝트수정](https://github.com/user-attachments/assets/89b4361a-9257-4a9b-b6fd-9f62749c884e)

</details>

---

## 🎁 기부(Donation) – 사용자 화면
<details>
<summary><b>클릭하여 펼치기</b></summary>
<br>

![기부리스트](https://github.com/user-attachments/assets/df8d2370-bf73-43bb-bcfa-160eb36f5e46)
![기부상세](https://github.com/user-attachments/assets/456aa61f-965a-4cdf-87b3-6b8f4865b71f)
![기부결제](https://github.com/user-attachments/assets/587916b8-9c24-4559-8fce-8f0c2023b025)
![기부댓글](https://github.com/user-attachments/assets/a49d10bb-63b7-40b0-9cdc-75ea5f1f2404)
![지도](https://github.com/user-attachments/assets/f1e4e845-c133-4f02-b7fc-bcce5143c73b)
</details>

---

## 🎁 펀딩(Funding) – 사용자 화면
<details>
<summary><b>클릭하여 펼치기</b></summary>
<br>

![펀딩리스트](https://github.com/user-attachments/assets/60d2cd16-62af-44aa-91a8-5e4760b3c59d)
![펀딩결제](https://github.com/user-attachments/assets/bbd1cb1f-8dd2-4c7e-af2c-b174003a48b3)
![펀딩댓글](https://github.com/user-attachments/assets/d72463d9-4380-42a0-85d3-40924847771f)

</details>

---

## 🛒 마이페이지(MyPage)
<details>
<summary><b>클릭하여 펼치기</b></summary>
<br>

![기부내역](https://github.com/user-attachments/assets/2e3170cb-4e48-4714-8508-6d61f6ccfe3a)
![댓글내역](https://github.com/user-attachments/assets/2db235f1-0ad9-438d-94a1-59c69442d0ef)
![비번변경](https://github.com/user-attachments/assets/8922d808-05c5-4218-8409-1b795c890952)
![포인트](https://github.com/user-attachments/assets/f045948e-d09e-44c2-9ce9-aad4caa81c8b)
![펀딩내역](https://github.com/user-attachments/assets/2f255bb4-38a0-4989-bc49-27c8f009e3e2)
![프로필](https://github.com/user-attachments/assets/97d476f3-420c-4fb5-b64d-a4e1b9a73773)

</details>

---

## 🔐 인증(Authentication)
<details>
<summary><b>클릭하여 펼치기</b></summary>
<br>

### 로그인
![g](https://github.com/user-attachments/assets/6818afa9-f67d-485c-b117-1ed612d640da)
![n](https://github.com/user-attachments/assets/0dab2c6b-dbda-4b93-b699-4eb58e0ca182)
![k](https://github.com/user-attachments/assets/d37181dd-ee54-435f-bfc7-54f33984f33c)
![basic](https://github.com/user-attachments/assets/62c447e4-dbb5-48ef-b3c6-54e8e4577e1b)

### 회원가입
![j1](https://github.com/user-attachments/assets/afc6d8cf-e4c4-43d6-827a-caec464026bc)
![j2](https://github.com/user-attachments/assets/e14bb968-77fa-452b-982c-c035def21297)
![j3](https://github.com/user-attachments/assets/d25746ba-5330-476e-8b3c-391eea03cf84)
![j4](https://github.com/user-attachments/assets/ed507604-06ad-40ef-bc1a-5614bfed438d)

### 아이디/비밀번호 찾기
![find_id](https://github.com/user-attachments/assets/b4d426ed-f699-4193-8b69-980e3fd462ae)
![reset_pw](https://github.com/user-attachments/assets/608cb447-d23a-4534-85d9-bfc7e0f149ca)

</details>

---

## 🛠 관리자(Admin) – 기부·펀딩
<details>
<summary><b>클릭하여 펼치기</b></summary>
<br>

### 🔷 기부(Donation) 관리자 기능

#### 기부 프로젝트 목록 조회 + 삭제  
![기부목록](https://github.com/user-attachments/assets/8238b5df-2ac5-41f3-beff-1a0d96c4a6d6)

#### 기부 상세 – 댓글 및 후원 관리  
![기부상세관리](https://github.com/user-attachments/assets/295075d3-f448-41b2-8a02-6c2b10ac9f8a)

#### 기부 상세 생성/수정/삭제  
![기부상세CRUD](https://github.com/user-attachments/assets/0b88c3cc-0130-41f2-a2eb-2ae05b43c674)

#### 기부 프로젝트 생성  
![기부생성](https://github.com/user-attachments/assets/39be3e94-2ba0-4c85-b9ec-598b049366dc)

#### 기부 프로젝트 수정  
![기부수정](https://github.com/user-attachments/assets/ba6697ff-86bf-44e8-8f32-d188bff9635e)


---

### 🔶 펀딩(Funding) 관리자 기능

#### 펀딩 프로젝트 목록 조회 + 삭제  
![펀딩목록](https://github.com/user-attachments/assets/b7a17cb8-aca4-4c31-82d4-328318067016)

#### 펀딩 상세 – 댓글 및 후원 관리  
![펀딩댓글](https://github.com/user-attachments/assets/d09193d0-f740-494f-9934-eccfd4ae0d05)

#### 리워드 생성·수정·삭제  
![리워드관리](https://github.com/user-attachments/assets/81525c04-53bd-4efb-84d3-c63ca2259a1f)

#### 펀딩 상세 생성·수정·삭제  
![펀딩CRUD](https://github.com/user-attachments/assets/8ae8d639-e41e-4872-972b-e79b45211fcb)

#### 펀딩 프로젝트 생성  
![펀딩생성](https://github.com/user-attachments/assets/f38206e5-5ca6-4fd2-9d88-42cc6de64a9f)

#### 펀딩 프로젝트 수정  
![펀딩수정](https://github.com/user-attachments/assets/d44e4746-abc4-44cf-99ff-1bc2ab794ad2)

</details>

---

## 📊 관리자(Admin) – 기타
<details>
<summary><b>클릭하여 펼치기</b></summary>
<br>

#### Dashboard  
![Dashboard](https://github.com/user-attachments/assets/0c2249ec-9d2b-44a5-8521-206ee69ccda3)

#### 유저 등급별 조회 및 삭제  
![유저조회](https://github.com/user-attachments/assets/375ed5a4-7d67-4034-a05c-6cc22a0bba3c)

#### 관리자 접근제한  
![접근제한](https://github.com/user-attachments/assets/6bf47606-6561-4d77-97dd-b12adc779ae0)

</details>

---

# 💡 프로젝트 회고
<details>
<summary><b>클릭하여 펼치기</b></summary>
<br/>

이번 GIVE 프로젝트는 **단순 기능 구현을 넘어 실제 서비스 수준의 설계와 운영 경험을 쌓는 과정**이었습니다.  
특히 기부·펀딩·스토어라는 서로 다른 도메인을 하나의 플랫폼으로 통합하면서  
**도메인 모델링, API 구조 설계, 데이터 정합성 유지**의 중요성을 깊이 체감했습니다.

---

## 🔎 1) 시스템 설계의 중요성 체감  
초기에는 기능 중심으로 개발을 시작했지만,  
도메인이 확장될수록 **엔티티 관계, 테이블 설계, API 모듈화, 중복 로직 제거**의 필요성이 크게 느껴졌습니다.

- Donation / Funding / Store 세 도메인이 서로 다른 특징을 가지면서도  
  공통된 구조(후원, 결제, 배송, 댓글, 신고 등)를 공유한다는 점에서  
  “**확장 가능한 구조**”의 중요성을 실감했습니다.

이 경험 덕분에 **실제 SaaS 레벨 프로젝트 설계 감각**을 얻을 수 있었습니다.

---

## 🔐 2) 인증/인가 시스템 구축 경험  
Spring Security + JWT + OAuth2를 직접 구현하면서  
**보안, 세션 관리, 토큰 재발급, 접근 제어 등 실무 난이도의 문제**를 해결했습니다.

- 토큰 만료 시 재발급 로직  
- 관리자/Admin 접근 제어  
- 소셜 로그인 후 추가 정보 입력  

이 과정을 통해 인증 시스템의 난이도와 중요성을 제대로 이해하게 되었습니다.

---

## 🧱 3) 인프라 & DevOps 역량 성장  
특히 이번 프로젝트에서 가장 큰 성장은 **Blue/Green 무중단 배포 구축 경험**이었습니다.

- Docker 컨테이너 기반 Blue/Green 서버 구성  
- Nginx Reverse Proxy + Health Check  
- GitHub Actions 자동 배포 파이프라인 구축  

이 과정을 통해 “**배포가 단순히 코드를 올리는 것이 아니라 운영의 일부**”라는 점을 배웠고,  
장애 대응 경험(포트 충돌, Nginx 설정 문제, Docker 재시작 등)도 큰 자산이 되었습니다.

---

## 🎨 4) 사용자 경험(UX)을 고려한 UI 개발  
Front-End에서는 React Query, Recoil, Emotion 기반으로  

- 페이지 캐싱  
- 비동기 요청 최적화  
- 반응형 UI  
- 이미지 업로드 및 미리보기  
- 결제 UX 설계  
- 지도 기반 서비스  

등을 구현하며 실 사용자를 고려한 UI/UX의 중요성을 체험했습니다.

특히 **관리자 페이지의 복잡한 CRUD 구조를 명확하게 UI화**한 것이 좋은 경험이었습니다.

---

## 🚀 5) 실제 서비스처럼 운영해 본 경험  
기능이 많아질수록 정합성·성능·데이터 흐름이 중요해졌습니다.

- 주문/결제/배송 상태 자동 업데이트 Scheduler  
- 포인트 사용/적립 로직 검증  
- 리뷰 신고 프로세스  
- 기부/펀딩 후원 집계  
- 관리자 Dashboard 통계  

이 모든 과정을 직접 설계·개발·테스트하며  
“**프로젝트를 서비스처럼 운영하는 감각**”을 키웠습니다.

---

## 🔧 6) 기술적 문제 해결 능력 향상  
이 프로젝트에서는 정말 많은 에러와 충돌을 해결해야 했습니다.

- Nginx host not found  
- Docker 컨테이너 재시작 문제  
- CloudFront 캐싱 이슈  
- JWT 토큰 인증 오류  
- MyBatis 매핑 오류  
- 이미지 용량 문제(Firebase Storage)  

문제를 해결할 때마다 **로그 분석 → 재현 → 근본 원인 파악 → 해결 → 문서화**  
이 루틴을 자연스럽게 갖추게 되었습니다.

---

## 💭 7) 앞으로의 개선 방향  
이번 프로젝트를 통해 많은 성장을 했지만, 다음과 같은 개선 여지가 있습니다.

- 도메인 분리를 통한 **Microservice 구조** 적용 가능  
- 테스트 코드(JUnit, Mockito) 비중 확대  
- Redis 도입 → 캐싱/세션 성능 향상  
- 대규모 트래픽 대비 로드밸런싱 적용  
- CloudWatch 기반 장애 모니터링 고도화  

앞으로는 **확장성과 운영 효율성 중심 설계**에 더 집중하고자 합니다.

---

## 🎯 8) 총평  
GIVE 프로젝트는 지금까지 진행한 프로젝트들 중  
가장 많은 기술을 사용하고, 가장 넓은 범위를 경험한 프로젝트였습니다.

- 백엔드  
- 프론트엔드  
- 데이터베이스  
- 인프라  
- 배포 자동화  
- 실서비스 운영 수준의 구조  

이 모든 영역을 직접 만들어보며  
**“풀스택 개발자”로서의 자신감과 방향성을 확실히 잡은 프로젝트**였습니다.

앞으로도 확장성과 안정성을 고려한 설계를 바탕으로  
더 완성도 높은 서비스를 개발하고자 합니다.

</details>



