import java.math.BigDecimal;
import java.sql.Timestamp;

public class TicketService {

    public static void main(String[] args) {

        BigDecimal price = new BigDecimal("60.55");
        Ticket Ticket1 = new Ticket();
        Ticket Ticket2 = new Ticket("123A", "Opera", (short) 123, new Timestamp(System.currentTimeMillis()), false, StadiumSector.A, (float) 5.001, price);
        Ticket Ticket3 = new Ticket("Theatre", (short) 124, new Timestamp(System.currentTimeMillis()));
    }

}