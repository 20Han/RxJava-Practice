package rxjava.Scheduler;

import java.io.File;

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
        System.out.println("\nprint file Name with IO Scheduler");
        String root = "/";
        File[] files = new File(root).listFiles();
        Observable.fromArray(files)
                .filter(it -> it.canRead())
                .map(it -> it.getName())
                .subscribeOn(Schedulers.io())
                .subscribe(Log::i);
        Thread.sleep(1000);
    }

}
