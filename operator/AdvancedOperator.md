# 리액티브 연산자의 활용

## 연산자의 종류
- 생성연산자 : just, from~~, create, interval, range
- 변환연산자 : map, flatmap
- 필터연산자 : filter, take, skip
- 결합연산자 : zip, combineLastest, merge, concat
- 조건연산자 : amb, takeUntil, skipUntil, all
- 기타 연산자 : subscribe, reduce...

## 생성연산자 : 데이터 흐름을 만드는 연산자
- interval() : 일정 시간 간격으로 0부터 1씩 증가하는 데이터 생성, 영원히 실행된다
- timer() : 일정 시간이 지난 후 한 개의 데이터를 발행하고 onComplete이벤트를 발생
- range() : 주어진 값부터 count 개수의 integer 발행
- interavalRange : 특정 시간 간격으로 특정 range의 데이터를 발행
- defer : 특정 Observable의 생성을 Subscribe전 까지 미룬다. Callable<ObservableSource>를 인자로 받는다.
- repeat : 특정 observable의 발행을 n번 반복한다. 주기적인 서버 통신 등에 쓰인다

## 변환 연산자
- concatMap : flatmap과 같지만 인터리빙(데이터의 결과 처리 순서를 보장)을 허용하지 않는다.
- switchMap : Observable의 데이터 발행에서 인터리빙을 방지하기위해 기존의 진행중이던 작업을 중지하는 Map함수
- groupBy : Observable을 기준에 따라 여러 Observable의 그룹으로 만든다
- scan : reduce와 비슷하지만 중간결과 및 최종 결과를 구독자에게 발행한다