CREATE TABLE IF NOT EXISTS routes
(
    id            CHAR(36) NOT NULL,
    departurePort CHAR(36) NOT NULL,
    arrivalPort   CHAR(36) NOT NULL,
    path          JSON     NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS ports
(
    id        CHAR(36) NOT NULL,
    latitude  DOUBLE,
    longitude DOUBLE,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;
