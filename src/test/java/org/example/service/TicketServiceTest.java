package org.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.example.model.TicketType;
import org.example.model.UserStatus;
import org.example.model.ticket.Ticket;
import org.example.model.user.User;
import org.example.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

  @Mock private TicketRepository ticketRepository;
  @InjectMocks private TicketService ticketService;

  private User user;
  private Ticket testTicket;

  @BeforeEach
  void init() {
    user = new User("Masha", UserStatus.DEACTIVATED, new Timestamp(System.currentTimeMillis()));
    testTicket = new Ticket(user, TicketType.DAY, new Timestamp(System.currentTimeMillis()));
  }

  @Test
  void saveTicket_withTicket() {
    UUID expectedId = UUID.randomUUID();
    testTicket.setId(expectedId);

    when(ticketRepository.save(testTicket)).thenReturn(testTicket);

    Ticket actualTicket = ticketService.saveTicket(testTicket);

    assertNotNull(actualTicket);
    assertEquals(expectedId, actualTicket.getId());
    verify(ticketRepository, times(1)).save(testTicket);
  }

  @Test
  void saveTicket_withNull() {
    doThrow(new IllegalArgumentException()).when(ticketRepository).save(null);

    assertThrows(IllegalArgumentException.class, () -> ticketService.saveTicket(null));
    verify(ticketRepository, times(1)).save(null);
  }

  @Test
  void saveTicket_withException_duringTransaction() {
    doThrow(new OptimisticLockingFailureException("Save operation failed"))
        .when(ticketRepository)
        .save(testTicket);

    assertThrows(
        OptimisticLockingFailureException.class, () -> ticketService.saveTicket(testTicket));
    verify(ticketRepository, times(1)).save(testTicket);
  }

  @Test
  void getTicketById_withTicket() {
    UUID expectedId = UUID.randomUUID();

    when(ticketRepository.findById(expectedId)).thenReturn(Optional.ofNullable(testTicket));

    Ticket actualResult = ticketService.getTicket(expectedId);

    assertEquals(testTicket, actualResult);
    verify(ticketRepository, times(1)).findById(expectedId);
  }

  @Test
  void getTicketById_withNull() {
    doThrow(new IllegalArgumentException()).when(ticketRepository).findById(null);

    assertThrows(IllegalArgumentException.class, () -> ticketService.getTicket(null));
    verify(ticketRepository, times(1)).findById(null);
  }

  @Test
  void getTicketById_withException_duringTransaction() {
    testTicket.setId(UUID.randomUUID());
    doThrow(new OptimisticLockingFailureException("Id is invalid"))
        .when(ticketRepository)
        .findById(testTicket.getId());

    assertThrows(
        OptimisticLockingFailureException.class, () -> ticketService.getTicket(testTicket.getId()));
    verify(ticketRepository, times(1)).findById(testTicket.getId());
  }

  @Test
  void getTicketByUserID_withTicket() {
    user.setId(UUID.randomUUID());
    List<Ticket> expectedTickets = new ArrayList<>();
    expectedTickets.add(
        new Ticket(user, TicketType.DAY, new Timestamp(System.currentTimeMillis())));
    expectedTickets.add(
        new Ticket(user, TicketType.YEAR, new Timestamp(System.currentTimeMillis())));

    when(ticketRepository.findByUserId(user.getId())).thenReturn(expectedTickets);

    List<Ticket> actualResultTicketList = ticketService.getTicketByUserId(user.getId());

    assertEquals(expectedTickets, actualResultTicketList, "The tickets should match the expected list");
    verify(ticketRepository, times(1)).findByUserId(user.getId());
  }

  @Test
  void getTicketByUserId_withNoTickets() {
    user.setId(UUID.randomUUID());

    when(ticketRepository.findByUserId(user.getId())).thenReturn(new ArrayList<>());

    List<Ticket> actualResultTicketList = ticketService.getTicketByUserId(user.getId());

    assertTrue(actualResultTicketList.isEmpty(), "Expected an empty list when no tickets are found");
    verify(ticketRepository, times(1)).findByUserId(user.getId());
  }

  @Test
  void getTicketByUserId_withException_duringTransaction() {
    user.setId(UUID.randomUUID());
    doThrow(new OptimisticLockingFailureException("Id is invalid"))
        .when(ticketRepository)
        .findByUserId(user.getId());

    assertThrows(
        OptimisticLockingFailureException.class,
        () -> ticketService.getTicketByUserId(user.getId()));
    verify(ticketRepository, times(1)).findByUserId(user.getId());
  }

  @Test
  void updateTicketTypeById_withTicket() {
    testTicket.setId(UUID.randomUUID());
    testTicket.setTicketType(TicketType.WEEK);

    when(ticketRepository.updateTicketTypeById(testTicket.getId(), testTicket.getTicketType()))
        .thenReturn(1);

    int actualResult = ticketService.updateTicketType(testTicket.getId(), testTicket.getTicketType());

    assertEquals(1, actualResult, "The tickets should match the expected list");
    verify(ticketRepository, times(1))
        .updateTicketTypeById(testTicket.getId(), testTicket.getTicketType());
  }

  @Test
  void updateTicketTypeById_withNoTickets() {
    testTicket.setId(UUID.randomUUID());
    testTicket.setTicketType(TicketType.WEEK);

    doThrow(new OptimisticLockingFailureException("Update ticket exception"))
        .when(ticketRepository)
        .updateTicketTypeById(testTicket.getId(), testTicket.getTicketType());

    assertThrows(
        OptimisticLockingFailureException.class,
        () -> ticketService.updateTicketType(testTicket.getId(), testTicket.getTicketType()));
    verify(ticketRepository, times(1))
        .updateTicketTypeById(testTicket.getId(), testTicket.getTicketType());
  }

  @Test
  void updateTicketTypeById_withException_duringTransaction() {
    testTicket.setId(UUID.randomUUID());
    testTicket.setTicketType(TicketType.WEEK);

    when(ticketRepository.updateTicketTypeById(testTicket.getId(), testTicket.getTicketType()))
        .thenReturn(0);

    int actualResult = ticketService.updateTicketType(testTicket.getId(), testTicket.getTicketType());

    assertEquals(0, actualResult, "Expected not updated ticket type when no tickets are found");
    verify(ticketRepository, times(1))
        .updateTicketTypeById(testTicket.getId(), testTicket.getTicketType());
  }

  @Test
  void deleteTicket_withTicket() {
    testTicket.setId(UUID.randomUUID());

    doNothing().when(ticketRepository).deleteById(testTicket.getId());

    ticketService.deleteTicket(testTicket.getId());

    verify(ticketRepository, times(1)).deleteById(testTicket.getId());
  }

  @Test
  void deleteTicket_withNoTicket() {
    doThrow(new IllegalArgumentException()).when(ticketRepository).deleteById(null);

    assertThrows(IllegalArgumentException.class, () -> ticketService.deleteTicket(null));
    verify(ticketRepository, times(1)).deleteById(null);
  }

  @Test
  void deleteTicket_withException_duringTransaction() {
    testTicket.setId(UUID.randomUUID());

    doThrow(new OptimisticLockingFailureException("Delete operation failed"))
        .when(ticketRepository)
        .deleteById(testTicket.getId());

    assertThrows(
        OptimisticLockingFailureException.class,
        () -> ticketService.deleteTicket(testTicket.getId()));
    verify(ticketRepository, times(1)).deleteById(testTicket.getId());
  }
}
