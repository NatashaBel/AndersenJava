import java.math.BigDecimal;
import java.sql.Timestamp;

public class Ticket {
    private String ID;
    private String concertHall;
    private short eventCode;
    private Timestamp timeStamp;
    private boolean isPromo;
    private StadiumSector stadiumSector;
    private float maxBackpackWeightInKg;
    private BigDecimal price;

    public Ticket() {

    }

    public Ticket(String ID, String concertHall, short eventCode, Timestamp timeStamp, boolean isPromo, StadiumSector stadiumSector, float maxBackpackWeightInKg, BigDecimal price) {
        setId(ID);
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.timeStamp = timeStamp;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeightInKg = maxBackpackWeightInKg;
        this.price = price;
    }

    public Ticket(String concertHall, short eventCode, Timestamp timeStamp) {
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.timeStamp = timeStamp;
    }

    public String getID() {
        return ID;
    }

    public String getConcertHall() {
        return concertHall;
    }

    public short getEventCode() {
        return eventCode;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public boolean getIsPromo() {
        return isPromo;
    }

    public StadiumSector getStadiumSector() {
        return stadiumSector;
    }

    public float getMaxBackpackWeightInKg() {
        return maxBackpackWeightInKg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    // Instead of this.ID = ID; we added ID validation
    private void setId(String ID) {
        if (ID == null || ID.isEmpty()) {
            throw new IllegalArgumentException("ID can not be null or empty");
        }
        if (ID.length() > 4) {
            throw new IllegalArgumentException("ID length can not be more then 4 digits or chars");
        }
        if (!ID.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("ID should contain digits or chars");
        }
        this.ID = ID;
    }

    // Instead of this.concertHall = concertHall; we added concertHall validation
    private void setConcertHall(String concertHall) {
        if (concertHall == null || concertHall.isEmpty()) {
            throw new IllegalArgumentException("ID can not be null or empty");
        }
        if (concertHall.length() > 10) {
            throw new IllegalArgumentException("ID length can not be more then 10 chars");
        }
        if (!concertHall.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("ID should contain chars");
        }
        this.concertHall = concertHall;
    }

    // Instead of this.eventCode = eventCode; we added eventCode validation
    private void setEventCode(short eventCode) {
        if (eventCode == 0) {
            throw new IllegalArgumentException("ID can not be 0");
        }
        if (eventCode > 999) {
            throw new IllegalArgumentException("ID length can not be more then 3 digits");
        }
        this.eventCode = eventCode;
    }

    @Override
    public String toString() {
        return "ID='" + ID + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", timeStamp=" + timeStamp +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeightInKg=" + maxBackpackWeightInKg +
                ", price=" + price +
                '}';
    }
}