package com.ecommerce;

import com.ecommerce.util.HibernateUtil;

/**
 * Main entry point for the Hibernate e-commerce application.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hibernate E-Commerce Application Started.");

        HibernateUtil.getSessionFactory();

        System.out.println("Hibernate SessionFactory initialized successfully.");

        HibernateUtil.shutdown();
    }
}