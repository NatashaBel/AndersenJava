package org.example.model.collection.set;

import org.example.entity.CustomSet;
import org.example.model.ticket.Ticket;

import java.util.HashMap;

public class CustomHashSet implements CustomSet {
    private static final Object PRESENT = new Object();
    HashMap<Ticket, Object> keyCollection;

    public CustomHashSet() {
        keyCollection = new HashMap<>();
    }

    @Override
    public boolean put(Ticket ticket) {
        return keyCollection.put(ticket, PRESENT) == null;
    }

    @Override
    public boolean check(Ticket ticket) {
        for (Ticket keyCheck : keyCollection.keySet()) {
            if (keyCheck.equals(ticket)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void iterate() {
        for (Ticket ticket : keyCollection.keySet()) {
            System.out.println(ticket);
        }
    }

    @Override
    public void delete(Ticket ticket) {
        if (check(ticket)) {
            keyCollection.remove(ticket);
        }
    }
}