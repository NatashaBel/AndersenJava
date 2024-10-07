package model.user;

import model.ticket.Ticket;

public class Admin extends User {

    public void checkTicket(Ticket ticket){
        if (ticket.getId() == null) {
            System.out.println("ID can not be null");
        } else {
            System.out.println("ID is valid");
        }
    }

    @Override
    public void printRole(){
        System.out.println("User Role: Admin");
    }
}
