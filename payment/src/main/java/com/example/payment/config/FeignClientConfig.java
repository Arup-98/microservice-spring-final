package com.example.payment.config;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "order-service")
public interface FeignClientConfig {


}
