package org.example.dao;

import org.example.model.TicketType;
import org.example.model.ticket.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketDAO {
    void save(Ticket ticket);

    Ticket getById(UUID id);

    List<Ticket> getByUserId(UUID userId);

    void update(TicketType ticketType, UUID id);

    void delete(UUID id);
}