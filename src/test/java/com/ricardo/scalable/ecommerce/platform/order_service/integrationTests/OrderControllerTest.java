package com.ricardo.scalable.ecommerce.platform.order_service.integrationTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class OrderControllerTest {

    @Autowired
	private Environment env;

	@Autowired
	private WebTestClient client;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
	}

    @Test
	@Order(1)
	void testGetOrderById() {
		client.get()
			.uri("/1")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBody()
            .consumeWith(res -> {
                try {
                    JsonNode json = objectMapper.readTree(res.getResponseBody());
                    assertAll(
                        () -> assertNotNull(json),
                        () -> assertEquals(1L, json.path("id").asLong()),
                        () -> assertEquals(1L, json.path("user").path("id").asLong()),
                        () -> assertEquals(89.99, json.path("totalAmount").asDouble()),
                        () -> assertEquals("PENDING", json.path("orderStatus").asText()),
                        () -> assertEquals("PENDING", json.path("paymentStatus").asText()),
                        () -> assertEquals(1L, json.path("shippingAddress").path("id").asLong()),
                        () -> assertEquals(1L, json.path("billingAddress").path("id").asLong())	
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
	}

    @Test
	@Order(2)
	void testGetOrderByIdNotFound() {
		client.get()
			.uri("/999")
			.exchange()
			.expectStatus().isNotFound();
	}

	@Test
	@Order(3)
	void testGetOrderByUserId() {
		client.get()
			.uri("/user/1")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBody()
            .consumeWith(res -> {
                try {
                    JsonNode json = objectMapper.readTree(res.getResponseBody());
                    assertAll(
                        () -> assertNotNull(json),
                        () -> assertTrue(json.isArray()),
                        () -> assertEquals(2, json.size()),
                        () -> assertEquals(1L, json.get(0).path("id").asLong()),
                        () -> assertEquals(1L, json.get(0).path("user").path("id").asLong()),
                        () -> assertEquals(89.99, json.get(0).path("totalAmount").asDouble()),
                        () -> assertEquals("PENDING", json.get(0).path("orderStatus").asText()),
                        () -> assertEquals("PENDING", json.get(0).path("paymentStatus").asText()),
                        () -> assertEquals(1L, json.get(0).path("shippingAddress").path("id").asLong()),
                        () -> assertEquals(1L, json.get(0).path("billingAddress").path("id").asLong()),
                        () -> assertEquals(2L, json.get(1).path("id").asLong()),
                        () -> assertEquals(1L, json.get(1).path("user").path("id").asLong()),
                        () -> assertEquals(49.99, json.get(1).path("totalAmount").asDouble()),
                        () -> assertEquals("PENDING", json.get(1).path("orderStatus").asText()),
                        () -> assertEquals("COMPLETED", json.get(1).path("paymentStatus").asText()),
                        () -> assertEquals(1L, json.get(1).path("shippingAddress").path("id").asLong()),
                        () -> assertEquals(1L, json.get(1).path("billingAddress").path("id").asLong())		
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
	}

	@Test
	@Order(4)
	void testGetOrderByUserIdNotFound() {
		client.get()
			.uri("/user/999")
			.exchange()
			.expectStatus().isNotFound();
	}

    @Test
	@Order(5)
	void testGetOrderByIdAndUserId() {
		client.get()
			.uri("/3/user/2")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBody()
            .consumeWith(res -> {
                try {
                    JsonNode json = objectMapper.readTree(res.getResponseBody());
                    assertAll(
                        () -> assertNotNull(json),
                        () -> assertEquals(3L, json.path("id").asLong()),
                        () -> assertEquals(2L, json.path("user").path("id").asLong()),
                        () -> assertEquals(249.99, json.path("totalAmount").asDouble()),
                        () -> assertEquals("SHIPPED", json.path("orderStatus").asText()),
                        () -> assertEquals("COMPLETED", json.path("paymentStatus").asText()),
                        () -> assertEquals(2L, json.path("shippingAddress").path("id").asLong()),
                        () -> assertEquals(2L, json.path("billingAddress").path("id").asLong())	
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
	}

	
	@Test
	@Order(6)
	void testGetOrderByIdAndUserIdNotFoundOrder() {
		client.get()
			.uri("/999/user/1")
			.exchange()
			.expectStatus().isNotFound();
	}

	@Test
	@Order(7)
	void testGetOrderByIdAndUserIdNotFoundUser() {
		client.get()
			.uri("/1/user/999")
			.exchange()
			.expectStatus().isNotFound();
	}

    @Test
	@Order(8)
	void testGetOrderByOrderStatus() {
		client.get()
			.uri("/status/shipped")
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBody()
            .consumeWith(res -> {
                try {
                    JsonNode json = objectMapper.readTree(res.getResponseBody());
                    assertAll(
                        () -> assertNotNull(json),
                        () -> assertTrue(json.isArray()),
                        () -> assertEquals(2, json.size()),
                        () -> assertEquals(3L, json.get(0).path("id").asLong()),
                        () -> assertEquals(2L, json.get(0).path("user").path("id").asLong()),
                        () -> assertEquals(249.99, json.get(0).path("totalAmount").asDouble()),
                        () -> assertEquals("SHIPPED", json.get(0).path("orderStatus").asText()),
                        () -> assertEquals("COMPLETED", json.get(0).path("paymentStatus").asText()),
                        () -> assertEquals(2L, json.get(0).path("shippingAddress").path("id").asLong()),
                        () -> assertEquals(2L, json.get(0).path("billingAddress").path("id").asLong()),
                        () -> assertEquals(5L, json.get(1).path("id").asLong()),
                        () -> assertEquals(3L, json.get(1).path("user").path("id").asLong()),
                        () -> assertEquals(49.99, json.get(1).path("totalAmount").asDouble()),
                        () -> assertEquals("SHIPPED", json.get(1).path("orderStatus").asText()),
                        () -> assertEquals("COMPLETED", json.get(1).path("paymentStatus").asText()),
                        () -> assertEquals(3L, json.get(1).path("shippingAddress").path("id").asLong()),
                        () -> assertEquals(3L, json.get(1).path("billingAddress").path("id").asLong())		
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
	}

	@Test
	@Order(9)
	void testGetOrderByOrderStatusNotFound() {
		client.get()
			.uri("/status/not-shipped")
			.exchange()
			.expectStatus().isNotFound();
	}


    @Test
    void testProfile() {
        String[] activeProfiles = env.getActiveProfiles();
        assertArrayEquals(new String[] { "test" }, activeProfiles);
    }

    @Test
    void testApplicationPropertyFile() {
        assertEquals("jdbc:h2:mem:public;NON_KEYWORDS=value", env.getProperty("spring.datasource.url"));
    }

}
