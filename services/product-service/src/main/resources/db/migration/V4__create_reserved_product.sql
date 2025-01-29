create sequence if not exists category_seq increment by 1;
create table if not exists reserved_product(
     id integer not null primary key ,
     product_id integer,
     customer_id integer,
     created_at TIMESTAMP NOT NULL DEFAULT NOW(),
     reserved_until TIMESTAMP
)