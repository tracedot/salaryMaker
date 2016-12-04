-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: SalarySheet
-- ------------------------------------------------------
-- Server version	5.5.47-0+deb8u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emp_post` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emp_work_place` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `officePlace` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `home_fare_percent` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gen_fund_percent` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `home_const_installment` int(11) DEFAULT NULL,
  `first_installment` int(11) DEFAULT NULL,
  `sec_installment` int(11) DEFAULT NULL,
  `thrd_installment` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mainsalary`
--

DROP TABLE IF EXISTS `mainsalary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mainsalary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `salary_basic` int(11) DEFAULT NULL,
  `home_allowance` int(11) DEFAULT NULL,
  `treatment_allowance` int(11) DEFAULT NULL,
  `tiffin_allowance` int(11) DEFAULT NULL,
  `education_allowance` int(11) DEFAULT NULL,
  `mohargo_allowance` int(11) DEFAULT NULL,
  `washing_allowance` int(11) DEFAULT NULL,
  `salaryTotal` int(11) DEFAULT NULL,
  `gen_welfare_fund` int(11) DEFAULT NULL,
  `welfare_fund` int(11) DEFAULT NULL,
  `gen_welfare_fund_paid` int(11) DEFAULT NULL,
  `home_fare_deduct` int(11) DEFAULT NULL,
  `home_deduct_adv_installment` int(11) DEFAULT NULL,
  `bike_deduct` int(11) DEFAULT NULL,
  `home_const_installment` int(11) DEFAULT NULL,
  `first_installment` int(11) DEFAULT NULL,
  `sec_installment` int(11) DEFAULT NULL,
  `thrd_installment` int(11) DEFAULT NULL,
  `total_deduction` int(11) DEFAULT NULL,
  `total_demand` int(11) DEFAULT NULL,
  `salarydate` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mainsalary_1_idx` (`emp_id`),
  CONSTRAINT `fk_mainsalary_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mainsalary`
--

LOCK TABLES `mainsalary` WRITE;
/*!40000 ALTER TABLE `mainsalary` DISABLE KEYS */;
/*!40000 ALTER TABLE `mainsalary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `office`
--

DROP TABLE IF EXISTS `office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `office` (
  `office_id` int(11) NOT NULL AUTO_INCREMENT,
  `employer` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `officeName` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `place` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`office_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `office`
--

LOCK TABLES `office` WRITE;
/*!40000 ALTER TABLE `office` DISABLE KEYS */;
/*!40000 ALTER TABLE `office` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `username` varchar(45) COLLATE utf8_czech_ci DEFAULT NULL,
  `password` varchar(45) COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-24 22:07:05
