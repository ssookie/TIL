/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.greglturnquist.hackingspringboot.reactive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.greglturnquist.hackingspringboot.reactive.Cart;
import com.greglturnquist.hackingspringboot.reactive.CartItem;
import com.greglturnquist.hackingspringboot.reactive.CartRepository;
import com.greglturnquist.hackingspringboot.reactive.InventoryService;
import com.greglturnquist.hackingspringboot.reactive.Item;
import com.greglturnquist.hackingspringboot.reactive.ItemRepository;

/**
 * @author Greg Turnquist
 */
// tag::extend[]
@ExtendWith(SpringExtension.class) // 스프링에 특화된 테스트 기능을 사용하게 해준다.
class InventoryServiceUnitTest { // 테스트 대상 클래스를 명시한다.
    // end::extend[]

    // tag::class-under-test[]
    // 테스트 대상 클래스(CUT)에는 어노테이션이 없다.
    InventoryService inventoryService; // <1>

    // 가짜 협력자 객체 (테스트 대상 클래스인 InventoryService에 주입되는 것으로 테스트 대상이 아니다.)
    @MockBean private ItemRepository itemRepository; // <2>
    @MockBean private CartRepository cartRepository; // <2>
    // end::class-under-test[]

    // tag::before[]
    @BeforeEach // <1>
    void setUp() {
        // Define test data <2>
        Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        CartItem sampleCartItem = new CartItem(sampleItem);
        Cart sampleCart = new Cart("My Cart", Collections.singletonList(sampleCartItem));

        // 모키토를 사용하여 가짜 객체와의 상호작용을 정의한다. <3>
        when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
        when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
        when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sampleCart));

        // 가짜 협력자를 생성자에 주입하여 테스트 대상 클래스를 생성한다. <4>
        inventoryService = new InventoryService(itemRepository, cartRepository);
    }
    // end::before[]

    // tag::test[]
    @Test
    void addItemToEmptyCartShouldProduceOneCartItem() { // <1>
        inventoryService.addItemToCart("My Cart", "item1") // <2>
                .as(StepVerifier::create) // <3> 구독을 시작한다.
                .expectNextMatches(cart -> { // <4> 결과 검증, boolean 반환
                    assertThat(cart.getCartItems()).extracting(CartItem::getQuantity) //
                            .containsExactlyInAnyOrder(1); // <5>

                    assertThat(cart.getCartItems()).extracting(CartItem::getItem) //
                            .containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99)); // <6>

                    return true; // <7>
                }) //
                .verifyComplete(); // <8> 리액티브 스트림의 complete 시그널 발생
    }
    // end::test[]

    // tag::test2[]
    @Test
    void alternativeWayToTest() { // <1>
        StepVerifier.create( //
                inventoryService.addItemToCart("My Cart", "item1")) //
                .expectNextMatches(cart -> { // <4>
                    assertThat(cart.getCartItems()).extracting(CartItem::getQuantity) //
                            .containsExactlyInAnyOrder(1); // <5>

                    assertThat(cart.getCartItems()).extracting(CartItem::getItem) //
                            .containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99)); // <6>

                    return true; // <7>
                }) //
                .verifyComplete(); // <8>
    }
    // end::test2[]

}
