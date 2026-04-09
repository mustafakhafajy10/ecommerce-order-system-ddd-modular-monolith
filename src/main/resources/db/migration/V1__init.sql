CREATE TABLE IF NOT EXISTS customers (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS products (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    price DECIMAL(12, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS inventory_items (
    id VARCHAR(36) PRIMARY KEY,
    product_id VARCHAR(36) NOT NULL UNIQUE,
    quantity INT NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    id VARCHAR(36) PRIMARY KEY,
    customer_id VARCHAR(36) NOT NULL,
    total_amount DECIMAL(12, 2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS order_items (
    id VARCHAR(36) PRIMARY KEY,
    order_id VARCHAR(36) NOT NULL,
    product_id VARCHAR(36) NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(12, 2) NOT NULL,
    line_total DECIMAL(12, 2) NOT NULL,
    CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_orders_customer_id ON orders(customer_id);
CREATE INDEX IF NOT EXISTS idx_order_items_order_id ON order_items(order_id);
CREATE INDEX IF NOT EXISTS idx_order_items_product_id ON order_items(product_id);

INSERT INTO customers (id, name, email) VALUES
('11111111-1111-1111-1111-111111111111', 'Alice Carter', 'alice@example.com'),
('22222222-2222-2222-2222-222222222222', 'Brian Lee', 'brian@example.com');

INSERT INTO products (id, name, price) VALUES
('33333333-3333-3333-3333-333333333333', 'Mechanical Keyboard', 129.99),
('44444444-4444-4444-4444-444444444444', 'Wireless Mouse', 59.50),
('55555555-5555-5555-5555-555555555555', 'USB-C Dock', 89.00);

INSERT INTO inventory_items (id, product_id, quantity) VALUES
('66666666-6666-6666-6666-666666666666', '33333333-3333-3333-3333-333333333333', 15),
('77777777-7777-7777-7777-777777777777', '44444444-4444-4444-4444-444444444444', 25),
('88888888-8888-8888-8888-888888888888', '55555555-5555-5555-5555-555555555555', 8);
