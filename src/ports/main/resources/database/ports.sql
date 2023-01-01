CREATE TABLE IF NOT EXISTS ports
(
    id     CHAR(36)     NOT NULL,
    name   VARCHAR(255) NOT NULL,
    locode CHAR(5)      NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;
