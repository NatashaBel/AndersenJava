package dao;

import model.TicketType;
import model.ticket.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class TicketDaoImpl implements TicketDao {
    private final Connection connection;

    public TicketDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public boolean save(Ticket ticket) {
        String sqlCommand = "INSERT INTO public.\"Ticket\" (id, user_id, ticket_type, creation_date)" +
                " VALUES (?, ?, ?::ticket_type, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
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
        String sqlCommand = "SELECT * FROM public.\"Ticket\" WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setObject(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UUID columnId = (UUID) resultSet.getObject("id");
                    UUID columnUserId = (UUID) resultSet.getObject("user_id");
                    TicketType columnTicketType = TicketType.valueOf(resultSet.getString("ticket_type"));
                    Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");
                    return new Ticket(columnId, columnUserId, columnTicketType, columnCreationDate);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Ticket> getByUserId(UUID userId) {
        String sqlCommand = "SELECT * FROM public.\"Ticket\" WHERE user_id = ?";
        ArrayList<Ticket> tickets = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setObject(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UUID columnId = (UUID) resultSet.getObject("id");
                    UUID columnUserId = (UUID) resultSet.getObject("user_id");
                    TicketType columnTicketType = TicketType.valueOf(resultSet.getString("ticket_type"));
                    Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");
                    tickets.add(new Ticket(columnId, columnUserId, columnTicketType, columnCreationDate));
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
        String sqlCommand = "UPDATE public.\"Ticket\" SET ticket_type = ?::ticket_type WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, ticketType.name()); // name returns string of ticket_type
            statement.setObject(2, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(UUID id) {
        String sqlCommand = "DELETE FROM public.\"Ticket\" WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setObject(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}