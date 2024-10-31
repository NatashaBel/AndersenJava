package org.example.dao;

import org.example.model.TicketType;
import org.example.model.ticket.Ticket;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class TicketRowMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UUID columnId = (UUID) resultSet.getObject("id");
        UUID columnUserId = (UUID) resultSet.getObject("user_id");
        TicketType columnTicketType = TicketType.valueOf(resultSet.getString("ticket_type"));
        Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");
        Ticket ticket = new Ticket(columnUserId, columnTicketType, columnCreationDate);
        ticket.setId(columnId);
        return ticket;
    }
}