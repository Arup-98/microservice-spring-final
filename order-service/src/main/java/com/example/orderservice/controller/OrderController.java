package com.example.orderservice.controller;

import com.example.orderservice.config.FeignClientConfig;
import com.example.orderservice.model.BookResponseModel;
import com.example.orderservice.model.OrderRequestModel;
import com.example.orderservice.model.OrderResponseModel;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final FeignClientConfig feignClientConfig;
    @PostMapping("/create")
    public ResponseEntity<Object> orderCreate(@RequestBody OrderRequestModel orderRequestModel){
       Integer bookId = orderRequestModel.getBookId();
       Integer bookQuantity= orderRequestModel.getQuantity();
       Integer orderQuantity=orderRequestModel.getOrderQuantity();


        ResponseEntity<BookResponseModel> bookResponse;
        BookResponseModel book;

        ResponseEntity<OrderResponseModel> orderResponse;
        OrderResponseModel order;

        try {
            bookResponse = feignClientConfig.bookDetails(bookId);


                book = bookResponse.getBody();


        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Book Id not match with Database!");
        }
        if (book.getQuantity() >= orderQuantity) {
            return orderService.createOrder(orderRequestModel);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient quantity for the requested book.");
        }

            //return orderService.createOrder(orderRequestModel);



    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllOrder(){
        return orderService.getAllOrder();
    }

    @DeleteMapping ("/delete/{id}")
    public void deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
    }

    @GetMapping("/id/{orderId}")
    public ResponseEntity<Object> bookDetails(@PathVariable Integer bookId) {
        return orderService.orderDetails(bookId);
    }


}
