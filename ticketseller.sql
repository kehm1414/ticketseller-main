CREATE DATABASE  IF NOT EXISTS `ticketseller` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ticketseller`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: ticketseller
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Documentary'),(2,'Comedy'),(3,'Concert'),(4,'Sports'),(5,'Fashion Show'),(7,'Cooking Competition');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `date` date NOT NULL,
  `start_time` time NOT NULL,
  `category_id` bigint unsigned NOT NULL,
  `venue_id` bigint unsigned NOT NULL,
  `ticket_price` float NOT NULL,
  `creation_date` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `events_ibfk_1` (`category_id`),
  KEY `events_ibfk_2` (`venue_id`),
  CONSTRAINT `events_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `events_ibfk_2` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'The Musical Box','The musical box is as good as ever, they are coming back to make an unforgetable show.','2022-04-23','19:00:00',3,2,70,'2022-04-08 19:06:59'),(2,'Cat Power','The known Cat Power at its peak.','2022-04-23','19:00:00',3,2,30,'2022-04-13 08:00:00'),(3,'Natti Natasha in concert','Natti Natasha comming for the first time to this venue','2022-05-05','16:00:00',3,2,250,'2022-04-09 00:04:35'),(4,'Kany Garcia in concert','Kany Garcia playing her biggest hits','2022-05-25','19:30:00',3,2,150,'2022-04-09 00:04:35'),(5,'Briceño visiting','Briceño making the best of his comedy shows','2022-04-28','19:00:00',2,2,250,'2022-04-09 00:04:35'),(6,'Alejandro Sanz','Alejandro Sanz playing his greatest hits','2022-05-14','20:30:00',3,2,100,'2022-04-09 00:04:35'),(7,'Il Divo: Greatest Hits Tour','Il Divo comming back with their best for this tour','2022-05-17','20:00:00',3,2,250,'2022-04-09 00:04:35'),(22,'Manchester City vs Real Madrid','Champions League\'s semi final 2/2...','2022-05-04','15:00:00',4,3,100,'2022-04-18 08:00:00');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `serial_key` varchar(20) NOT NULL,
  `user_id` bigint unsigned DEFAULT NULL,
  `event_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `user_id` (`user_id`),
  KEY `event_id` (`event_id`),
  CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (1,'c5u8n8f8m8a3l2o3j4u9',2,2),(2,'w7y4f7d8m5k4t4n9p3o6',2,6),(3,'a0x6t2v8g1b9a9b6f8n8',2,3),(4,'z5u2n8b2q3p0h4v0e8w3',11,1),(5,'s9d6q6f5c1d2d1m3i5o2',2,7);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `created_on` timestamp NOT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `auth_provider` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@admin.com','$2a$10$HeCe6D6WaSGXngSvVsBKH.6VeCUUqyOBaxJ2PElMe2wgDyfyfHVDK','Admin','A','2022-04-15 17:27:58',1,'ROLE_ADMIN',NULL),(2,'user@user.com','$2a$10$ky8S3uZxb/F0cvnq6gVQb.ib7p0tT9Ag9zBTlyUDBnz40bbwt0mNu','User','U','2022-04-15 17:27:58',1,'ROLE_USER',NULL),(5,'test@test.com','$2a$10$B9Y/dLNRhNIr3EivBleX2OD53a/eJEK9YDqwZ5Itta857kyYjqe4O','Test','test','2022-04-16 08:00:00',1,'ROLE_USER','LOCAL'),(6,'test2@test.com','$2a$10$ACTrSwVZruJebG/q1jOSaeonqHy/Z0uBBLfQvnQPSWL75qhGHTkb.','Test2','test','2022-04-16 08:00:00',1,'ROLE_USER','LOCAL'),(7,'Test3@gmail.com','$2a$10$1dh.bYFHgUmw7VWv7TjhFeHQYYOrZYvV46n4hHk7ERc8cwB49cEqi','Test3','test','2022-04-16 08:00:00',1,'ROLE_USER','LOCAL'),(8,'test4@gmail.com','$2a$10$J7GOq/WCKf5/WVhZtpefEujl3RTc6Fcc.SEcnvTXKPNYXRYIz0lHG','Test4','test','2022-04-16 08:00:00',1,'ROLE_USER','LOCAL'),(10,'test5@gmail.com','$2a$10$TkcRainzrbOOEPOPIm/oFeL.Sxfqhj04vi3YGgjcTgyS2n2Yv.MqS','Test5','test','2022-04-16 08:00:00',1,'ROLE_USER','LOCAL'),(11,'cashcan1023@gmail.com',NULL,'cash can',NULL,'2022-04-16 08:00:00',1,'ROLE_USER','GOOGLE');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venues`
--

DROP TABLE IF EXISTS `venues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venues` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venues`
--

LOCK TABLES `venues` WRITE;
/*!40000 ALTER TABLE `venues` DISABLE KEYS */;
INSERT INTO `venues` VALUES (2,'Danforth Music Hall','Toronto, Canada',5000),(3,'Budweiser Stage','Toronto, Canada',1000),(4,'\"Teresa Carreño\" Theater',' Av. \"Paseo Colón\", Caracas, Venezuela',2714),(6,'CCCT','Chuao, Caracas, Venezuela',500);
/*!40000 ALTER TABLE `venues` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-18 17:50:05
