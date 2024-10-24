package dao;

import model.ticket.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketDao {
    void save(Ticket ticket);

    Ticket getById(UUID id);

    List<Ticket> getByUserId(UUID userId);

    void update(Ticket ticket, UUID id);

    void delete(Ticket ticket);
}
