package org.shop.dao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.shop.model.User;
import org.shop.sessionFactory.SessionFactoryImpl;

import java.util.List;

public class UserDAO {
    public void save(User user) {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    public User getUserById(Long id) {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

    public User getUserByUsername(String username) {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }

    public List<User> getAllUsers() {
        try (Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }
}
