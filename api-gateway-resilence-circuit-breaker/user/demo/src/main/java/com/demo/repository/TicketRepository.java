package com.demo.repository;

import com.demo.model.Ticket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends ReactiveMongoRepository<Ticket, String> {
}