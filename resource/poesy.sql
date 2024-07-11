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
-- Create the image table
CREATE TABLE IF NOT EXISTS `image` (
    -- use uuid for id
    `id` VARCHAR(36) NOT NULL,
    `content` LONGBLOB NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- Create the question table
CREATE TABLE IF NOT EXISTS `question` (
    -- use uuid for id
    `id` VARCHAR(36) NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL,
    `author-email` VARCHAR(255) NOT NULL,
    `createdtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`author-email`) REFERENCES `user`(`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- Create the article table
CREATE TABLE IF NOT EXISTS `article` (
    -- use uuid for id
    `id` VARCHAR(36) NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL,
    `author-email` VARCHAR(255) NOT NULL,
    `createdtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`author-email`) REFERENCES `user`(`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- Create the answer table
CREATE TABLE IF NOT EXISTS `answer` (
    -- use uuid for id
    `id` VARCHAR(36) NOT NULL,
    `content` TEXT NOT NULL,
    `author-email` VARCHAR(255) NOT NULL,
    `question-id` VARCHAR(36) NOT NULL,
    `createdtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`author-email`) REFERENCES `user`(`email`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`question-id`) REFERENCES `question`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- Create the answercomment table
CREATE TABLE IF NOT EXISTS `answercomment` (
    -- use uuid for id
    `id` VARCHAR(36) NOT NULL,
    `content` TEXT NOT NULL,
    `author-email` VARCHAR(255) NOT NULL,
    `answer-id` VARCHAR(36) NOT NULL,
    `createdtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`author-email`) REFERENCES `user`(`email`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`answer-id`) REFERENCES `answer`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
-- Create the articlecomment table
CREATE TABLE IF NOT EXISTS `articlecomment` (
    -- use uuid for id
    `id` VARCHAR(36) NOT NULL,
    `content` TEXT NOT NULL,
    `author-email` VARCHAR(255) NOT NULL,
    `article-id` VARCHAR(36) NOT NULL,
    `createdtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`author-email`) REFERENCES `user`(`email`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`article-id`) REFERENCES `article`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
