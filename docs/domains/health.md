# Health Domain

Health 도메인은 서버 실행 상태를 확인하는 기본 기능이다.

---

## 책임

- 서버 상태를 `UP`으로 반환한다.
- 로드밸런서, 배포 확인, 로컬 실행 확인에 사용할 수 있는 최소 상태 API를 제공한다.

---

## 패키지 구조

```text
health
├── controller
├── dto
└── service
```

---

## 관련 API

- `GET /health`
