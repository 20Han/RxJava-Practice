package rxjava.operator;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import rxjava.Log;

public class ConditionOperatorTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\nAmbTest");
        Observable.amb(Arrays.asList(Observable.interval(10, TimeUnit.MILLISECONDS).take(3).map(it -> "intervalObservable : " + it), Observable.just("just1", "just2", "just3"))).subscribe(Log::i);
        Thread.sleep(50);

        System.out.println("\nTakeUntilTest");
        Observable.merge(Observable.interval(10, TimeUnit.MILLISECONDS).take(3), Observable.just(1,2,3)).takeUntil(it -> (it.intValue() == 0)).subscribe(Log::i);
        Thread.sleep(50);

        System.out.println("\nSkipUntilTest");
        Observable.merge(Observable.interval(10, TimeUnit.MILLISECONDS).take(3), Observable.just(1,2,3)).skipUntil(Observable.timer(20, TimeUnit.MILLISECONDS)).subscribe(Log::i);
        Thread.sleep(50);

        System.out.println("\nAllTest, result = true");
        Observable.just(1,2,3).all(it -> it < 4).subscribe(Log::i);

        System.out.println("\nAllTest, result = false");
        Observable.just(1,2,3).all(it -> it < 3).subscribe(Log::i);
    }

}
