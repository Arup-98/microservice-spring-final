package com.example.orderservice.service;

import com.example.orderservice.model.OrderRequestModel;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<Object> createOrder(OrderRequestModel orderRequestModel);
    ResponseEntity<Object> getAllOrder();
    void deleteOrder(Integer orderId);
    ResponseEntity<Object> orderDetails( Integer orderId) ;
}
