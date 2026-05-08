# Sample API

샘플 API는 공통 응답, 파라미터 처리, 커스텀 예외 처리 방식을 확인하기 위한 예제 API다.

---

## 엔드포인트

| Method | Path | 설명 |
|--------|------|------|
| `GET` | `/api/v1/sample/getSample` | 데이터 없이 성공 응답만 반환 |
| `GET` | `/api/v1/sample/getSampleData` | 요청 파라미터 값을 응답 데이터로 반환 |
| `GET` | `/api/v1/sample/exception/{isError}` | 예외 처리 예제 |

---

## GET /api/v1/sample/getSample

### Response

```json
{
  "status": 200,
  "success": true,
  "message": "샘플 조회 성공"
}
```

---

## GET /api/v1/sample/getSampleData

### Query Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| `data` | `String` | true | 응답으로 반환할 샘플 데이터 |

### Response

```json
{
  "status": 200,
  "success": true,
  "message": "샘플 조회 성공",
  "data": "hello"
}
```

### Error

`data` 파라미터가 없으면 `400 Bad Request`를 반환한다.

```json
{
  "status": 400,
  "success": false,
  "message": "요청 값이 입력되지 않았습니다."
}
```

---

## GET /api/v1/sample/exception/{isError}

### Path Variables

| Name | Type | Required | Description |
|------|------|----------|-------------|
| `isError` | `Boolean` | true | `true`이면 커스텀 예외 발생 |

### Response

`isError=false`

```json
{
  "status": 200,
  "success": true,
  "message": "샘플 조회 성공"
}
```

### Error

`isError=true`

```json
{
  "status": 400,
  "success": false,
  "message": "존재하지 않는 사용자 입니다."
}
```
