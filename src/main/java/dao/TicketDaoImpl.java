package dao;

import model.ticket.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class TicketDaoImpl implements TicketDao {

    @Override
    public void save(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }

    @Override
    public Ticket getById(UUID id) {
        try(Session session = SessionFactoryProvider.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            transaction.commit();
            return ticket;
        }
    }

    @Override
    public List<Ticket> getByUserId(UUID id) {
        Transaction transaction = null;
        List<Ticket> tickets = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            tickets = session.createQuery("FROM Ticket t WHERE t.user.id = :userId", Ticket.class)
                    .setParameter("userId", id)
                    .getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public void update(Ticket ticket, UUID id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(ticket);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(ticket);
        transaction.commit();
        session.close();
    }
}