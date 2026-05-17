# 공모전 백엔드 학습 로드맵

이 문서는 `부모님 동반 여행 맞춤형 코스 추천 앱` 공모전 백엔드를 완성하기 위한
코프링 학습 진행표다.

사용자가 "다음 단계 보여줘", "오늘 할 작업 줘", "다음 수업 시작하자"라고 요청하면
Codex는 이 문서를 기준으로 현재 진행 상태를 확인하고, 다음 미완료 단계를 코드와 함께 안내한다.

---

## 학습 운영 규칙

- 목표는 단순 문법 학습이 아니라 공모전에서 사용할 수 있는 백엔드 기능을 끝까지 만드는 것이다.
- 매 단계는 직접 코드를 따라 치며 진행한다.
- 한 번에 너무 많은 기능을 만들지 않는다. 하루 작업은 1~2개 API 또는 한 가지 기술 주제로 제한한다.
- 사용자가 "다음 단계"를 요청하면 아래 순서에서 가장 앞의 미완료 항목을 수업으로 제공한다.
- 수업 응답에는 반드시 다음 내용을 포함한다.
  - 오늘의 목표
  - 만들어야 할 파일
  - 따라 칠 코드
  - 실행 또는 테스트 방법
  - 완료 기준
  - 다음 단계 예고
- 사용자가 직접 구현한 뒤 검토를 요청하면, Codex는 코드 리뷰 후 필요한 수정만 제안한다.
- 단계가 끝나면 이 문서의 체크박스를 갱신한다.

---

## 최종 목표

공모전 MVP 백엔드 기능을 완성한다.

```text
부모님 정보 입력
→ 도시 직접 선택 또는 추천
→ 관광지 데이터 조회 및 저장
→ 부모님 체력/취향 기반 코스 추천
→ 일자별 상세 일정 생성
→ 일정 저장
→ 공유 링크 조회
→ 배포
```

---

## 목표 기술 스택

| 영역 | 기술 | 학습 목적 |
|---|---|---|
| Backend | Kotlin, Spring Boot | REST API 서버 구현 |
| Persistence | PostgreSQL, JPA | 도메인 데이터 저장, 연관관계, 트랜잭션 |
| Local Infra | Docker, Docker Compose | 로컬 DB와 서버 실행 환경 구성 |
| External API | TourAPI, Kakao Local API | 관광지/장소/좌표 데이터 연동 |
| AI | OpenAI 또는 LLM API | 일정 생성, 추천 이유 문장 생성 |
| Deploy | AWS EC2, RDS | 공모전 제출 가능한 서버 배포 |
| Ops | GitHub Actions, 환경변수 | 빌드/테스트/배포 자동화 |

---

## 프로젝트 도메인 설계

초기 MVP 도메인은 아래 순서로 만든다.

```text
parent
travel
city
place
recommendation
schedule
share
feedback
```

초기 패키지 구조는 프로젝트 컨벤션을 따른다.

```text
com.sangyoon.kopring
├── common
└── {domain}
    ├── controller
    ├── service
    ├── repository
    ├── entity
    └── dto
```

---

## 전체 진행표

### Phase 0. 준비

- [ ] 현재 프로젝트 구조 점검
- [ ] 학습용 브랜치 생성 여부 결정
- [ ] 공모전 백엔드 MVP 범위 확정
- [ ] 로컬 실행 방법 확인

완료 기준:

```text
./gradlew test
./gradlew bootRun
```

중 최소 하나 이상 정상 실행을 확인한다.

---

### Phase 1. 코프링 REST API 기본

목표: 부모님 프로필 CRUD를 만들면서 Controller, Service, DTO, Validation, 예외 처리를 익힌다.

- [x] Lesson 1. `parent` 도메인 생성
- [x] Lesson 2. `ParentProfileCreateRequest`, `ParentProfileResponse` DTO 작성
- [x] Lesson 3. `ParentProfileController` 기본 API 작성
- [x] Lesson 4. `ParentProfileService` 작성
- [x] Lesson 5. Validation 적용
- [x] Lesson 6. 예외 응답 확인
- [x] Lesson 7. Controller 테스트 작성

