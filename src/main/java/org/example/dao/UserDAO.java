package org.example.dao;

import org.example.model.user.User;

import java.util.UUID;

public interface UserDAO {
    void save(User user);

    User get(UUID id);

    void updateUserStatusById(User user);

    void delete(UUID id);
}