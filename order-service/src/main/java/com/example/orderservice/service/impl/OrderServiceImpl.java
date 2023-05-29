package com.example.orderservice.service.impl;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.model.OrderRequestModel;
import com.example.orderservice.model.OrderResponseModel;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderRepository orderRepository;


    @Override
    public ResponseEntity<Object> createOrder(OrderRequestModel orderRequestModel) {
        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(orderRequestModel.getOrderId())
                .orderQuantity(orderRequestModel.getOrderQuantity())
                .orderPrice(orderRequestModel.getOrderPrice())
                .email(orderRequestModel.getEmail())
                .phoneNumber(orderRequestModel.getPhoneNumber())
                .build();
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        OrderResponseModel orderResponseModel = OrderResponseModel.builder()
                .orderId(savedOrder.getOrderId())
                .bookId(savedOrder.getBookId())
                .orderQuantity(savedOrder.getOrderQuantity())
                .orderPrice(savedOrder.getOrderPrice())
                .email(savedOrder.getEmail())
                .phoneNumber(savedOrder.getPhoneNumber())
                .build();
        return new ResponseEntity<>(orderResponseModel, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getAllOrder() {
        List<OrderEntity> OrderList = orderRepository.findAll();
        List<OrderResponseModel> responseList = new ArrayList<>();
        for (OrderEntity order : OrderList) {
            OrderResponseModel orderResponseModel = OrderResponseModel.builder()
                    .orderId(order.getOrderId())
                    .bookId(order.getBookId())
                    .orderQuantity(order.getOrderQuantity())
                    .orderPrice(order.getOrderPrice())
                    .email(order.getEmail())
                    .phoneNumber(order.getPhoneNumber())
                    .build();
            responseList.add(orderResponseModel);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if (orderEntity != null) {
            orderRepository.delete(orderEntity);
        }

    }

    @Override
    public ResponseEntity<Object> orderDetails(Integer orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if (orderEntity != null) {
            OrderResponseModel orderResponseModel = OrderResponseModel.builder()
                    .orderId(orderEntity.getOrderId())
                    .orderQuantity(orderEntity.getOrderQuantity())
                    .orderPrice(orderEntity.getOrderPrice())
                    .email(orderEntity.getEmail())
                    .phoneNumber(orderEntity.getPhoneNumber())
                    .build();
            return new ResponseEntity<>(orderResponseModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(new RuntimeException("Nothing Found"), HttpStatus.NOT_FOUND);
    }
}
