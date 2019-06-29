-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: dairy
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `password_reset_token` binary(255) DEFAULT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`account_id`),
  KEY `FK6mfkmm2wqhabh6xi2f5pqay` (`employee_id`),
  CONSTRAINT `FK6mfkmm2wqhabh6xi2f5pqay` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (0,_binary '','$2a$10$Paw6baolVjOhxDHm.KtKsezvg3qeBRUxgKAqmaloEmyYwFF8mRbmC',NULL,32259337);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `breeding`
--

DROP TABLE IF EXISTS `breeding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `breeding` (
  `breeding_id` int(11) NOT NULL,
  `servicing_date` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `methodof_insemination` varchar(255) DEFAULT NULL,
  `reproductive_condition` varchar(255) DEFAULT NULL,
  `reproductive_treatment_offered` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `calf_breeding_id` int(11) DEFAULT NULL,
  `cow_id` varchar(255) NOT NULL,
  PRIMARY KEY (`breeding_id`),
  KEY `FK2vxnhwjipkkymbfpsei893e06` (`calf_breeding_id`),
  KEY `FK169by402cylbni3g6q3tersxv` (`cow_id`),
  CONSTRAINT `FK169by402cylbni3g6q3tersxv` FOREIGN KEY (`cow_id`) REFERENCES `cow` (`cow_tag`),
  CONSTRAINT `FK2vxnhwjipkkymbfpsei893e06` FOREIGN KEY (`calf_breeding_id`) REFERENCES `calf` (`breeding_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `breeding`
--

