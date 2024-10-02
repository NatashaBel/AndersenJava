import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TicketService {
    public static void main(String[] args) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        //ArrayList is a type of variable, <Ticket> is a type of collection elements, tickets - variable
        //create collection object and assigned into variable, tickets is store the link of new object& ArrayList is object of class.

        BigDecimal price = new BigDecimal("60.55");
        tickets.add(new Ticket("111A", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("112A", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("113A", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("120B", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("121B", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("122B", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("131C", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("132C", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("133C", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));
        tickets.add(new Ticket("134C", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.00, price));

        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            System.out.println(tickets.get(i));
        }

    }
}