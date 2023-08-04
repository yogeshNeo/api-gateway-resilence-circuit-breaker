package com.demo.controller;

import com.demo.model.Ticket;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.resilience4j.retry.annotation.Retry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class UserController {

    @PostMapping(path = "/user/{data}")
    @CircuitBreaker(name = "userService", fallbackMethod = "onSaveUserFallback")
    @Retry(name = "USER-SERVICE", fallbackMethod = "onSaveUserFallback")
    public Mono<String> saveUser(@PathVariable String data) {
        log.info("Inside Service 1 User :::: " + data);
        return Mono.just("User saved successfully ..");
    }

    public Mono<String> onSaveUserFallback() {
        log.info("Inside onSaveUserFallback :::: ");
        return Mono.just("User fallback method called !!");
    }

    @GetMapping(path = "/user")
    public Mono<String> getAllUsers() {
        log.info("Inside getAllUsers()");
        return Mono.just("Get All Users saved successfully ..");
    }

}
