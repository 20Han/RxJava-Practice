# Observable

- 데이터의 변화가 발생하는 데이터 소스

- 기본 팩토리 함수 : create, just, from
추가 팩토리 함수(from 세분화)  : fromArray, fromIterable, fromCallable, fromFuture, fromPublisher
기타 팩토리 함수 : interval, range, timer, defer

- subscribe() 함수
Observable은 subscribe함수를 호출해야 데이터를 발행한다. 
Disposable 객체를 리턴하는데, 요 클래스는 dispose()와 isDispose()함수를 가진다. 즉 구독 해지를 하기 위한 객체. Observable Contract에 따르면 Observable().onComplete()이 호출되면 dispose함수가 저절로 불린다.

- Single Class
오직 한 개의 데이터만 발행하는 클래스
서버 API등 결과가 하나인 API를 호출할 때 유용
만약 여러개의 데이터가 발행되면 onNext과정에서 error가 발생한다

- Maybe Class
최대 데이터를 하나 가질 수 있지만 데이터를 발행하지 않고도 종료할 수 있는 클래스

- Observable의 종류
차가운 Observable : subscribe함수를 호출하여 구독하지 않으면 데이터를 발행x, 웹 요청, 데이터베이스 쿼리, 파일 읽기 등에 적합
뜨거운 Observable : 구독자와 관계없이 데이터를 발행, 마우스 이벤트 ,키보드 이벤트 등등 실시간으로 들어오는 데이터에 적합

- Subject
Observable과 Observer 모두로써 동작할 수 있는 클래스. cold Observable을 Hot Observable로 만들 수 있다
1. AsyncSubject 클래스
 onComplete이 발생하면 직전의 onNext로 지정한 데이터를 발행하는 클래스. 직전의 onNext를 제외한 과거의 onNext들은 무시된다. 또한 onComplete이후의 onNext로 지정한 데이터도 무시된다. 
 Observable의 Subscribe에 Observer로써도 지정할 수 있다. 이는 Subject가 Observable과 Observer모두 가지고 있기 때문에 가능하다

2. BehaviourSubject 클래스
 가장 최근에 발행된 값, 혹은 기본 값을 Observer에게 전달하는 클래스.
 onNext마다 Oberver에게 값을 전달한다. 

3. PublishSubject 클래스
 오직 해당 시간에 발행하는 데이터만을 Observer에게 전달하는 클래스

4. ReplySubject 클래스
   항상 데이터의 처음부터 끝까지 발행하는 것을 보장하는 subject. subject의 목적은 Hot Observable로써 동작하도록 하는 것인데 Cold Observable처럼 동작하는 셈이다. 발행하는 모든 데이터를 저장하므로 메모리 누수에 조심해야 한다.
    
- ConnectableObservable
subscribe시점에 observable이 데이터 발행을 시작하지 않고 connect()를 호출해야 데이터 발행을 시작
Observable 인스턴스의 publish함수를 호출하여 만들 수 있다