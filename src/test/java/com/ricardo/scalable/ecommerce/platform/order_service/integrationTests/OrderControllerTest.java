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
    void testProfile() {
        String[] activeProfiles = env.getActiveProfiles();
        assertArrayEquals(new String[] { "test" }, activeProfiles);
    }

    @Test
    void testApplicationPropertyFile() {
        assertEquals("jdbc:h2:mem:public;NON_KEYWORDS=value", env.getProperty("spring.datasource.url"));
    }

}
