CREATE TABLE IF NOT EXISTS routes
(
    id              CHAR(36) NOT NULL,
    originPort      CHAR(36) NOT NULL,
    destinationPort CHAR(36) NOT NULL,
    path            JSON     NOT NULL,
    journeys        BIGINT   NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS journeys
(
    id              CHAR(36) NOT NULL,
    shipId          CHAR(7)  NOT NULL,
    teus            INT      NOT NULL,
    originPort      CHAR(36),
    destinationPort CHAR(36),
    departureDate   TIMESTAMP(3),
    arrivalDate     TIMESTAMP(3),
    path            JSON,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS journeys_month
(
    id              CHAR(36) NOT NULL,
    originPort      CHAR(36) NOT NULL,
    destinationPort CHAR(36) NOT NULL,
    path            JSON,
    month           INT      NOT NULL,
    year            INT      NOT NULL,
    journeys        BIGINT   NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS journeys_year
(
    id              CHAR(36) NOT NULL,
    originPort      CHAR(36) NOT NULL,
    destinationPort CHAR(36) NOT NULL,
    path            JSON,
    year            INT      NOT NULL,
    journeys        BIGINT   NOT NULL,
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
