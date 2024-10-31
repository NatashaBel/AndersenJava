package org.example.dao;

import org.example.model.ticket.Ticket;
import org.example.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDAO {
    @Value("${app.user-update-create-ticket.enabled}")
    private boolean userUpdateCreateEnabled;


    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private final TicketDAO ticketDao;
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(TicketDAO ticketDao, JdbcTemplate jdbcTemplate) {
        this.ticketDao = ticketDao;
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
    public void updateUserAndCreateTicket(User user, Ticket ticket) {
        if (!userUpdateCreateEnabled) {
            throw new UnsupportedOperationException("Creating a ticket is disabled.");
        }
        userDAO.updateUserStatusById(user);
        ticketDao.save(ticket);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        String sqlCommand = "DELETE FROM user_data WHERE id = ?";
        jdbcTemplate.update(sqlCommand, id);
    }
}