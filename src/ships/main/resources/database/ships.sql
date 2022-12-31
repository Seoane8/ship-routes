CREATE TABLE IF NOT EXISTS ships (
    imo CHAR(7) NOT NULL,
    name VARCHAR(255) NOT NULL,
    teus INT NOT NULL,
    PRIMARY KEY (imo)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;
