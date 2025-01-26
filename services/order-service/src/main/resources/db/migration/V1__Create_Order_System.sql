-- Create a sequence for the orders table
CREATE SEQUENCE orders_id_seq START 1;

-- Create the table for orders
CREATE TABLE orders (
                        id INT PRIMARY KEY DEFAULT nextval('orders_id_seq'),
                        customer_id INT NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                        updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Create a sequence for the payments table
CREATE SEQUENCE payments_id_seq START 1;

-- Create the table for payments
CREATE TABLE payments (
                          id INT PRIMARY KEY DEFAULT nextval('payments_id_seq'),
                          payment_method VARCHAR(50) NOT NULL,
                          payment_status VARCHAR(50) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                          order_id INT NOT NULL,
                          CONSTRAINT fk_payments_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

-- Create a sequence for the order_lines table
CREATE SEQUENCE order_lines_id_seq START 1;

-- Create the table for order_lines
CREATE TABLE order_lines (
                             id INT PRIMARY KEY DEFAULT nextval('order_lines_id_seq'),
                             product_id INT NOT NULL,
                             price numeric(38,2),
                             order_id INT NOT NULL,
                             CONSTRAINT fk_order_lines_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

-- Enum for payment methods
DO $$ BEGIN
    CREATE TYPE payment_methods AS ENUM ('PAYPAL', 'VISA', 'CRYPTO', 'CREDIT');
EXCEPTION
    WHEN duplicate_object THEN NULL; -- Ignore if it already exists
END $$;

-- Enum for payment status
DO $$ BEGIN
    CREATE TYPE payment_status AS ENUM ('FAILED', 'SUCCESS', 'WAITING_FOR_PAYMENT');
EXCEPTION
    WHEN duplicate_object THEN NULL; -- Ignore if it already exists
END $$;

-- Alter payments table to use enums
ALTER TABLE payments ALTER COLUMN payment_method TYPE payment_methods USING payment_method::payment_methods;
ALTER TABLE payments ALTER COLUMN payment_status TYPE payment_status USING payment_status::payment_status;
