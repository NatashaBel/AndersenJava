package model.user;

import entity.BaseEntity;
import entity.Printable;

import java.sql.Timestamp;

public class User extends BaseEntity implements Printable {
    private String name;
    private Timestamp creationDate;

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

    public void setName(String name){
        this.name = name;
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