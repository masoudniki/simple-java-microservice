-- Insert into category table
INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronics and gadgets', 'Electronics'),
    (nextval('category_seq'), 'Furniture and home decor', 'Furniture'),
    (nextval('category_seq'), 'Books and stationery', 'Books');

