CREATE TABLE IF NOT EXISTS ships
(
    imo CHAR(7) NOT NULL,
    PRIMARY KEY (imo)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS ports
(
    id     CHAR(36) NOT NULL,
    locode CHAR(5)  NOT NULL UNIQUE,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;