목표 API:

```http
POST   /api/v1/parent-profiles
GET    /api/v1/parent-profiles/{parentProfileId}
PATCH  /api/v1/parent-profiles/{parentProfileId}
DELETE /api/v1/parent-profiles/{parentProfileId}
```

완료 기준:

```text
부모님 프로필을 생성, 조회, 수정, 삭제할 수 있다.
잘못된 입력값은 400 응답으로 처리된다.
```

---

### Phase 2. PostgreSQL, Docker Compose, JPA

목표: 메모리 응답이 아니라 실제 PostgreSQL에 데이터를 저장한다.

- [x] Lesson 8. Docker Compose로 PostgreSQL 실행
- [ ] Lesson 9. Spring Boot와 PostgreSQL 연결
- [ ] Lesson 10. `ParentProfile` Entity 작성
- [ ] Lesson 11. `ParentProfileRepository` 작성
- [ ] Lesson 12. Service를 JPA 기반으로 변경
- [ ] Lesson 13. BaseTimeEntity 적용
- [ ] Lesson 14. Repository/Service 테스트 작성

완료 기준:

```text
서버를 재시작해도 부모님 프로필 데이터가 DB에 남아 있다.
```

---

### Phase 3. 여행 계획 도메인

목표: 부모님 프로필을 기반으로 여행 계획을 생성하고 도시 선택 전 상태를 저장한다.

- [ ] Lesson 15. `travel` 도메인 생성
- [ ] Lesson 16. `TravelPlan` Entity 작성
- [ ] Lesson 17. 부모님 프로필과 여행 계획 연관관계 설정
- [ ] Lesson 18. 여행 기간, 출발지, 선호 테마 저장
- [ ] Lesson 19. 여행 계획 생성/조회 API 작성
- [ ] Lesson 20. 여행 계획 상태값 작성

목표 API:

```http
POST /api/v1/travel-plans
GET  /api/v1/travel-plans/{travelPlanId}
```

완료 기준:

```text
부모님 프로필을 선택해서 여행 계획을 생성할 수 있다.
여행 계획은 도시 확정 전/후 상태를 가진다.
```

---

### Phase 4. 도시 선택과 도시 추천

목표: 사용자가 도시를 직접 선택하거나 서버가 후보 도시를 추천한다.

- [ ] Lesson 21. `city` 도메인 생성
- [ ] Lesson 22. 도시 기본 데이터 모델링
- [ ] Lesson 23. 도시 검색 API 작성
- [ ] Lesson 24. 여행 계획에 도시 확정 API 작성
- [ ] Lesson 25. 규칙 기반 도시 추천 로직 작성
- [ ] Lesson 26. 추천 이유 응답 작성

목표 API:

```http
GET  /api/v1/cities?keyword=경주
POST /api/v1/travel-plans/{travelPlanId}/city
POST /api/v1/recommendations/cities
```

완료 기준:

```text
도시를 직접 선택할 수 있다.
부모님 체력/취향 조건에 따라 도시 후보 3개 이상을 추천할 수 있다.
```

---

### Phase 5. TourAPI 연동

목표: 한국관광공사 관광 데이터를 가져오고, 서비스에서 사용할 수 있게 저장한다.

- [ ] Lesson 27. 외부 API Key 환경변수 설정
- [ ] Lesson 28. WebClient 기본 사용법 학습
- [ ] Lesson 29. TourAPI Client 작성
- [ ] Lesson 30. TourAPI 응답 DTO 작성
- [ ] Lesson 31. 관광지 목록 조회 API 작성
- [ ] Lesson 32. 관광지 상세 조회 API 작성
- [ ] Lesson 33. TourAPI 데이터를 `Place` Entity로 저장
- [ ] Lesson 34. 외부 API 실패 예외 처리
- [ ] Lesson 35. API 호출 로그 저장

목표 API:

```http
GET  /api/v1/tour/places?areaCode=...
GET  /api/v1/tour/places/{contentId}
POST /api/v1/tour/places/sync
```

