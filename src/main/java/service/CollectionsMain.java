package service;

import model.collection.list.CustomArrayList;
import model.collection.set.CustomHashSet;
import model.ticket.Ticket;

public class CollectionsMain {
    public static void main(String[] args) {
        CustomHashSet mySet = new CustomHashSet();
        Ticket mySetTicket = new Ticket();
        System.out.println(mySet.put(new Ticket()));
        mySet.iterate();
        mySet.delete(mySetTicket);
        System.out.println(mySet.check(mySetTicket));

        CustomArrayList customArrayList = new CustomArrayList();
        customArrayList.put(new Ticket());
        customArrayList.put(new Ticket());
        customArrayList.put(new Ticket());
        customArrayList.put(new Ticket());
        customArrayList.put(new Ticket());
        customArrayList.delete(0);
        customArrayList.delete(3);
        customArrayList.delete(5);
        for (int index = 0; index < customArrayList.getMyArray().length; index++) {
            System.out.println(customArrayList.getMyArray()[index]);
        }
        System.out.println(customArrayList.get(0));
    }
}
