# 리액티브 연산자 입문

리액티브 연산자는 순수함수 → 동일한 인자에 동일한 리턴값을 가짐
400여개가 존재.

- map
Observable의 데이터를 특정 함수를 통해 다른 데이터로 가공한다. 데이터 타입이 바뀌어도 상관없다. 하나의 데이터는 하나의 데이터로 맵핑된다. Fuction을 인자로 받는다

- flatmap
Observable의 데이터를 다른 Observable로 가공한다. 하나의 데이터는 하나의 Observable로 맵핑된다. 이때 subscribe시 Observable을 구독하는 것이 아니라 Observable들의 데이터 흐름을 구독하게 된다. Function을 인자로 받는다

- filter
Observable에서 원하는 데이터만 걸러내는 연산자. Predicate를 인자로 받는다

- reduce
발행한 데이터를 모두 사용하여 최종 결과 데이터를 합성할 때 사용. BiFunction 인터페이스를 인자로 받는다