# City Domain

도시 도메인입니다.

## 책임

- 사용자가 여행 도시를 검색할 수 있게 한다.
- 부모님 조건에 맞는 도시 추천 후보를 제공한다.
- 여행 계획에 확정 도시를 연결하기 위한 기준 데이터가 된다.

## 현재 상태

- `city` 도메인 패키지 생성 완료
- `CityController`, `CityService` 뼈대 생성 완료
- `City` Entity 작성 완료
- `CityRepository`를 JPA Repository로 구현 완료

## Entity

### City

도시 기본 데이터를 저장할 Entity입니다.

| 필드 | 설명 |
|---|---|
| `id` | 도시 ID |
| `name` | 도시 이름 |
| `region` | 권역 또는 시도 |
| `description` | 도시 설명 |
| `createdAt` | 생성일 |
| `updatedAt` | 수정일 |

## Repository

### CityRepository

`JpaRepository<City, Long>`을 상속합니다.

- 도시 저장
- 도시 ID 기반 조회

## 예정 API

```http
GET /api/v1/cities?keyword=경주
```

## 이후 확장

- 도시 검색 API 구현
- 여행 계획에 확정 도시 연결
- 부모님 조건 기반 도시 추천
