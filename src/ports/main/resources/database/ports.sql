CREATE TABLE IF NOT EXISTS ports
(
    id CHAR
(
    36
) NOT NULL,
    name VARCHAR
(
    255
) NOT NULL,
    locode CHAR
(
    5
) NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE,
    departures BIGINT,
    arrivals BIGINT,
    PRIMARY KEY
(
    id
)
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS port_events
(
    id CHAR
(
    36
) NOT NULL,
    type CHAR
(
    16
) NOT NULL,
    portId CHAR
(
    36
) NOT NULL,
    portName VARCHAR
(
    255
) NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE,
    shipId CHAR
(
    7
) NOT NULL,
    teus INT NOT NULL,
    date TIMESTAMP
(
    3
) NOT NULL,
    PRIMARY KEY
(
    id
)
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS port_events_month
(
    id CHAR
(
    36
) NOT NULL,
    portId CHAR
(
    36
) NOT NULL,
    portName VARCHAR
(
    255
) NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE,
    year INT NOT NULL,
    month INT NOT NULL,
    departures BIGINT NOT NULL,
    arrivals BIGINT NOT NULL,
    teus INT NOT NULL,
    PRIMARY KEY
(
    id
)
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS port_events_year
(
    id CHAR
(
    36
) NOT NULL,
    portId CHAR
(
    36
) NOT NULL,
    portName VARCHAR
(
    255
) NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE,
    year INT NOT NULL,
    departures BIGINT NOT NULL,
    arrivals BIGINT NOT NULL,
    teus INT NOT NULL,
    PRIMARY KEY
(
    id
)
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS ships
(
    imo CHAR
(
    7
) NOT NULL,
    teus INT NOT NULL,
    PRIMARY KEY
(
    imo
)
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS domain_events
(
    id CHAR
(
    36
) NOT NULL,
    aggregate_id CHAR
(
    36
) NOT NULL,
    name VARCHAR
(
    255
) NOT NULL,
    payload JSON NOT NULL,
    occurred_on DATETIME
(
    6
) NOT NULL,
    PRIMARY KEY
(
    id
)
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;
