package model.user;

import entity.BaseEntity;
import entity.Printable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import model.ticket.Ticket;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user_data")
public class User extends BaseEntity implements Printable {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp creationDate;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    public User() {

    }

    public User(String name, Timestamp creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void printRole() {
        System.out.println("User Role: User");
    }

    @Override
    public void print() {
        System.out.println("Class content: " + this);
    }

    @Override
    public String toString() {
        return "User{" +
                "tickets=" + tickets +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", id=" + id +
                '}';
    }
}