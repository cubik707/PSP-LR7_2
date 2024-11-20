package org.shop.sessionFactory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.shop.model.OrderItem;
import org.shop.model.Product;
import org.shop.model.ShopOrder;
import org.shop.model.User;

public class SessionFactoryImpl {
    private static SessionFactory sessionFactory;

    private SessionFactoryImpl() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();

                // Добавляем аннотированные классы
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(ShopOrder.class);
                configuration.addAnnotatedClass(OrderItem.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение при создании SessionFactory: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
