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
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  `role` VARCHAR(45) NULL,
  `created_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip_code` VARCHAR(45) NULL,
  `street` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `company` ;

CREATE TABLE IF NOT EXISTS `company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `overview` VARCHAR(10000) NULL,
  `address_id` INT NOT NULL,
  `enabled` TINYINT(1) NULL,
  `logo` VARCHAR(2000) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_company_location1_idx` (`address_id` ASC),
  INDEX `fk_company_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_company_location1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_company_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `industry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `industry` ;

CREATE TABLE IF NOT EXISTS `industry` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_posting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_posting` ;

CREATE TABLE IF NOT EXISTS `job_posting` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `company_id` INT NOT NULL,
  `minimum_salary` INT NULL,
  `maximum_salary` INT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `positions` INT NULL,
  `address_id` INT NOT NULL,
  `enabled` TINYINT(1) NULL,
  `requirements` TEXT NULL,
  `industry_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Job Posting_Company_idx` (`company_id` ASC),
  INDEX `fk_job_posting_location1_idx` (`address_id` ASC),
  INDEX `fk_job_posting_industry1_idx` (`industry_id` ASC),
  CONSTRAINT `fk_Job Posting_Company`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_posting_location1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_posting_industry1`
    FOREIGN KEY (`industry_id`)
    REFERENCES `industry` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `application_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `application_status` ;

CREATE TABLE IF NOT EXISTS `application_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `applicant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `applicant` ;

CREATE TABLE IF NOT EXISTS `applicant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address_id` INT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `birthdate` DATE NULL,
  `email` VARCHAR(200) NULL,
  `eligible_to_work` TINYINT NULL,
  `relocation` TINYINT NULL,
  `profile_img` VARCHAR(2000) NULL,
  `user_id` INT NOT NULL,
  `updated_at` DATETIME NULL,
  `about_me` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_location1_idx` (`address_id` ASC),
  INDEX `fk_applicant_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_location10`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_applicant_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
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
  `application_status_id` INT NOT NULL,
  `applicant_id` INT NOT NULL,
  `notes` TEXT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_application_Job Posting1_idx` (`job_posting_id` ASC),
  INDEX `fk_application_application_status1_idx` (`application_status_id` ASC),
  INDEX `fk_application_applicant1_idx` (`applicant_id` ASC),
  CONSTRAINT `fk_application_Job Posting1`
    FOREIGN KEY (`job_posting_id`)
    REFERENCES `job_posting` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_application_application_status1`
    FOREIGN KEY (`application_status_id`)
    REFERENCES `application_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_application_applicant1`
    FOREIGN KEY (`applicant_id`)
    REFERENCES `applicant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_benefit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_benefit` ;

CREATE TABLE IF NOT EXISTS `job_benefit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_benefit_has_job_posting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_benefit_has_job_posting` ;

CREATE TABLE IF NOT EXISTS `job_benefit_has_job_posting` (
  `job_benefit_id` INT NOT NULL,
  `job_posting_id` INT NOT NULL,
  PRIMARY KEY (`job_benefit_id`, `job_posting_id`),
  INDEX `fk_job_benefits_has_job_posting_job_posting1_idx` (`job_posting_id` ASC),
  INDEX `fk_job_benefits_has_job_posting_job_benefits1_idx` (`job_benefit_id` ASC),
  CONSTRAINT `fk_job_benefits_has_job_posting_job_benefits1`
    FOREIGN KEY (`job_benefit_id`)
    REFERENCES `job_benefit` (`id`)
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
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `created_at`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, NULL, NULL);

COMMIT;

