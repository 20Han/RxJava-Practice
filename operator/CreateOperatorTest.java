package rxjava.operator;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CreateOperatorTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        //inter
        Observable.interval(50, 10, TimeUnit.MILLISECONDS).take(5).subscribe(System.out::println);

        //timer
        Observable<String> timerObservable = Observable.timer(50, TimeUnit.MILLISECONDS)
                .map(temp -> "emit");
        timerObservable.subscribe(System.out::println);

        //intervalRange
        Observable.intervalRange(2, 3, 50, 10, TimeUnit.MILLISECONDS).map(it -> "IntervalRange : " + it).subscribe(System.out::println);

        Thread.sleep(100);

        //range
        Observable.range(1, 3).map(it -> "Range : " + it).subscribe(System.out::println);

        //defer
        Observable deferObservable = Observable.defer(() -> Observable.intervalRange(1,3, 10, 10, TimeUnit.MILLISECONDS));
        deferObservable.subscribe(it -> System.out.println("1: defer" + it));
        deferObservable.subscribe(it -> System.out.println("2: defer" + it));

        Thread.sleep(100);

        //repeat
        Observable repeatSouce = Observable.just("repeat1","repeat2","repeat3");
        Observable repeatObservable = repeatSouce.repeat(3);

        repeatObservable.subscribe(System.out::println);

        //serverPing Example
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder().url("https://www.google.com").get();
        Observable.timer(1, TimeUnit.SECONDS) 
                .map(it -> "pint Result : " + client.newCall(builder.build()).execute().code())
                .repeat()
                .subscribe(System.out::println);
        Thread.sleep(10_000);
    }

}
