-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema enchanteddb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `enchanteddb` ;

-- -----------------------------------------------------
-- Schema enchanteddb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enchanteddb` DEFAULT CHARACTER SET utf8 ;
USE `enchanteddb` ;

-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip_code` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `location_id` INT NULL,
  `enabled` TINYINT(1) NOT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_location1_idx` (`location_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_user_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `company` ;

CREATE TABLE IF NOT EXISTS `company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `overview` VARCHAR(10000) NULL,
  `location_id` INT NOT NULL,
  `isDisabled` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_company_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_company_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_posting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_posting` ;

CREATE TABLE IF NOT EXISTS `job_posting` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `description` VARCHAR(1000) NULL,
  `company_id` INT NOT NULL,
  `minimum_salary` INT NULL,
  `maximum_salary` INT NULL,
  `created_at` DATE NULL,
  `updated_at` DATE NULL,
  `work_type` VARCHAR(45) NULL,
  `pay_type` VARCHAR(45) NULL,
  `positions` INT NULL,
  `location_id` INT NOT NULL,
  `isDisabled` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Job Posting_Company_idx` (`company_id` ASC),
  INDEX `fk_job_posting_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_Job Posting_Company`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_posting_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `application` ;

CREATE TABLE IF NOT EXISTS `application` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `job_posting_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_application_Job Posting1_idx` (`job_posting_id` ASC),
  INDEX `fk_application_User1_idx` (`user_id` ASC),
  CONSTRAINT `fk_application_Job Posting1`
    FOREIGN KEY (`job_posting_id`)
    REFERENCES `job_posting` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_application_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_imgs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `company_imgs` ;

CREATE TABLE IF NOT EXISTS `company_imgs` (
  `id` INT NOT NULL,
  `company_id` INT NOT NULL,
  `imgUrl` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_company_imgs_Company1_idx` (`company_id` ASC),
  CONSTRAINT `fk_company_imgs_Company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_requirements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_requirements` ;

CREATE TABLE IF NOT EXISTS `job_requirements` (
  `id` INT NOT NULL,
  `job_posting_id` INT NOT NULL,
  `name` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_job_requirements_Job Posting1_idx` (`job_posting_id` ASC),
  CONSTRAINT `fk_job_requirements_Job Posting1`
    FOREIGN KEY (`job_posting_id`)
    REFERENCES `job_posting` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_benefits`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_benefits` ;

CREATE TABLE IF NOT EXISTS `job_benefits` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_benefits_has_job_posting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_benefits_has_job_posting` ;

CREATE TABLE IF NOT EXISTS `job_benefits_has_job_posting` (
  `job_benefits_id` INT NOT NULL,
  `job_posting_id` INT NOT NULL,
  PRIMARY KEY (`job_benefits_id`, `job_posting_id`),
  INDEX `fk_job_benefits_has_job_posting_job_posting1_idx` (`job_posting_id` ASC),
  INDEX `fk_job_benefits_has_job_posting_job_benefits1_idx` (`job_benefits_id` ASC),
  CONSTRAINT `fk_job_benefits_has_job_posting_job_benefits1`
    FOREIGN KEY (`job_benefits_id`)
    REFERENCES `job_benefits` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_benefits_has_job_posting_job_posting1`
    FOREIGN KEY (`job_posting_id`)
    REFERENCES `job_posting` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS enchanted@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'enchanted'@'localhost' IDENTIFIED BY 'enchanted';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'enchanted'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `enchanteddb`;
INSERT INTO `user` (`id`, `username`, `password`, `location_id`, `enabled`, `role`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', NULL, 1, NULL);

COMMIT;

