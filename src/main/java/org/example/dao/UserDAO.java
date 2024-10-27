package org.example.dao;

import org.example.model.user.User;

import java.util.UUID;

public interface UserDAO {
    boolean save(User user);

    User get(UUID id);

    boolean update(User user);

    boolean delete(UUID id);
}