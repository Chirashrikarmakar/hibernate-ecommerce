package com.ecommerce.crud;

import com.ecommerce.entity.Users;
import com.ecommerce.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Provides CRUD operations for Users.
 */
public class UsersCRUD {

    public void saveUser(Users user) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Hash the password before storing it in the database.
            user.setPassword(hashPassword(user.getPassword()));

            session.persist(user);

            transaction.commit();
        }
    }

    public Users getUser(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Users.class, id);
        }
    }

    public void updateUser(Users user) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.merge(user);

            transaction.commit();
        }
    }

    public void deleteUser(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Users user = session.get(Users.class, id);

            if (user != null) {
                session.remove(user);
            }

            transaction.commit();
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException("Password hashing failed", exception);
        }
    }
}