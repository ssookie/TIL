package chapter0102;

import io.reactivex.Observable;

public class MarbleExample01 {
    public static void main(String[] args) {
        Observable.just(1, 25, 9)   // data 발행
                .filter(x -> x > 10)    // filter 연산자
                .subscribe(x -> System.out.println(x));
    }
}
