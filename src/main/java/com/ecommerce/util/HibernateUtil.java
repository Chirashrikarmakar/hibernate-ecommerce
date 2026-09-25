package com.ecommerce.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utility class responsible for creating and providing
 * the Hibernate SessionFactory.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Load Hibernate configuration from hibernate.cfg.xml
            return new Configuration()
                    .configure()
                    .buildSessionFactory();

        } catch (Throwable exception) {
            System.err.println("SessionFactory creation failed: " + exception);
            throw new ExceptionInInitializerError(exception);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Close Hibernate resources when the application ends
    public static void shutdown() {
        getSessionFactory().close();
    }
}