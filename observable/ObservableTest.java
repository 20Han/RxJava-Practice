package rxjava.observable;

import java.util.ArrayList;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.exceptions.OnErrorNotImplementedException;

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


    }
}
