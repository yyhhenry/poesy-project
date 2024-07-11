-- Create the database and the user table
CREATE DATABASE IF NOT EXISTS `poesy`;
USE `poesy`;
-- Create the user table
CREATE TABLE IF NOT EXISTS `user` (
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    -- Avatar will be got from gravatar
    PRIMARY KEY (`email`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- In redis we have `pending-user` for users who have not activated their account
-- "pending-user:<email>" -> {password: <password>, code: <code>}
