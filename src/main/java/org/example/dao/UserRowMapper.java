package org.example.dao;

import org.example.model.UserStatus;
import org.example.model.user.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UUID columnId = (UUID) resultSet.getObject("id");
        String columnName = resultSet.getString("name");
        UserStatus columnUserStatus = UserStatus.valueOf(resultSet.getString("user_status"));
        Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");
        User user = new User(columnName, columnUserStatus, columnCreationDate);
        user.setId(columnId);
        return user;
    }
}