완료 기준:

```text
TourAPI에서 관광지 데이터를 가져와 DB에 저장할 수 있다.
외부 API 실패 시 서버가 죽지 않고 명확한 에러를 반환한다.
```

---

### Phase 6. Kakao Local API 연동

목표: 좌표, 주소, 식당 검색 등 위치 기반 데이터를 보강한다.

- [ ] Lesson 36. Kakao Local API Key 설정
- [ ] Lesson 37. 주소로 좌표 변환 API 연동
- [ ] Lesson 38. 키워드 장소 검색 API 연동
- [ ] Lesson 39. 주변 식당 검색 API 연동
- [ ] Lesson 40. 특별 기념일 식당 추천 기초 구현

목표 API:

```http
GET /api/v1/local/search?query=경주 한정식
GET /api/v1/local/address-to-coordinate?address=...
GET /api/v1/restaurants/recommendations?cityId=...
```

완료 기준:

```text
관광지 주변 식당을 검색하고 추천 후보로 사용할 수 있다.
```

---

### Phase 7. 코스 추천

목표: 부모님 체력과 취향을 반영한 규칙 기반 추천 코스를 만든다.

- [ ] Lesson 41. `recommendation` 도메인 생성
- [ ] Lesson 42. 선호 테마 점수 계산
- [ ] Lesson 43. 걷기 부담 점수 계산
- [ ] Lesson 44. 휴식/식사 간격 점수 계산
- [ ] Lesson 45. 코스 후보 3개 생성
- [ ] Lesson 46. 추천 이유 생성
- [ ] Lesson 47. 추천 결과 저장

목표 API:

```http
POST /api/v1/recommendations/courses
GET  /api/v1/travel-plans/{travelPlanId}/course-recommendations
```

완료 기준:

```text
확정된 도시와 부모님 프로필을 기준으로 코스 3개 이상을 추천한다.
각 코스에는 예상 소요 시간, 이동 거리, 추천 이유가 포함된다.
```

---

### Phase 8. AI 일정 생성

목표: 선택한 코스를 바탕으로 일자별/시간대별 상세 일정을 생성한다.

- [ ] Lesson 48. AI API 환경변수 설정
- [ ] Lesson 49. AI 요청 Prompt 설계
- [ ] Lesson 50. AI 응답 JSON 구조 설계
- [ ] Lesson 51. 일정 생성 API 작성
- [ ] Lesson 52. AI 응답 검증 로직 작성
- [ ] Lesson 53. 생성된 일정 저장
- [ ] Lesson 54. 일정 수정 API 작성
- [ ] Lesson 55. 체력 제약 위반 경고 로직 작성

목표 API:

```http
POST  /api/v1/travel-plans/{travelPlanId}/schedules/generate
GET   /api/v1/travel-plans/{travelPlanId}/schedules
PATCH /api/v1/schedules/{scheduleId}/items/{itemId}
```

완료 기준:

```text
선택한 코스를 일정으로 변환하고 저장할 수 있다.
일정 수정 시 무리한 이동/짧은 휴식 조건을 경고할 수 있다.
```

---

### Phase 9. 공유 링크와 10계명

목표: 가족이 앱 설치 없이 웹 링크로 일정을 볼 수 있게 한다.

- [ ] Lesson 56. 공유 코드 생성
- [ ] Lesson 57. 공유 일정 조회 API 작성
- [ ] Lesson 58. 공유 권한과 만료 정책 설계
- [ ] Lesson 59. 10계명 서명 데이터 저장
- [ ] Lesson 60. QR 생성을 위한 응답 설계

목표 API:

```http
POST /api/v1/travel-plans/{travelPlanId}/share
GET  /api/v1/shared/{shareCode}
POST /api/v1/travel-plans/{travelPlanId}/ten-commandments/sign
```

완료 기준:

```text
공유 링크만 있으면 가족이 일정을 조회할 수 있다.
10계명 서명 상태를 저장할 수 있다.
```

---

### Phase 10. 피드백과 데이터 개선

