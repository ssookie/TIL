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

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.*;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Disabled("pom.xml에서 blockhound-junit-platform 의존 관계를 제거한 후에 실행해야 성공한다.")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //<1> 실제 애플리케이션 구동
@AutoConfigureWebTestClient // <2> WebTestClient생성
public class LoadingWebSiteIntegrationTest {

    @Autowired WebTestClient client; // <3> 인스턴스 주입

    @Test // <4>
    void test() {
        client.get().uri("/").exchange() // 홈 컨트롤러의 루트 경로 호출
                .expectStatus().isOk() // HTTP 응답 코드 검증
                .expectHeader().contentType(TEXT_HTML) // Content-TYPE 헤더 검증
                .expectBody(String.class)
                .consumeWith(exchangeResult -> { // responsebody 값 검증
                    assertThat(exchangeResult.getResponseBody()).contains("<a href=\"/add");
                });
    }
}
// end::code[]
