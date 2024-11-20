package org.shop.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.shop.model.Product;
import org.shop.sessionFactory.SessionFactoryImpl;

import java.util.List;

public class ProductDAO {
    public void save(Product product) {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        }
    }

    public Product getProductById(Long id) {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            return session.get(Product.class, id);
        }
    }

    public List<Product> getAllProducts() {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        }
    }
}

