package rxjava.operator;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class MapOperatorTest {
    public static void main(String[] args) throws InterruptedException {
        //concatMap
        Observable.intervalRange(1,3, 10, 10, TimeUnit.MILLISECONDS)
                .concatMap(it -> Observable.intervalRange(1,3,10,10, TimeUnit.MILLISECONDS).map(it2 -> ("ConcatMap :: first : " + it + " , second :" + it2)))
                .subscribe(System.out::println);
        Thread.sleep(200);

        Observable.intervalRange(1,3, 10, 10, TimeUnit.MILLISECONDS)
                .flatMap(it -> Observable.intervalRange(1,3,10,10, TimeUnit.MILLISECONDS).map(it2 -> ("flatMap :: first : " + it + " , second :" + it2)))
                .subscribe(System.out::println);
        Thread.sleep(200);
        
        //switchMap
        Observable.intervalRange(1,3, 10, 10, TimeUnit.MILLISECONDS)
                .switchMap(it -> Observable.intervalRange(1,3,10,10, TimeUnit.MILLISECONDS).map(it2 -> ("switchMap :: first : " + it + " , second :" + it2)))
                .subscribe(System.out::println);
        Thread.sleep(200);

        //groupBy
        System.out.println("\ngroupBy");
        Observable<GroupedObservable<Boolean, Integer>> groupedObservable = Observable.just(1,2,3,4,5,6).groupBy(it -> (it%2 == 0));
        groupedObservable.take(1).subscribe(observable -> observable.subscribe(System.out::println));

        //scan
        System.out.println("\nscan");
        Observable.just(1,2,3,4,5,6).scan((i1,i2) -> i1 + i2).subscribe(System.out::println);
    }

}
