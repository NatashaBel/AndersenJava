package org.example.service;

import org.example.repository.TicketRepository;
import org.example.model.TicketType;
import org.example.model.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> getTicket(UUID id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getTicketByUserId(UUID userId) {
        return ticketRepository.findByUserId(userId);
    }

    public void updateTicketType(UUID id, TicketType ticketType) {
        ticketRepository.updateTicketTypeById(ticketType, id);
    }

    public void deleteTicket(UUID id) {
        ticketRepository.deleteById(id);
    }
}