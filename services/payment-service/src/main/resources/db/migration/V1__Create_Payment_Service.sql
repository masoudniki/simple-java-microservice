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


-- Create a sequence for the payments table
CREATE SEQUENCE if not exists payment_id_seq START 1;

create table if not exists payment (
                        id INT PRIMARY KEY DEFAULT nextval('payment_id_seq'),
                        amount numeric(2,38) not null ,
                        payment_method PAYMENT_METHODS NOT NULL,
                        payment_status PAYMENT_STATUS NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                        order_id INT NOT NULL,
                        customer_id INT not null
)