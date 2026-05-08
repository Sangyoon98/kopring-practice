# API Response Convention

이 프로젝트의 일반 API 응답은 `ApiResponse<T>`로 감싼다.

---

## 성공 응답

데이터가 없는 경우:

```json
{
  "status": 200,
  "success": true,
  "message": "샘플 조회 성공"
}
```

데이터가 있는 경우:

```json
{
  "status": 200,
  "success": true,
  "message": "샘플 조회 성공",
  "data": {}
}
```

---

## 실패 응답

```json
{
  "status": 400,
  "success": false,
  "message": "요청 값이 입력되지 않았습니다."
}
```

---

## 규칙

- `status`는 HTTP 상태 코드 숫자다.
- `success`는 성공 여부다.
- `message`는 사용자 또는 API 소비자가 이해할 수 있는 응답 메시지다.
- `data`는 응답 데이터가 있을 때만 포함한다.
- Health check처럼 단순 상태 확인 API는 예외적으로 전용 응답 DTO를 사용할 수 있다.
