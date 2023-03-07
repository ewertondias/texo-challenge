CREATE TABLE IF NOT EXISTS `Award` (
    `id`            UUID            DEFAULT RANDOM_UUID() PRIMARY KEY,
    `year`          VARCHAR(5)      NOT NULL,
    `title`         VARCHAR(255)    NOT NULL,
    `studio`        VARCHAR(255)    NOT NULL,
    `producer`      VARCHAR(255)    NOT NULL,
    `winner`        VARCHAR(5)      NOT NULL
);