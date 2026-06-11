# City API

도시 API 문서입니다.

## 예정 API

```http
GET /api/v1/cities?keyword=경주
```

## 현재 상태

- 아직 API 메서드는 구현하지 않았습니다.
- `city` 도메인 기본 패키지를 생성했습니다.
- `City` Entity와 JPA Repository를 작성했습니다.
- 다음 단계에서 도시 검색 API를 구현합니다.

## 예정 응답 DTO

### CityResponse

| 필드 | 설명 |
|---|---|
| `id` | 도시 ID |
| `name` | 도시 이름 |
| `region` | 권역 또는 시도 |
| `description` | 도시 설명 |

## 예정 실패 응답

- `400 Bad Request`: 검색 조건이 올바르지 않음
- `404 Not Found`: 도시를 찾을 수 없음
