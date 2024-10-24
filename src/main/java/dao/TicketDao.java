package dao;

import model.TicketType;
import model.ticket.Ticket;

import java.util.ArrayList;
import java.util.UUID;

public interface TicketDao {
    boolean save(Ticket ticket);

    Ticket getById(UUID id);

    ArrayList<Ticket> getByUserId(UUID userId);

    boolean update(TicketType ticketType, UUID id);

    boolean delete(UUID id);
}
