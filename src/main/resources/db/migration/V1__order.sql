CREATE SCHEMA IF NOT EXISTS order_service;

CREATE TABLE IF NOT EXISTS order_service.orders
(
    id                  bigserial,
    user_id             bigint  NOT NULL,
    product_id_quantity jsonb   NOT NULL,
    created_date        timestamp without time zone,
    order_status        character varying NOT NULL,
    PRIMARY KEY (id)
);