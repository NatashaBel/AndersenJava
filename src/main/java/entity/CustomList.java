package entity;

import model.ticket.Ticket;

public interface CustomList {
    void put(Ticket ticket);

    Ticket get(int index);

    boolean delete(int index);
}
