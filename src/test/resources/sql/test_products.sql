INSERT INTO products
  (color, description, email, name, price, id)
VALUES
  (0, 'Fresh Valencia oranges', 'jj@gg.com', 'oranges', 54, 1);
commit;
INSERT INTO reviews
    (evaluation, comment, product_id, id)
  VALUES
    (4, 'very good', 1, 1);
commit;
