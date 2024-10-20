package dao;

import model.user.User;

import java.sql.*;
import java.util.UUID;

public class UserDaoImpl implements UserDao {
    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(User user) {
        String sqlCommand = "INSERT INTO public.\"User\" (id, name, creation_date) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
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
        String sqlCommand = "SELECT * FROM public.\"User\" WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setObject(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UUID columnId = (UUID) resultSet.getObject("id");
                    String columnName = resultSet.getString("name");
                    Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");
                    return new User(columnId, columnName, columnCreationDate);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean update(User user){
        String sqlCommand = "UPDATE public.\"User\" SET name = ?, creation_date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, user.getName());
            statement.setTimestamp(2, user.getCreationDate());
            statement.setObject(3, user.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(UUID id) {
        String sqlCommand = "DELETE FROM public.\"User\" WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setObject(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}