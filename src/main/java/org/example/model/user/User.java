package org.example.model.user;

import org.example.entity.BaseEntity;
import org.example.entity.Printable;
import org.example.model.UserStatus;

import java.sql.Timestamp;

public class User extends BaseEntity implements Printable {
    private String name;
    private UserStatus userStatus;
    private Timestamp creationDate;

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
                "id=" + id +
                ", name='" + name + '\'' +
                ", userStatus=" + userStatus +
                ", creationDate=" + creationDate +
                '}';
    }
}