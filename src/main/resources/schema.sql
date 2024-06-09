-- CREATE TABLE categories (
--     id BIGINT PRIMARY KEY AUTO_INCREMENT,
--     nombre VARCHAR(255) NOT NULL,
--     descripcion VARCHAR(255)
-- );

-- CREATE TABLE products (
--     id BIGINT PRIMARY KEY AUTO_INCREMENT,
--     nombre VARCHAR(255) NOT NULL,
--     descripcion VARCHAR(255),
--     precio DECIMAL(10, 2) NOT NULL,
--     category_id BIGINT,
--     CONSTRAINT fk_category
--         FOREIGN KEY (category_id) 
--         REFERENCES Categories(id)
-- );

select CURRENT_DATE;
