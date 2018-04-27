ALTER TABLE `user` DROP `name`;
ALTER TABLE `user` ADD `register_date` TIMESTAMP NOT NULL;
ALTER TABLE `user` ADD `avatar` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL ;