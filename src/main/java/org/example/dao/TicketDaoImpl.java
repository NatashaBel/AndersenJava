package org.example.dao;

import org.example.model.TicketType;
import org.example.model.ticket.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class TicketDaoImpl implements TicketDAO {

    private final JdbcTemplate jdbcTemplate;

    public TicketDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        String sqlCommand = "INSERT INTO ticket_data (id, user_id, ticket_type, creation_date)" +
                " VALUES (?, ?, ?::ticket_type, ?)";
        jdbcTemplate.update(sqlCommand, ticket.getId(), ticket.getUserId(), ticket.getTicketType().name(),
                ticket.getCreationDate());
    }

    @Override
    @Transactional
    public Ticket getById(UUID id) {
        String sqlCommand = "SELECT * FROM ticket_data WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlCommand, new TicketRowMapper(), id);
    }

    @Override
    @Transactional
    public List<Ticket> getByUserId(UUID userId) {
        String sqlCommand = "SELECT * FROM ticket_data WHERE user_id = ?";
        return jdbcTemplate.query(sqlCommand, new TicketRowMapper(), userId);
    }

    @Override
    @Transactional
    public void update(TicketType ticketType, UUID id) {
        // ?::ticket_type - instruction for DB that value will as ticket type
        String sqlCommand = "UPDATE ticket_data SET ticket_type = ?::ticket_type WHERE id = ?";
        jdbcTemplate.update(sqlCommand, ticketType.name(), id);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        String sqlCommand = "DELETE FROM ticket_data WHERE id = ?";
        jdbcTemplate.update(sqlCommand, id);
    }
}