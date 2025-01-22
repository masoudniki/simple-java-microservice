-- Insert into category table
INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronics and gadgets', 'Electronics'),
    (nextval('category_seq'), 'Furniture and home decor', 'Furniture'),
    (nextval('category_seq'), 'Books and stationery', 'Books');

-- Insert into product table
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    -- Electronics
    (nextval('product_seq'), 'Smartphone with 6GB RAM and 128GB storage', 'Smartphone', 150, 299.99, 1),
    (nextval('product_seq'), 'LED TV with 4K resolution, 55-inch', '4K LED TV', 75, 499.99, 1),
    (nextval('product_seq'), 'Bluetooth noise-canceling headphones', 'Headphones', 120, 99.99, 1),
    (nextval('product_seq'), 'Portable power bank, 20000mAh', 'Power Bank', 250, 29.99, 1),
    (nextval('product_seq'), 'Smartwatch with heart rate monitor', 'Smartwatch', 100, 199.99, 1),
    (nextval('product_seq'), 'Wireless gaming mouse with RGB lighting', 'Gaming Mouse', 200, 49.99, 1),
    (nextval('product_seq'), 'Mechanical keyboard with customizable keys', 'Keyboard', 150, 79.99, 1),
    (nextval('product_seq'), 'Full HD Webcam with microphone', 'Webcam', 90, 39.99, 1),
    (nextval('product_seq'), 'Portable Bluetooth speaker, waterproof', 'Bluetooth Speaker', 180, 59.99, 1),
    (nextval('product_seq'), 'External hard drive, 2TB', 'Hard Drive', 70, 89.99, 1),

    -- Furniture
    (nextval('product_seq'), 'Ergonomic office chair with adjustable height', 'Office Chair', 200, 89.99, 2),
    (nextval('product_seq'), 'Solid wood dining table, 6-seater', 'Dining Table', 30, 399.99, 2),
    (nextval('product_seq'), 'Modern sofa, 3-seater with cushions', 'Sofa', 20, 799.99, 2),
    (nextval('product_seq'), 'King-sized bed with memory foam mattress', 'King Bed', 15, 999.99, 2),
    (nextval('product_seq'), 'Bookshelf with 5 tiers', 'Bookshelf', 50, 149.99, 2),
    (nextval('product_seq'), 'Study desk with drawer storage', 'Study Desk', 100, 129.99, 2),
    (nextval('product_seq'), 'Outdoor patio set, 4 chairs and table', 'Patio Set', 25, 699.99, 2),
    (nextval('product_seq'), 'Bean bag chair, large size', 'Bean Bag Chair', 80, 49.99, 2),
    (nextval('product_seq'), 'TV stand with cable management', 'TV Stand', 60, 99.99, 2),
    (nextval('product_seq'), 'Coffee table with glass top', 'Coffee Table', 40, 129.99, 2),

    -- Books
    (nextval('product_seq'), 'Novel: "The Great Adventure"', 'The Great Adventure', 300, 19.99, 3),
    (nextval('product_seq'), 'Notebook with 200 pages', 'Notebook', 500, 2.99, 3),
    (nextval('product_seq'), 'Guidebook: "Learn Python Programming"', 'Python Programming', 150, 29.99, 3),
    (nextval('product_seq'), 'Cookbook: "Healthy Recipes for Everyone"', 'Cookbook', 200, 24.99, 3),
    (nextval('product_seq'), 'Fantasy novel: "Realm of Dragons"', 'Realm of Dragons', 100, 14.99, 3);

