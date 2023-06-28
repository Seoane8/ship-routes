CREATE TABLE IF NOT EXISTS ports
(
    id        CHAR(36)     NOT NULL,
    name      VARCHAR(255) NOT NULL,
    locode    CHAR(5)      NOT NULL,
    latitude  DOUBLE,
    longitude DOUBLE,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS arrivals
(
    id        CHAR(36)     NOT NULL,
    portId    CHAR(36)     NOT NULL,
    latitude  DOUBLE,
    longitude DOUBLE,
    shipId    CHAR(7)      NOT NULL,
    teus      INT          NOT NULL,
    date      TIMESTAMP(3) NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS departures
(
    id        CHAR(36)     NOT NULL,
    portId    CHAR(36)     NOT NULL,
    latitude  DOUBLE,
    longitude DOUBLE,
    shipId    CHAR(7)      NOT NULL,
    teus      INT          NOT NULL,
    date      TIMESTAMP(3) NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS ships
(
    imo  CHAR(7) NOT NULL,
    teus INT     NOT NULL,
    PRIMARY KEY (imo)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;
