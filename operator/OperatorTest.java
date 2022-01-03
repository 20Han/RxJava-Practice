package rxjava.operator;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class OperatorTest {
    public static void main(String[] args) {
        //map
        Observable<String> mapObservable = Observable.fromArray(new String[]{"1","2","3"});
        mapObservable.map(it -> it + " modified by map").subscribe(System.out::println);

        Observable<String> lengthObservable = Observable.fromArray(new String[]{"apple","banana","melon"});
        lengthObservable.map(it -> "length of '" + it + "' is " + it.length()).subscribe(System.out::println);


        //flatmap
        Observable<String> flatmapObservable = Observable.fromArray(new String[]{"1","2","3"});
        flatmapObservable.flatMap(it -> Observable.just(it + "first", it + "second", it + "third")).subscribe(System.out::println);


        //flatmap divide
        Observable<Integer> divideObservable = Observable.fromArray(new Integer[]{4,9,12});
        divideObservable.flatMap(it -> Observable.just("is " + it + " can be divided by 2? : " + (it%2==0) , "is " + it + " can be divided by 3? : " + (it%3 == 0) )).subscribe(System.out::println);

        //flatmap gugudan
        Observable<Integer> dan = Observable.just(3);
        Function<Integer, Observable<String>> gugudan = it -> Observable.range(1,9).map(it2 -> it + " * " + it2 + " = " + it * it2);
        Observable<String> gugudanSamDan = dan.flatMap(gugudan);
        gugudanSamDan.subscribe(System.out::println);

        //flatmap with bifunction
        Observable.just(4)
                .flatMap(it -> Observable.range(1,9), (it, it2) ->  it + " * " + it2 + " = " + it * it2)
                .subscribe(System.out::println);

        //filter
        Observable.just(1,2,3,4)
                .filter(it -> it % 2 == 0)
                .subscribe(System.out::println);

        //reduce : 합을 구함
        Observable.just(1,2,3,4,5,6,7,8,9,10)
                .reduce((p1, p2) -> p1 + p2)
                .subscribe(System.out::println);


    }
}
