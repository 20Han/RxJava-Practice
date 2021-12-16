package rxjava.operator;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class OperatorTestAdvanced {
    public static void main(String[] args) throws InterruptedException {
       Observable.interval(50,10, TimeUnit.MILLISECONDS).subscribe(System.out::println);
       Thread.sleep(100); //0~5까지만 출력

    }
}
