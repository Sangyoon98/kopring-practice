# Travel Plan API

여행 계획 API 문서입니다.

## 구현 API

```http
POST /api/v1/travel-plans
GET  /api/v1/travel-plans/{travelPlanId}
```

## 현재 상태

- `TravelPlan` Entity는 작성되었습니다.
- 부모님 프로필과 여행 계획의 연관관계가 설정되었습니다.
- 여행 계획 생성 요청/응답 DTO가 작성되었습니다.
- 여행 계획 생성/조회 API가 구현되었습니다.
- 다음 단계에서 여행 계획 상태값을 추가합니다.

## API 상세

### 여행 계획 생성

```http
POST /api/v1/travel-plans
```

성공 응답:

- HTTP Status: `201 Created`
- Message: `여행 계획 생성 성공`

실패 응답:

- `400 Bad Request`: 필수 입력값 누락 또는 빈 값
- `404 Not Found`: 부모님 프로필을 찾을 수 없음

### 여행 계획 조회

```http
GET /api/v1/travel-plans/{travelPlanId}
```

성공 응답:

- HTTP Status: `200 OK`
- Message: `여행 계획 조회 성공`

실패 응답:

- `404 Not Found`: 여행 계획을 찾을 수 없음

## 요청 DTO

### TravelPlanCreateRequest

| 필드 | 설명 |
|------|------|
| `parentProfileId` | 부모님 프로필 ID |
| `title` | 여행 계획 제목 |
| `startDate` | 여행 시작일 |
| `endDate` | 여행 종료일 |
| `departurePlace` | 출발지 |
| `preferredThemes` | 이번 여행의 선호 테마 |

## 응답 DTO

### TravelPlanResponse

| 필드 | 설명 |
|------|------|
| `id` | 여행 계획 ID |
| `parentProfileId` | 부모님 프로필 ID |
| `title` | 여행 계획 제목 |
| `startDate` | 여행 시작일 |
| `endDate` | 여행 종료일 |
| `departurePlace` | 출발지 |
| `preferredThemes` | 이번 여행의 선호 테마 |
