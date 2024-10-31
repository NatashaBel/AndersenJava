package org.example;

import org.example.dao.TicketDAO;
import org.example.dao.UserDAO;
import org.example.entity.BaseEntity;
import org.example.entity.Printable;
import org.example.model.TicketType;
import org.example.model.UserStatus;
import org.example.model.ticket.Ticket;
import org.example.model.user.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Timestamp;
import java.util.List;

@ComponentScan
public class TicketService extends BaseEntity implements Printable {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TicketService.class);
        TicketDAO ticketDao = applicationContext.getBean(TicketDAO.class);
        UserDAO userDao = applicationContext.getBean(UserDAO.class);

        User user = new User("Dasha", UserStatus.DEACTIVATED, new Timestamp(System.currentTimeMillis()));
        userDao.save(user);
        user = userDao.get(user.getId());
        System.out.println(user);

        user.setName("Lisa");

        userDao.updateUserStatusById(user);
        user = userDao.get(user.getId());
        System.out.println(user);


        Ticket ticket = new Ticket(user.getId(), TicketType.DAY, new Timestamp(System.currentTimeMillis()));
        user.setUserStatus(UserStatus.ACTIVATED);
        userDao.updateUserAndCreateTicket(user, ticket);

        ticketDao.save(ticket);

        System.out.println(ticketDao.getById(ticket.getId()));
        System.out.println(ticketDao.getByUserId(user.getId()));

        ticketDao.update(TicketType.MONTH, ticket.getId());

        List<Ticket> ticketCollection = ticketDao.getByUserId(user.getId());
        System.out.println(ticketCollection);
        if (!ticketCollection.isEmpty()) {
            for (Ticket ticketColl : ticketCollection) {
                ticketDao.delete(ticketColl.getId());
            }
        }
        userDao.delete(user.getId());
    }
}