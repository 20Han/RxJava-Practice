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