ALTER TABLE `user` DROP INDEX `nickname`;
ALTER TABLE `artise`.`user` ADD UNIQUE `email` (`email`);
ALTER TABLE `user` CHANGE `nickname` `nickname` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
