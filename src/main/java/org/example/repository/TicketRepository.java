package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.model.TicketType;
import org.example.model.ticket.Ticket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, UUID> {
    //    @Query("SELECT t FROM Ticket t WHERE t.user =:userId")
    List<Ticket> findByUserId(UUID userId); // Custom method to find tickets by user ID

    @Modifying
    @Transactional
    @Query("UPDATE Ticket t SET t.ticketType = :ticketType WHERE t.id = :id")
    void updateTicketTypeById(@Param("ticketType") TicketType ticketType, @Param("id") UUID id); // Custom query method to find tickets by type
}