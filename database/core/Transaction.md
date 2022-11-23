# Transaction

- 데이터베이스의 무결성을 보장한다
- 데이터베이스 최소 작업 단위
- tx 의 특징은 ACID 원칙
  - Atomic : tx 는 commit 되거나 rollback 되어야 한다
  - Consistency : tx 결과가 항상 일관되어야 한ㄷ
  - Isolation : 다른 tx 영향을 받으면 안된다
  - Durability : commit 되었을 때는 persist 되어야 한다
