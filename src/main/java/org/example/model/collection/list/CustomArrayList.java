package org.example.model.collection.list;

import org.example.entity.CustomList;
import org.example.model.ticket.Ticket;

import java.util.Arrays;

public class CustomArrayList implements CustomList {
    private Ticket[] myArray;
    private int index = 0;

    public CustomArrayList() {
        myArray = new Ticket[5];
    }

    @Override
    public void put(Ticket ticket) {
        if (index >= myArray.length) {
            myArray = Arrays.copyOf(myArray, myArray.length + 1);
        }
        myArray[index] = ticket;
        index++;
    }

    @Override
    public Ticket get(int index) {
        if (index > myArray.length) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + myArray.length);
        } else {
            return myArray[index];
        }
    }

    @Override
    public boolean delete(int index) {
        if (index >= 0 && index <= myArray.length - 1) {
            for (int i = index; i < myArray.length - 1; i++) {
                myArray[i] = myArray[i + 1];
            }
            myArray[this.index - 1] = null;
            this.index--;
            return true;
        }
        return false;
    }

    public Ticket[] getMyArray() {
        return myArray;
    }
}