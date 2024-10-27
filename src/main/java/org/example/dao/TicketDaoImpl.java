package org.example.dao;

import org.example.model.TicketType;
import org.example.model.ticket.Ticket;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class TicketDaoImpl implements TicketDAO {

    private final DataSource dataSource;

    public TicketDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean save(Ticket ticket) {
        String sqlCommand = "INSERT INTO ticket_data (id, user_id, ticket_type, creation_date)" +
                " VALUES (?, ?, ?::ticket_type, ?)";
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(sqlCommand)) {
            statement.setObject(1, ticket.getId());
            statement.setObject(2, ticket.getUserId());
            statement.setString(3, ticket.getTicketType().name());
            statement.setTimestamp(4, ticket.getCreationDate());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ticket getById(UUID id) {
        String sqlCommand = "SELECT * FROM ticket_data WHERE id = ?";
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(sqlCommand)) {
            statement.setObject(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UUID columnId = (UUID) resultSet.getObject("id");
                    UUID columnUserId = (UUID) resultSet.getObject("user_id");
                    TicketType columnTicketType = TicketType.valueOf(resultSet.getString("ticket_type"));
                    Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");
                    Ticket ticket = new Ticket(columnUserId, columnTicketType, columnCreationDate);
                    ticket.setId(columnId);
                    return ticket;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Ticket> getByUserId(UUID userId) {
        String sqlCommand = "SELECT * FROM ticket_data WHERE user_id = ?";
        ArrayList<Ticket> tickets = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(sqlCommand)) {
            statement.setObject(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UUID columnId = (UUID) resultSet.getObject("id");
                    UUID columnUserId = (UUID) resultSet.getObject("user_id");
                    TicketType columnTicketType = TicketType.valueOf(resultSet.getString("ticket_type"));
                    Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");
                    Ticket ticket = new Ticket(columnUserId, columnTicketType, columnCreationDate);
                    ticket.setId(columnId);
                    tickets.add(ticket);
                }
                return tickets;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return tickets;
    }

    public boolean update(TicketType ticketType, UUID id) {
        // ?::ticket_type - instruction for DB that value will as ticket type
        String sqlCommand = "UPDATE ticket_data SET ticket_type = ?::ticket_type WHERE id = ?";
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(sqlCommand)) {
            statement.setString(1, ticketType.name()); // name returns string of ticket_type
            statement.setObject(2, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(UUID id) {
        String sqlCommand = "DELETE FROM ticket_data WHERE id = ?";
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(sqlCommand)) {
            statement.setObject(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}