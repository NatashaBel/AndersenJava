package service;

import dao.TicketDaoImpl;
import dao.UserDaoImpl;
import entity.BaseEntity;
import entity.Printable;
import model.TicketType;
import model.ticket.Ticket;
import model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class TicketService extends BaseEntity implements Printable {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String jdbcUrl = "jdbc:postgresql://localhost:5432/my_ticket_service_db";
        String username = "postgres";
        String password = "password";
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

        TicketDaoImpl ticketDao = new TicketDaoImpl(connection);
        UserDaoImpl userDao = new UserDaoImpl(connection);

        Ticket ticket = new Ticket(UUID.fromString("0234dab7-b186-472c-8d07-420223ed9557"),
                UUID.fromString("cb1565a0-68e2-48b6-a7d6-019d4935db43"), TicketType.DAY,
                new Timestamp(System.currentTimeMillis()));
        ticketDao.save(ticket);

        System.out.println(ticketDao.getById(UUID.fromString("0234dab7-b186-472c-8d07-420223ed9557")));
        System.out.println(ticketDao.getByUserId(UUID.fromString("cb1565a0-68e2-48b6-a7d6-019d4935db43")));

        System.out.println(ticketDao.update(TicketType.MONTH, UUID.fromString("1974db25-fc0e-4322-9a01-c98eabc49a28")));
        UUID userUuid = UUID.fromString("4742dab7-b186-472c-8d07-420223ed9551");
        ArrayList<Ticket> ticketCollection = ticketDao.getByUserId(userUuid);
        System.out.println(ticketCollection);
        if (!ticketCollection.isEmpty()){
            for (Ticket ticketColl : ticketCollection){
                ticketDao.delete(ticketColl.getId());
            }
        }

        User user = new User(UUID.fromString("8742dab7-b456-472c-8d07-420223ed9551"), "Kitt", new Timestamp(System.currentTimeMillis()));
        userDao.save(user);

        User user1 = userDao.get(UUID.fromString("4742dab7-b186-472c-8d07-420223ed9551"));
        System.out.println(user1);

        User user2 = new User(UUID.fromString("cb1565a0-68e2-48b6-a7d6-019d4935db43"),"Nima", new Timestamp(System.currentTimeMillis()));
        userDao.update(user2);
        System.out.println(user2);

        System.out.println(userDao.delete(UUID.fromString("8742dab7-b456-472c-8d07-420223ed9551")));

        connection.close();
    }
}