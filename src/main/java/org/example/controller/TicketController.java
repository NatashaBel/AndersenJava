package org.example.controller;

import org.example.model.TicketType;
import org.example.model.ticket.Ticket;
import org.example.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private String thisIsMyFirstConditionalBean;

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable UUID id) {
        return ticketService.getTicket(id);
    }

    @GetMapping("/users/{userId}")
    public List<Ticket> getTicketsByUserId(@PathVariable UUID userId) {
        return ticketService.getTicketByUserId(userId);
    }

    @PutMapping("/{id}/update-type")
    public void updateTicketType(@PathVariable UUID id, @RequestBody TicketType ticketType) {
        ticketService.updateTicketType(id, ticketType);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable UUID id) {
        ticketService.deleteTicket(id);
    }
}