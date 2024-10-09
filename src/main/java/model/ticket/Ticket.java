package model.ticket;

import entity.BaseEntity;
import entity.Printable;
import entity.Shareable;
import model.StadiumSector;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Ticket extends BaseEntity implements Printable, Shareable {
    private String concertHall;
    private short eventCode;
    private Timestamp timeStamp;
    private boolean isPromo;
    private StadiumSector stadiumSector;
    private float maxBackpackWeightInKg;
    private BigDecimal price;

    public Ticket() {
    }

    public Ticket(String concertHall, short eventCode, Timestamp timeStamp, boolean isPromo, StadiumSector stadiumSector, float maxBackpackWeightInKg, BigDecimal price) {
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

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setStadiumSector(StadiumSector stadiumSector) {
        this.stadiumSector = stadiumSector;
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
    public boolean equals(Object o) {
        if (this == o) return true; //compare with itself
        if (o == null || getClass() != o.getClass()) //compare that input object is not null and that class in o and this.class are the same
            return false;
        Ticket ticket = (Ticket) o; //convert o to ticket class Ticket
        return eventCode == ticket.eventCode && isPromo == ticket.isPromo && Float.compare(maxBackpackWeightInKg, ticket.maxBackpackWeightInKg) == 0 && ID.equals(ticket.ID) && concertHall.equals(ticket.concertHall) && timeStamp.equals(ticket.timeStamp) && stadiumSector == ticket.stadiumSector && price.equals(ticket.price);
    }

    @Override
    public int hashCode() {
        int result = ID == null ? 0 : ID.hashCode();
        result = 31 * result + concertHall.hashCode();
        result = 31 * result + eventCode;
        result = 31 * result + timeStamp.hashCode();
        result = 31 * result + Boolean.hashCode(isPromo);
        result = 31 * result + stadiumSector.hashCode();
        result = 31 * result + Float.hashCode(maxBackpackWeightInKg);
        result = 31 * result + price.hashCode();
        return result;
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

    @Override
    public void print() {
        System.out.println("Class content: " + this);
    }

    public void share() {
        System.out.println("Default share");
    }

    public void share(String phone) {
        System.out.println("Default share by phone");
    }

    public void share(String phone, String email) {
        System.out.println("Default share by phone and email");
    }
}




