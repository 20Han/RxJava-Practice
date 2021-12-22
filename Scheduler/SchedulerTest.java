package rxjava.Scheduler;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import rxjava.Log;

public class SchedulerTest {
    public static void main(String[] args) throws InterruptedException {
        //subscribeOn, observeOn
        System.out.println("---subscribeOn, observeOn---");
        Observable.just(1,2)
                .doOnNext(data -> Log.i("onNext :" + data))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(Log::i);
        Thread.sleep(100);

        System.out.println("\n---observeOn---");
        Observable.just(1,2)
                .doOnNext(data -> Log.i("onNext :" + data))
//                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(Log::i);
        Thread.sleep(100);

        System.out.println("\n---subscribeOn---");
        Observable.just(1,2)
                .doOnNext(data -> Log.i("onNext :" + data))
                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.newThread())
                .subscribe(Log::i);
        Thread.sleep(100);

        //Print File with IO Scheduler
        System.out.println("\n---print file Name with IO Scheduler---");
        String root = "/";
        File[] files = new File(root).listFiles();
        Observable.fromArray(files)
                .filter(it -> it.canRead())
                .map(it -> it.getName())
                .subscribeOn(Schedulers.io())
                .subscribe(Log::i);
        Thread.sleep(1000);

        //Trampoline 스케줄러
        System.out.println("\n---Trampoline Scheduler---");
        Observable trampolineSource = Observable.intervalRange(1,3, 10, 10, TimeUnit.MILLISECONDS);
        trampolineSource.subscribeOn(Schedulers.trampoline()).map(it -> "First Observer " + it).subscribe(Log::i);
        trampolineSource.subscribeOn(Schedulers.trampoline()).map(it -> "Second Observer " + it).subscribe(Log::i);

        Thread.sleep(100);

        //SingleThread 스케줄러
        System.out.println("\n---SingleThread Scheduler---");
        Observable singleThreadSource1 = Observable.intervalRange(1,3, 10, 10, TimeUnit.MILLISECONDS);
        Observable singleThreadSource2 = Observable.just(10,11,12);
        singleThreadSource1.subscribeOn(Schedulers.single()).map(it -> "First Observer " + it).subscribe(Log::i);
        singleThreadSource2.subscribeOn(Schedulers.single()).map(it -> "Second Observer " + it).subscribe(Log::i);

        Thread.sleep(100);

        //MultipleSubscribeOn
        System.out.println("\n---MultipleSubscribe Test---");
        Observable.just(1,2,3)
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(Log::i); //io 스레드에서 로그가 출력된다.
        Thread.sleep(100);

        System.out.println("\n---MultipleObserveOn Test---");
        Observable.just(1,2,3)
                .observeOn(Schedulers.io())
                .map(it -> it * 2)
                .observeOn(Schedulers.newThread())
                .map(it -> it * 2)
                .observeOn(Schedulers.computation())
                .subscribe(Log::i); //computation 스레드에서 로그가 출력된다.
        Thread.sleep(100);
    }

}
