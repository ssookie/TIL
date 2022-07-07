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

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.*;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author Greg Turnquist
 */
// tag::intro[]
@WebFluxTest(controllers = ApiItemController.class) // 웹플럭스 컨트롤러 테스트에 필요한 내용만 자동 설정되게 한다. (오직 ApiItemController 클래스만 집중적으로 테스트하겠다는 의미)
@AutoConfigureRestDocs // 스프링 레스트 독 사용에 필요한 내용을 자동을 설정해준다.
public class ApiItemControllerDocumentationTest {

    @Autowired private WebTestClient webTestClient; // 자동 주입

    @MockBean InventoryService service; // 웹플럭스 컨트롤러 외의 컴포넌트는 생성되지 않는다.
    @MockBean ItemRepository repository;
    // end::intro[]

    // tag::test1[]
    @Test
    void findingAllItems() {
        when(repository.findAll()).thenReturn( // <1>
                Flux.just(new Item("item-1", "Alf alarm clock", //
                        "nothing I really need", 19.99)));

        this.webTestClient.get().uri("/api/items") //
                .exchange() //
                .expectStatus().isOk() //
                .expectBody() //
                .consumeWith(document("findAll", preprocessResponse(prettyPrint()))); // <2>
    }
    // end::test1[]

    // tag::test2[]
    @Test
    void postNewItem() {
        when(repository.save(any())).thenReturn( //
                Mono.just(new Item("1", "Alf alarm clock", "nothing important", 19.99)));

        this.webTestClient.post().uri("/api/items") // <1>
                .bodyValue(new Item("Alf alarm clock", "nothing important", 19.99)) // <2>
                .exchange() //
                .expectStatus().isCreated() // <3>
                .expectBody() //
                .consumeWith(document("post-new-item", preprocessResponse(prettyPrint()))); // <4>
    }
    // end::test2[]

    // tag::test3[]
    @Test
    void findOneItem() {
        when(repository.findById("item-1")).thenReturn( //
                Mono.just(new Item("item-1", "Alf alarm clock", "nothing I really need", 19.99))); // <1>

        this.webTestClient.get().uri("/api/items/item-1") //
                .exchange() //
                .expectStatus().isOk() //
                .expectBody() //
                .consumeWith(document("findOne", preprocessResponse(prettyPrint()))); // <2>
    }
    // end::test3[]

    @Test
    void updateItem() {
        when(repository.save(any())).thenReturn( //
                Mono.just(new Item("1", "Alf alarm clock", "updated", 19.99)));

        this.webTestClient.put().uri("/api/items/1") // <1>
                .bodyValue(new Item("Alf alarm clock", "updated", 19.99)) // <2>
                .exchange() //
                .expectStatus().isOk() // <3>
                .expectBody() //
                .consumeWith(document("update-item", preprocessResponse(prettyPrint()))); // <4>
    }
}
