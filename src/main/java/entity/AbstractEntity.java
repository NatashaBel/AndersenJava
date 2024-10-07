package entity;

import java.util.UUID;

public abstract class AbstractEntity {
    protected String ID;

    public AbstractEntity(){
        ID = UUID.randomUUID().toString();
    }

    public String getId(){
        return ID;

    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
