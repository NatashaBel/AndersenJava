package model.user;

import entity.BaseEntity;
import entity.Printable;

public class User extends BaseEntity implements Printable {

    @Override
    public void print() {
        System.out.println("Class content: " + this);
    }

    public void printRole() {
        System.out.println("User Role: User");
    }
}
