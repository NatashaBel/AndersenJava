package service;

import entity.AbstractEntity;
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

public class TicketService extends AbstractEntity implements Printable{

    public static void main(String[] args) {

        BigDecimal price = new BigDecimal("60.55");
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket("Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.001, price);
        Ticket ticket3 = new Ticket("Theatre", (short) 124, new Timestamp(System.currentTimeMillis()));

        // TASK1 GET/SET ID for every class
        AbstractEntity ticket = new Ticket();
        System.out.println(ticket.getId());
        ticket.setID("563gdj");

        //TASK2 There should be a possibility to add an ability to print class content for every class.
        //Also, there should be a possibility to override this function as well as use the default implementation,
        //which is "print content in console".
        Printable defaultPrint = new TicketService();
        defaultPrint.print();
        ticket1.print();
        Printable fromClient = new Client(new Ticket());
        fromClient.print();
        //    @Override
        //    public void print() {
        //        System.out.println("Class content:" + " " + toString());
        //    }

        //TASK3 For your Ticket class from the previous lesson, there should be a possibility to change only "time"
        // and "stadium sector" fields. Also, there should be a possibility to get all Ticket values.
        ticket1.setStadiumSector(StadiumSector.B);
        ticket2.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        ticket1.print();
        ticket2.print();

        //TASK4 A Ticket should be able to be shared by phone and by phone and email. Think about the type of
        // polymorphism that you will use here. In the main method show the example of the chosen polymorphism approach.
        Ticket busTicket1 = new BusTicket();
        BusTicket busTicket2 = new BusTicket();
        Ticket cinemaTicket1 = new CinemaTicket();
        CinemaTicket cinemaTicket2 = new CinemaTicket();
        // Static Polymorphism
        busTicket1.share(); //call the parent method .share().
        busTicket2.share("+48 555 666 777"); //call the child method that overload the parent method
        cinemaTicket1.share(); //call the parent method .share()
        cinemaTicket2.share("+48 111 222 333", "testemail@gmail.com"); //call the child method that overload the parent method

        //TASK5 In your application, there should be 2 Users - Client and Admin, both should be able to printRole() and
        // also have unique functions - getTicket() for User and checkTicket() - for Admin. Think about inheritance and
        // the type of polymorphism that you will use here. In the main() method show the example of the chosen polymorphism approach.
        User user = new User();
        User clientUserAsUser = new Client(new Ticket());
        Client clientUser = new Client(new Ticket());
        User adminUserAsUser = new Admin();
        Admin adminUser = new Admin();
        //Dynamic Polymorphism
        user.printRole();
        clientUserAsUser.printRole();
        adminUserAsUser.printRole();
        System.out.println("Get Ticket:" + " " + clientUser.getTicket());
        adminUser.checkTicket(ticket2);

        //Task6 Override equals, toString() and hashCode in your Ticket class, so they will be able to return meaningful
        // values that follow equals & hashCode contract.
        ticket2.equals(ticket1);
        ticket2.hashCode();
    }
}