# CLAUDE.md

이 파일은 Claude Code(claude.ai/code)가 이 저장소에서 작업할 때 따르는 행동 지침과 프로젝트 가이드입니다.
추가 문서는 `docs/` 디렉터리와 `agents.md`를 참고하세요.

> **하네스 엔지니어링**: 이 프로젝트의 최우선 원칙이다. 에이전트는 작업 중 발견한 오류, 보완점, 패턴을 스스로 `docs/`에 기록하여 다음 에이전트가 더 잘 작동할 수 있도록 성장시킨다. 사용자가 요청하지 않아도 컨텍스트 유지를 위해 문서를 능동적으로 관리한다.

---

## 행동 원칙

### 1. 코딩 전에 먼저 생각하기

**가정하지 말 것. 혼란을 숨기지 말 것. 트레이드오프를 드러낼 것.**

구현 전에:
- 가정을 명시적으로 밝혀라. 불확실하면 질문하라.
- 해석이 여러 가지라면 제시하라 — 혼자 선택하지 말라.
- 더 단순한 접근이 있다면 말하라. 필요하면 반론하라.
- 불분명한 게 있으면 멈춰라. 무엇이 애매한지 짚고 질문하라.

### 2. 단순함 우선

**문제를 해결하는 최소한의 코드. 추측성 코드는 없다.**

- 요청된 것 이상의 기능은 추가하지 않는다.
- 단일 용도 코드에 추상화를 끼워 넣지 않는다.
- 요청하지 않은 "유연성"이나 "설정 가능성"을 만들지 않는다.
- 불가능한 시나리오에 대한 예외 처리를 추가하지 않는다.
- 200줄을 썼는데 50줄로 가능하다면, 다시 작성하라.

스스로 물어라: "시니어 엔지니어가 이걸 보고 과하다고 할까?" — 그렇다면 단순화하라.

### 3. 외과적 변경

**반드시 건드려야 하는 것만 건드린다. 내가 만든 쓰레기만 치운다.**

기존 코드를 수정할 때:
- 인접한 코드, 주석, 포맷팅을 "개선"하지 않는다.
- 망가지지 않은 코드를 리팩터링하지 않는다.
- 내가 다르게 하더라도 기존 스타일에 맞춘다.
- 관련 없는 죽은 코드를 발견하면 언급은 하되, 삭제하지 않는다.

내 변경이 고아를 만들었을 때:
- 내 변경으로 인해 불필요해진 import/변수/함수는 제거한다.
- 기존에 존재하던 죽은 코드는 요청받지 않는 한 건드리지 않는다.

기준: 변경된 모든 줄은 사용자의 요청으로 직접 추적 가능해야 한다.

### 4. 목표 주도 실행

**성공 기준을 정의하라. 검증될 때까지 반복하라.**

작업을 검증 가능한 목표로 변환하라:
- "검증 추가" → "잘못된 입력에 대한 테스트를 작성하고, 통과시킨다"
- "버그 수정" → "버그를 재현하는 테스트를 작성하고, 통과시킨다"
- "X 리팩터링" → "리팩터링 전후로 테스트가 통과함을 확인한다"

여러 단계 작업에서는 간략한 계획을 먼저 제시하라:

```
1. [단계] → 검증: [확인 방법]
2. [단계] → 검증: [확인 방법]
3. [단계] → 검증: [확인 방법]
```

강한 성공 기준이 있어야 독립적으로 반복할 수 있다. "작동하게 해줘" 같은 약한 기준은 계속 확인을 요구한다.

---

## 프로젝트 개요

`kopring`은 Kotlin + Spring Boot 4 기반의 실습 프로젝트로, JPA를 사용한다.

- **Group**: `com.sangyoon`
- **Java toolchain**: 21
- **Kotlin**: 2.2.21
- **Spring Boot**: 4.0.6

## 명령어

```bash
# 빌드
./gradlew build

# 애플리케이션 실행
./gradlew bootRun

# 전체 테스트 실행
./gradlew test

# 단일 테스트 클래스 실행
./gradlew test --tests "com.sangyoon.kopring.KopringApplicationTests"

# 단일 테스트 메서드 실행
./gradlew test --tests "com.sangyoon.kopring.KopringApplicationTests.contextLoads"
```

