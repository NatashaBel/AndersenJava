package org.example.service;

import jakarta.transaction.Transactional;
import org.example.repository.TicketRepository;
import org.example.model.TicketType;
import org.example.model.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket getTicket(UUID id) {
        return ticketRepository.findById(id)
         .orElseThrow(() -> new IllegalArgumentException("Ticket not found with id: " + id));
    }

    public List<Ticket> getTicketByUserId(UUID userId) {
        return ticketRepository.findByUserId(userId);
    }

    @Transactional
    public void updateTicketType(UUID id, TicketType ticketType) {
        ticketRepository.updateTicketTypeById(ticketType, id);
    }

    @Transactional
    public void deleteTicket(UUID id) {
        ticketRepository.deleteById(id);
    }
}