package org.example.dao;

import org.example.model.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void save(User user) {
        String sqlCommand = "INSERT INTO user_data (id, name, user_status, creation_date)" +
                "VALUES (?, ?, ?::user_status, ?)";
        jdbcTemplate.update(sqlCommand, user.getId(), user.getName(), user.getUserStatus().name(),
                user.getCreationDate());
    }

    @Override
    @Transactional
    public User get(UUID id) {
        String sqlCommand = "SELECT * FROM user_data WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlCommand, new UserRowMapper(), id);
    }

    @Override
    @Transactional
    public void updateUserStatusById(User user) {
        String sqlCommand = "UPDATE user_data SET user_status = CAST(? AS user_status) WHERE id = ?";
        jdbcTemplate.update(sqlCommand, user.getUserStatus().name(), user.getId());
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        String sqlCommand = "DELETE FROM user_data WHERE id = ?";
        jdbcTemplate.update(sqlCommand, id);
    }
}