## 아키텍처

`src/main/kotlin/com/sangyoon/kopring/` 하위의 표준 Spring Boot 레이어드 아키텍처:

- 진입점: `KopringApplication.kt` — `@SpringBootApplication` + `main()`
- 설정: `src/main/resources/application.yaml`
- 런타임 DB: 기본 H2 인메모리 DB, PostgreSQL 전환 설정은 `application.yaml` 주석과 `docs/setup/local-run.md` 참고
- 테스트: `spring-boot-starter-data-jpa-test`, `spring-boot-starter-webmvc-test` (슬라이스 테스트 사용 가능)

## Kotlin/JPA 특이사항

- `allOpen` 플러그인이 `@Entity`, `@MappedSuperclass`, `@Embeddable`에 적용되어 있으므로 수동으로 `open`을 붙이지 않아도 된다.
- `-Xjsr305=strict`: JSR-305 어노테이션에 Kotlin null-safety를 강제한다.
- `-Xannotation-default-target=param-property`: 생성자 파라미터 어노테이션이 파라미터와 backing property 양쪽에 적용된다.

---

## 문서 구조

관련 작업 전 해당 문서를 먼저 확인하라. 새로운 규칙이나 패턴이 발견되면 즉시 해당 문서에 추가하라.

```
docs/
├── api/                # API 스펙 및 엔드포인트 문서
├── conventions/
│   ├── api-response.md # 공통 응답 컨벤션
│   ├── commit.md       # 커밋 메시지 컨벤션
│   ├── exception.md    # 예외 처리 컨벤션
│   ├── package.md      # 패키지 구조 컨벤션
│   └── swagger.md      # Swagger 문서화 컨벤션
├── database/
│   └── schema.md       # DB 스키마 및 공통 엔티티 문서
├── domains/            # 기능/도메인별 문서
├── harness/
│   ├── agent-team.md   # 팀형 서브에이전트 역할과 분배 기준
│   ├── documentation-sync.md # 문서 최신화 트리거
│   └── mistakes.md     # AI 실수 재발 방지 기록
├── domains.md          # 패키지 대분류 목록 (커밋 스코프용, 자동 관리)
├── architecture/       # 설계 결정 및 시스템 구조 (추가 예정)
├── setup/
│   └── local-run.md    # 로컬 실행 방법
└── adr/                # Architecture Decision Records (추가 예정)

.claude/commands/
└── commit.md           # /commit 스킬 정의

agents.md               # 에이전트 역할 및 협업 규칙
```

## 하네스 엔지니어링 실천 규칙

- **능동적 문서화**: 작업 중 새로운 패턴, 규칙, 특이사항을 발견하면 적절한 `docs/` 문서에 즉시 기록한다.
- **문서 최신화 트리거 준수**: 작업 시작 전, 파일 수정 후, 커밋 전 `docs/harness/documentation-sync.md`의 체크리스트를 따른다.
- **팀형 에이전트 운영**: 복합 작업은 `docs/harness/agent-team.md` 기준으로 역할을 나누되, 단순 작업은 직접 처리한다.
- **도메인 동기화**: 패키지 구조가 변경될 때마다 `docs/domains.md`를 업데이트한다.
- **컨벤션 준수**: 커밋 전 반드시 `docs/conventions/commit.md`를 참조한다.
- **오류 기록**: 반복되는 실수나 엣지 케이스를 발견하면 관련 문서에 주의사항으로 추가한다.
- **실수 재발 방지**: 작업 전 `docs/harness/mistakes.md`를 확인하고, 새 실수가 발견되면 즉시 기록한다.
- **API 문서화 기본 포함**: 컨트롤러/API 작업에는 사용자가 따로 말하지 않아도 Swagger `@Tag`, `@Operation`, `@ApiResponses`, 필요한 `@Parameter`/`@Schema`를 함께 작성한다.
- **스킬 활용**: `/commit` 등 `.claude/commands/`에 정의된 스킬을 우선 사용한다.
