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
  `street` VARCHAR(100) NULL,
  `state` VARCHAR(45) NULL,
  `zip_code` VARCHAR(45) NULL,
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
  `address_id` INT NULL,
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
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `created_at`) VALUES (2, 'foundinguser', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Applicant', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `created_at`) VALUES (3, 'foundingcompany', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Company', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `created_at`) VALUES (4, 'apexcapital', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Company', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `created_at`) VALUES (5, 'stellarmart', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Company', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `created_at`) VALUES (6, 'sereneescape', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Company', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `created_at`) VALUES (7, 'academia', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Company', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `created_at`) VALUES (8, 'precisionworks', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Company', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `created_at`) VALUES (9, 'urbanbuilders', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Company', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `created_at`) VALUES (10, 'horizonprop', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Company', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `created_at`) VALUES (11, 'ecopower', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Company', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `enchanteddb`;
INSERT INTO `address` (`id`, `city`, `street`, `state`, `zip_code`) VALUES (1, 'Wonka', 'Willy Wonka Way', 'Land', '12345');
INSERT INTO `address` (`id`, `city`, `street`, `state`, `zip_code`) VALUES (2, 'New York', '1234 First St', 'New York', '12345');
INSERT INTO `address` (`id`, `city`, `street`, `state`, `zip_code`) VALUES (3, 'Chicago', '2345 Second St', 'Illinois', '12345');
INSERT INTO `address` (`id`, `city`, `street`, `state`, `zip_code`) VALUES (4, 'Miami', '3456 Third St', 'Florida', '12345');
INSERT INTO `address` (`id`, `city`, `street`, `state`, `zip_code`) VALUES (5, 'Dallas', '4567 Fourth St', 'Texas', '12345');
INSERT INTO `address` (`id`, `city`, `street`, `state`, `zip_code`) VALUES (6, 'Houston', '5678 Fifth St', 'Texas', '12345');
INSERT INTO `address` (`id`, `city`, `street`, `state`, `zip_code`) VALUES (7, 'Seattle', '6789 Sixth St', 'Washington', '12345');
INSERT INTO `address` (`id`, `city`, `street`, `state`, `zip_code`) VALUES (8, 'Portland', '7890 Seventh St', 'Oregon', '12345');
INSERT INTO `address` (`id`, `city`, `street`, `state`, `zip_code`) VALUES (9, 'Tuscon', '8901 Eight St', 'Arizona', '12345');
INSERT INTO `address` (`id`, `city`, `street`, `state`, `zip_code`) VALUES (10, 'San Jose', '9012 Ninth St', 'California', '12345');
INSERT INTO `address` (`id`, `city`, `street`, `state`, `zip_code`) VALUES (11, 'Los Angeles', '0123 Tenth St', 'California', '12345');

COMMIT;


-- -----------------------------------------------------
-- Data for table `company`
-- -----------------------------------------------------
START TRANSACTION;
USE `enchanteddb`;
INSERT INTO `company` (`id`, `name`, `overview`, `address_id`, `enabled`, `logo`, `user_id`) VALUES (1, 'MediLife Solutions', 'MediLife Solutions is a cutting-edge healthcare company specializing in personalized medicine. Using advanced technologies and innovative approaches, we provide tailored treatment plans and therapies to individuals worldwide. From genetic testing to precision medicine, our team of experts is dedicated to improving patient outcomes and revolutionizing the healthcare industry.', 2, 1, 'https://media.licdn.com/dms/image/C5612AQFPA97NHw212A/article-cover_image-shrink_600_2000/0/1520155840320?e=2147483647&v=beta&t=1jCjsZ_9KUmsqELFD5wkXVE96IMx3Npc33Mdi8osodM', 3);
INSERT INTO `company` (`id`, `name`, `overview`, `address_id`, `enabled`, `logo`, `user_id`) VALUES (2, 'NexusTech Innovations', 'NexusTech Innovations is a leading technology company at the forefront of digital transformation. We offer a comprehensive suite of software solutions, including AI-powered analytics, cloud computing, and cybersecurity services. Our hardware division specializes in designing and manufacturing state-of-the-art devices for IoT applications and smart infrastructure. With a focus on innovation and agility, NexusTech is shaping the future of technology.', 3, 1, 'https://st3.depositphotos.com/3258807/18139/i/450/depositphotos_181392818-stock-photo-jolly-four-colleagues-calculating-profits.jpg', 2);
INSERT INTO `company` (`id`, `name`, `overview`, `address_id`, `enabled`, `logo`, `user_id`) VALUES (3, 'Apex Capital Partners', 'Apex Capital Partners is a premier financial services firm providing a wide range of investment solutions to clients worldwide. From wealth management to corporate finance, our team of seasoned professionals delivers tailored strategies and expert advice to help our clients achieve their financial goals. With a commitment to integrity and excellence, Apex Capital Partners is a trusted partner in the global financial markets.', 4, 1, 'https://www.referenceforbusiness.com/photos/generic-competitive-strategies-54.jpg', 4);
INSERT INTO `company` (`id`, `name`, `overview`, `address_id`, `enabled`, `logo`, `user_id`) VALUES (4, 'StellarMart', 'StellarMart is a leading retail chain offering a diverse range of products and services to customers both online and in-store. From electronics to fashion, home goods to groceries, we provide quality products at competitive prices. With a seamless shopping experience and personalized customer service, StellarMart is redefining retail excellence.', 5, 1, 'https://www.referenceforbusiness.com/photos/generic-competitive-strategies-54.jpg', 5);
INSERT INTO `company` (`id`, `name`, `overview`, `address_id`, `enabled`, `logo`, `user_id`) VALUES (5, 'Serene Escapes Hospitality', 'Serene Escapes Hospitality is a luxury hospitality company dedicated to providing unforgettable experiences for travelers around the world. From boutique hotels to exclusive resorts, our properties offer unparalleled service and amenities in breathtaking locations. Whether you\'re seeking relaxation or adventure, Serene Escapes Hospitality promises a memorable journey filled with warmth and hospitality.', 6, 1, 'https://st2.depositphotos.com/2251265/10034/i/450/depositphotos_100340000-stock-photo-businessman-working-in-modern-cretive.jpg', 6);
INSERT INTO `company` (`id`, `name`, `overview`, `address_id`, `enabled`, `logo`, `user_id`) VALUES (6, 'AcademiaX Learning Solutions', 'AcademiaX Learning Solutions is an innovative education company committed to empowering learners of all ages through personalized and engaging learning experiences. From K-12 curriculum development to professional development programs, we leverage cutting-edge technology and pedagogical research to deliver high-quality educational solutions. With a focus on lifelong learning and skill development, AcademiaX is shaping the future of education.', 7, 1, 'https://media.licdn.com/dms/image/D4D12AQFH-8enEuc-og/article-cover_image-shrink_600_2000/0/1689858521164?e=2147483647&v=beta&t=0Yio3qskLKPxF4RkMgUuRMpIxNjZxmyymQpE7ZH7DJg', 7);
INSERT INTO `company` (`id`, `name`, `overview`, `address_id`, `enabled`, `logo`, `user_id`) VALUES (7, 'PrecisionWorks Industries', 'PrecisionWorks Industries is a leading manufacturing company specializing in precision engineering and advanced manufacturing solutions. From aerospace components to consumer electronics, we deliver high-quality products with unmatched precision and reliability. With state-of-the-art facilities and a skilled workforce, PrecisionWorks Industries is a trusted partner for complex manufacturing projects.', 8, 1, 'https://media.istockphoto.com/id/530718355/vector/handshake.jpg?s=612x612&w=0&k=20&c=pRdCN0_NhZ_stAK3ZPNfTrPx8Z8KuwOgjrXrRCjW3Uw=', 8);
INSERT INTO `company` (`id`, `name`, `overview`, `address_id`, `enabled`, `logo`, `user_id`) VALUES (8, 'UrbanBuilders Solutions', 'UrbanBuilders Solutions is a premier construction company specializing in residential, commercial, and infrastructure projects. From high-rise buildings to transportation hubs, we deliver innovative solutions that meet the highest standards of quality and sustainability. With a focus on collaboration and excellence, UrbanBuilders Solutions is reshaping skylines and communities worldwide.', 9, 1, 'https://media.istockphoto.com/id/1333820977/photo/young-business-woman-using-a-digital-tablet.jpg?s=612x612&w=0&k=20&c=ScWnRUoOvbbPlPmrkideZ0rjDQg0VdL30C3jTrAq1GI=', 9);
INSERT INTO `company` (`id`, `name`, `overview`, `address_id`, `enabled`, `logo`, `user_id`) VALUES (9, 'Horizon Properties Group', 'Horizon Properties Group is a leading real estate development and investment firm with a diverse portfolio of properties across residential, commercial, and mixed-use sectors. From master-planned communities to urban revitalization projects, we create vibrant spaces that enrich lives and enhance communities. With a commitment to innovation and sustainability, Horizon Properties Group is shaping the future of real estate.', 10, 1, 'https://media.istockphoto.com/id/169987078/photo/business-people-silhouette-staring-at-sunset.jpg?s=612x612&w=0&k=20&c=8McBB5FpE4JIzpJ0vv8DR1G_1-PrRI_uOLhPOsHm4uU=', 10);
INSERT INTO `company` (`id`, `name`, `overview`, `address_id`, `enabled`, `logo`, `user_id`) VALUES (10, 'EcoPower Solutions', 'EcoPower Solutions is a renewable energy company committed to driving the transition to a sustainable energy future. From solar and wind to hydro and geothermal, we provide innovative solutions that harness the power of nature to meet the world\'s energy needs. With a focus on efficiency and environmental stewardship, EcoPower Solutions is leading the charge towards a cleaner, greener planet.', 11, 1, 'https://thumbs.dreamstime.com/b/business-people-meeting-discussion-cityscape-concept-60793301.jpg', 11);

COMMIT;


-- -----------------------------------------------------
-- Data for table `industry`
-- -----------------------------------------------------
START TRANSACTION;
USE `enchanteddb`;
INSERT INTO `industry` (`id`, `name`) VALUES (1, 'Healthcare');
INSERT INTO `industry` (`id`, `name`) VALUES (2, 'Technology');
INSERT INTO `industry` (`id`, `name`) VALUES (3, 'Finance');
INSERT INTO `industry` (`id`, `name`) VALUES (4, 'Retail');
INSERT INTO `industry` (`id`, `name`) VALUES (5, 'Hospitality');
INSERT INTO `industry` (`id`, `name`) VALUES (6, 'Education');
INSERT INTO `industry` (`id`, `name`) VALUES (7, 'Manufacturing');
INSERT INTO `industry` (`id`, `name`) VALUES (8, 'Real Estate');
INSERT INTO `industry` (`id`, `name`) VALUES (9, 'Construction');
INSERT INTO `industry` (`id`, `name`) VALUES (10, 'Energy');

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_posting`
-- -----------------------------------------------------
START TRANSACTION;
USE `enchanteddb`;
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (1, 'Research Scientist', 'MedLife Solutions, a leading biopharmaceutical company, is seeking a highly motivated Research Scientist to join our innovative team. The ideal candidate will conduct research and development activities focusing on the discovery of novel therapeutic targets and drug candidates. Responsibilities include designing and executing experiments, analyzing data, and presenting findings to cross-functional teams. Candidates should have a Ph.D. in biochemistry, pharmacology, or related field, with experience in drug discovery and proficiency in molecular biology techniques.', 1, 80000, 100000, NULL, NULL, 1, 1, 1, NULL, 1);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (2, 'Clinical Research Coordinator', 'MedStar Pharmaceuticals is hiring a Clinical Research Coordinator to support our clinical trials team. The successful candidate will be responsible for coordinating all aspects of clinical research studies, including participant recruitment, regulatory compliance, and data management. Applicants should have a Bachelor\'s degree in a health-related field and prior experience in clinical research coordination. Strong organizational skills and attention to detail are essential.', 1, 50000, 60000, NULL, NULL, 1, 1, 1, NULL, 1);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (3, 'Senior Software Engineer (Backend)', 'Apex Capital, a dynamic software development firm, is seeking a talented Senior Software Engineer to join our backend engineering team. The ideal candidate will have extensive experience in designing and implementing scalable backend systems using technologies such as Java, Python, or Node.js. Responsibilities include architecture design, coding, testing, and debugging complex software solutions. Candidates should have a Bachelor\'s degree in Computer Science or related field, along with strong problem-solving skills and a passion for technology innovation.', 2, 100000, 120000, NULL, NULL, 2, 2, 1, NULL, 2);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (4, 'IT Support Specialist', 'Apex Capital is hiring an IT Support Specialist to provide technical assistance and support to our internal teams. The successful candidate will troubleshoot hardware and software issues, deploy and configure new equipment, and provide training to end-users as needed. Applicants should have a solid understanding of IT fundamentals, including networking, operating systems, and productivity software. Excellent communication skills and a customer-centric approach are essential for this role.', 2, 60000, 75000, NULL, NULL, 2, 2, 1, NULL, 2);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (5, 'Financial Analyst', 'Nexus Bank, a leading financial institution, is seeking a skilled Financial Analyst to join our corporate finance team. The ideal candidate will perform financial modeling, analysis, and forecasting to support strategic decision-making and business planning initiatives. Responsibilities include analyzing financial data, preparing reports, and providing recommendations to senior management. Candidates should have a Bachelor\'s degree in Finance, Accounting, or Economics, with proficiency in Excel and financial modeling techniques.', 3, 60000, 80000, NULL, NULL, 1, 3, 1, NULL, 3);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (6, 'Risk Management Associate', 'Nexus Bank is hiring a Risk Management Associate to assist in identifying, assessing, and mitigating operational and financial risks. The successful candidate will participate in risk assessments, develop risk management strategies, and monitor compliance with regulatory requirements. Applicants should have a Bachelor\'s degree in Business, Finance, or related field, with strong analytical skills and attention to detail. Prior experience in risk management or auditing is preferred.', 3, 50000, 65000, NULL, NULL, 1, 3, 1, NULL, 3);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (7, 'E-commerce Marketing Manager', 'BrightMart, a rapidly growing online retailer, is seeking an experienced E-commerce Marketing Manager to drive customer acquisition and retention strategies. The ideal candidate will develop and execute digital marketing campaigns across various channels, including email, social media, and paid advertising. Responsibilities include analyzing campaign performance, optimizing conversion funnels, and implementing A/B testing strategies. Candidates should have a Bachelor\'s degree in Marketing or related field, with proven experience in e-commerce marketing and proficiency in analytics tools.', 4, 90000, 130000, NULL, NULL, 1, 4, 1, NULL, 4);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (8, 'Retail Store Manager\nRetail Store Manager\n', 'BrightMart is hiring a Retail Store Manager to oversee daily operations and drive sales growth in our brick-and-mortar store. The successful candidate will manage store staff, ensure excellent customer service, and implement merchandising strategies to maximize store profitability. Applicants should have prior experience in retail management, strong leadership skills, and a passion for delivering exceptional shopping experiences.', 4, 80000, 110000, NULL, NULL, 1, 4, 1, NULL, 4);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (9, 'Guest Relations Manager', 'Horizon Hotels, a luxury hospitality brand, is seeking a Guest Relations Manager to ensure exceptional guest experiences at our flagship property. The ideal candidate will oversee front desk operations, handle guest inquiries and complaints, and coordinate special requests to exceed guest expectations. Responsibilities also include training and supervising front desk staff and implementing guest service standards. Candidates should have prior experience in hotel guest services, strong communication skills, and a commitment to customer satisfaction.\n', 5, 65000, 80000, NULL, NULL, 1, 5, 1, NULL, 5);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (10, 'Event Coordinator\n', 'Horizon Hotels is hiring an Event Coordinator to manage corporate and social events hosted at our hotel venues. The successful candidate will work closely with clients to plan and execute events, coordinate logistics, and ensure seamless execution from setup to breakdown. Applicants should have strong organizational and multitasking skills, with prior experience in event planning and hospitality operations.', 5, 70000, 80000, NULL, NULL, 4, 5, 1, NULL, 5);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (11, 'Admissions Counselor', 'Nova Academy, a prestigious private school, is seeking an Admissions Counselor to recruit and enroll students for the upcoming academic year. The ideal candidate will represent the school to prospective families, conduct campus tours, and guide applicants through the admissions process. Responsibilities also include reviewing applications, interviewing candidates, and collaborating with the admissions team to meet enrollment goals. Candidates should have a Bachelor\'s degree in Education or related field, with excellent communication and interpersonal skills.', 6, 55000, 65000, NULL, NULL, 2, 6, 1, NULL, 6);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (12, 'Online Course Instructor', 'Nova Academy is hiring Online Course Instructors to teach virtual classes in various subjects for K-12 students. The successful candidates will develop engaging curriculum materials, facilitate live online sessions, and provide feedback and support to students. Applicants should have teaching experience and expertise in their subject area, along with proficiency in online teaching platforms and technology tools.', 6, 85000, 120000, NULL, NULL, 1, 6, 1, NULL, 6);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (13, 'Quality Control Inspector', 'PrecisionTech Industries, a leading manufacturer of precision components, is seeking a detail-oriented Quality Control Inspector to ensure product quality and compliance with specifications. The ideal candidate will perform visual and dimensional inspections of manufactured parts, identify defects or deviations, and document inspection results. Responsibilities also include maintaining inspection equipment and supporting continuous improvement initiatives. Candidates should have prior experience in quality control within a manufacturing environment and familiarity with measurement tools.', 7, 100000, 115000, NULL, NULL, 5, 7, 1, NULL, 7);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (14, 'Production Supervisor', 'PrecisionTech Industries is hiring a Production Supervisor to oversee manufacturing operations and lead a team of production workers. The successful candidate will ensure production schedules are met, monitor workflow, and implement process improvements to enhance efficiency and productivity. Applicants should have previous supervisory experience in manufacturing, strong leadership skills, and a commitment to safety and quality standards.', 7, 75000, 100000, NULL, NULL, 1, 7, 1, NULL, 7);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (15, 'BuildRight Construction', 'BuildRight Construction, a reputable general contractor, is seeking a seasoned Project Manager to oversee commercial construction projects from conception to completion. The ideal candidate will be responsible for project planning, scheduling, budgeting, and resource allocation, while ensuring compliance with safety and quality standards. Responsibilities also include managing subcontractors, resolving construction issues, and communicating with clients and stakeholders. Candidates should have a Bachelor\'s degree in Construction Management or related field, along with proven experience in project management within the construction industry.\n', 8, 70000, 120000, NULL, NULL, 1, 8, 1, NULL, 8);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (16, 'Construction Laborer', 'BuildRight Construction is hiring Construction Laborers to assist with various tasks on construction sites. The successful candidates will perform duties such as carrying materials, operating hand and power tools, and assisting skilled tradespeople as needed. No prior experience is required, but candidates should have a strong work ethic, willingness to learn, and the ability to work in a fast-paced environment.\n', 8, 85000, 110000, NULL, NULL, 1, 8, 1, NULL, 8);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (17, 'Skyline Properties', 'Skyline Properties, a leading real estate brokerage firm, is seeking a Leasing Consultant to assist with residential property leasing activities. The ideal candidate will handle inquiries from prospective tenants, conduct property tours, and process lease applications and renewals. Responsibilities also include maintaining rental listings, coordinating move-ins and move-outs, and ensuring tenant satisfaction. Candidates should have excellent communication skills, sales experience, and a passion for customer service.', 9, 85000, 100000, NULL, NULL, 1, 9, 1, NULL, 9);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (18, 'Property Manager', 'Skyline Properties is hiring a Property Manager to oversee a portfolio of residential and commercial properties. The successful candidate will be responsible for property operations, tenant relations, lease administration, and financial management. Applicants should have prior experience in property management, strong leadership and organizational skills, and knowledge of landlord-tenant laws and regulations.', 9, 120000, 150000, NULL, NULL, 1, 9, 1, NULL, 9);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (19, 'Renewable Energy Engineer', 'SolarBright Solutions, a leading provider of solar energy solutions, is seeking a Renewable Energy Engineer to design and develop solar power systems. The ideal candidate will perform site assessments, conduct engineering analyses, and prepare system designs and specifications. Responsibilities also include collaborating with sales and installation teams to ensure successful project implementation. Candidates should have a Bachelor\'s degree in Engineering, preferably with a focus on renewable energy, and experience with solar energy modeling software.\n', 10, 110000, 130000, NULL, NULL, 1, 10, 1, NULL, 10);
INSERT INTO `job_posting` (`id`, `title`, `description`, `company_id`, `minimum_salary`, `maximum_salary`, `created_at`, `updated_at`, `positions`, `address_id`, `enabled`, `requirements`, `industry_id`) VALUES (20, 'Maintenance Technician (Wind Turbines)\n', 'SolarBright Solutions is hiring a Maintenance Technician to inspect, troubleshoot, and repair wind turbines at renewable energy sites. The successful candidate will perform preventive maintenance tasks, diagnose mechanical and electrical issues, and coordinate with engineering and operations teams to ensure optimal turbine performance. Applicants should have technical training in mechanical or electrical systems, along with experience in maintenance or service within the wind energy industry.\n', 10, 100000, 115000, NULL, NULL, 2, 10, 1, NULL, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `application_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `enchanteddb`;
INSERT INTO `application_status` (`id`, `name`) VALUES (1, 'Pending');
INSERT INTO `application_status` (`id`, `name`) VALUES (2, 'Accepted');
INSERT INTO `application_status` (`id`, `name`) VALUES (3, 'Denied');

COMMIT;


-- -----------------------------------------------------
-- Data for table `applicant`
-- -----------------------------------------------------
START TRANSACTION;
USE `enchanteddb`;
INSERT INTO `applicant` (`id`, `address_id`, `first_name`, `last_name`, `birthdate`, `email`, `eligible_to_work`, `relocation`, `profile_img`, `user_id`, `updated_at`, `about_me`) VALUES (1, 1, 'Willy', 'Wonka', '01/01/01', 'willywonka@gmail.com', 1, 1, 'https://upload.wikimedia.org/wikipedia/en/thumb/5/5e/Johnny_Depp_as_Willy_Wonka.jpg/220px-Johnny_Depp_as_Willy_Wonka.jpg', 2, NULL, 'I like making candy');

COMMIT;


-- -----------------------------------------------------
-- Data for table `application`
-- -----------------------------------------------------
START TRANSACTION;
USE `enchanteddb`;
INSERT INTO `application` (`id`, `job_posting_id`, `application_status_id`, `applicant_id`, `notes`, `created_at`, `updated_at`) VALUES (1, 1, 1, 1, 'Hope I can get this job. it would be great!', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_benefit`
-- -----------------------------------------------------
START TRANSACTION;
USE `enchanteddb`;
INSERT INTO `job_benefit` (`id`, `name`) VALUES (1, 'Medical Insurance');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (2, 'Dental Insurance');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (3, 'Vision Insurance');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (4, 'Life Insurance');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (5, 'Mental Health');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (6, 'Retirement');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (7, 'Paid Time Off');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (8, 'Paid Vacation Time');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (9, 'Paid Sick Leave');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (10, 'Extended Leave');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (11, 'Family Leave');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (12, 'Disability benefits');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (13, 'Workers Compensation');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (14, 'Travel Expenses');
INSERT INTO `job_benefit` (`id`, `name`) VALUES (15, 'Investment Opportunities');

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_benefit_has_job_posting`
-- -----------------------------------------------------
START TRANSACTION;
USE `enchanteddb`;
INSERT INTO `job_benefit_has_job_posting` (`job_benefit_id`, `job_posting_id`) VALUES (1, 1);
INSERT INTO `job_benefit_has_job_posting` (`job_benefit_id`, `job_posting_id`) VALUES (2, 1);
INSERT INTO `job_benefit_has_job_posting` (`job_benefit_id`, `job_posting_id`) VALUES (3, 1);
INSERT INTO `job_benefit_has_job_posting` (`job_benefit_id`, `job_posting_id`) VALUES (4, 1);
INSERT INTO `job_benefit_has_job_posting` (`job_benefit_id`, `job_posting_id`) VALUES (5, 1);
INSERT INTO `job_benefit_has_job_posting` (`job_benefit_id`, `job_posting_id`) VALUES (6, 1);

COMMIT;

