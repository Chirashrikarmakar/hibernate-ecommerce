package com.ecommerce.crud;

import com.ecommerce.entity.Category;
import com.ecommerce.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Provides CRUD operations for Category.
 */
public class CategoryCRUD {

    public void saveCategory(Category category) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(category);

            transaction.commit();
        }
    }

    public Category getCategory(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Category.class, id);
        }
    }

    public void updateCategory(Category category) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.merge(category);

            transaction.commit();
        }
    }

    public void deleteCategory(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Category category = session.get(Category.class, id);

            if (category != null) {
                session.remove(category);
            }

            transaction.commit();
        }
    }
}