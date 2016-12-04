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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'মো: জিহাদুর রহমান','উ.স.ক মেডিকেল অফিসার','সয়ার','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(2,'অনিল কুমার রায়','ফার্মাসিস্ট','হাড়িয়ারকুঠি','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(3,'সনাতন রায়','ফার্মাসিস্ট','তারাগন্জ','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(4,'মো: মোখতার আহমেদ','অফিস সহকারী','তারাগন্জ','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(5,'মোছ: মাকসুদা বেগম','প:ক: পরিদর্শিকা','ইকরচালী','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(6,'মোছা: সামছুন্নাহার বেগম','প:ক: পরিদর্শিকা','হাড়িয়ারকুঠি','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(7,'মোছা: রাজিয়া খাতুন','প:ক: পরিদর্শিকা','সয়ার','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(8,'হাবীবা কুলসুম','প:ক: পরিদর্শিকা','সদর ক্লিনিক','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(9,'পনচোমী রায়','প:ক: পরিদর্শিকা','আলমপুর','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(10,'আবুল হোসেন','অফিস সহায়ক','তারাগন্জ','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(11,'শেখ মো: নুরুল ইসলাম','নি: প্রহরী/এমএলএসএস','তারাগন্জ','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(12,'মো: মাহবুব অালম','নি: প্রহরী/এমএলএসএস','তারাগন্জ','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(13,'আয়শা খাতুন','আয়া','ইকরচালী','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(14,'মোছা: নুরুন্নাহার বেগম','আয়া','আলমপুর','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL),(15,'কাজলী বেগম','আয়া','তারাগন্জ','তারাগন্জ,রংপুর','45','20',NULL,NULL,NULL,NULL);
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
  `home_const_installment` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_installment` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sec_installment` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `thrd_installment` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `total_deduction` int(11) DEFAULT NULL,
  `total_demand` int(11) DEFAULT NULL,
  `salarydate` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mainsalary_1_idx` (`emp_id`),
  CONSTRAINT `fk_mainsalary_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mainsalary`
--

LOCK TABLES `mainsalary` WRITE;
/*!40000 ALTER TABLE `mainsalary` DISABLE KEYS */;
INSERT INTO `mainsalary` VALUES (3,38890,17500,700,150,0,0,0,57240,7778,50,10500,300,0,0,'0','0','0','0',18628,38612,'26/5/2016',1),(4,14480,6516,700,150,0,0,0,21846,2896,50,0,0,0,0,'0','0','0','0',2946,18900,'26/5/2016',3),(5,17960,8082,700,150,300,0,0,27192,3592,50,0,0,0,0,'0','0','0','0',3642,23550,'26/5/2016',4),(6,24870,11191,700,150,300,0,0,37211,4974,50,0,300,0,0,'800 24 সুদ','0','0','0',5324,31887,'26/5/2016',5),(7,20420,9189,700,150,0,0,0,30459,4084,50,2350,500,1100,0,'14 100','40 48','33 48','0',8084,22375,'26/5/2016',6),(8,20420,9189,700,150,200,0,0,30659,4084,50,3500,300,0,0,'0','18 24','0','0',7934,22725,'26/5/2016',7),(9,11820,5319,700,150,200,0,0,18189,2364,50,0,0,0,0,'0','0','0','0',2414,15775,'26/5/2016',8),(10,10710,4819,700,150,0,0,0,16379,2142,50,0,0,0,0,'0','0','0','0',2192,14187,'26/5/2016',9),(11,13340,6003,700,150,200,0,75,20468,2668,50,1300,0,0,666,'0','0','30 36','0',4684,15784,'26/5/2016',10),(12,10560,4752,700,150,300,0,75,16537,2112,50,0,0,0,0,'0','0','0','0',2162,14375,'26/5/2016',11),(13,9570,4306,700,150,0,0,75,14801,1914,50,0,0,0,0,'0','0','0','0',1964,12837,'26/5/2016',12),(14,13340,6003,700,150,0,0,75,20268,2668,50,0,0,0,0,'0','0','0','0',2718,17550,'26/5/2016',13),(15,13340,6003,700,150,0,0,75,20268,2668,50,0,0,0,0,'0','0','0','0',2718,17550,'26/5/2016',14),(16,9110,40549,700,150,200,0,0,131709,18022,43,0,0,0,0,'0','0','0','0',18065,113644,'26/5/2016',15),(17,38890,17500,700,150,0,0,0,57240,7778,50,0,0,0,0,'0','0','0','0',7828,49412,'26/5/2016',2);
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
INSERT INTO `users` VALUES (1,'ripon','pass');
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

-- Dump completed on 2016-05-27 18:34:11
