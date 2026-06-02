# Travel Domain

여행 계획 도메인입니다.

## 책임

- 부모님 프로필을 기반으로 여행 계획을 생성한다.
- 여행 기간, 출발지, 여행 조건을 저장한다.
- 도시 확정 전/후 상태를 관리한다.
- 이후 코스 추천, 일정 생성, 공유 기능의 기준 데이터가 된다.

## 현재 상태

- `TravelPlan` Entity 작성 완료
- 여행 계획 제목, 여행 시작일, 여행 종료일, 출발지를 저장한다.
- 부모님 프로필 연관관계, Repository, API는 이후 Lesson에서 구현

## Entity

### TravelPlan

| 필드 | 설명 |
|------|------|
| `id` | 여행 계획 ID |
| `title` | 여행 계획 제목 |
| `startDate` | 여행 시작일 |
| `endDate` | 여행 종료일 |
| `departurePlace` | 출발지 |
| `createdAt` | 생성일 |
| `updatedAt` | 수정일 |
