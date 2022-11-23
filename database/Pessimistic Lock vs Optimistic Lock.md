# Pessimistic Lock

- system 은 비관적이게 항상 충돌이 발생할거라고 생각하기 떄문에 row 자체에 lock 을 걸어버림
- 만약 하나의 커넥션이 lock 을 오래 물고 있으면 이후 커넥션은 모두 터짐
- innoDB 의 lock_wait_timeout 은 50 초

# Optimistic Lock

- system 은 낙관적이게 충돌이 잘 발생하지 않을거라고 생각하기에 row 락을 걸지 않고 version 으로 컨트롤함
- update query 에 where 조건 추가
