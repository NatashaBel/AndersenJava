package org.example.dao;

import org.example.model.user.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDAO {

    private final DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean save(User user) {
        String sqlCommand = "INSERT INTO user_data (id, name, creation_date) VALUES (?, ?, ?)";
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(sqlCommand)) {
            statement.setObject(1, user.getId());
            statement.setString(2, user.getName());
            statement.setTimestamp(3, user.getCreationDate());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(UUID id) {
        String sqlCommand = "SELECT * FROM user_data WHERE id = ?";
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(sqlCommand)) {
            statement.setObject(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UUID columnId = (UUID) resultSet.getObject("id");
                    String columnName = resultSet.getString("name");
                    Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");
                    User user = new User(columnName, columnCreationDate);
                    user.setId(columnId);
                    return user;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean update(User user) {
        String sqlCommand = "UPDATE user_data SET name = ?, creation_date = ? WHERE id = ?";
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(sqlCommand)) {
            statement.setString(1, user.getName());
            statement.setTimestamp(2, user.getCreationDate());
            statement.setObject(3, user.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(UUID id) {
        String sqlCommand = "DELETE FROM user_data WHERE id = ?";
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(sqlCommand)) {
            statement.setObject(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}