# Travel Domain

여행 계획 도메인입니다.

## 책임

- 부모님 프로필을 기반으로 여행 계획을 생성한다.
- 여행 기간, 출발지, 여행 조건을 저장한다.
- 도시 확정 전/후 상태를 관리한다.
- 이후 코스 추천, 일정 생성, 공유 기능의 기준 데이터가 된다.

## 현재 상태

- `TravelPlan` Entity 작성 완료
- 부모님 프로필과 여행 계획의 단방향 연관관계를 설정했다.
- 여행 계획 제목, 여행 시작일, 여행 종료일, 출발지, 이번 여행의 선호 테마를 저장한다.
- `TravelPlanRepository`를 JPA Repository로 구현했다.
- 여행 계획 생성/조회 API를 구현했다.
- 여행 계획 상태값을 저장하고 응답에 포함한다.

## Entity

### TravelPlan

| 필드 | 설명 |
|------|------|
| `id` | 여행 계획 ID |
| `parentProfile` | 여행 계획의 기준이 되는 부모님 프로필 |
| `title` | 여행 계획 제목 |
| `startDate` | 여행 시작일 |
| `endDate` | 여행 종료일 |
| `departurePlace` | 출발지 |
| `status` | 여행 계획 상태 |
| `preferredThemes` | 이번 여행에서 사용할 선호 테마 목록 |
| `createdAt` | 생성일 |
| `updatedAt` | 수정일 |

## 상태값

### TravelPlanStatus

| 값 | 설명 |
|---|---|
| `READY_TO_SELECT_CITY` | 여행 계획은 생성했지만 아직 도시를 확정하지 않은 상태 |
| `CITY_CONFIRMED` | 여행 도시를 확정한 상태 |

## 연관관계

```text
ParentProfile 1개 → TravelPlan 여러 개
```

- `TravelPlan`에서 `ParentProfile`을 `@ManyToOne(fetch = FetchType.LAZY)`로 참조한다.
- `travel_plans.parent_profile_id` 컬럼으로 부모님 프로필을 연결한다.
- 현재는 `TravelPlan → ParentProfile` 단방향 관계만 사용한다.

## 부가 테이블

### travel_plan_preferred_themes

여행 계획별 선호 테마를 저장한다.

| 필드 | 설명 |
|------|------|
| `travel_plan_id` | 여행 계획 ID |
| `theme` | 선호 테마 |

## Repository

### TravelPlanRepository

`JpaRepository<TravelPlan, Long>`을 상속한다.

- 여행 계획 저장
- 여행 계획 ID 기반 조회

## API

```http
POST /api/v1/travel-plans
GET  /api/v1/travel-plans/{travelPlanId}
```

- 생성 API는 부모님 프로필 ID로 `ParentProfile`을 조회한 뒤 여행 계획을 저장한다.
- 조회 API는 여행 계획 ID로 `TravelPlan`을 조회한다.
