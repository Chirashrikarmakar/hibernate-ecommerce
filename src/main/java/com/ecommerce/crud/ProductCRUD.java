package com.ecommerce.crud;

import com.ecommerce.entity.Product;
import com.ecommerce.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Provides CRUD operations for Product.
 */
public class ProductCRUD {

    public void saveProduct(Product product) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(product);

            transaction.commit();
        }
    }

    public Product getProduct(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Product.class, id);
        }
    }

    public void updateProduct(Product product) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.merge(product);

            transaction.commit();
        }
    }

    public void deleteProduct(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Product product = session.get(Product.class, id);

            if (product != null) {
                session.remove(product);
            }

            transaction.commit();
        }
    }
}