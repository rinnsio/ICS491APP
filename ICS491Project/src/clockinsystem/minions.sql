CREATE DATABASE  IF NOT EXISTS `minions` /*!40100 DEFAULT CHARACTER SET big5 */;
USE `minions`;
-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: localhost    Database: minions
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Table structure for table `clockinout`
--

DROP TABLE IF EXISTS `clockinout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clockinout` (
  `user_id` varchar(25) NOT NULL DEFAULT '',
  `in_out` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clockinout`
--

LOCK TABLES `clockinout` WRITE;
/*!40000 ALTER TABLE `clockinout` DISABLE KEYS */;
INSERT INTO `clockinout` VALUES ('Bob',0),('Dave',0),('Gru',0),('Jerry',1),('Jon',0),('Jorge',1),('Kevin',0),('Mark',0),('Phil',1),('Stuart',1),('Tim',0);
/*!40000 ALTER TABLE `clockinout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currentwages`
--

DROP TABLE IF EXISTS `currentwages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currentwages` (
  `user_id` varchar(25) NOT NULL DEFAULT '',
  `wage` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currentwages`
--

LOCK TABLES `currentwages` WRITE;
/*!40000 ALTER TABLE `currentwages` DISABLE KEYS */;
INSERT INTO `currentwages` VALUES ('Bob',1),('Dave',2),('Gru',3),('Jerry',1),('Jon',2),('Jorge',1),('Kevin',3),('Mark',1),('Phil',2),('Stuart',2),('Tim',1);
/*!40000 ALTER TABLE `currentwages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `user_id` varchar(25) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `wage` int(1) DEFAULT NULL,
  `time_in` datetime DEFAULT NULL,
  `time_out` datetime DEFAULT NULL,
  `total_hours` decimal(3,1) DEFAULT NULL,
  `total_wages` decimal(3,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES ('Bob','2015-07-03',1,'2015-08-09 09:21:00','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-07-15',1,'2015-08-09 10:45:00','2015-08-09 12:06:37',0.0,0.0),('Dave','2015-07-01',2,'2015-08-09 13:29:00','2015-08-09 23:44:00',10.3,20.5),('Dave','2015-07-08',2,'2015-08-09 12:38:00','2015-08-09 14:48:00',2.2,4.3),('Dave','2015-07-21',2,'2015-08-09 20:24:00','2015-08-09 23:10:00',2.8,5.5),('Dave','2015-07-22',2,'2015-08-09 07:01:00','2015-08-09 21:09:00',14.1,28.3),('Dave','2015-07-31',2,'2015-08-09 13:22:00','2015-08-09 22:15:00',8.9,17.8),('Jerry','2015-07-04',1,'2015-08-09 14:49:00','2015-08-09 18:33:00',3.7,3.7),('Jerry','2015-07-25',1,'2015-08-09 11:39:00','2015-08-09 22:12:00',10.6,10.6),('Jerry','2015-07-27',1,'2015-08-09 12:38:00','2015-08-09 22:32:00',9.9,9.9),('Jon','2015-07-02',2,'2015-08-09 14:25:00','2015-08-09 18:48:00',4.4,8.8),('Jon','2015-07-06',2,'2015-08-09 10:09:00','2015-08-09 16:24:00',6.3,12.5),('Jon','2015-07-13',2,'2015-08-09 08:20:00','2015-08-09 17:35:00',9.3,18.5),('Jon','2015-07-28',2,'2015-08-09 12:02:00','2015-08-09 20:20:00',8.3,16.6),('Jorge','2015-07-02',1,'2015-08-09 09:01:00','2015-08-09 21:34:00',12.6,12.6),('Jorge','2015-07-23',1,'2015-08-09 08:22:00','2015-08-09 19:43:00',11.4,11.4),('Kevin','2015-07-01',3,'2015-08-09 07:26:00','2015-08-09 19:02:00',11.6,34.8),('Kevin','2015-07-14',3,'2015-08-09 21:27:00','2015-08-09 23:11:00',1.7,5.2),('Kevin','2015-07-19',3,'2015-08-09 15:37:00','2015-08-09 18:29:00',2.9,8.6),('Kevin','2015-07-26',3,'2015-08-09 19:20:00','2015-08-09 21:28:00',2.1,6.4),('Kevin','2015-07-30',3,'2015-08-09 16:48:00','2015-08-09 19:27:00',2.7,8.0),('Mark','2015-07-01',1,'2015-08-09 08:24:00','2015-08-09 20:29:00',12.1,12.1),('Mark','2015-07-11',1,'2015-08-09 07:43:00','2015-08-09 20:24:00',12.7,12.7),('Phil','2015-07-12',2,'2015-08-09 17:20:00','2015-08-09 23:25:00',6.1,12.2),('Phil','2015-07-21',2,'2015-08-09 16:39:00','2015-08-09 22:11:00',5.5,11.1),('Phil','2015-07-27',2,'2015-08-09 18:24:00','2015-08-09 21:30:00',3.1,6.2),('Stuart','2015-07-16',2,'2015-08-09 13:18:00','2015-08-09 17:02:00',3.7,7.5),('Tim','2015-07-07',1,'2015-08-09 07:09:00','2015-08-09 15:33:00',8.4,8.4),('Tim','2015-07-24',1,'2015-08-09 20:47:00','2015-08-09 23:09:00',2.4,2.4),('Bob','2015-08-09',1,'2015-08-09 10:18:25','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 10:47:54','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 10:48:50','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 10:49:26','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 10:51:38','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 10:53:11','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 10:53:59','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:00:46','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:05:22','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:05:53','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:27:41','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:29:09','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:30:57','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:38:39','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:39:18','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:41:34','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:41:54','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:42:42','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:43:55','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:46:09','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:47:03','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:51:14','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:54:27','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:56:02','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 11:58:14','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 12:01:10','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 12:04:25','2015-08-09 12:06:37',0.0,0.0),('Bob','2015-08-09',1,'2015-08-09 12:06:32','2015-08-09 12:06:37',0.0,0.0);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `user_id` varchar(25) NOT NULL DEFAULT '',
  `password` varchar(25) DEFAULT NULL,
  `privilege_level` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('Bob','bobob',0),('Dave','davav',0),('Gru','gruru',1),('Jerry','jerer',0),('Jon','jonon',0),('Jorge','joror',0),('Kevin','kevev',0),('Mark','marar',0),('Phil','phihi',0),('Stuart','stutu',0),('Tim','timim',0);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pin`
--

DROP TABLE IF EXISTS `pin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pin` (
  `user_id` varchar(25) NOT NULL DEFAULT '',
  `pin` int(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pin`
--

LOCK TABLES `pin` WRITE;
/*!40000 ALTER TABLE `pin` DISABLE KEYS */;
INSERT INTO `pin` VALUES ('Bob',1234),('Dave',2345),('Gru',3456),('Jerry',4567),('Jon',5678),('Jorge',6789),('Kevin',7890),('Mark',1357),('Phil',2468),('Stuart',4680),('Tim',6802);
/*!40000 ALTER TABLE `pin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-09 12:15:00
