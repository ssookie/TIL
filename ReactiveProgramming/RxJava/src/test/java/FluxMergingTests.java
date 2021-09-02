import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;

public class FluxMergingTests {

    @Test
    public void mergeFluxes() {

        // delays needed to avoid the first flux from streaming the
        // data through before subscribing to the second flux.

        Flux<String> characterFlux = Flux
                .just("Garfield", "Kojak", "Barbossa")
                // flux 가 데이터를 조금 느리게(500밀리초마다) 방출하도록 함.
                .delayElements(Duration.ofMillis(500));
        Flux<String> foodFlux = Flux
                .just("Lasagna", "Lollipops", "Apples")
                // foodFlux가 characterFlux 다음에 스트리밍을 시작하도록, 250밀리초 지난 후에 구독 및 데이터를 방출하도록 함.
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));

        // 두 객체가 결합되어 하나의 Flux인 mergeFlex가 새로 생성됨.
        Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux);

        StepVerifier.create(mergedFlux)
                .expectNext("Garfield")
                .expectNext("Lasagna")
                .expectNext("Kojak")
                .expectNext("Lollipops")
                .expectNext("Barbossa")
                .expectNext("Apples")
                .verifyComplete();
    }

    @Test
    public void zipFluxes() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa");
        Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples");

        Flux<Tuple2<String, String>> zippedFlux =
                Flux.zip(characterFlux, foodFlux);

        StepVerifier.create(zippedFlux)
                .expectNextMatches(p ->
                        p.getT1().equals("Garfield") && p.getT2().equals("Lasagna"))
                .expectNextMatches(p ->
                        p.getT1().equals("Kojak") && p.getT2().equals("Lollipops"))
                .expectNextMatches(p ->
                        p.getT1().equals("Barbossa") && p.getT2().equals("Apples"))
                .verifyComplete();
    }

    /**
     * Tuple2가 아닌 다른 타입을 사용하고 싶다면, 원하는 객체를 생성하는 함수를 zip()에 제공하면 된다.
     */
    @Test
    public void zipFluxesToObject() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa");
        Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples");

        Flux<String> zippedFlux =
                Flux.zip(characterFlux, foodFlux, (c, f) -> c + " eats " + f);

        StepVerifier.create(zippedFlux)
                .expectNext("Garfield eats Lasagna")
                .expectNext("Kojak eats Lollipops")
                .expectNext("Barbossa eats Apples")
                .verifyComplete();
    }

    @Test
    public void firstFlux() {
        // delay needed to "slow down" the slow Flux

        Flux<String> slowFlux = Flux.just("tortoise", "snail", "sloth")
                .delaySubscription(Duration.ofMillis(100));
        Flux<String> fastFlux = Flux.just("hare", "cheetah", "squirrel");

        Flux<String> firstFlux = Flux.firstWithSignal(slowFlux, fastFlux);

        // slowFlux는 100밀리초가 경과한 후에 구독 신청과 발행을 시작하므로,
        // 새로 생성되는 firstFlux 는 slowFlux를 무시하고 fastFlux의 값만 발행하게 된다.
        StepVerifier.create(firstFlux)
                .expectNext("hare")
                .expectNext("cheetah")
                .expectNext("squirrel")
                .verifyComplete();
    }
}
