# Travel Plan API

여행 계획 API 문서입니다.

## 예정 API

```http
POST /api/v1/travel-plans
GET  /api/v1/travel-plans/{travelPlanId}
```

## 현재 상태

- 아직 API 메서드는 구현하지 않았습니다.
- `TravelPlan` Entity는 작성되었습니다.
- 부모님 프로필과 여행 계획의 연관관계가 설정되었습니다.
- 여행 계획 생성 요청/응답 DTO가 작성되었습니다.
- 다음 단계에서 여행 계획 생성/조회 API를 구현합니다.

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
