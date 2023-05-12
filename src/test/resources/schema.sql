CREATE TABLE IF NOT EXISTS brands
(
    id   SERIAL       NOT NULL
        CONSTRAINT brand_pk PRIMARY KEY,
    name VARCHAR(255) NOT NULL
        CONSTRAINT un_brand_name UNIQUE
);

CREATE TABLE IF NOT EXISTS cars
(
    id                SERIAL        NOT NULL
        CONSTRAINT car_pk PRIMARY KEY,
    plate             VARCHAR(255)  NOT NULL
        CONSTRAINT un_car_plate UNIQUE,
    model             VARCHAR(255)  NOT NULL,
    vin               VARCHAR(20)   NOT NULL,
    type              VARCHAR(255),
    cylinder_capacity NUMERIC(9, 2) NOT NULL,
    value             NUMERIC(9, 2) NOT NULL,
    brand_id          INTEGER       NOT NULL
        CONSTRAINT car_brand_id_fk
            REFERENCES brands
);

CREATE TABLE IF NOT EXISTS car_yards
(
    id            SERIAL       NOT NULL
        CONSTRAINT car_yards_pk PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    address       VARCHAR(512) NOT NULL,
    phone_number  VARCHAR(20)  NOT NULL,
    point_of_sale VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS clients
(
    id                  SERIAL       NOT NULL
        CONSTRAINT client_pk PRIMARY KEY,
    identification      VARCHAR(20)  NOT NULL
        CONSTRAINT un_client_identification UNIQUE,
    first_name          VARCHAR(255) NOT NULL,
    last_name           VARCHAR(255) NOT NULL,
    address             VARCHAR(512) NOT NULL,
    local_phone_number  VARCHAR(20)  NOT NULL,
    mobile_phone_number VARCHAR(20)  NOT NULL,
    birthdate           DATE         NOT NULL,
    gender              VARCHAR(50)  NOT NULL,
    marital_status      VARCHAR(50)  NOT NULL,
    credit_approval     BOOLEAN      NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS car_yard_clients
(
    id            SERIAL    NOT NULL
        CONSTRAINT car_yard_clients_pk PRIMARY KEY,
    car_yard_id   INTEGER   NOT NULL
        CONSTRAINT car_yard_id_fk REFERENCES car_yards,
    client_id     INTEGER   NOT NULL
        CONSTRAINT client_id_fk REFERENCES clients,
    assigned_date TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT un_car_yard_clients UNIQUE (car_yard_id, client_id)
);

CREATE TABLE IF NOT EXISTS sellers
(
    id                  SERIAL       NOT NULL
        CONSTRAINT seller_pk PRIMARY KEY,
    identification      VARCHAR(20)  NOT NULL
        CONSTRAINT un_seller_identification UNIQUE,
    first_name          VARCHAR(255) NOT NULL,
    last_name           VARCHAR(255) NOT NULL,
    address             VARCHAR(512) NOT NULL,
    local_phone_number  VARCHAR(20)  NOT NULL,
    mobile_phone_number VARCHAR(20)  NOT NULL,
    birthdate           DATE         NOT NULL,
    gender              VARCHAR(50)  NOT NULL,
    marital_status      VARCHAR(50)  NOT NULL,
    car_yard_id         INTEGER      NOT NULL
        CONSTRAINT seller_car_yard_id_fk
            REFERENCES car_yards
);

CREATE TABLE IF NOT EXISTS credit_requests
(
    id              SERIAL        NOT NULL
        CONSTRAINT credit_request_pk PRIMARY KEY,
    car_id          INTEGER       NOT NULL
        CONSTRAINT credit_request_car_id_fk REFERENCES cars,
    client_id       INTEGER       NOT NULL
        CONSTRAINT credit_request_client_id_fk REFERENCES clients,
    seller_id       INTEGER       NOT NULL
        CONSTRAINT credit_request_seller_id_fk REFERENCES sellers,
    car_yard_id     INTEGER       NOT NULL
        CONSTRAINT credit_request_car_yard_id_fk REFERENCES car_yards,
    installments    INTEGER       NOT NULL,
    month_terms     INTEGER       NOT NULL,
    status          VARCHAR(50)   NOT NULL,
    observation     VARCHAR(512),
    upfront_payment NUMERIC(9, 2) NOT NULL,
    creation_date   TIMESTAMP     NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS credit_requests_car_id_idx
    ON credit_requests (car_id, creation_date DESC);

CREATE INDEX IF NOT EXISTS credit_requests_client_id_idx
    ON credit_requests (client_id, creation_date DESC);

CREATE INDEX IF NOT EXISTS car_model_idx ON cars (model);

CREATE INDEX IF NOT EXISTS car_brand_id_idx ON cars (brand_id);

CREATE INDEX IF NOT EXISTS brand_name_idx ON brands (name);

CREATE INDEX IF NOT EXISTS client_identification_idx ON clients (identification);

CREATE INDEX IF NOT EXISTS seller_identification_idx ON sellers (identification);
