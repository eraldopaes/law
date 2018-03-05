CREATE TABLE `addresses` (
  `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `address`     VARCHAR(200) NOT NULL,
  `complement`  VARCHAR(200) NULL,
  `postal_code` VARCHAR(8)   NOT NULL,
  `number`      INT(6)       NOT NULL,
  `district`    VARCHAR(50)  NOT NULL,
  `state_id`    BIGINT(20)   NOT NULL,
  `city_id`     BIGINT(20)   NOT NULL,
  `client_id`   BIGINT(20)   NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_state_id_idx` (`state_id` ASC),
  INDEX `fk_city_id_idx` (`city_id` ASC),
  INDEX `fk_client_id_idx` (`client_id` ASC),
  CONSTRAINT `fk_state_id` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_city_id` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clients_id` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
