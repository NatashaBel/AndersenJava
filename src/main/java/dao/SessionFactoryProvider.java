package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {
    private static SessionFactory sessionFactory;

    private SessionFactoryProvider() {

    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure(); // configure() reads our hibernate.cfg.xml and creates configuration object
                sessionFactory = configuration.buildSessionFactory(); //creates buildSessionFactory() based on configuration
            } catch (Exception e) {
                System.out.println("An error occurred during Hibernate init: " + e);
            }
        }
        return sessionFactory; // returns factory object
    }
}