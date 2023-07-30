CREATE TABLE IF NOT EXISTS ships (
    imo CHAR(7) NOT NULL,
    name VARCHAR(255) NOT NULL,
    teus INT NOT NULL,
    PRIMARY KEY (imo)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS domain_events
(
    id           CHAR(36)     NOT NULL,
    aggregate_id CHAR(36)     NOT NULL,
    name         VARCHAR(255) NOT NULL,
    payload      JSON         NOT NULL,
    occurred_on  DATETIME(6)  NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;
