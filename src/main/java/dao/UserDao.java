package dao;

import model.user.User;

import java.util.UUID;

public interface UserDao {
    boolean save(User user);

    User get(UUID id);

    boolean update(User user);

    boolean delete(UUID id);
}