CREATE TABLE `artise`.`profession` ( `id` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(50) NOT NULL , PRIMARY KEY (`id`), UNIQUE `uprofname` (`name`));
CREATE TABLE `artise`.`category` ( `id` INT NOT NULL AUTO_INCREMENT , `profession_id` INT NOT NULL , `name` VARCHAR(50) NOT NULL , PRIMARY KEY (`id`));
ALTER TABLE `category` ADD FOREIGN KEY (`profession_id`) REFERENCES `profession`(`id`) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE `artise`.`user` ( `id` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(50) NOT NULL , `surname` VARCHAR(50) NOT NULL , `nickname` VARCHAR(50) NOT NULL , `email` VARCHAR(50) NOT NULL , `password` VARCHAR(255) NOT NULL , `phone` VARCHAR(50) NULL , `info` VARCHAR(255) NULL , PRIMARY KEY (`id`), UNIQUE (`nickname`));

CREATE TABLE `artise`.`user_category` ( `id` INT NOT NULL AUTO_INCREMENT , `user_id` INT NOT NULL , `category_id` INT NOT NULL , `rating` FLOAT NULL , PRIMARY KEY (`id`));

ALTER TABLE `user_category` ADD FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `user_category` ADD FOREIGN KEY (`category_id`) REFERENCES `category`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE `artise`.`review` ( `id` INT NOT NULL AUTO_INCREMENT , `user_category_id` INT NOT NULL , `user_id` INT NOT NULL , `text` VARCHAR(255) NULL , `rating` INT NOT NULL , PRIMARY KEY (`id`));
ALTER TABLE `review` ADD FOREIGN KEY (`user_category_id`) REFERENCES `user_category`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `review` ADD FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE `artise`.`service` ( `id` INT NOT NULL AUTO_INCREMENT , `user_category_id` INT NOT NULL , `name` VARCHAR(50) NOT NULL , `description` VARCHAR(255) NULL , `price` FLOAT NOT NULL , `duration_minutes` INT NOT NULL , PRIMARY KEY (`id`));
ALTER TABLE `service` ADD FOREIGN KEY (`user_category_id`) REFERENCES `user_category`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE `artise`.`user_service` ( `id` INT NOT NULL AUTO_INCREMENT , `user_id` INT NOT NULL , `service_id` INT NOT NULL , `time` DATETIME NOT NULL , `status` INT NOT NULL DEFAULT '0' , `price` FLOAT NOT NULL , PRIMARY KEY (`id`));
ALTER TABLE `user_service` ADD FOREIGN KEY (`service_id`) REFERENCES `service`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `user_service` ADD FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE `artise`.`notes` ( `id` INT NOT NULL , `user_service_id` INT NOT NULL , `text` VARCHAR(255) NOT NULL );
ALTER TABLE `notes` ADD FOREIGN KEY (`user_service_id`) REFERENCES `user_service`(`id`) ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE `artise`.`connection` ( `id` INT NOT NULL AUTO_INCREMENT , `user_init_id` INT NOT NULL , `user_to_connect_id` INT NOT NULL , `status` INT NOT NULL DEFAULT '0' , PRIMARY KEY (`id`));
ALTER TABLE `connection` ADD FOREIGN KEY (`user_init_id`) REFERENCES `user`(`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `connection` ADD FOREIGN KEY (`user_to_connect_id`) REFERENCES `user`(`id`) ON DELETE CASCADE ON UPDATE CASCADE;
