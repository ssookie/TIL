package chapter0302;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import utils.LogType;
import utils.Logger;
import utils.TimeUtil;

import java.util.concurrent.TimeUnit;

/**
 * 배압 전략이 없을 때 발생하는 현상은?
 * 생산자 쪽에서 통지하는 데이터가 소비자 쪽에서 처리하는 데이터 속도보다 훨씬 빠른 상황.
 */
public class MissingBackpressureExceptionExample {
    public static void main(String[] agrs) throws InterruptedException {
        Flowable.interval(1L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .observeOn(Schedulers.computation())    // 데이터 처리하는 thread 분리
                .subscribe(
                        data -> {   // 데이터 전달받아서 처리함.
                            Logger.log(LogType.PRINT, "# 소비자 처리 대기 중..");
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );
        Thread.sleep(2000L);
    }
}
/*
doOnNext() | RxComputationThreadPool-2 | 00:06:44.733 | 0
doOnNext() | RxComputationThreadPool-2 | 00:06:44.735 | 1
print() | RxComputationThreadPool-1 | 00:06:44.735 | # 소비자 처리 대기 중..
doOnNext() | RxComputationThreadPool-2 | 00:06:44.735 | 2
doOnNext() | RxComputationThreadPool-2 | 00:06:44.736 | 3
...
doOnNext() | RxComputationThreadPool-2 | 00:06:44.827 | 127
onNext() | RxComputationThreadPool-1 | 00:06:45.740 | 0
onERROR() | RxComputationThreadPool-1 | 00:06:45.740 | io.reactivex.exceptions.MissingBackpressureException: Can't deliver value 128 due to lack of requests
 */
