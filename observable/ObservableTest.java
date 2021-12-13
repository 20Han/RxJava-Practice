package rxjava.observable;

import java.util.ArrayList;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.subjects.AsyncSubject;

public class ObservableTest {
    public static void main(String[] args) {
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

        //AsyncSubject as Observable
        String[] asyncSubjectArr = {"asyncSubject as Observer 1", "asyncSubject as Observer 2"};
        Observable<String> asyncSubjectSource = Observable.fromArray(asyncSubjectArr);
        AsyncSubject<String> asyncSubject2 = AsyncSubject.create();
        asyncSubject2.subscribe(System.out::println);
        asyncSubjectSource.subscribe(asyncSubject2);

    }
}
