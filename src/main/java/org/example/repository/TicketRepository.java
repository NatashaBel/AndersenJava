package org.example.repository;

import java.util.List;
import java.util.UUID;
import org.example.model.TicketType;
import org.example.model.ticket.Ticket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, UUID> {
  List<Ticket> findByUserId(UUID userId);

  @Modifying
  @Query("UPDATE Ticket t SET t.ticketType = :ticketType WHERE t.id = :id")
  int updateTicketTypeById(@Param("id") UUID id, @Param("ticketType") TicketType ticketType);
}