목표: 사용자의 만족도와 오류 제보를 받아 추천 품질 개선 기반을 만든다.

- [ ] Lesson 61. 피드백 도메인 생성
- [ ] Lesson 62. 코스 만족도 저장
- [ ] Lesson 63. 관광지 데이터 오류 제보 저장
- [ ] Lesson 64. 관리자용 조회 API 기초 작성

목표 API:

```http
POST /api/v1/travel-plans/{travelPlanId}/feedback
POST /api/v1/places/{placeId}/reports
GET  /api/v1/admin/place-reports
```

완료 기준:

```text
추천 결과에 대한 만족도와 데이터 오류 제보를 저장할 수 있다.
```

---

### Phase 11. 테스트와 문서화

목표: 팀원이 믿고 사용할 수 있는 API 문서와 테스트를 갖춘다.

- [ ] Lesson 65. Swagger 문서 정리
- [ ] Lesson 66. API 문서 Markdown 작성
- [ ] Lesson 67. 핵심 Service 테스트 보강
- [ ] Lesson 68. Controller 테스트 보강
- [ ] Lesson 69. 외부 API Mock 테스트 작성

완료 기준:

```text
핵심 API는 Swagger와 문서에서 확인할 수 있다.
테스트로 주요 성공/실패 흐름을 검증한다.
```

---

### Phase 12. 배포

목표: 공모전 제출 가능한 공개 서버를 만든다.

- [ ] Lesson 70. 운영용 application 설정 분리
- [ ] Lesson 71. Spring Boot Dockerfile 작성
- [ ] Lesson 72. EC2에 Docker 설치
- [ ] Lesson 73. RDS PostgreSQL 생성
- [ ] Lesson 74. EC2에서 Spring Boot 컨테이너 실행
- [ ] Lesson 75. Nginx 리버스 프록시 설정
- [ ] Lesson 76. HTTPS 적용
- [ ] Lesson 77. GitHub Actions 배포 자동화
- [ ] Lesson 78. 운영 체크리스트 작성

완료 기준:

```text
Android 앱에서 공개 API 서버를 호출할 수 있다.
서버 재시작 후에도 DB 데이터가 유지된다.
```

---

## 매 수업 템플릿

Codex는 다음 단계 안내 시 이 형식을 사용한다.

```text
오늘의 목표:

만들 파일:

개념 설명:

따라 칠 코드:

실행 방법:

완료 기준:

숙제:

다음 단계:
```

---

## 진행 상태 기록

| 날짜 | Lesson | 작업 내용 | 상태 | 메모 |
|---|---:|---|---|---|
| 2026-05-11 | - | 공모전 백엔드 학습 로드맵 문서 생성 | 완료 | 다음 요청부터 Lesson 1 시작 |
| 2026-05-12 | 1-4 | parent 도메인, 부모님 프로필 DTO/Controller/Service 작성 | 완료 | DB 없이 메모리 기반 생성/조회 API 구현 |
| 2026-05-13 | 5 | 부모님 프로필 생성 요청 Validation 적용 | 완료 | 빈 문자열/빈 테마 목록을 400 응답으로 차단 |
| 2026-05-15 | 6 | 부모님 프로필 NotFound 예외 응답 상태 추가 | 완료 | Validation 400과 Service NotFound 404 흐름 구분 |
| 2026-05-16 | 7 | 부모님 프로필 컨트롤러 테스트 작성 | 완료 | 생성/조회 성공, Validation 실패, NotFound 실패 검증 |
| 2026-05-17 | 8 | Docker Compose로 PostgreSQL 실행 | 완료 | postgres:16 컨테이너 실행 및 psql 접속 확인 |

---

## 다음에 시작할 작업

다음 수업은 `Lesson 9. Spring Boot와 PostgreSQL 연결`이다.

목표는 Spring Boot datasource를 H2에서 Docker PostgreSQL로 전환하는 것이다.

```text
jdbc:postgresql://localhost:5432/kopring
```

다음 단계에서는 `application.yaml`의 datasource와 JPA 설정을 PostgreSQL 기준으로 바꾼다.
