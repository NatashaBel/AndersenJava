package org.example.dao;

import org.example.model.TicketType;
import org.example.model.ticket.Ticket;

import java.util.ArrayList;
import java.util.UUID;

public interface TicketDAO {
    boolean save(Ticket ticket);

    Ticket getById(UUID id);

    ArrayList<Ticket> getByUserId(UUID userId);

    boolean update(TicketType ticketType, UUID id);

    boolean delete(UUID id);
}
