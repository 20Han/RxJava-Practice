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

## 결합연산자
- zip : 입력된 Observable에서 데이터를 모두 새로 발행했을 때 둘을 합친다.
- combineLatest : 입력된 Observable 중 하나의 데이터가 새로 발행했을 때 마지막 데이터들을 바탕으로 하나의 결과를 산출한다.
- merge : 여러 Observable에서 데이터가 들어올 때 어느 Observable에서 발행되었는지 신경쓰지 않고 들어온 데이터를 그대로 발행
- concat : 두 Observable을 연속적으로 발행하는 함수. 한 Observable의 onComplet이 끝나야 다음 Observable이 불린다

## 조건연산자
- amb : 가장 먼저 데이터를 발행하는 한 개 의 Observable만을 인식하는 연산자. 나머지 데이터는 무시한다.
- takeUntil : 특정 값을 발행하면 해당 Observable의 데이터 발행을 중단하고 onComplete 이벤트 발생. predicator혹은 특정 Observable의 데이터를 condition으로 줄 수 있다
- skipUntil : 특정 값을 발행할때까지 Observable의 데이터 발행을 Observer에게 전달하지 않는다. Observable의 데이터를 condition으로 줄 수 있다
- all : 발행된 값들의 all 안의 predicator에 일치하면 true를, 아니면 false를 반환한다.