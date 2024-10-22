package service;

import dao.TicketDaoImpl;
import dao.UserDaoImpl;
import entity.BaseEntity;
import entity.Printable;
import model.TicketType;
import model.ticket.Ticket;
import model.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;

public class TicketService extends BaseEntity implements Printable {

    public static void main(String[] args) {

        TicketDaoImpl ticketDao = new TicketDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();

        User user = new User("Kira", new Timestamp(System.currentTimeMillis()));
        userDao.save(user);
        user = userDao.get(user.getId());
        System.out.println(user);

        user.setName("Lisa");

        userDao.update(user);
        user = userDao.get(user.getId());
        System.out.println(user);


        Ticket ticket = new Ticket(user.getId(), TicketType.DAY, new Timestamp(System.currentTimeMillis()));
        ticketDao.save(ticket);

        System.out.println(ticketDao.getById(ticket.getId()));
        System.out.println(ticketDao.getByUserId(user.getId()));

        System.out.println(ticketDao.update(TicketType.MONTH, ticket.getId()));

        ArrayList<Ticket> ticketCollection = ticketDao.getByUserId(user.getId());
        System.out.println(ticketCollection);
        if (!ticketCollection.isEmpty()) {
            for (Ticket ticketColl : ticketCollection) {
                ticketDao.delete(ticketColl.getId());
            }
        }
        System.out.println(userDao.delete(user.getId()));
    }
}