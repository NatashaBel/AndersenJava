package org.example.model.ticket;

public class OperaTicket extends Ticket {

    @Override
    public void share(String phone) {
        System.out.println("Send SMS to:" + phone);
    }
}