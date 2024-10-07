package model.ticket;

public class BusTicket extends Ticket{

    public void share(String phone){
        System.out.println("Send SMS to:" + phone);
    }
}
