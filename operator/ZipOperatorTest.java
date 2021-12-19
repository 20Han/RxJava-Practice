package rxjava.operator;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import rxjava.Log;

public class ZipOperatorTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\nZipTest");
        Observable.zip(Observable.just(1,2,3), Observable.just(1,2,3), Integer::sum).subscribe(System.out::println);

        System.out.println("\nZipIntervalTest");
        Observable.zip(Observable.just(1,2,3), Observable.interval(10, TimeUnit.MILLISECONDS), (l1,l2) -> l1 + l2).subscribe(Log::i);
        Thread.sleep(50);

        System.out.println("\nCombineLatestTest");
        Observable.combineLatest(Observable.just(1,2,3), Observable.interval(10, TimeUnit.MILLISECONDS).take(3), (l1,l2) -> l1 + l2).subscribe(Log::i);
        Thread.sleep(50);

        System.out.println("\nMergeTest");
        Observable.merge(Observable.just(1,2,3), Observable.interval(10, TimeUnit.MILLISECONDS).take(3)).subscribe(Log::i);
        Thread.sleep(50);

        System.out.println("\nConcatTest");
        Observable.concat(Observable.interval(10, TimeUnit.MILLISECONDS).take(3).map(it -> "intervalObservable : " + it), Observable.just("just1", "just2", "just3")).subscribe(Log::i);
        Thread.sleep(50);
    }

}
