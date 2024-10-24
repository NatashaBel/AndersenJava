package service;

import dao.TicketDaoImpl;
import dao.UserDaoImpl;
import entity.BaseEntity;
import entity.Printable;
import model.TicketType;
import model.ticket.Ticket;
import model.user.User;

import java.sql.Timestamp;

public class TicketService extends BaseEntity implements Printable {

    public static void main(String[] args) {

        TicketDaoImpl ticketDao = new TicketDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();


        User user = new User("Vera", new Timestamp(System.currentTimeMillis()));
        userDao.save(user);
        user = userDao.get(user.getId());

        System.out.println(user);

        user.setName("Vlad");

        userDao.update(user);
        user = userDao.get(user.getId());
        System.out.println(user);

        Ticket ticket = new Ticket(user, TicketType.MONTH, new Timestamp(System.currentTimeMillis()));
        ticketDao.save(ticket);
        Ticket ticket1 = new Ticket(user, TicketType.DAY, new Timestamp(System.currentTimeMillis()));
        ticketDao.save(ticket1);
        Ticket ticket2 = new Ticket(user, TicketType.YEAR, new Timestamp(System.currentTimeMillis()));
        ticketDao.save(ticket2);

        user = userDao.get(user.getId());
        System.out.println(user);

        System.out.println(ticketDao.getById(ticket.getId()));
        System.out.println(ticketDao.getByUserId(user.getId()));
        ticket.setTicketType(TicketType.YEAR);

        ticketDao.update(ticket, ticket.getId());
        userDao.updateUserAndTickets(user);
        userDao.delete(user);
    }
}