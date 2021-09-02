package chapter0101;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ToDoSample {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(100, 200, 300, 400, 500)    // data 발행
                // 각각의 데이터가 발행이 될 때, doOnNext 함수가 실행됨. thread는 계속 main 임.
                .doOnNext(data -> System.out.println(getThreadName() + " : " + "#doOnNext() : " + data))
                // main thread 가 아닌, 다른 thread 를 사용하도록 함.
                .subscribeOn(Schedulers.io())
                // RxComputationThreadPool-1 를 사용하여 데이터를 처리하게 됨.
                .observeOn(Schedulers.computation())
                .filter(number -> number > 300)
                .subscribe(num -> System.out.println(getThreadName() + " : result : " + num));

        Thread.sleep(500);
    }

    public static String getThreadName(){
        return Thread.currentThread().getName();
    }
}
