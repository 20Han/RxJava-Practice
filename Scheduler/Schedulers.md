# 스케줄러

### 스케줄러
- 데이터 발행 스레드 : subscribeOn 함수로 지정, 기본 스레드는 subscribe를 호출한 스레드. subscribeOn은 첫 번째 불린 스레드만 유효하다.
- 데이터 소비 스레드 : observeOn 함수로 지정, 기본 스레드는 데이터 발행 스레드. observeOn은 호출시마다 실행 스레드가 바뀐다.

### 스케줄러 종류
- 뉴 스레드 스케줄러 : 새로운 스레드
- 계산 스케줄러(Computation Scheduler) :  빠른 결과 계산이 필요한 경우 사용. 입출력이 없는 경우에 사용한다고 보면 됨
- IO 스케줄러 (IO Scheduler) : 네트워크, 데이터 입출력 등 오래 걸리는 작업을 할당
- 트램펄린 스케줄러 : 현재 스레드에 대기 Queue를 할당하는 스케줄러. Observable에서 데이터가 발행될 때마다 queue에 넣는다.
- 싱글스레드 스케줄러 : 단일 스레드를 만들어서 해당 스레드에서 Observable의 데이터를 하나씩 발급
    의문점 : IntervalRange로 Obsevable로 만들면 RxComputationThreadPool에서 데이터 사용이 발생한다... → 데이터 발행 자체는 SingleThread에서 되지만 내부에서 ComputationThread에서 ObserveOn하도록 바꾼다
- Executor 이용 : 특정 executor를 스케줄러 스레드로 지정할 수 있다