LOCK TABLES `breeding` WRITE;
/*!40000 ALTER TABLE `breeding` DISABLE KEYS */;
INSERT INTO `breeding` VALUES (3,'2019-06-27','2019-06-27','fghjk','ghjk','nm,',_binary '',NULL,'Too1');
/*!40000 ALTER TABLE `breeding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `breeds`
--

DROP TABLE IF EXISTS `breeds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `breeds` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `breeds`
--

LOCK TABLES `breeds` WRITE;
/*!40000 ALTER TABLE `breeds` DISABLE KEYS */;
INSERT INTO `breeds` VALUES (1,'Ayrshire');
/*!40000 ALTER TABLE `breeds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calf`
--

DROP TABLE IF EXISTS `calf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `calf` (
  `breeding_id` int(11) NOT NULL,
  `calf_id` varchar(255) DEFAULT NULL,
  `dateof_calving` date DEFAULT NULL,
  `dead` bit(1) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `breed` bigint(20) NOT NULL,
  `cow_id` varchar(255) NOT NULL,
  `mortality` int(11) DEFAULT NULL,
  PRIMARY KEY (`breeding_id`),
  KEY `FKdcgralm9mlto3uy7393mcthiv` (`breed`),
  KEY `FK3xsv7viqx57wpicix4g1ciwdy` (`cow_id`),
  KEY `FKno23vmbl3v8ylra0obintn0dq` (`mortality`),
  CONSTRAINT `FK3xsv7viqx57wpicix4g1ciwdy` FOREIGN KEY (`cow_id`) REFERENCES `cow` (`cow_tag`),
  CONSTRAINT `FKdcgralm9mlto3uy7393mcthiv` FOREIGN KEY (`breed`) REFERENCES `breeds` (`id`),
  CONSTRAINT `FKno23vmbl3v8ylra0obintn0dq` FOREIGN KEY (`mortality`) REFERENCES `mortality` (`cow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calf`
--

LOCK TABLES `calf` WRITE;
/*!40000 ALTER TABLE `calf` DISABLE KEYS */;
/*!40000 ALTER TABLE `calf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cow`
--

DROP TABLE IF EXISTS `cow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cow` (
  `cow_tag` varchar(255) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `dead` bit(1) DEFAULT NULL,
  `cow_name` varchar(255) NOT NULL,
  `breed` bigint(20) NOT NULL,
  `mortality` int(11) DEFAULT NULL,
  PRIMARY KEY (`cow_tag`),
  KEY `FKmqhsli389tbll4h5tk308rehq` (`breed`),
  KEY `FK38fckylha1xh0lj56orwkcnpw` (`mortality`),
  CONSTRAINT `FK38fckylha1xh0lj56orwkcnpw` FOREIGN KEY (`mortality`) REFERENCES `mortality` (`cow_id`),
  CONSTRAINT `FKmqhsli389tbll4h5tk308rehq` FOREIGN KEY (`breed`) REFERENCES `breeds` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cow`
--

LOCK TABLES `cow` WRITE;
/*!40000 ALTER TABLE `cow` DISABLE KEYS */;
INSERT INTO `cow` VALUES ('Too1','2019-06-27',_binary '\0','fgas',1,NULL);
/*!40000 ALTER TABLE `cow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_roles`
--

DROP TABLE IF EXISTS `employee_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee_roles` (
  `employee_id` int(11) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`employee_id`,`role_id`),
  UNIQUE KEY `UK_3ene23nq9uvnj1cktmk8ydhah` (`role_id`),
  CONSTRAINT `FK398vvu81xw154mvy3g2eytscn` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FKhks9ko6y0a2odqoa0s8c3qsr4` FOREIGN KEY (`employee_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_roles`
--

LOCK TABLES `employee_roles` WRITE;
/*!40000 ALTER TABLE `employee_roles` DISABLE KEYS */;
INSERT INTO `employee_roles` VALUES (0,1);
/*!40000 ALTER TABLE `employee_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employees` (
  `employee_id` int(11) NOT NULL,
  `date_of_dismissal` date DEFAULT NULL,
  `date_of_employment` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(60) NOT NULL,
  `phone_number` int(11) DEFAULT NULL,
  `account_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `UK_j9xgmd0ya5jmus09o0b8pqrpb` (`email`),
  KEY `FKtm5vejyph1typgv9npon95v3s` (`account_account_id`),
  CONSTRAINT `FKtm5vejyph1typgv9npon95v3s` FOREIGN KEY (`account_account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (32259337,NULL,'0022-12-10','fraponyo94@gmail.com','Fredrick Aponyo',704034231,0);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health`
--

DROP TABLE IF EXISTS `health`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `health` (
  `health_id` int(11) NOT NULL,
  `contact_of_veterinary_doctor` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `history` varchar(255) DEFAULT NULL,
  `name_of_veterinary_doctor` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `symptoms` varchar(255) DEFAULT NULL,
  `treatment` varchar(255) DEFAULT NULL,
  `calf_id` int(11) DEFAULT NULL,
  `cow_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`health_id`),
  KEY `FK8qbeh97422yqs52o44xjwxds7` (`calf_id`),
  KEY `FK7nkd379p38nnaygpt019no5hn` (`cow_id`),
  CONSTRAINT `FK7nkd379p38nnaygpt019no5hn` FOREIGN KEY (`cow_id`) REFERENCES `cow` (`cow_tag`),
  CONSTRAINT `FK8qbeh97422yqs52o44xjwxds7` FOREIGN KEY (`calf_id`) REFERENCES `calf` (`breeding_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health`
--

LOCK TABLES `health` WRITE;
/*!40000 ALTER TABLE `health` DISABLE KEYS */;
INSERT INTO `health` VALUES (4,NULL,NULL,'','df','xcv','sdfg','sdfvb','sdfv',NULL,'Too1');
/*!40000 ALTER TABLE `health` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (5),(5),(5),(5),(5),(5),(5);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `milk`
--

DROP TABLE IF EXISTS `milk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `milk` (
  `id` int(11) NOT NULL,
  `milking_date` date DEFAULT NULL,
  `first_milking` double DEFAULT NULL,
  `other_milking` double DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `second_milking` double DEFAULT NULL,
  `cow_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhhv15ns7m0mmwa8824mc37l6o` (`cow_id`),
  CONSTRAINT `FKhhv15ns7m0mmwa8824mc37l6o` FOREIGN KEY (`cow_id`) REFERENCES `cow` (`cow_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `milk`
--

LOCK TABLES `milk` WRITE;
/*!40000 ALTER TABLE `milk` DISABLE KEYS */;
INSERT INTO `milk` VALUES (2,'2019-06-27',9922,442,'tyu nm',452,'Too1');
/*!40000 ALTER TABLE `milk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mortality`
--

DROP TABLE IF EXISTS `mortality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `mortality` (
  `cow_id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `findings` varchar(255) DEFAULT NULL,
  `post_mortem_report` varchar(255) DEFAULT NULL,
  `calf_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cow_id`),
  KEY `FKa08ys1x7euekyhlk3tmkufp2h` (`calf_id`),
  CONSTRAINT `FKa08ys1x7euekyhlk3tmkufp2h` FOREIGN KEY (`calf_id`) REFERENCES `calf` (`breeding_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mortality`
--

LOCK TABLES `mortality` WRITE;
/*!40000 ALTER TABLE `mortality` DISABLE KEYS */;
/*!40000 ALTER TABLE `mortality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `other_expenses`
--

DROP TABLE IF EXISTS `other_expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `other_expenses` (
  `expense_id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `price_per_unit` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`expense_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `other_expenses`
--

LOCK TABLES `other_expenses` WRITE;
/*!40000 ALTER TABLE `other_expenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `other_expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_EMPLOYEE');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-27  9:31:03
