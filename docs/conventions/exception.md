# Exception Convention

공통 예외는 `BaseException`을 상속하는 HTTP 상태별 예외 클래스로 표현한다.

---

## 예외 계층

```text
BaseException
├── BadRequestException
├── UnAuthorizedException
├── NotFoundException
└── InternalServerException
```

---

## 처리 방식

- 커스텀 예외는 `ControllerExceptionAdvice`에서 공통 실패 응답으로 변환한다.
- 요청 파라미터 누락은 `MissingServletRequestParameterException`에서 처리한다.
- Bean Validation 실패는 `MethodArgumentNotValidException`에서 처리한다.
- 잘못된 인자는 `IllegalArgumentException`에서 처리한다.

---

## 사용 예시

```kotlin
throw BadRequestException("잘못된 요청입니다.")
```

응답:

```json
{
  "status": 400,
  "success": false,
  "message": "잘못된 요청입니다."
}
```
