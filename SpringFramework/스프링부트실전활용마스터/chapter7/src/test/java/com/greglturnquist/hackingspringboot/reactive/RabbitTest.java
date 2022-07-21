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

import static org.assertj.core.api.Assertions.*;

import reactor.test.StepVerifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * @author Greg Turnquist
 */
// tag::setup[]
@SpringBootTest // <1>
@AutoConfigureWebTestClient // <2>
@Testcontainers // <3>
@ContextConfiguration // <4>
public class RabbitTest {

    // <5> 테스트에 사용할 래빗엠큐 인스턴스를 관리
    @Container static RabbitMQContainer container = new RabbitMQContainer("rabbitmq:3.7.25-management-alpine");

    @Autowired WebTestClient webTestClient; // <6>

    @Autowired ItemRepository repository; // <7>

    @DynamicPropertySource // <8>
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", container::getContainerIpAddress);
        registry.add("spring.rabbitmq.port", container::getAmqpPort);
    }
    // end::setup[]

    // tag::spring-amqp-test[]
    @Test
    void verifyMessagingThroughAmqp() throws InterruptedException {
        this.webTestClient.post().uri("/items") // <1>
                .bodyValue(new Item("Alf alarm clock", "nothing important", 19.99)) //
                .exchange() //
                .expectStatus().isCreated() //
                .expectBody();

        // <2> sleep() 처리를 통해, 해당 메시지가 브로커를 거쳐 데이터 저장소에 저장될 때까지 기다린다.
        // 이렇게 해서 테스트에 사용되는 메시지 처리 순서를 맞춘다.
        Thread.sleep(1500L); //

        this.webTestClient.post().uri("/items") // <3>
                .bodyValue(new Item("Smurf TV tray", "nothing important", 29.99)) //
                .exchange() //
                .expectStatus().isCreated() //
                .expectBody();

        Thread.sleep(2000L); // <4>

        // <5> 몽고 디비에 쿼리를 날려서 위 2개의 Item 객체가 저장되었는지 확인한다.
        this.repository.findAll() //
                .as(StepVerifier::create) //
                .expectNextMatches(item -> {
                    assertThat(item.getName()).isEqualTo("Alf alarm clock");
                    assertThat(item.getDescription()).isEqualTo("nothing important");
                    assertThat(item.getPrice()).isEqualTo(19.99);
                    return true;
                }) //
                .expectNextMatches(item -> {
                    assertThat(item.getName()).isEqualTo("Smurf TV tray");
                    assertThat(item.getDescription()).isEqualTo("nothing important");
                    assertThat(item.getPrice()).isEqualTo(29.99);
                    return true;
                }) //
                .verifyComplete();
    }
    // end::spring-amqp-test[]

}
