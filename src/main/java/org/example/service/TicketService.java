package org.example.service;

import org.example.dao.TicketDAO;
import org.example.dao.UserDAO;
import org.example.model.ticket.Ticket;
import org.example.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {
    @Value("${app.user-update-create-ticket.enabled}")
    private boolean userUpdateCreateEnabled;

    private TicketDAO ticketDAO;
    private UserDAO userDAO;

    public TicketService(TicketDAO ticketDAO, UserDAO userDAO){
        this.ticketDAO = ticketDAO;
        this.userDAO = userDAO;
    }

    @Transactional
    public void updateUserAndCreateTicket(User user, Ticket ticket) {
        if (!userUpdateCreateEnabled) {
            throw new UnsupportedOperationException("Creating a ticket is disabled.");
        }
        userDAO.updateUserStatusById(user);
        ticketDAO.save(ticket);
    }
}