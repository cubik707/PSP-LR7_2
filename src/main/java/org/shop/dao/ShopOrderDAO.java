package org.shop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.shop.model.ShopOrder;
import org.shop.sessionFactory.SessionFactoryImpl;

import java.util.List;

public class ShopOrderDAO {
    public void save(ShopOrder order) {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        }
    }

    public ShopOrder getOrderById(Long id) {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            return session.get(ShopOrder.class, id);
        }
    }

    public List<ShopOrder> getAllOrders() {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            return session.createQuery("FROM ShopOrder", ShopOrder.class).list();
        }
    }

    public List<ShopOrder> getOrdersByProductId(Long productId) {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT o FROM ShopOrder o JOIN o.orderItems oi WHERE oi.product.id = :productId", ShopOrder.class)
                    .setParameter("productId", productId)
                    .list();
        }
    }
}

