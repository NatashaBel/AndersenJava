package entity;

public interface Shareable {
    void share();

    void share(String phone);

    void share(String phone, String email);
}
