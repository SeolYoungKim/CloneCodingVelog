# CloneCodingVelog

노션 : https://robust-price-530.notion.site/7927298209a14c7c819bb1f545ae8fd1

---
## 목표
1. ThymeLeaf를 이용한 Spring MVC로, Velog 기능 클론 코딩.
2. 최대한 스스로 구현하기. (남의 블로그 코드 보지 않기)
---
## 기획
### 기술 선정
- Java Spring boot framework
  - 선정 이유
    - 내가 공부해 둔 게 이거라서
    - 스스로 활용해보고 싶어서
    - 기능을 깊게 이해할 때 까지는 해당 프레임워크와 해당 언어를 깊게 파고들 생각임
- My SQL
  - 선정 이유
    - 오픈 소스 
    - Maria DB와 MySQL 중 고민했지만, My SQL에서 파생되어 나온 것이 Maria DB라고 하여, 원조를 써봐야겠다고 결심.
    - 관계형 데이터베이스 -> SQL 습득에 도움이 됨
---
## 항상 마음에 새길 것
- GitHub 공개 리포지토리에 수정이 조금이라도 있을 경우, 커밋할 것. (매일 있겠지!)
- 당연히 처음엔 Velog보다 못한 스레기가 나올 것이니, 야생형 학습에 의의를 둘 것.
- 스레기를 만들어도, 나중에는 예쁘게 바꾸어줄것.
- JSON을 이용한 REST API와 ThymeLeaf를 이용한 구현의 차이를 학습할 것.
- 절대 남의 코드를 보지 말 것.
- 검색 영어로 할 것. 영어 자료를 무서워하지 말자. 논문보다 덜 무섭다.
---
## 구현 완료 기능
- 회원가입
- 로그인 -> 세션을 통한 접근 가능 페이지 설정
- CRUD
  - 글 쓰기
  - 글 조회
  - 글 삭제
  - 글 수정
- 시리즈(목록) 추가 (다대일 양방향 매핑 사용)
- 글 목록 조회, 시리즈 목록 조회 기능

---
## 구현해야 하는 것 -> 이는 ThymeLeaf보단, REST API에서 전부 구현해볼 예정임
1. 개인 홈
    1. 글 목록
        1. 최근 것이 상위에 노출되도록 해보자.
        2. 타임라인 처럼, 동적 조회를 구현해보자. (스스로 공부)
    2. 시리즈 페이지
        1. 글을 시리즈 단위로 관리하기 위한 방법은 ? → 시리즈 관리 필드를 이용해야 한다.
        2. 시리즈는 고객이 필드를 마음대로 작성하여 넘겨줄 수 있고, 그게 하나의 목록으로써 관리되어야 한다. 
    3. 소개 페이지
    4. 새글 작성 버튼
    5. 검색 버튼 (검색 창으로 넘어간다)
    6. 내 프로필을 눌렀을 때
        1. 내 벨로그
        2. 임시 글
        3. 읽기 목록
        4. 설정
        5. 로그아웃 → 로그아웃 상태에서는 개인 페이지를 들어가는 버튼이 없다. 즉, 로그인 상태에서만 개인 홈에 가는 버튼이 생겨야 함
    7. git hub 접근 버튼
    8. email 접근 버튼
2. 새 글 작성 페이지
    1. 제목 입력란
    2. 태그 입력란
    3. H1, H2, H3, H4 … 등 글쓰기에 도움되는 기능 구현란
    4. 글쓰기란
    5. 나가기 버튼
    6. 임시저장 버튼
    7. 출간하기 버튼
3. 작성한 글 조회
    1. 통계 버튼
    2. ~~수정 버튼~~
    3. ~~삭제 버튼~~
    4. 하트
    5. 공유
4. 작성한 글 수정
    1. 작성 페이지와 다른 부분 : 버튼이 수정하기임
5. velog home (Trending)
    1. 조회수 기반으로 리포지토리의 글들이 올라오게 해야 함. 
6. 회원가입, 로그인 및 로그아웃 기능 구현
    1. 쿠키, 세션 
    2. 인증
    3. 회원가입 (이메일 or 소셜 계정으로 가입)
7. 임시 글
    1. 글 목록이 조회되고, 삭제 버튼 있음.
8. 읽기 목록
    1. 내가 좋아요 한 글들이 모여있음.
9. 설정
    1. 내 개인 정보를 설정할 수 있음.
