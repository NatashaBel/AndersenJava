package org.example.model.user;

import jakarta.persistence.*;
import org.example.entity.BaseEntity;
import org.example.entity.Printable;
import org.example.model.UserStatus;
import org.example.model.ticket.Ticket;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user_data")
public class User extends BaseEntity implements Printable {
    @Column(name = "name", nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;
    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp creationDate;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    public User() {

    }

    public User(String name, UserStatus userStatus, Timestamp creationDate) {
        this.name = name;
        this.userStatus = userStatus;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
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
                "name='" + name + '\'' +
                ", userStatus=" + userStatus +
                ", creationDate=" + creationDate +
                ", tickets=" + tickets +
                ", id=" + id +
                '}';
    }
}