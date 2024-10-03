INSERT INTO "products" ("description", "barcode", "unit_price", "unit_of_measure", "created_at", "updated_at")
VALUES
    ('Produto A', '7891000100101', 10.99, 'unidade', NOW(), NOW()),
    ('Produto B', '7891000100102', 15.49, 'pacote', NOW(), NOW()),
    ('Produto C', '7891000100103', 8.75, 'caixa', NOW(), NOW()),
    ('Produto D', '7891000100104', 23.00, 'litro', NOW(), NOW()),
    ('Produto E', '7891000100105', 12.99, 'quilo', NOW(), NOW()),
    ('Produto F', '7891000100106', 30.45, 'metro', NOW(), NOW()),
    ('Produto G', '7891000100107', 9.99, 'litro', NOW(), NOW()),
    ('Produto H', '7891000100108', 18.50, 'unidade', NOW(), NOW()),
    ('Produto I', '7891000100109', 5.25, 'caixa', NOW(), NOW()),
    ('Produto J', '7891000100110', 50.00, 'quilo', NOW(), NOW());

INSERT INTO "products" ("description", "barcode", "unit_price", "unit_of_measure", "created_at", "updated_at", "deleted_at")
VALUES
    ('Testando Soft Delete', '7891000100121', 500.00, 'litro', NOW(), NOW(), NOW());
