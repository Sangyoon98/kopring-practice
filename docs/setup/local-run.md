# Local Run

로컬 실행 방법과 기본 개발 환경 설정을 정리한다.

---

## 실행

```bash
./gradlew bootRun
```

기본 포트는 `8080`이다.

---

## 주요 URL

| Name | URL |
|------|-----|
| Swagger UI | `http://localhost:8080/swagger-ui.html` |
| OpenAPI JSON | `http://localhost:8080/v3/api-docs` |
| H2 Console | `http://localhost:8080/h2-console` |
| Health Check | `http://localhost:8080/health` |

---

## H2 설정

기본 실행 환경은 H2 인메모리 DB를 사용한다.

| Field | Value |
|-------|-------|
| JDBC URL | `jdbc:h2:mem:kopring` |
| Username | `sa` |
| Password | empty |

---

## PostgreSQL 전환

PostgreSQL을 사용할 때는 `src/main/resources/application.yaml`의 PostgreSQL 주석 블록을 참고해 datasource 설정을 교체한다.
