package org.shop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.shop.model.OrderItem;
import org.shop.sessionFactory.SessionFactoryImpl;

import java.util.List;

public class OrderItemDAO {
    public void save(OrderItem orderItem) {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(orderItem);
            transaction.commit();
        }
    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM OrderItem WHERE order.id = :orderId", OrderItem.class)
                    .setParameter("orderId", orderId)
                    .list();
        }
    }
}

