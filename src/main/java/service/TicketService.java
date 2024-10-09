package service;

import entity.BaseEntity;
import entity.Printable;
import model.StadiumSector;
import model.ticket.BusTicket;
import model.ticket.CinemaTicket;
import model.ticket.Ticket;
import model.user.Admin;
import model.user.Client;
import model.user.User;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TicketService extends BaseEntity implements Printable {

    public static void main(String[] args) {

        BigDecimal price = new BigDecimal("60.55");
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket("Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.001, price);
        Ticket ticket3 = new Ticket("Theatre", (short) 124, new Timestamp(System.currentTimeMillis()));

        BaseEntity ticket = new Ticket();
        System.out.println(ticket.getId());
        ticket.setID("563gdj");

        Printable defaultPrint = new TicketService();
        defaultPrint.print();
        ticket1.print();
        Printable fromClient = new Client(new Ticket());
        fromClient.print();

        ticket1.setStadiumSector(StadiumSector.B);
        ticket2.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        ticket1.print();
        ticket2.print();

        Ticket busTicket1 = new BusTicket();
        BusTicket busTicket2 = new BusTicket();
        Ticket cinemaTicket1 = new CinemaTicket();
        CinemaTicket cinemaTicket2 = new CinemaTicket();

        busTicket1.share(); //call the parent method .share().
        busTicket2.share("+48 555 666 777"); //call the child method that overload the parent method
        cinemaTicket1.share(); //call the parent method .share()
        cinemaTicket2.share("+48 111 222 333", "testemail@gmail.com"); //call the child method that overload the parent method

        User user = new User();
        User clientUserAsUser = new Client(new Ticket());
        Client clientUser = new Client(new Ticket());
        User adminUserAsUser = new Admin();
        Admin adminUser = new Admin();

        user.printRole();
        clientUserAsUser.printRole();
        adminUserAsUser.printRole();
        System.out.println("Get Ticket:" + " " + clientUser.getTicket());
        adminUser.checkTicket(ticket2);

        ticket2.equals(ticket1);
        ticket2.hashCode();
    }
}