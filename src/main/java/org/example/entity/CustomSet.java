package org.example.entity;

import org.example.model.ticket.Ticket;

public interface CustomSet {
    boolean put(Ticket ticket);

    boolean check(Ticket ticket);

    void iterate();

    void delete(Ticket ticket);

}
