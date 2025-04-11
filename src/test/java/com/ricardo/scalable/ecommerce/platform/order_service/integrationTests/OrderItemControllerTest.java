package com.ricardo.scalable.ecommerce.platform.order_service.integrationTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.OrderItemControllerTestData.*;

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
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderItemDto;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class OrderItemControllerTest {

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
	void testGetOrderItemById() {
		client.get()
			.uri("/order-items/1")
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
						() -> assertEquals(1L, json.path("order").path("id").asLong()),
						() -> assertEquals(1L, json.path("productSku").path("id").asLong()),
						() -> assertEquals(1, json.path("quantity").asInt()),
						() -> assertEquals(1500.99, json.path("unitPrice").asDouble()),
						() -> assertEquals(1L, json.path("discount").path("id").asLong())	
					);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}

	@Test
	@Order(2)
	void testGetOrderItemByIdNotFound() {
		client.get()
			.uri("/order-items/999")
			.exchange()
			.expectStatus().isNotFound();
	}

    @Test
	@Order(3)
	void testGetOrderItemsByOrderId() {
		client.get()
			.uri("/order-items/order/2")
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
						() -> assertEquals(2L, json.get(0).path("order").path("id").asLong()),
						() -> assertEquals(3L, json.get(0).path("productSku").path("id").asLong()),
						() -> assertEquals(3, json.get(0).path("quantity").asInt()),
						() -> assertEquals(29.99, json.get(0).path("unitPrice").asDouble()),
						() -> assertEquals(1L, json.get(0).path("discount").path("id").asLong()),
						() -> assertEquals(4L, json.get(1).path("id").asLong()),
						() -> assertEquals(2L, json.get(1).path("order").path("id").asLong()),
						() -> assertEquals(7L, json.get(1).path("productSku").path("id").asLong()),
						() -> assertEquals(2, json.get(1).path("quantity").asInt()),
						() -> assertEquals(19.99, json.get(1).path("unitPrice").asDouble()),
						() -> assertEquals(3L, json.get(1).path("discount").path("id").asLong())		
					);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}

	@Test
	@Order(4)
	void testGetOrderItemsByOrderIdNotFound() {
		client.get()
			.uri("/order-items/order/999")
			.exchange()
			.expectStatus().isNotFound();
	}

    @Test
	@Order(5)
	void testGetOrderItemsByProductSkuId() {
		client.get()
			.uri("/order-items/product-sku/1")
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
						() -> assertEquals(1L, json.get(0).path("order").path("id").asLong()),
						() -> assertEquals(1L, json.get(0).path("productSku").path("id").asLong()),
						() -> assertEquals(1, json.get(0).path("quantity").asInt()),
						() -> assertEquals(1500.99, json.get(0).path("unitPrice").asDouble()),
						() -> assertEquals(1L, json.get(0).path("discount").path("id").asLong()),
						() -> assertEquals(7L, json.get(1).path("id").asLong()),
						() -> assertEquals(5L, json.get(1).path("order").path("id").asLong()),
						() -> assertEquals(1L, json.get(1).path("productSku").path("id").asLong()),
						() -> assertEquals(1, json.get(1).path("quantity").asInt()),
						() -> assertEquals(1500.99, json.get(1).path("unitPrice").asDouble()),
						() -> assertEquals(2L, json.get(1).path("discount").path("id").asLong())		
					);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}

	@Test
	@Order(6)
	void testGetOrderItemsByProductSkuIdNotFound() {
		client.get()
			.uri("/order-items/product-sku/999")
			.exchange()
			.expectStatus().isNotFound();
	}

    @Test
	@Order(7)
	void testGetOrderItemByOrderIdAndProductSkuId() {
		client.get()
			.uri("/order-items/order/2/product-sku/3")
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
						() -> assertEquals(2L, json.path("order").path("id").asLong()),
						() -> assertEquals(3L, json.path("productSku").path("id").asLong()),
						() -> assertEquals(3, json.path("quantity").asInt()),
						() -> assertEquals(29.99, json.path("unitPrice").asDouble()),
						() -> assertEquals(1L, json.path("discount").path("id").asLong())	
					);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}

	@Test
	@Order(8)
	void testGetOrderItemByOrderIdAndProductSkuIdNotFound() {
		client.get()
			.uri("/order-items/order/999/product-sku/999")
			.exchange()
			.expectStatus().isNotFound();
	}

    @Test
	@Order(9)
	void testGetOrderItemsByUnitPrice() {
		client.get()
			.uri("/order-items/unit-price/1500.99")
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
						() -> assertEquals(1L, json.get(0).path("order").path("id").asLong()),
						() -> assertEquals(1L, json.get(0).path("productSku").path("id").asLong()),
						() -> assertEquals(1, json.get(0).path("quantity").asInt()),
						() -> assertEquals(1500.99, json.get(0).path("unitPrice").asDouble()),
						() -> assertEquals(1L, json.get(0).path("discount").path("id").asLong()),
						() -> assertEquals(7L, json.get(1).path("id").asLong()),
						() -> assertEquals(5L, json.get(1).path("order").path("id").asLong()),
						() -> assertEquals(1L, json.get(1).path("productSku").path("id").asLong()),
						() -> assertEquals(1, json.get(1).path("quantity").asInt()),
						() -> assertEquals(1500.99, json.get(1).path("unitPrice").asDouble()),
						() -> assertEquals(2L, json.get(1).path("discount").path("id").asLong())		
					);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}
	
	@Test
	@Order(10)
	void testGetOrderItemsByUnitPriceNotFound() {
		client.get()
			.uri("/order-items/unit-price/162315.99")
			.exchange()
			.expectStatus().isNotFound();
	}

    @Test
	@Order(11)
	void testGetOrderItemsByDiscountId() {
		client.get()
			.uri("/order-items/discount/3")
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
						() -> assertEquals(4L, json.get(0).path("id").asLong()),
						() -> assertEquals(2L, json.get(0).path("order").path("id").asLong()),
						() -> assertEquals(7L, json.get(0).path("productSku").path("id").asLong()),
						() -> assertEquals(2, json.get(0).path("quantity").asInt()),
						() -> assertEquals(19.99, json.get(0).path("unitPrice").asDouble()),
						() -> assertEquals(3L, json.get(0).path("discount").path("id").asLong()),
						() -> assertEquals(6L, json.get(1).path("id").asLong()),
						() -> assertEquals(4L, json.get(1).path("order").path("id").asLong()),
						() -> assertEquals(6L, json.get(1).path("productSku").path("id").asLong()),
						() -> assertEquals(2, json.get(1).path("quantity").asInt()),
						() -> assertEquals(29.99, json.get(1).path("unitPrice").asDouble()),
						() -> assertEquals(3L, json.get(1).path("discount").path("id").asLong())		
					);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}

	@Test
	@Order(12)
	void testGetOrderItemsByDiscountIdNotFound() {
		client.get()
			.uri("/order-items/discount/999")
			.exchange()
			.expectStatus().isNotFound();
	}

    @Test
	@Order(13)
	void testAllOrderItems() {
		client.get()
			.uri("/order-items")
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
						() -> assertEquals(8, json.size())		
					);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}

    @Test
	@Order(14)
	void testCreateOrderItem() {
		OrderItemDto requestBody = createOrderItemCreationDto();
			
		client.post()
			.uri("/order-items")
			.contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
			.exchange()
			.expectStatus().isCreated()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBody()
			.consumeWith(res -> {
				try {
					JsonNode json = objectMapper.readTree(res.getResponseBody());
					assertAll(
						() -> assertNotNull(json),
						() -> assertEquals(9L, json.path("id").asLong()),
						() -> assertEquals(2L, json.path("order").path("id").asLong()),
						() -> assertEquals(3L, json.path("productSku").path("id").asLong()),
						() -> assertEquals(2, json.path("quantity").asInt()),
						() -> assertEquals(29.99, json.path("unitPrice").asDouble()),
						() -> assertEquals(2L, json.path("discount").path("id").asLong())	
					);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}
	
	@Test
	@Order(15)
	void testCreateOrderItemWithNoOrderId() {
		OrderItemDto requestBody = createOrderItemCreationDto();
		requestBody.setOrderId(null);
			
		client.post()
			.uri("/order-items")
			.contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
			.exchange()
			.expectStatus().isBadRequest();
	}

	@Test
	@Order(16)
	void testCreateOrderItemWithNoProductSkuId() {
		OrderItemDto requestBody = createOrderItemCreationDto();
		requestBody.setProductSkuId(null);
			
		client.post()
			.uri("/order-items")
			.contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
			.exchange()
			.expectStatus().isBadRequest();
	}

	@Test
	@Order(17)
	void testCreateOrderItemWithNoDiscountId() {
		OrderItemDto requestBody = createOrderItemCreationDtoWithNullDiscount();
		requestBody.setDiscountId(null);
			
		client.post()
			.uri("/order-items")
			.contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
			.exchange()
			.expectStatus().isCreated();
	}

	@Test
	@Order(18)
	void testCreateOrderItemNotFoundOrderId() {
		OrderItemDto requestBody = createOrderItemCreationDto();
		requestBody.setOrderId(999L);
			
		client.post()
			.uri("/order-items")
			.contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
			.exchange()
			.expectStatus().isNotFound();
	}

	@Test
	@Order(19)
	void testCreateOrderItemNotFoundProductSkuId() {
		OrderItemDto requestBody = createOrderItemCreationDto();
		requestBody.setProductSkuId(999L);
			
		client.post()
			.uri("/order-items")
			.contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
			.exchange()
			.expectStatus().isNotFound();
	}

	@Test
	@Order(20)
	void testCreateOrderItemNotFoundDiscountId() {
		OrderItemDto requestBody = createOrderItemCreationDto();
		requestBody.setDiscountId(999L);
			
		client.post()
			.uri("/order-items")
			.contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
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
