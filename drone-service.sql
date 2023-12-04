-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.28-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

SET FOREIGN_KEY_CHECKS = 0;

-- Dumping database structure for drone-service
CREATE DATABASE IF NOT EXISTS `drone-service` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `drone-service`;

-- Dumping structure for table drone-service.drone
DROP TABLE IF EXISTS `drone`;
CREATE TABLE IF NOT EXISTS `drone` (
  `id` varchar(255) NOT NULL,
  `battery_capacity` int(11) NOT NULL,
  `model` varchar(255) DEFAULT NULL,
  `serial_number` varchar(255) DEFAULT NULL,
  `state` enum('IDLE','LOADING','LOADED','DELIVERING','DELIVERED','RETURNING') DEFAULT NULL,
  `weight_limit` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table drone-service.drone: ~9 rows (approximately)
INSERT INTO `drone` (`id`, `battery_capacity`, `model`, `serial_number`, `state`, `weight_limit`) VALUES
	('1', 100, 'Cruiserweight', '7823Uhjdo00-00', 'LOADED', 400),
	('152', 30, 'Heavyweight', '7823Uhjdo0089', 'LOADED', 500),
	('2', 27, 'Middleweight', '7823Uhjdo00-01', 'LOADED', 300),
	('3', 25, 'Lightweight', '7823Uhjdo00-02', 'LOADED', 100),
	('4', 100, 'Lightweight', '7823Uhjdo00-03', 'LOADED', 100),
	('5', 100, 'Heavyweight', '7823Uhjdo00-04', 'LOADED', 300),
	('6', 100, 'Heavyweight', '7823Uhjdo00-05', 'LOADED', 300),
	('7', 100, 'Heavyweight', '7823Uhjdo00-06', 'LOADED', 350),
	('8', 50, 'Heavyweight', '7823Uhjdo00-07', 'IDLE', 400);

-- Dumping structure for table drone-service.drone_loaded_medications
DROP TABLE IF EXISTS `drone_loaded_medications`;
CREATE TABLE IF NOT EXISTS `drone_loaded_medications` (
  `drone_id` varchar(255) NOT NULL,
  `loaded_medications_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_8hkefixnxq4tal98pu1hd75xi` (`loaded_medications_id`),
  KEY `FK35seax94vgdgecvsvrkohjwgf` (`drone_id`),
  CONSTRAINT `FK35seax94vgdgecvsvrkohjwgf` FOREIGN KEY (`drone_id`) REFERENCES `drone` (`id`),
  CONSTRAINT `FK8ux2vutfqx56krxmjukb59xbn` FOREIGN KEY (`loaded_medications_id`) REFERENCES `medication` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table drone-service.drone_loaded_medications: ~11 rows (approximately)
INSERT INTO `drone_loaded_medications`(`drone_id`, `loaded_medications_id`) VALUES
	('1', 302),
	('1', 303),
	('152', 502),
	('2', 452),
	('2', 453),
	('3', 352),
	('3', 353),
	('4', 252),
	('5', 202),
	('6', 152),
	('7', 52);

-- Dumping structure for table drone-service.drone_seq
DROP TABLE IF EXISTS `drone_seq`;
CREATE TABLE IF NOT EXISTS `drone_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table drone-service.drone_seq: ~0 rows (approximately)
INSERT INTO `drone_seq` (`next_val`) VALUES
	(601);

-- Dumping structure for table drone-service.medication
DROP TABLE IF EXISTS `medication`;
CREATE TABLE IF NOT EXISTS `medication` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table drone-service.medication: ~18 rows (approximately)
INSERT INTO `medication` (`id`, `code`, `image`, `name`, `weight`) VALUES
	(1, 'SRN6894', 'https://medImage1', 'Aspirin', 32),
	(2, 'SRN6895', 'https://medImage2', 'Tylenol', 12),
	(3, 'SRN6896', 'https://medImage3', 'Spikevax', 25),
	(4, 'SRN6897', 'https://medImage4', 'Oxford/AstraZeneca', 40),
	(5, 'SRN6898', 'https://medImage5', 'Gamaleya', 45),
	(52, 'SRN68103', 'https://medImage', 'cloxacilyn', 50),
	(102, 'SRN68105', 'https://amplicloxImage', 'Ampliclox', 56),
	(152, 'SRN68104', 'https://medImage', 'Ciprotab', 250),
	(202, 'SRN68105', 'https://medImage', 'Combantrin', 300),
	(252, 'SRN68106', 'https://medImage', 'Amatem', 72),
	(302, 'SRN68108', 'https://medImage', 'Amatem3', 72),
	(303, 'SRN68109', 'https://anotherMedImage', 'AnotherMed', 120),
	(352, 'SRN68110', 'https://Amatem4', 'Amatem4', 50),
	(353, 'SRN68111', 'https://anotherMedImage2', 'AnotherMed2', 50),
	(402, 'SRN68105', 'https://tetranolImage', 'Tetrano;', 100),
	(452, 'ABC123', 'https://medImage1', 'Medication1', 50),
	(453, 'XYZ789', 'https://medImage2', 'Medication2', 75),
	(502, 'ABC1234', 'https://medImage1', 'Medication3', 53);

-- Dumping structure for table drone-service.medication_seq
DROP TABLE IF EXISTS `medication_seq`;
CREATE TABLE IF NOT EXISTS `medication_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table drone-service.medication_seq: ~0 rows (approximately)
INSERT INTO `medication_seq` (`next_val`) VALUES
	(601);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
