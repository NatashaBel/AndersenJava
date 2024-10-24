package model.ticket;

import entity.BaseEntity;
import entity.Printable;
import entity.Shareable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import model.StadiumSector;
import model.TicketType;
import model.user.User;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ticket_data")

public class Ticket extends BaseEntity implements Printable, Shareable {
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type", nullable = false)
    private TicketType ticketType;
    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp creationDate;
    @Transient
    private String concertHall;
    @Transient
    private short eventCode;
    @Transient
    private boolean isPromo;
    @Transient
    private StadiumSector stadiumSector;
    @Transient
    private float maxBackpackWeightInKg;
    @Transient
    private BigDecimal price;

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

    public Ticket(User user, TicketType ticketType,Timestamp creationDate){
        this.user = user;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }

    public User getUser(){
        return user;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public String getConcertHall() {
        return concertHall;
    }

    public short getEventCode() {
        return eventCode;
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

    public void setUser(User user){
        this.user = user;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
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
        return eventCode == ticket.eventCode && isPromo == ticket.isPromo && Float.compare(maxBackpackWeightInKg, ticket.maxBackpackWeightInKg) == 0 && concertHall.equals(ticket.concertHall) && creationDate.equals(ticket.creationDate) && stadiumSector == ticket.stadiumSector && price.equals(ticket.price) && user.equals(ticket.user) && ticketType == ticket.ticketType;
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
        result = 31 * result + user.hashCode();
        result = 31 * result + ticketType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                ", ticketType=" + ticketType +
                ", creationDate=" + creationDate +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeightInKg=" + maxBackpackWeightInKg +
                ", price=" + price +
                ", id=" + id +
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