# Database Schema

현재 실제 도메인 Entity는 없다.

---

## 공통 엔티티 기반

`BaseTimeEntity`는 JPA Entity가 상속해서 생성/수정 시간을 자동 기록하기 위한 공통 기반 클래스다.

| Field | Type | Nullable | Description |
|-------|------|----------|-------------|
| `createdAt` | `LocalDateTime` | true | 생성 시간, JPA Auditing으로 기록 |
| `updatedAt` | `LocalDateTime` | true | 수정 시간, JPA Auditing으로 기록 |

---

## 로컬 DB

기본 실행 환경은 H2 인메모리 DB를 사용한다.

- JDBC URL: `jdbc:h2:mem:kopring`
- H2 Console: `/h2-console`
- PostgreSQL 전환 설정은 `src/main/resources/application.yaml`의 주석을 참고한다.
