# 도메인 대분류

이 문서는 `/commit` 스킬이 커밋 스코프를 결정할 때 참조합니다.
**커밋 전마다 에이전트가 `src/` 패키지 구조를 스캔하여 이 목록을 최신 상태로 유지합니다.**

---

## 현재 등록된 도메인

| 스코프 | 패키지 경로 | 설명 |
|--------|------------|------|
| `root` | `com.sangyoon.kopring` | 애플리케이션 진입점 |
| `common` | `com.sangyoon.kopring.common` | 공통 응답, 예외, advice, config, 엔티티 기반 클래스 |
| `health` | `com.sangyoon.kopring.health` | health 체크 API (`controller/service/dto`) |
| `sample` | `com.sangyoon.kopring.sample` | 샘플 API 예제 (`controller/service/dto`) |

---

## 관리 규칙

- `src/main/kotlin/com/sangyoon/kopring/` 하위에 새 패키지가 생기면 이 목록에 추가한다.
- 패키지가 삭제되면 이 목록에서도 제거한다.
- 스코프 이름은 패키지명과 일치시키되, 너무 길면 축약한다.
- 도메인이 추가/삭제될 때는 변경 이유를 간략히 기록한다.

---

## 변경 이력

| 날짜 | 변경 내용 |
|------|-----------|
| 2026-05-06 | 초기 구조 생성 (root만 존재) |
| 2026-05-08 | health 체크 API 도메인 추가 |
| 2026-05-08 | common 응답/예외와 sample API 도메인 추가 |
| 2026-05-08 | 공모전 학습 목적에 맞춰 기능별 레이어드 구조로 단순화 |
| 2026-05-08 | common 엔티티 시간 추적 기반 클래스 추가 |
| 2026-05-08 | Swagger/OpenAPI 설정 추가 |
| 2026-05-08 | Java 연습 프로젝트와 동일한 status 기반 공통 응답/예외 구조로 정렬 |
