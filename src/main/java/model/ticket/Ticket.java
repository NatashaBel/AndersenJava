package model.ticket;

import entity.BaseEntity;
import entity.Printable;
import entity.Shareable;
import model.StadiumSector;
import model.TicketType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class Ticket extends BaseEntity implements Printable, Shareable {
    private String concertHall;
    private short eventCode;
    private Timestamp creationDate;
    private boolean isPromo;
    private StadiumSector stadiumSector;
    private float maxBackpackWeightInKg;
    private BigDecimal price;
    private UUID userId;
    private TicketType ticketType;

    public Ticket() {}

    public Ticket(String concertHall, short eventCode, Timestamp creationDate, boolean isPromo, StadiumSector stadiumSector, float maxBackpackWeightInKg, BigDecimal price) {
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.creationDate = creationDate;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeightInKg = maxBackpackWeightInKg;
        this.price = price;
    }

    public Ticket(String concertHall, short eventCode, Timestamp creationDate) {
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.creationDate = creationDate;
    }

    public Ticket(UUID userId, TicketType ticketType,Timestamp creationDate){
        this.userId = userId;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }

    public String getConcertHall() {
        return concertHall;
    }

    public short getEventCode() {
        return eventCode;
    }

    public Timestamp getCreationDate() {
        return creationDate;
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

    public UUID getUserId(){
        return userId;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;
        return eventCode == ticket.eventCode && isPromo == ticket.isPromo && Float.compare(maxBackpackWeightInKg, ticket.maxBackpackWeightInKg) == 0 && concertHall.equals(ticket.concertHall) && creationDate.equals(ticket.creationDate) && stadiumSector == ticket.stadiumSector && price.equals(ticket.price) && userId.equals(ticket.userId) && ticketType == ticket.ticketType;
    }

    @Override
    public int hashCode() {
        int result = concertHall.hashCode();
        result = 31 * result + eventCode;
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + Boolean.hashCode(isPromo);
        result = 31 * result + stadiumSector.hashCode();
        result = 31 * result + Float.hashCode(maxBackpackWeightInKg);
        result = 31 * result + price.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + ticketType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", creationDate=" + creationDate +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeightInKg=" + maxBackpackWeightInKg +
                ", price=" + price +
                ", userId=" + userId +
                ", ticketType=" + ticketType +
                ", ID=" + id +
                '}';
    }

    @Override
    public void print() {
        System.out.println("Class content: " + this);
    }

    @Override
    public void share() {
        System.out.println("Default share");
    }

    @Override
    public void share(String phone) {
        System.out.println("Default share by phone");
    }

    @Override
    public void share(String phone, String email) {
        System.out.println("Default share by phone and email");
    }
}