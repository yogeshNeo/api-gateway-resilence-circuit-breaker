package com.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/userServiceFallback")
    public String userServiceFallback() {
        return "user service is down at this time !!";
    }

    @GetMapping("/orderServiceFallback")
    public String contactServiceFallback() {
        return "order service is down at this time !!";
    }

}