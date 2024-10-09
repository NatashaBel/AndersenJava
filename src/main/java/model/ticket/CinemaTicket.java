package model.ticket;

public class CinemaTicket extends Ticket {

    @Override
    public void share(String phone, String email) {
        System.out.println("Send SMS and email:" + phone + "," + email);
    }
}