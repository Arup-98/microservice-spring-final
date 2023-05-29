package com.example.orderservice.controller;

import com.example.orderservice.config.FeignClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class BookController {
    Logger logger = Logger.getLogger("BookController");
    @Autowired
    FeignClientConfig feignClientConfig;

}
