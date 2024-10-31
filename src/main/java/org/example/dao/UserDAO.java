package org.example.dao;

import org.example.model.ticket.Ticket;
import org.example.model.user.User;

import java.util.UUID;

public interface UserDAO {
    void save(User user);

    User get(UUID id);

    void updateUserStatusById(User user);

    void updateUserAndCreateTicket(User user, Ticket ticket);

    void delete(UUID id);
}