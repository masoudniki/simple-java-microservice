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


-- Create a sequence for the orders table
CREATE SEQUENCE order_id_seq START 1;

-- Create the table for orders
CREATE TABLE orders(
                        id INT PRIMARY KEY DEFAULT nextval('order_id_seq'),
                        customer_id INT NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                        updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Create a sequence for the payments table
CREATE SEQUENCE payment_id_seq START 1;

-- Create the table for payments
CREATE TABLE payment(
                          id INT PRIMARY KEY DEFAULT nextval('payment_id_seq'),
                          payment_method PAYMENT_METHODS NOT NULL,
                          payment_status PAYMENT_STATUS NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                          order_id INT NOT NULL,
                          CONSTRAINT fk_payments_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

-- Create a sequence for the order_lines table
CREATE SEQUENCE order_line_id_seq START 1;

-- Create the table for order_lines
CREATE TABLE order_line(
                             id INT PRIMARY KEY DEFAULT nextval('order_line_id_seq'),
                             product_id INT NOT NULL,
                             sale_price numeric(38,2),
                             order_id INT NOT NULL,
                             CONSTRAINT fk_order_lines_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);