select  * from product;

INSERT INTO product (id, slug, name, reference, price, vat, stockable)
VALUES
    (1, 'fake-slug-1', 'Product 1', 'REF001', 10.99, 0.10, 1),
    (2, 'fake-slug-2', 'Product 2', 'REF002', 19.99, 0.15, 1),
    (3, 'fake-slug-3', 'Product 3', 'REF003', 8.50, 0.05, 0);
