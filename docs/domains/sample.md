# Sample Domain

샘플 도메인은 API, 공통 응답, 예외 처리, Swagger 문서화 방식을 확인하기 위한 예제 기능이다.

---

## 책임

- 데이터 없는 성공 응답 예제 제공
- 요청 파라미터를 응답 데이터로 반환하는 예제 제공
- 커스텀 예외 처리 예제 제공

---

## 패키지 구조

```text
sample
├── controller
├── dto
└── service
```

---

## 관련 API

- `GET /api/v1/sample/getSample`
- `GET /api/v1/sample/getSampleData`
- `GET /api/v1/sample/exception/{isError}`
