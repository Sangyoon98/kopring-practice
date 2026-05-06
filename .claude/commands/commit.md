# /commit

스테이징되지 않은 변경사항을 포함해 현재 작업을 커밋합니다.
커밋 메시지는 `docs/conventions/commit.md` 컨벤션을 따릅니다.

## 실행 순서

**1. 변경사항 파악**
- `git status`로 변경된 파일 목록 확인
- `git diff`로 실제 변경 내용 확인

**2. 도메인 목록 최신화**
- `src/main/kotlin/com/sangyoon/kopring/` 하위 패키지 구조 스캔
- `docs/domains.md`의 도메인 목록과 비교
- 추가/삭제된 패키지가 있으면 `docs/domains.md` 업데이트 후 함께 스테이징

**3. 커밋 타입 결정**
`docs/conventions/commit.md`의 타입 목록을 참고하여 결정:
- 새 기능 → `feat`
- 버그 수정 → `fix`
- 리팩터링 → `refactor`
- 설정/환경 → `setting`
- 문서 → `docs`
- 빌드/의존성 → `chore`
- 테스트 → `test`

**4. 스코프 결정**
- 변경 파일들이 `docs/domains.md`의 단일 도메인에 속하면 스코프 추가
- 여러 도메인에 걸치면 스코프 없이 작성

**5. 커밋 메시지 작성 및 커밋**
- 형식: `<type>(<scope>): <한국어 작업 내용>` 또는 `<type>: <한국어 작업 내용>`
- `git add`로 관련 파일 스테이징
- `git commit`으로 커밋 실행

## 주의사항

- `.env`, 시크릿, 인증 정보가 포함된 파일은 스테이징하지 않는다.
- 커밋 메시지는 반드시 한국어로 작성한다.
- `docs/domains.md`가 변경된 경우 항상 같은 커밋에 포함한다.
