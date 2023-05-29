package com.example.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestModel {
    private Integer bookId;
    private Integer bookQuantity;
    private Integer orderId;
    private Integer orderQuantity;
    private String orderPrice;
    private String email;
    private  String phoneNumber;

    private void setQuantity(Integer bookQuantity){
        this.bookQuantity=bookQuantity;
    }
    public Integer getQuantity() {
        return this.bookQuantity;
    }
}
