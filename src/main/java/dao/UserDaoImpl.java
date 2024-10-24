package dao;

import model.TicketType;
import model.user.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User get(UUID id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            transaction.commit();
            return user;
        }
    }

    @Override
    public void update(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUserAndTickets(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            if (user == null) {
                throw new RuntimeException("User not found!");
            }
            user.setName("Bob");
            if (!user.getTickets().isEmpty()) {
                user.getTickets().getFirst().setTicketType(TicketType.YEAR);
            } else {
                System.out.println("User does not have tickets");
            }
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(user);
        transaction.commit();
        session.close();
    }
}