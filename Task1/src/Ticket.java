import java.math.BigDecimal;

public class Ticket {
    private String ID;
    private String concertHall;
    private short eventCode;
    private long time;
    private boolean isPromo;
    private StadiumSector stadiumSector;
    private float maxBackpackWeightInKg;
    private BigDecimal price;

    public Ticket() {

    }

    public Ticket(String ID, String concertHall, short eventCode, long time, boolean isPromo, StadiumSector stadiumSector, float maxBackpackWeightInKg, BigDecimal price) {
        this.ID = ID;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeightInKg = maxBackpackWeightInKg;
        this.price = price;
    }

    public Ticket(String concertHall, short eventCode, long time) {
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
    }

}