package com.example.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseModel {
    private Integer bookId;

    private Integer orderId;
    private Integer orderQuantity;
    private String orderPrice;
    private String email;
    private  String phoneNumber;

}
