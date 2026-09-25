package com.ecommerce.crud;

import com.ecommerce.entity.Orders;
import com.ecommerce.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Provides CRUD operations for Orders.
 */
public class OrdersCRUD {

    public void saveOrder(Orders order) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(order);

            transaction.commit();
        }
    }

    public Orders getOrder(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Orders order = session.get(Orders.class, id);

            // Initialize lazy relationships while the session is open.
            if (order != null) {
                order.getUser().getUsername();

                order.getOrderDetails().forEach(detail ->
                        detail.getProduct().getName()
                );
            }

            return order;
        }
    }

    public void updateOrder(Orders order) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.merge(order);

            transaction.commit();
        }
    }

    public void deleteOrder(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Orders order = session.get(Orders.class, id);

            if (order != null) {
                session.remove(order);
            }

            transaction.commit();
        }
    }
}