/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.greglturnquist.hackingspringboot.reactive;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@RestController // <1> JSON 형태로 데이터를 입력받고 JSON 형태로 데이터를 출력한다.
public class SpringAmqpItemController {

    private static final Logger log = LoggerFactory.getLogger(SpringAmqpItemController.class);

    // <2> 래빗엠큐를 사용하므로 실제 구현체로는 RabbitTemplate이 사용된다.
    private final AmqpTemplate template; //

    // 생성자 주입
    public SpringAmqpItemController(AmqpTemplate template) {
        this.template = template;
    }
    // end::code[]

    // tag::post[]
    @PostMapping("/items") // <1>
    Mono<ResponseEntity<?>> addNewItemUsingSpringAmqp(@RequestBody Mono<Item> item) { // <2> @RequestBody - 요청 본문에서 데이터 추출
        return item //
                // <3> 바운디드 엘라스틱 스케줄러에서 관리하는 별도의 스레드에서 실행한다. (AmqpTemplate은 블로킹 API를 호출함)
                .subscribeOn(Schedulers.boundedElastic())//
                .flatMap(content -> { //
                    return Mono //
                            .fromCallable(() -> { // <4>
                                this.template.convertAndSend( // <5> Item 데이터 전송
                                        "hacking-spring-boot", "new-items-spring-amqp", content);
                                return ResponseEntity.created(URI.create("/items")).build(); // <6>
                            });
                });
    }
    // end::post[]
}
