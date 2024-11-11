package org.example.service;

import jakarta.transaction.Transactional;
import java.util.UUID;
import org.example.model.UserStatus;
import org.example.model.ticket.Ticket;
import org.example.model.user.User;
import org.example.repository.TicketRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired private UserRepository userRepository;
  @Autowired private TicketRepository ticketRepository;

  @Value("${app.user-update-create-ticket.enabled}")
  private boolean userUpdateCreateTicketEnabled;

  @Transactional
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  public User getUserById(UUID id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
  }

  @Transactional
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

  @Transactional
  public void deleteUser(UUID id) {
    userRepository.deleteById(id);
  }
}
