import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TicketService {

    public static void main(String[] args) {
        ArrayList<Ticket> tickets = new ArrayList<>();

        BigDecimal price = new BigDecimal("60.55");
        tickets.add(new Ticket("111A", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("112A", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("113A", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("120B", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.B, (float) 5.00, price));
        tickets.add(new Ticket("121B", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.B, (float) 5.00, price));
        tickets.add(new Ticket("122B", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.B, (float) 5.00, price));
        tickets.add(new Ticket("131C", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.C, (float) 5.00, price));
        tickets.add(new Ticket("132C", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.C, (float) 5.00, price));
        tickets.add(new Ticket("133C", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.C, (float) 5.00, price));
        tickets.add(new Ticket("134C", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.C, (float) 5.00, price));


        ArrayList<Ticket> sectorTickets = findByStadiumSector(tickets, StadiumSector.A);
        for (Ticket ticket : sectorTickets) {
            System.out.println(ticket);
        }
    }

    public static ArrayList<Ticket> findByStadiumSector(ArrayList<Ticket> tickets, StadiumSector sector) {
        ArrayList<Ticket> sectorTickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getStadiumSector().equals(sector)) {
                sectorTickets.add(ticket);
            }
        }
        return sectorTickets;
    }
}