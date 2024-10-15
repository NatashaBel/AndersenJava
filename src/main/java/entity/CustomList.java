package entity;

import model.ticket.Ticket;

public interface CustomList {
    void put(Ticket ticket);

    Ticket get(int index);

    void delete(int index);
}
