CREATE TABLE `clients` (
  `id`        BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `name`      VARCHAR(200) NOT NULL,
  `last_name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;