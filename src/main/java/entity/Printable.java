package entity;

public interface Printable {

    default void print(){
        System.out.println("print content in console");
    }
}
