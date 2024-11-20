CREATE DATABASE IF NOT EXISTS store_db;

USE store_db;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    username VARCHAR(45) NOT NULL UNIQUE, 
    email VARCHAR(45) NOT NULL UNIQUE, 
    password VARCHAR(45) NOT NULL
);

-- Создание таблицы продуктов
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL
);

-- Создание таблицы заказов
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,      -- Уникальный идентификатор заказа
    order_number VARCHAR(50) NOT NULL,         -- Номер заказа
    arrival_date DATE NOT NULL,                -- Дата поступления
    user_id BIGINT,                            -- Внешний ключ на таблицу users
    FOREIGN KEY (user_id) REFERENCES users(id) -- Связь с таблицей users
);
-- Создание таблицы для связи заказов и товаров
CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    product_id BIGINT,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);



INSERT INTO products (name, description, price) 
VALUES 
    ('Телефон', 'Смартфон с 6.5 дюймовым экраном и процессором Snapdragon 888', 1000.00),
    ('Ноутбук', 'Ноутбук с процессором Intel Core i7, 16 GB RAM, 512 GB SSD', 2000.00),
    ('Клавиатура', 'Механическая клавиатура с подсветкой и клавишами Cherry MX', 150.00),
    ('Мышь', 'Беспроводная мышь с эргономичным дизайном', 50.00);

INSERT INTO orders (order_number, arrival_date)
VALUES 
    ('ORD12345', '2024-11-20'),
    ('ORD12346', '2024-11-21'),
    ('ORD12347', '2024-11-22');

INSERT INTO order_items (order_id, product_id, quantity)
VALUES 
    (1, 1, 2),  -- 2 Телефона в заказе ORD12345
    (1, 2, 1),  -- 1 Ноутбук в заказе ORD12345
    (2, 3, 1),  -- 1 Клавиатура в заказе ORD12346
    (3, 4, 3);  -- 3 Мыши в заказе ORD12347

INSERT INTO users (username, email, password)
VALUES 
    ('user1', 'user1@example.com', '111'),
    ('user2', 'user2@example.com', '222'),
    ('user3', 'user3@example.com', '333');