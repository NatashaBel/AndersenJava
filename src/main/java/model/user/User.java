package model.user;

import entity.BaseEntity;
import entity.Printable;

import java.sql.Timestamp;
import java.util.UUID;

public class User extends BaseEntity implements Printable {
    private String name;
    private Timestamp creationDate;

    public User() {

    }

    public User(UUID id, String name, Timestamp creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreationDate() {
        return creationDate;
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
                "columnId='" + id + '\'' +
                ", columnName='" + name + '\'' +
                ", columnCreationDate=" + creationDate +
                '}';
    }
}