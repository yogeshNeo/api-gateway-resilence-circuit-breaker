package com.demo.controller;

import com.demo.model.Ticket;
import com.demo.repository.TicketRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TicketController {

    private final WebClient webClient;
    @Inject
    private TicketRepository ticketRepo;
    @Value("${order-service-uri}")
    private String orderServiceUri;

    @PostMapping(path = "/ticket")
    @CircuitBreaker(name = "userService", fallbackMethod = "onSaveTicketFallback")
    @Retry(name = "userService", fallbackMethod = "onSaveUserFallback")
    public Mono<Ticket> saveTicket(@RequestBody Ticket ticket) {
        log.info("Inside Service 1 Ticket :::: " + ticket);
        try {
            return ticketRepo.save(ticket);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error while saving ticket !!!");
            return Mono.just(Ticket.builder().build());
        }
    }

    public Mono<Ticket> onSaveTicketFallback() {
        log.info("Inside onSaveTicketFallback :::: ");
        return Mono.empty();
    }

    @GetMapping(path = "/ticket")
    @CircuitBreaker(name = "USER-SERVICE", fallbackMethod = "onGetAllTicketFallback")
    public Flux<String> getAllTickets() throws Exception{
        log.info("Inside getAllUsers()");
        return webClient.get().uri(orderServiceUri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(String.class)
                .doOnError(throwable -> log.error(throwable.getMessage()));
        //  return ticketRepo.findAll();
    }

    public Flux<String> onGetAllTicketFallback(Exception e) {
        log.info("Inside onGetAllTicketFallback :::: ");
        return Flux.just("<onGetAllTicketFallback>");
    }

}
