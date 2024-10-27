package org.example.entity;

import org.example.model.ticket.Ticket;

public interface CustomList {
    void put(Ticket ticket);

    Ticket get(int index);

    boolean delete(int index);
}
