package rxjava.observable;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class ObservableTest {
    public static void main(String[] args) throws InterruptedException {
        //just
        Observable.just(1,2,3).subscribe(System.out::println);

        //create
        Observable.create(
                emitter -> {
                    emitter.onNext("create1");
                    emitter.onNext("create2");
                    emitter.onNext("create3");
                    emitter.onComplete();
                }).subscribe(System.out::println);

        //fromArray
        String[] arr = {"array1", "array2", "array3"};
        Observable.fromArray(arr).subscribe(System.out::println);

        //fromIterable
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        Observable.fromIterable(arrayList).subscribe(System.out::println);

        //Single
        Single<String> singleSource = Single.just("Hello Single");
        singleSource.subscribe(System.out::println);

        //Maybe
        Maybe<String> maybeSource = Maybe.just("Hello Maybe");
        maybeSource.subscribe(System.out::println);

        //AsyncSubject as Observable
        AsyncSubject<String> asyncSubject1 = AsyncSubject.create();
        asyncSubject1.onNext("asyncSubject as Observable 1");
        asyncSubject1.onNext("asyncSubject as Observable 2");
        asyncSubject1.onComplete();
        asyncSubject1.onNext("asyncSubject as Observable 3");
        asyncSubject1.subscribe(System.out::println);

        //AsyncSubject as Observer
        String[] asyncSubjectArr = {"asyncSubject as Observer 1", "asyncSubject as Observer 2"};
        Observable<String> asyncSubjectSource = Observable.fromArray(asyncSubjectArr);
        AsyncSubject<String> asyncSubject2 = AsyncSubject.create();
        asyncSubject2.subscribe(System.out::println);
        asyncSubjectSource.subscribe(asyncSubject2);
        asyncSubject2.onComplete();

        //BehaviorSubject
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.createDefault("default value");
        behaviorSubject.subscribe(System.out::println); // print "default value"
        behaviorSubject.onNext("behaviorSubject 1"); //print "behaviorSubject 1"
        behaviorSubject.onNext("behaviorSubject 2"); // print "behaviorSubject 2"
        behaviorSubject.subscribe(System.out::println); // print "behaviorSubject 2"
        behaviorSubject.onComplete();

        //PublishSubject
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.onNext("publishSubject 1"); // no print
        publishSubject.subscribe(System.out::println);
        publishSubject.onNext("publishSubject 2"); //print "publishSubject 2"
        publishSubject.onNext("publishSubject 3"); //print "publishSubject 3"
        publishSubject.onComplete();

        //ReplaySubject
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.onNext("replaySubject 1");
        replaySubject.onNext("replaySubject 2");
        replaySubject.onNext("replaySubject 3");
        replaySubject.subscribe(System.out::println); // print "replaySubject 1", "replaySubject 2", "replaySubject 3"
        replaySubject.onNext("replaySubject 4"); // print "replaySubject 3"
        replaySubject.onComplete();

        //Connectable Observable
        ConnectableObservable<String> connectableObservable = Observable.fromArray(new String[]{"ConnectableObservable 1", "ConnectableObservable 2", "ConnectableObservable 3"}).publish();
        connectableObservable.subscribe(System.out::println); // no print
        Thread.sleep(100);
        System.out.println("---connectableObservable connect---");
        connectableObservable.connect(); // print "connectableObservable 1", "connectableObservable 2", "connectableObservable 3"

//        PublishSubject subject = PublishSubject.create();
//        Observable.interval(10, TimeUnit.MILLISECONDS)
//                .subscribe(subject);
//        Thread.sleep(100);
//        subject.subscribe(System.out::println);
//        Thread.sleep(100);

        ConnectableObservable connectableObservable1 = Observable.interval(10, TimeUnit.MILLISECONDS).publish();
        connectableObservable1.connect();
        Thread.sleep(100);
        connectableObservable1.subscribe(System.out::println);
        Thread.sleep(100);
    }
}
