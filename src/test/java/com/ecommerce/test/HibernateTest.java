package com.ecommerce.test;

import com.ecommerce.crud.CategoryCRUD;
import com.ecommerce.crud.OrdersCRUD;
import com.ecommerce.crud.ProductCRUD;
import com.ecommerce.crud.UsersCRUD;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.OrderDetails;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Users;
import com.ecommerce.util.HibernateUtil;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Hibernate CRUD operations and entity relationships.
 */
public class HibernateTest {

    @Test
    void testECommerceOperations() {

        CategoryCRUD categoryCRUD = new CategoryCRUD();
        ProductCRUD productCRUD = new ProductCRUD();
        UsersCRUD usersCRUD = new UsersCRUD();
        OrdersCRUD ordersCRUD = new OrdersCRUD();

        // Create and save a category
        Category category = new Category(
                "Electronics_" + System.currentTimeMillis(),
                "Electronic products"
        );

        categoryCRUD.saveCategory(category);

        assertNotNull(category.getId());

        // Create and save a product
        Product product = new Product(
                "Laptop_" + System.currentTimeMillis(),
                new BigDecimal("65000.00"),
                10,
                category
        );

        productCRUD.saveProduct(product);

        assertNotNull(product.getId());

        // Create and save a user
        Users user = new Users(
                "customer_" + System.currentTimeMillis(),
                "password123",
                "customer" + System.currentTimeMillis() + "@example.com",
                Users.Role.CUSTOMER
        );

        usersCRUD.saveUser(user);

        assertNotNull(user.getId());

        // Create an order
        Orders order = new Orders(
                LocalDateTime.now(),
                new BigDecimal("130000.00"),
                user
        );

        // Add multiple products to the order
        OrderDetails detail1 = new OrderDetails(
                1,
                new BigDecimal("65000.00"),
                product
        );

        OrderDetails detail2 = new OrderDetails(
                1,
                new BigDecimal("65000.00"),
                product
        );

        order.addOrderDetail(detail1);
        order.addOrderDetail(detail2);

        user.addOrder(order);

        // Save the order
        ordersCRUD.saveOrder(order);

        assertNotNull(order.getId());

        // Fetch the order with associated user and products
        Orders fetchedOrder = ordersCRUD.getOrder(order.getId());

        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getUser());
        assertEquals(2, fetchedOrder.getOrderDetails().size());

        System.out.println("Order ID: " + fetchedOrder.getId());
        System.out.println("Customer: " +
                fetchedOrder.getUser().getUsername());

        for (OrderDetails detail : fetchedOrder.getOrderDetails()) {
            System.out.println(
                    "Product: " + detail.getProduct().getName() +
                            ", Quantity: " + detail.getQuantity() +
                            ", Unit Price: " + detail.getUnitPrice()
            );
        }

        System.out.println("Hibernate test completed successfully.");

        HibernateUtil.shutdown();
    }
}