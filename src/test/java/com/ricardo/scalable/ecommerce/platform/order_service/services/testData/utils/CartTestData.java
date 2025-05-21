package com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Cart;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.User;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.UserTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.CartItemTestData.*;

public class CartTestData {

     public static List<Cart> createListOfCarts() {
        Cart cart1 = createCart001().orElseThrow();
        Cart cart2 = createCart002().orElseThrow();
        Cart cart3 = createCart003().orElseThrow();
        Cart cart4 = createCart004().orElseThrow();

        return List.of(cart1, cart2, cart3, cart4);
    }

    public static Optional<Cart> createCart001() {
        Cart cart = new Cart();
        User user = createUser001().orElseThrow();

        cart.setId(1L);
        cart.setUser(user);
        cart.setItems(List.of(
            createCartItem001().orElseThrow(),
            createCartItem002().orElseThrow()
        ));
        cart.setCreatedAt(Timestamp.from(Instant.now()));
        cart.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(cart);
    }

    public static Optional<Cart> createCart002() {
        Cart cart = new Cart();
        User user = createUser002().orElseThrow();

        cart.setId(2L);
        cart.setUser(user);
        cart.setItems(List.of(
            createCartItem003().orElseThrow(),
            createCartItem004().orElseThrow()
        ));
        cart.setCreatedAt(Timestamp.from(Instant.now()));
        cart.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(cart);
    }

    public static Optional<Cart> createCart003() {
        Cart cart = new Cart();
        User user = createUser003().orElseThrow();

        cart.setId(3L);
        cart.setUser(user);
        cart.setCreatedAt(Timestamp.from(Instant.now()));
        cart.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(cart);
    }

    public static Optional<Cart> createCart004() {
        Cart cart = new Cart();
        User user = createUser004().orElseThrow();

        cart.setId(4L);
        cart.setUser(user);
        cart.setCreatedAt(Timestamp.from(Instant.now()));
        cart.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(cart);
    }
    
}
