CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE TABLE IF NOT EXISTS "products" (
    "id" SERIAL PRIMARY KEY,
    "description" VARCHAR(255) NOT NULL,
    "barcode" VARCHAR(255) NOT NULL,
    "unit_price" NUMERIC(38,2) NOT NULL ,
    "unit_of_measure" VARCHAR(255) NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    "deleted_at" TIMESTAMP NULL,
    UNIQUE(description),
    UNIQUE(barcode),
    CONSTRAINT positive_unit_price CHECK (unit_price > 0)
);
CREATE INDEX idx_product_description_gin ON products USING GIN (description gin_trgm_ops);