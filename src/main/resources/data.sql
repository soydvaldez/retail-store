INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_WITH_NOT_PRIVILEGES');

INSERT INTO users (username, password) VALUES ('user', '$2a$10$j2RTyMWPxEfWrTtlYd7L/O3Gt8R9UUHubjPtf3zNi/xq41vHdD0fq'); -- userpassword
INSERT INTO users (username, password) VALUES ('admin', '$2a$10$j2RTyMWPxEfWrTtlYd7L/O3Gt8R9UUHubjPtf3zNi/xq41vHdD0fq'); -- adminpassword
INSERT INTO users (username, password) VALUES ('user2', '$2a$10$g/NPJC0mhk4/TlcrobrWxODLxYyRv8/BzLCbUt.vrXLXKOkuEJDT2'); -- adminpassword

INSERT INTO users_roles (user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'ROLE_USER'));
INSERT INTO users_roles (user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO users_roles (user_id, role_id) VALUES ((SELECT id FROM users WHERE username = 'user2'), (SELECT id FROM roles WHERE name = 'ROLE_WITH_NOT_PRIVILEGES'));

-- Insertando Categorías
INSERT INTO Categories (nombre, descripcion) VALUES
('Electronics','Category for electronic devices and gadgets'),
('Books', 'Category for various types of books and novels'),
('Clothing', 'Category for clothing items and apparel');

-- Insertando Productos adicionales
INSERT INTO Products (nombre, descripcion, precio, category_id) VALUES 
('Wireless Mouse', 'Ergonomic wireless mouse for improved productivity', 20.00, 1),
('Bluetooth Headphones', 'High-quality Bluetooth headphones for immersive audio experience', 50.00, 1),
('Python Programming Book', 'Comprehensive guide to Python programming language', 25.00, 2),
('Java Programming Book', 'In-depth coverage of Java programming concepts and techniques', 30.00, 2),
('Dress Shirt', 'Formal dress shirt made from premium fabric', 35.00, 3),
('Casual Shirt', 'Casual shirt for everyday wear', 25.00, 3),
('Running Shoes', 'Lightweight running shoes for jogging and workouts', 60.00, 3),
('Gaming Laptop', 'High-performance gaming laptop with dedicated graphics card', 1500.00, 1),
('Desktop PC', 'Powerful desktop PC for intensive computing tasks', 1200.00, 1),
('Romantic Novel', 'Heartwarming romantic novel with a compelling storyline', 18.00, 2),
('Fantasy Book', 'Exciting fantasy novel set in a magical world', 22.00, 2),
('Polo Shirt', 'Stylish polo shirt made from breathable fabric', 30.00, 3),
('Leather Jacket', 'Classic leather jacket for a rugged and stylish look', 100.00, 3),
('Basketball Shoes', 'Durable basketball shoes with excellent traction', 80.00, 3),
('Wireless Keyboard', 'Sleek wireless keyboard for convenient typing', 35.00, 1),
('Graphic Design Book', 'Comprehensive guide to graphic design principles and techniques', 40.00, 2),
('Thriller Novel', 'Suspenseful thriller novel that keeps you on the edge', 20.00, 2),
('Sweatshirt', 'Warm and comfortable sweatshirt for chilly days', 28.00, 3),
('Cargo Pants', 'Durable cargo pants with plenty of pockets for storage', 45.00, 3),
('Fitness Tracker', 'Smart fitness tracker to monitor your health and workouts', 70.00, 1);
