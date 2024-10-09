package entity;

import java.util.UUID;

public abstract class BaseEntity {
    protected String ID;

    public BaseEntity() {
        ID = UUID.randomUUID().toString();
    }

    public String getId() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
