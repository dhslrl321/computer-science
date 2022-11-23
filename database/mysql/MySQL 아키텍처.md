# MySQL 아키텍처

- MySQL 클라이언트
- Connection Pool layer
- MySQL 엔진
  - parser
  - optimizer
    - 실행 계획
  - caches & buffers
- MySQL Storage Engine
  - 실제로 디스크에 저장
  - InnoDB

# 쿼리 실행 과정

- 사용자가 처음 쿼리를 실행시킨다
- 쿼리 캐시
  - 가장 처음에 만나는 컴포넌트
  - SQL 실행 결과를 메모리에 캐싱
  - MySQL 8.0 에서 완전히 제거됨
- 쿼리 파서
  - SQL 문장 오류 체크
  - 쿼리문을 의미 있는 문장의 토큰으로 파싱
  - 토큰들을 토대로 트리를 만듦
  - MySQL 은 이 parsed tree 를 이용해서 쿼리 실행
  - btree 로 만듦
- 전처리기
  - Parse Tree 를 기반으로 SQL 문장 구조를 체크
  - Parse Token 이 유효한지 체크
- 옵티마이저
  - SQL 실행을 최적화해서 실행 계획을 수립
  - 2가지 최적화 방법이 있다
    - 규칙 기반 최적화
      - 내장된 우선순위에 따라서 실행 계획 수립
    - 비용 기반 최적화
      - 작업의 비용과 대상 테이블의 통계 정보를 활용하여 실행 계획 수립
      - SQL 처리하는 여러 방법법을 마련해두고 각 방법의 비용과 테이블 통계 비용을 통해서
- 쿼리 실행 엔진
  - 옵티마이저가 생성해낸 쿼리를 실제로 실행시킴
  - 디스크에 저장하거나 디스크에서 저장된 데이터를 불러옴

# InnoDB 스토리지 엔진

- PK 에 의한 클러스터링
  - PK 를 기준으로, 순서대로 레코드를 정렬해서 디스크에 저장
  - 이렇게 하면 검색 속도가 빨라짐
  - 쓰기 성능이 저하됨
- 트랜잭션 지원
  - MVCC (Multi version concurrency control)
    - 트랜잭션 격리 수준에 따라서 결과가 어떻게 달라질지에 대해서 보여주는 기술
      - READ_UNCOMMITTED 이면 buffer pool 을 보고
      - READ_COMMITTED, REPEATABLE_READ, SERIALZABLE 이면 언두로그를 보게 함
  - innoDB 는 commit rollback 을 기본으로 제공
  - innoDB BufferPool
    - 변경된 데이터를 디스크에 반영하기 전까지 임시 저장
    - 변경되기 이전 데이터를 백업해둠
    - update 쿼리를 수행하면 버퍼풀에 변경된 데이터가 들어가고 변경하기 전 값이 언두 로그에 들어감
  - Undo Log 와 Redo Log
    - Undo Log
      - 변경되기 이전 데이터를 백업
      - 트랜잭션 보장
      - 트랜잭션 격리 수준 보장
    - Redo Log
      - 변경된 데이터를 백업
      - 영속성 보장
  - 레코드 단위 잠금
    - 레코드 단위로 잠금을 걸기에 동시성이 좋다
    - 레코드 자체를 잠그는 것이 아니라 인덱스를 잠근다
- InnoDB 버퍼풀 & 어댑티브 해시 인덱스
  - 데이터를 캐싱
  - 쓰기 작업 지연 버퍼
    - 변경된 데이터를 버퍼풀에 모아놨다가 주기적으로 이벤트를 발생시켜 한번에 변경사항을 디스크에 저장
