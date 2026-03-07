CREATE TABLE IF NOT EXISTS restaurant_table
(
    id             SERIAL PRIMARY KEY,
    table_number   VARCHAR(10)      NOT NULL,
    capacity       INT              NOT NULL,
    coordinate_x   DOUBLE PRECISION NOT NULL,
    coordinate_y   DOUBLE PRECISION NOT NULL,
    is_window_seat BOOLEAN DEFAULT FALSE,
    is_accessible  BOOLEAN DEFAULT FALSE,
    is_private     BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS reservation
(
    id                    SERIAL PRIMARY KEY,
    table_id              INT          NOT NULL,
    customer_name         VARCHAR(100) NOT NULL,
    customer_phone_number VARCHAR(100)  NOT NULL,
    start_time            TIMESTAMP    NOT NULL,
    end_time              TIMESTAMP    NOT NULL,
    guest_count           INT          NOT NULL,
    CONSTRAINT fk_table FOREIGN KEY (table_id) REFERENCES restaurant_table (id)
);