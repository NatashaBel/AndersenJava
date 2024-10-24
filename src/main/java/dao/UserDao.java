package dao;


import model.user.User;

import java.util.UUID;

public interface UserDao {
    void save(User user);

    User get(UUID id);

    void update(User user);

    void updateUserAndTickets(User user);

    void delete(User user);
}