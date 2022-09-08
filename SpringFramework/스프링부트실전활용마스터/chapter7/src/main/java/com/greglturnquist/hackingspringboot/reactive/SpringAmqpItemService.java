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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 리액티브 방식으로 AMQP 메시지 사용
 * @author Greg Turnquist
 */
// tag::code[]
@Service //
public class SpringAmqpItemService {

    private static final Logger log = //
            LoggerFactory.getLogger(SpringAmqpItemService.class);

    private final ItemRepository repository; //

    public SpringAmqpItemService(ItemRepository repository) {
        this.repository = repository;
    }
    // end::code[]

    // tag::listener[]
    // 래빗엠큐 메시지 리스너 등록
    @RabbitListener( // <1>
            ackMode = "MANUAL", //
            bindings = @QueueBinding( // <2>
                    value = @Queue, // <3>
                    exchange = @Exchange("hacking-spring-boot"), // <4>
                    key = "new-items-spring-amqp")) // <5>
	// @RabbitListener에서 지정한 내용에 맞는 메시지가 들어오면, 메시지에 들어있는 Item 데이터는 item 변수를 통해 전달된다.
    public Mono<Void> processNewItemsViaSpringAmqp(Item item) { //
        log.debug("Consuming => " + item);
        return this.repository.save(item).then(); // 몽고디비에 저장
    }
    // end::listener[]
}
