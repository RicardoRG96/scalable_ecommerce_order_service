package com.ricardo.scalable.ecommerce.platform.order_service.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.AddressNotFoundException;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.CartNotFoundException;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.InsufficientStockException;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.OrderNotFoundException;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.ResourceNotFoundException;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficientStockException(InsufficientStockException ex) {
        Map<String, Object> errorBody = Map.of(
                "error", "Insufficient stock",
                "timestamp", LocalDateTime.now(),
                "message", ex.getMessage(),
                "status", HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorBody);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> errorBody = Map.of(
                "error", "Resource not found",
                "timestamp", LocalDateTime.now(),
                "message", ex.getMessage(),
                "status", HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorBody);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, Object> errorBody = Map.of(
                "error", "User not found",
                "timestamp", LocalDateTime.now(),
                "message", ex.getMessage(),
                "status", HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorBody);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAddressNotFoundException(AddressNotFoundException ex) {
        Map<String, Object> errorBody = Map.of(
                "error", "Address not found",
                "timestamp", LocalDateTime.now(),
                "message", ex.getMessage(),
                "status", HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorBody);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCartNotFoundException(CartNotFoundException ex) {
        Map<String, Object> errorBody = Map.of(
                "error", "Cart not found",
                "timestamp", LocalDateTime.now(),
                "message", ex.getMessage(),
                "status", HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorBody);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleOrderNotFoundException(OrderNotFoundException ex) {
        Map<String, Object> errorBody = Map.of(
                "error", "Order not found",
                "timestamp", LocalDateTime.now(),
                "message", ex.getMessage(),
                "status", HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorBody);
    }

}
