package org.example.service;

import jakarta.transaction.Transactional;
import org.example.repository.TicketRepository;
import org.example.repository.UserRepository;
import org.example.model.UserStatus;
import org.example.model.ticket.Ticket;
import org.example.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Value("${app.user-update-create-ticket.enabled}")
    private boolean userUpdateCreateTicketEnabled;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public void updateUserStatusById(UUID id, UserStatus userStatus) {
        userRepository.updateUserStatusById(userStatus, id);
    }

    @Transactional
    public void updateUserAndCreateTicket(User user, Ticket ticket) {
        if (!userUpdateCreateTicketEnabled) {
            throw new UnsupportedOperationException("Creating a ticket is disabled.");
        }
        userRepository.updateUserStatusById(user.getUserStatus(), user.getId());
        ticket.setUser(user);
        ticketRepository.save(ticket);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}