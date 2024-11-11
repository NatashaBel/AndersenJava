package org.example.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.example.model.TicketType;
import org.example.model.ticket.Ticket;
import org.example.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
  private TicketRepository ticketRepository;

  public TicketService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  @Transactional
  public Ticket saveTicket(Ticket ticket) {
    return ticketRepository.save(ticket);
  }

  public Ticket getTicket(UUID id) {
    return ticketRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Ticket not found with id: " + id));
  }

  public List<Ticket> getTicketByUserId(UUID userId) {
    return ticketRepository.findByUserId(userId);
  }

  @Transactional
  public int updateTicketType(UUID id, TicketType ticketType) {
    return ticketRepository.updateTicketTypeById(id, ticketType);
  }

  @Transactional
  public void deleteTicket(UUID id) {
    ticketRepository.deleteById(id);
  }
}
