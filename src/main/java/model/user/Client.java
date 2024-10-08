package model.user;

import model.ticket.Ticket;

public class Client extends User {
    Ticket ticket;

    public Client(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public void printRole() {
        System.out.println("User Role: Client");
    }

}