CREATE TABLE spitter(
    `id` INT NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(30) NOT NULL,
    `lastName` VARCHAR(30) NOT NULL,
    `username` VARCHAR(30) NOT NULL,
    `password` VARCHAR(30) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE spittle(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `message` VARCHAR(255) NOT NULL,
    `time` TIMESTAMP NOT NULL,
    `latitude` DOUBLE DEFAULT 0,
    `longitude` DOUBLE DEFAULT 0,
    PRIMARY KEY(`id`)
);



