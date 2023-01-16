-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema periodicalsdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema periodicalsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `periodicalsdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `periodicalsdb` ;

-- -----------------------------------------------------
-- Table `periodicalsdb`.`admins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`admins` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `periodicalsdb`.`publications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`publications` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title_ua` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  `title_en` VARCHAR(45) NOT NULL,
  `description_ua` VARCHAR(45) NULL DEFAULT NULL,
  `description_en` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_ua_UNIQUE` (`title_ua` ASC) VISIBLE,
  UNIQUE INDEX `title_en_UNIQUE` (`title_en` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `periodicalsdb`.`topics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`topics` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title_ua` VARCHAR(45) NOT NULL,
  `title_en` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_ua_UNIQUE` (`title_ua` ASC) VISIBLE,
  UNIQUE INDEX `title_en_UNIQUE` (`title_en` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `periodicalsdb`.`topics_publications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`topics_publications` (
  `topic_id` INT NOT NULL,
  `publication_id` INT NOT NULL,
  INDEX `topic_id_idx` (`topic_id` ASC) VISIBLE,
  INDEX `publication_id_idx` (`publication_id` ASC) VISIBLE,
  CONSTRAINT `publication_id_`
    FOREIGN KEY (`publication_id`)
    REFERENCES `periodicalsdb`.`publications` (`id`),
  CONSTRAINT `topic_id`
    FOREIGN KEY (`topic_id`)
    REFERENCES `periodicalsdb`.`topics` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `periodicalsdb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `account` FLOAT NOT NULL DEFAULT '0',
  `login` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NULL DEFAULT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `periodicalsdb`.`users_publications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicalsdb`.`users_publications` (
  `user_id` INT NOT NULL,
  `publication_id` INT NOT NULL,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `publication_id_idx` (`publication_id` ASC) VISIBLE,
  CONSTRAINT `publication_id`
    FOREIGN KEY (`publication_id`)
    REFERENCES `periodicalsdb`.`publications` (`id`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `periodicalsdb`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
