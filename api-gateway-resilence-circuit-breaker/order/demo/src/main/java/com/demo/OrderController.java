package com.demo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {

    @PostMapping(path = "/order/{data}")
    public void saveUser(@PathVariable String data) {
        System.out.println("Inside Order 2 Service :::: " + data);
    }

    @GetMapping(path = "/order")
    @CircuitBreaker(name = "ORDER-SERVICE", fallbackMethod = "onGetAllOrderFallback")
    @Retry(name = "ORDER-SERVICE", fallbackMethod = "onGetAllOrderFallback")
    public Mono<String> getUsers() throws Exception{
        System.out.println("Get Orders");
        return Mono.just("All Orders !!");
    }

    public Mono<String> onGetAllOrderFallback(Exception e) {
        System.out.println("onGetAllOrderFallback");
        return Mono.just("onGetAllOrderFallback !!");
    }

}
