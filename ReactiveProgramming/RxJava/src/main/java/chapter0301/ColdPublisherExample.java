package chapter0301;

import io.reactivex.Flowable;

public class ColdPublisherExample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.just(1, 3, 5, 7);

        flowable.subscribe(data -> System.out.println("구독자1: " + data));
        flowable.subscribe(data -> System.out.println("구독자2: " + data));
    }
}

/*
> Task :ColdPublisherExample.main()
구독자1: 1
구독자1: 3
구독자1: 5
구독자1: 7
구독자2: 1
구독자2: 3
구독자2: 5
구독자2: 7
 */
