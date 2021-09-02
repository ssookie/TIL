package chapter0301;

import io.reactivex.processors.PublishProcessor;

public class HotPublisherExample {
    public static void main(String[] args){
        PublishProcessor<Integer> processor = PublishProcessor.create();
        processor.subscribe(data -> System.out.println("구독자1: " + data)); // 이 시점에는 아직 통제되는 데이터가 없음.
        processor.onNext(1);
        processor.onNext(3);

        processor.subscribe(data -> System.out.println("구독자2: " + data));
        processor.onNext(5);
        processor.onNext(7);

        processor.onComplete();
    }
}

/*
구독자1: 1
구독자1: 3
구독자1: 5
구독자2: 5
구독자1: 7
구독자2: 7
 */
