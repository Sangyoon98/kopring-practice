# AI 실수 재발 방지 기록

이 문서는 하네스 엔지니어링의 일부로, AI가 작업 중 저지른 실수와 재발 방지 규칙을 기록한다.
새 에이전트는 작업 전에 이 문서를 확인하고, 유사한 작업에서 같은 실수를 반복하지 않아야 한다.

---

## 기록 규칙

- 실수가 발견되면 원인, 영향, 재발 방지 규칙을 짧게 기록한다.
- 단순 회고가 아니라 다음 작업에서 바로 적용 가능한 체크리스트 형태로 남긴다.
- 같은 유형의 실수가 반복되면 기존 항목을 보강한다.

---

## 실수 목록

### 2026-05-09: Swagger 문서화 어노테이션 누락

**상황**

- 기존 Java Spring 프로젝트의 컨트롤러에는 `@Tag`, `@Operation`, `@ApiResponses`가 있었지만, Kotlin 프로젝트로 옮길 때 초기 구현에서 Swagger 설명 어노테이션을 누락했다.
- 사용자가 "스프링프로젝트에서 했던 것 처럼 스웨거 설명과 주석도 잘 달아줘야지"라고 지적한 뒤에야 보완했다.

**원인**

- API 동작 구현과 테스트 통과에만 집중했고, 기존 프로젝트의 문서화/주석 관례를 구현 완료 조건에 포함하지 않았다.
- Swagger 설정 파일은 추가했지만, 각 API 엔드포인트의 Swagger 설명까지 포함해야 한다는 요구를 암묵적 컨벤션으로 인식하지 못했다.

**영향**

- Swagger UI에서 API 목적, 파라미터, 응답 코드 설명이 부족해져 API 문서 품질이 떨어졌다.
- 사용자가 기대한 기존 Spring 프로젝트의 개발 스타일과 결과물이 달라졌다.

**재발 방지 규칙**

- 컨트롤러를 추가하거나 수정할 때는 반드시 Swagger 문서화 어노테이션을 함께 작성한다.
- 기본 체크리스트:
  - 컨트롤러 클래스에 `@Tag`
  - 각 엔드포인트에 `@Operation`
  - 성공/실패 응답에 `@ApiResponses`
  - 요청 파라미터와 path variable에 필요한 경우 `@Parameter`
  - 요청/응답 DTO 필드에 `@Schema`
- 사용자가 별도로 "Swagger 주석"을 말하지 않아도 API 작업에는 Swagger 문서화를 포함한다.
- Kotlin에서 프로젝트의 `ApiResponse` 클래스와 Swagger의 `ApiResponse` 어노테이션 이름이 겹치므로 Swagger 어노테이션은 alias import를 사용한다.

```kotlin
import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse
```

**검증 방법**

- `./gradlew test`로 컴파일을 확인한다.
- 애플리케이션 실행 후 `/swagger-ui.html`에서 태그, 요약, 설명, 응답 코드가 보이는지 확인한다.
