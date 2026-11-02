CREATE DATABASE  IF NOT EXISTS `rpms_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rpms_db`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: rpms_db
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `activity_logs`
--

DROP TABLE IF EXISTS `activity_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_logs` (
  `log_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `module_name` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `action_name` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `record_id` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `logged_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`log_id`),
  KEY `idx_activity_user` (`user_id`),
  KEY `idx_activity_module` (`module_name`),
  KEY `idx_activity_logged_at` (`logged_at`),
  CONSTRAINT `fk_activity_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_logs`
--

LOCK TABLES `activity_logs` WRITE;
/*!40000 ALTER TABLE `activity_logs` DISABLE KEYS */;
INSERT INTO `activity_logs` VALUES (1,1,'USER','LOGIN','1','Admin logged in','2026-10-31 19:11:22'),(2,2,'JOB','CREATE','JOB-2026-001','Created Java job opening','2026-10-31 19:11:22'),(3,3,'APPLICATION','SHORTLIST','APP-2026-0001','Candidate shortlisted','2026-10-31 19:11:22'),(4,4,'OFFER','CREATE','OFF-2026-001','Offer created','2026-10-31 19:11:22'),(5,5,'PLACEMENT','JOINING','1','Candidate marked as joined','2026-10-31 19:11:22');
/*!40000 ALTER TABLE `activity_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_history`
--

DROP TABLE IF EXISTS `application_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_history` (
  `history_id` bigint NOT NULL AUTO_INCREMENT,
  `application_id` int NOT NULL,
  `old_status` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `new_status` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `changed_by` int NOT NULL,
  `changed_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`),
  KEY `fk_app_history_user` (`changed_by`),
  KEY `idx_app_history_application` (`application_id`),
  KEY `idx_app_history_changed_at` (`changed_at`),
  CONSTRAINT `fk_app_history_application` FOREIGN KEY (`application_id`) REFERENCES `applications` (`application_id`),
  CONSTRAINT `fk_app_history_user` FOREIGN KEY (`changed_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_history`
--

LOCK TABLES `application_history` WRITE;
/*!40000 ALTER TABLE `application_history` DISABLE KEYS */;
INSERT INTO `application_history` VALUES (1,1,NULL,'APPLIED','Application created',2,'2026-10-02 10:07:00'),(2,1,'APPLIED','SHORTLISTED','Sample workflow status update',2,'2026-10-03 10:07:00'),(3,2,NULL,'APPLIED','Application created',3,'2026-10-03 10:14:00'),(4,2,'APPLIED','INTERVIEW_SCHEDULED','Sample workflow status update',3,'2026-10-04 10:14:00'),(5,3,NULL,'APPLIED','Application created',2,'2026-10-04 10:21:00'),(6,3,'APPLIED','INTERVIEWED','Sample workflow status update',2,'2026-10-05 10:21:00'),(7,4,NULL,'APPLIED','Application created',3,'2026-10-05 10:28:00'),(8,4,'APPLIED','SELECTED','Sample workflow status update',3,'2026-10-06 10:28:00'),(9,5,NULL,'APPLIED','Application created',2,'2026-10-06 10:35:00'),(10,5,'APPLIED','OFFERED','Sample workflow status update',2,'2026-10-07 10:35:00'),(11,6,NULL,'APPLIED','Application created',3,'2026-10-07 10:42:00'),(12,6,'APPLIED','REJECTED','Sample workflow status update',3,'2026-10-08 10:42:00'),(13,7,NULL,'APPLIED','Application created',2,'2026-10-08 10:49:00'),(14,8,NULL,'APPLIED','Application created',3,'2026-10-09 10:56:00'),(15,8,'APPLIED','ELIGIBILITY_VERIFIED','Sample workflow status update',3,'2026-10-10 10:56:00'),(16,9,NULL,'APPLIED','Application created',2,'2026-10-10 10:03:00'),(17,9,'APPLIED','SHORTLISTED','Sample workflow status update',2,'2026-10-11 10:03:00'),(18,10,NULL,'APPLIED','Application created',3,'2026-10-11 10:10:00'),(19,10,'APPLIED','INTERVIEW_SCHEDULED','Sample workflow status update',3,'2026-10-12 10:10:00'),(20,11,NULL,'APPLIED','Application created',2,'2026-10-12 10:17:00'),(21,11,'APPLIED','INTERVIEWED','Sample workflow status update',2,'2026-10-13 10:17:00'),(22,12,NULL,'APPLIED','Application created',3,'2026-10-13 10:24:00'),(23,12,'APPLIED','SELECTED','Sample workflow status update',3,'2026-10-14 10:24:00'),(24,13,NULL,'APPLIED','Application created',2,'2026-10-14 10:31:00'),(25,13,'APPLIED','OFFERED','Sample workflow status update',2,'2026-10-15 10:31:00'),(26,14,NULL,'APPLIED','Application created',3,'2026-10-15 10:38:00'),(27,14,'APPLIED','REJECTED','Sample workflow status update',3,'2026-10-16 10:38:00'),(28,15,NULL,'APPLIED','Application created',2,'2026-10-16 10:45:00'),(29,16,NULL,'APPLIED','Application created',3,'2026-10-17 10:52:00'),(30,16,'APPLIED','ELIGIBILITY_VERIFIED','Sample workflow status update',3,'2026-10-18 10:52:00'),(31,17,NULL,'APPLIED','Application created',2,'2026-10-18 10:59:00'),(32,17,'APPLIED','SHORTLISTED','Sample workflow status update',2,'2026-10-19 10:59:00'),(33,18,NULL,'APPLIED','Application created',3,'2026-10-19 10:06:00'),(34,18,'APPLIED','INTERVIEW_SCHEDULED','Sample workflow status update',3,'2026-10-20 10:06:00'),(35,19,NULL,'APPLIED','Application created',2,'2026-10-20 10:13:00'),(36,19,'APPLIED','INTERVIEWED','Sample workflow status update',2,'2026-10-21 10:13:00'),(37,20,NULL,'APPLIED','Application created',3,'2026-10-21 10:20:00'),(38,20,'APPLIED','SELECTED','Sample workflow status update',3,'2026-10-22 10:20:00'),(39,21,NULL,'APPLIED','Application created',2,'2026-10-22 10:27:00'),(40,21,'APPLIED','OFFERED','Sample workflow status update',2,'2026-10-23 10:27:00'),(41,22,NULL,'APPLIED','Application created',3,'2026-10-23 10:34:00'),(42,22,'APPLIED','REJECTED','Sample workflow status update',3,'2026-10-24 10:34:00'),(43,23,NULL,'APPLIED','Application created',2,'2026-10-24 10:41:00'),(44,24,NULL,'APPLIED','Application created',3,'2026-10-25 10:48:00'),(45,24,'APPLIED','ELIGIBILITY_VERIFIED','Sample workflow status update',3,'2026-10-26 10:48:00'),(46,25,NULL,'APPLIED','Application created',2,'2026-10-26 10:55:00'),(47,25,'APPLIED','SHORTLISTED','Sample workflow status update',2,'2026-10-27 10:55:00'),(48,26,NULL,'APPLIED','Application created',3,'2026-10-27 10:02:00'),(49,26,'APPLIED','INTERVIEW_SCHEDULED','Sample workflow status update',3,'2026-10-28 10:02:00'),(50,27,NULL,'APPLIED','Application created',2,'2026-10-01 10:09:00'),(51,27,'APPLIED','INTERVIEWED','Sample workflow status update',2,'2026-10-02 10:09:00'),(52,28,NULL,'APPLIED','Application created',3,'2026-10-02 10:16:00'),(53,28,'APPLIED','SELECTED','Sample workflow status update',3,'2026-10-03 10:16:00'),(54,29,NULL,'APPLIED','Application created',2,'2026-10-03 10:23:00'),(55,29,'APPLIED','OFFERED','Sample workflow status update',2,'2026-10-04 10:23:00'),(56,30,NULL,'APPLIED','Application created',3,'2026-10-04 10:30:00'),(57,30,'APPLIED','REJECTED','Sample workflow status update',3,'2026-10-05 10:30:00'),(58,31,NULL,'APPLIED','Application created',2,'2026-10-05 10:37:00'),(59,32,NULL,'APPLIED','Application created',3,'2026-10-06 10:44:00'),(60,32,'APPLIED','ELIGIBILITY_VERIFIED','Sample workflow status update',3,'2026-10-07 10:44:00'),(61,33,NULL,'APPLIED','Application created',2,'2026-10-07 10:51:00'),(62,33,'APPLIED','SHORTLISTED','Sample workflow status update',2,'2026-10-08 10:51:00'),(63,34,NULL,'APPLIED','Application created',3,'2026-10-08 10:58:00'),(64,34,'APPLIED','INTERVIEW_SCHEDULED','Sample workflow status update',3,'2026-10-09 10:58:00'),(65,35,NULL,'APPLIED','Application created',2,'2026-10-09 10:05:00'),(66,35,'APPLIED','INTERVIEWED','Sample workflow status update',2,'2026-10-10 10:05:00'),(67,36,NULL,'APPLIED','Application created',3,'2026-10-10 10:12:00'),(68,36,'APPLIED','SELECTED','Sample workflow status update',3,'2026-10-11 10:12:00'),(69,37,NULL,'APPLIED','Application created',2,'2026-10-11 10:19:00'),(70,37,'APPLIED','OFFERED','Sample workflow status update',2,'2026-10-12 10:19:00'),(71,38,NULL,'APPLIED','Application created',3,'2026-10-12 10:26:00'),(72,38,'APPLIED','REJECTED','Sample workflow status update',3,'2026-10-13 10:26:00'),(73,39,NULL,'APPLIED','Application created',2,'2026-10-13 10:33:00'),(74,40,NULL,'APPLIED','Application created',3,'2026-10-14 10:40:00'),(75,40,'APPLIED','ELIGIBILITY_VERIFIED','Sample workflow status update',3,'2026-10-15 10:40:00');
/*!40000 ALTER TABLE `application_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applications` (
  `application_id` int NOT NULL AUTO_INCREMENT,
  `application_code` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `candidate_id` int NOT NULL,
  `job_id` int NOT NULL,
  `applied_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `eligibility_status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'PENDING',
  `eligibility_reason` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `application_status` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'APPLIED',
  `source` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `assigned_recruiter_id` int DEFAULT NULL,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`application_id`),
  UNIQUE KEY `application_code` (`application_code`),
  UNIQUE KEY `uq_candidate_job` (`candidate_id`,`job_id`),
  KEY `fk_app_job` (`job_id`),
  KEY `idx_applications_status` (`application_status`),
  KEY `idx_applications_recruiter` (`assigned_recruiter_id`),
  CONSTRAINT `fk_app_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidates` (`candidate_id`),
  CONSTRAINT `fk_app_job` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`job_id`),
  CONSTRAINT `fk_app_recruiter` FOREIGN KEY (`assigned_recruiter_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `chk_application_status` CHECK ((`application_status` in (_utf8mb4'APPLIED',_utf8mb4'ELIGIBILITY_VERIFIED',_utf8mb4'SHORTLISTED',_utf8mb4'INTERVIEW_SCHEDULED',_utf8mb4'INTERVIEWED',_utf8mb4'SELECTED',_utf8mb4'REJECTED',_utf8mb4'OFFERED',_utf8mb4'JOINED',_utf8mb4'WITHDRAWN'))),
  CONSTRAINT `chk_eligibility_status` CHECK ((`eligibility_status` in (_utf8mb4'PENDING',_utf8mb4'ELIGIBLE',_utf8mb4'NOT_ELIGIBLE')))
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` VALUES (1,'APP-2026-0001',1,1,'2026-10-02 10:07:00','ELIGIBLE','Candidate meets all eligibility requirements.','SHORTLISTED','Internal',2,'2026-11-02 17:07:22'),(2,'APP-2026-0002',1,9,'2026-10-03 10:14:00','ELIGIBLE','Candidate meets all eligibility requirements.','ELIGIBILITY_VERIFIED','Recruiter',3,'2026-11-02 12:44:52'),(3,'APP-2026-0003',2,2,'2026-10-04 10:21:00','ELIGIBLE','Candidate meets all eligibility requirements.','INTERVIEWED','Referral',2,'2026-11-02 12:49:54'),(4,'APP-2026-0004',2,3,'2026-10-05 10:28:00','ELIGIBLE','Meets eligibility criteria','SELECTED','Portal',3,'2026-10-31 19:11:21'),(5,'APP-2026-0005',3,1,'2026-10-06 10:35:00','ELIGIBLE','Meets eligibility criteria','OFFERED','Internal',2,'2026-10-31 19:11:21'),(6,'APP-2026-0006',3,6,'2026-10-07 10:42:00','NOT_ELIGIBLE','Minimum percentage/backlog criteria not met','REJECTED','Recruiter',3,'2026-10-31 19:11:21'),(7,'APP-2026-0007',4,2,'2026-10-08 10:49:00','ELIGIBLE','Candidate meets all eligibility requirements.','ELIGIBILITY_VERIFIED','Referral',2,'2026-11-02 17:07:29'),(8,'APP-2026-0008',4,5,'2026-10-09 10:56:00','ELIGIBLE','Meets eligibility criteria','ELIGIBILITY_VERIFIED','Portal',3,'2026-10-31 19:11:21'),(9,'APP-2026-0009',5,1,'2026-10-10 10:03:00','ELIGIBLE','Meets eligibility criteria','SHORTLISTED','Internal',2,'2026-10-31 19:11:21'),(10,'APP-2026-0010',5,11,'2026-10-11 10:10:00','ELIGIBLE','Meets eligibility criteria','INTERVIEW_SCHEDULED','Recruiter',3,'2026-10-31 19:11:21'),(11,'APP-2026-0011',6,2,'2026-10-12 10:17:00','ELIGIBLE','Meets eligibility criteria','INTERVIEWED','Referral',2,'2026-10-31 19:11:21'),(12,'APP-2026-0012',6,12,'2026-10-13 10:24:00','ELIGIBLE','Meets eligibility criteria','SELECTED','Portal',3,'2026-10-31 19:11:21'),(13,'APP-2026-0013',7,4,'2026-10-14 10:31:00','ELIGIBLE','Meets eligibility criteria','OFFERED','Internal',2,'2026-10-31 19:11:21'),(14,'APP-2026-0014',7,10,'2026-10-15 10:38:00','NOT_ELIGIBLE','Minimum percentage/backlog criteria not met','REJECTED','Recruiter',3,'2026-10-31 19:11:21'),(15,'APP-2026-0015',8,3,'2026-10-16 10:45:00','ELIGIBLE','Meets eligibility criteria','APPLIED','Referral',2,'2026-10-31 19:11:21'),(16,'APP-2026-0016',8,8,'2026-10-17 10:52:00','ELIGIBLE','Meets eligibility criteria','ELIGIBILITY_VERIFIED','Portal',3,'2026-10-31 19:11:21'),(17,'APP-2026-0017',9,2,'2026-10-18 10:59:00','ELIGIBLE','Meets eligibility criteria','SHORTLISTED','Internal',2,'2026-10-31 19:11:21'),(18,'APP-2026-0018',9,11,'2026-10-19 10:06:00','ELIGIBLE','Meets eligibility criteria','INTERVIEW_SCHEDULED','Recruiter',3,'2026-10-31 19:11:21'),(19,'APP-2026-0019',10,1,'2026-10-20 10:13:00','ELIGIBLE','Meets eligibility criteria','INTERVIEWED','Referral',2,'2026-10-31 19:11:21'),(20,'APP-2026-0020',10,7,'2026-10-21 10:20:00','ELIGIBLE','Meets eligibility criteria','SELECTED','Portal',3,'2026-10-31 19:11:21'),(21,'APP-2026-0021',11,1,'2026-10-22 10:27:00','ELIGIBLE','Candidate meets all eligibility requirements.','OFFERED','Internal',2,'2026-11-02 18:04:16'),(22,'APP-2026-0022',11,9,'2026-10-23 10:34:00','ELIGIBLE','Candidate meets all eligibility requirements.','REJECTED','Recruiter',3,'2026-11-02 17:20:20'),(23,'APP-2026-0023',12,2,'2026-10-24 10:41:00','ELIGIBLE','Meets eligibility criteria','APPLIED','Referral',2,'2026-10-31 19:11:21'),(24,'APP-2026-0024',12,5,'2026-10-25 10:48:00','ELIGIBLE','Meets eligibility criteria','ELIGIBILITY_VERIFIED','Portal',3,'2026-10-31 19:11:21'),(25,'APP-2026-0025',13,4,'2026-10-26 10:55:00','ELIGIBLE','Meets eligibility criteria','SHORTLISTED','Internal',2,'2026-10-31 19:11:21'),(26,'APP-2026-0026',13,10,'2026-10-27 10:02:00','ELIGIBLE','Meets eligibility criteria','INTERVIEW_SCHEDULED','Recruiter',3,'2026-10-31 19:11:21'),(27,'APP-2026-0027',14,3,'2026-10-01 10:09:00','ELIGIBLE','Meets eligibility criteria','INTERVIEWED','Referral',2,'2026-10-31 19:11:21'),(28,'APP-2026-0028',14,8,'2026-10-02 10:16:00','ELIGIBLE','Meets eligibility criteria','SELECTED','Portal',3,'2026-10-31 19:11:21'),(29,'APP-2026-0029',15,1,'2026-10-03 10:23:00','ELIGIBLE','Meets eligibility criteria','OFFERED','Internal',2,'2026-10-31 19:11:21'),(30,'APP-2026-0030',15,7,'2026-10-04 10:30:00','NOT_ELIGIBLE','Minimum percentage/backlog criteria not met','REJECTED','Recruiter',3,'2026-10-31 19:11:21'),(31,'APP-2026-0031',16,2,'2026-10-05 10:37:00','ELIGIBLE','Meets eligibility criteria','APPLIED','Referral',2,'2026-10-31 19:11:21'),(32,'APP-2026-0032',16,12,'2026-10-06 10:44:00','ELIGIBLE','Meets eligibility criteria','ELIGIBILITY_VERIFIED','Portal',3,'2026-10-31 19:11:21'),(33,'APP-2026-0033',17,6,'2026-10-07 10:51:00','ELIGIBLE','Meets eligibility criteria','SHORTLISTED','Internal',2,'2026-10-31 19:11:21'),(34,'APP-2026-0034',17,11,'2026-10-08 10:58:00','ELIGIBLE','Meets eligibility criteria','INTERVIEW_SCHEDULED','Recruiter',3,'2026-10-31 19:11:21'),(35,'APP-2026-0035',18,4,'2026-10-09 10:05:00','ELIGIBLE','Meets eligibility criteria','INTERVIEWED','Referral',2,'2026-10-31 19:11:21'),(36,'APP-2026-0036',18,10,'2026-10-10 10:12:00','ELIGIBLE','Meets eligibility criteria','SELECTED','Portal',3,'2026-10-31 19:11:21'),(37,'APP-2026-0037',19,1,'2026-10-11 10:19:00','ELIGIBLE','Meets eligibility criteria','OFFERED','Internal',2,'2026-10-31 19:11:21'),(38,'APP-2026-0038',19,5,'2026-10-12 10:26:00','NOT_ELIGIBLE','Minimum percentage/backlog criteria not met','REJECTED','Recruiter',3,'2026-10-31 19:11:21'),(39,'APP-2026-0039',20,2,'2026-10-13 10:33:00','ELIGIBLE','Meets eligibility criteria','APPLIED','Referral',2,'2026-10-31 19:11:21'),(40,'APP-2026-0040',20,12,'2026-10-14 10:40:00','ELIGIBLE','Meets eligibility criteria','ELIGIBILITY_VERIFIED','Portal',3,'2026-10-31 19:11:21'),(41,'APPb099d39064f94840b3bbe9bf764',1,2,'2026-11-01 19:52:36','ELIGIBLE','Candidate meets all eligibility requirements.','ELIGIBILITY_VERIFIED','RECRUITER',2,'2026-11-02 12:53:47'),(42,'APPab3583033403489c8c9fd1a97f8',22,14,'2026-11-02 15:29:47','PENDING',NULL,'APPLIED','RECRUITER',2,'2026-11-02 15:29:47'),(43,'APP7fdc8af10dfd455db5ff9dd9f5e',22,10,'2026-11-02 17:44:18','PENDING',NULL,'APPLIED','RECRUITER',2,'2026-11-02 17:44:18');
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate_skills`
--

DROP TABLE IF EXISTS `candidate_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate_skills` (
  `candidate_skill_id` int NOT NULL AUTO_INCREMENT,
  `candidate_id` int NOT NULL,
  `skill_id` int NOT NULL,
  `proficiency_level` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `years_experience` decimal(4,1) NOT NULL DEFAULT '0.0',
  `is_primary` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`candidate_skill_id`),
  UNIQUE KEY `uq_candidate_skill` (`candidate_id`,`skill_id`),
  KEY `idx_candidate_skills_candidate` (`candidate_id`),
  KEY `idx_candidate_skills_skill` (`skill_id`),
  CONSTRAINT `fk_candidate_skills_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidates` (`candidate_id`),
  CONSTRAINT `fk_candidate_skills_skill` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`skill_id`),
  CONSTRAINT `chk_candidate_experience` CHECK ((`years_experience` >= 0)),
  CONSTRAINT `chk_candidate_skill_level` CHECK (((`proficiency_level` is null) or (`proficiency_level` in (_utf8mb4'BASIC',_utf8mb4'INTERMEDIATE',_utf8mb4'ADVANCED'))))
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_skills`
--

LOCK TABLES `candidate_skills` WRITE;
/*!40000 ALTER TABLE `candidate_skills` DISABLE KEYS */;
INSERT INTO `candidate_skills` VALUES (1,1,1,'INTERMEDIATE',0.0,1),(2,1,3,'INTERMEDIATE',0.0,0),(3,1,6,'BASIC',0.0,0),(4,1,7,'INTERMEDIATE',0.0,0),(5,2,2,'INTERMEDIATE',0.0,1),(6,2,3,'INTERMEDIATE',0.0,0),(7,2,7,'INTERMEDIATE',0.0,0),(8,2,4,'BASIC',0.0,0),(9,3,1,'INTERMEDIATE',0.0,1),(10,3,3,'INTERMEDIATE',0.0,0),(11,3,6,'BASIC',0.0,0),(12,3,7,'INTERMEDIATE',0.0,0),(13,4,2,'INTERMEDIATE',0.0,1),(14,4,3,'INTERMEDIATE',0.0,0),(15,4,7,'INTERMEDIATE',0.0,0),(16,4,4,'BASIC',0.0,0),(17,5,1,'INTERMEDIATE',0.0,1),(18,5,3,'INTERMEDIATE',0.0,0),(19,5,6,'BASIC',0.0,0),(20,5,7,'INTERMEDIATE',0.0,0),(21,6,2,'INTERMEDIATE',0.0,1),(22,6,3,'INTERMEDIATE',0.0,0),(23,6,7,'INTERMEDIATE',0.0,0),(24,6,4,'BASIC',0.0,0),(25,7,8,'INTERMEDIATE',0.0,1),(26,7,9,'BASIC',0.0,0),(27,7,3,'BASIC',0.0,0),(28,8,10,'INTERMEDIATE',0.0,1),(29,8,2,'BASIC',0.0,0),(30,8,3,'INTERMEDIATE',0.0,0),(31,9,2,'INTERMEDIATE',0.0,1),(32,9,3,'INTERMEDIATE',0.0,0),(33,9,7,'INTERMEDIATE',0.0,0),(34,9,4,'BASIC',0.0,0),(35,10,1,'INTERMEDIATE',0.0,1),(36,10,3,'INTERMEDIATE',0.0,0),(37,10,6,'BASIC',0.0,0),(38,10,7,'INTERMEDIATE',0.0,0),(39,11,1,'INTERMEDIATE',0.0,1),(40,11,3,'INTERMEDIATE',0.0,0),(41,11,6,'BASIC',0.0,0),(42,11,7,'INTERMEDIATE',0.0,0),(43,12,2,'INTERMEDIATE',0.0,1),(44,12,3,'INTERMEDIATE',0.0,0),(45,12,7,'INTERMEDIATE',0.0,0),(46,12,4,'BASIC',0.0,0),(47,13,8,'INTERMEDIATE',0.0,1),(48,13,9,'BASIC',0.0,0),(49,13,3,'BASIC',0.0,0),(50,14,10,'INTERMEDIATE',0.0,1),(51,14,2,'BASIC',0.0,0),(52,14,3,'INTERMEDIATE',0.0,0),(53,15,1,'INTERMEDIATE',0.0,1),(54,15,3,'INTERMEDIATE',0.0,0),(55,15,6,'BASIC',0.0,0),(56,15,7,'INTERMEDIATE',0.0,0),(57,16,2,'INTERMEDIATE',0.0,1),(58,16,3,'INTERMEDIATE',0.0,0),(59,16,7,'INTERMEDIATE',0.0,0),(60,16,4,'BASIC',0.0,0),(61,17,1,'INTERMEDIATE',0.0,1),(62,17,3,'INTERMEDIATE',0.0,0),(63,17,6,'BASIC',0.0,0),(64,17,7,'INTERMEDIATE',0.0,0),(65,18,8,'INTERMEDIATE',0.0,1),(66,18,9,'BASIC',0.0,0),(67,18,3,'BASIC',0.0,0),(68,19,1,'INTERMEDIATE',0.0,1),(69,19,3,'INTERMEDIATE',0.0,0),(70,19,6,'BASIC',0.0,0),(71,19,7,'INTERMEDIATE',0.0,0),(72,20,2,'INTERMEDIATE',0.0,1),(73,20,3,'INTERMEDIATE',0.0,0),(74,20,7,'INTERMEDIATE',0.0,0),(75,20,4,'BASIC',0.0,0);
/*!40000 ALTER TABLE `candidate_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidates`
--

DROP TABLE IF EXISTS `candidates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidates` (
  `candidate_id` int NOT NULL AUTO_INCREMENT,
  `candidate_code` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `qualification` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `college` varchar(180) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `university` varchar(180) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `passing_year` smallint NOT NULL,
  `percentage` decimal(5,2) DEFAULT NULL,
  `cgpa` decimal(4,2) DEFAULT NULL,
  `backlog_count` int NOT NULL DEFAULT '0',
  `primary_technology` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `current_location` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `preferred_location` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `profile_status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ACTIVE',
  `placement_status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'NOT_PLACED',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`candidate_id`),
  UNIQUE KEY `candidate_code` (`candidate_code`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `mobile` (`mobile`),
  KEY `idx_candidates_primary_technology` (`primary_technology`),
  KEY `idx_candidates_passing_year` (`passing_year`),
  KEY `idx_candidates_placement_status` (`placement_status`),
  CONSTRAINT `chk_candidate_backlog` CHECK ((`backlog_count` >= 0)),
  CONSTRAINT `chk_candidate_percentage` CHECK (((`percentage` is null) or ((`percentage` >= 0) and (`percentage` <= 100)))),
  CONSTRAINT `chk_candidate_placement_status` CHECK ((`placement_status` in (_utf8mb4'NOT_PLACED',_utf8mb4'PLACED'))),
  CONSTRAINT `chk_candidate_profile_status` CHECK ((`profile_status` in (_utf8mb4'ACTIVE',_utf8mb4'INACTIVE',_utf8mb4'ON_HOLD',_utf8mb4'BLACKLISTED')))
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidates`
--

LOCK TABLES `candidates` WRITE;
/*!40000 ALTER TABLE `candidates` DISABLE KEYS */;
INSERT INTO `candidates` VALUES (1,'CAN-2026-0001','Rahul Patil','rahul.patil01@example.com','9000000001','2002-04-12','Male','BE','ABC College Pune','SPPU',2025,68.50,NULL,0,'Java','Pune','Pune','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(2,'CAN-2026-0002','Sneha More','sneha.more02@example.com','9000000002','2002-08-21','Female','BTech','Modern College','SPPU',2025,74.20,NULL,0,'Python','Pune','Bengaluru','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(3,'CAN-2026-0003','Amit Shinde','amit.shinde03@example.com','9000000003','2001-12-02','Male','MCA','MIT College','SPPU',2024,71.00,NULL,0,'Java','Pune','Pune','ACTIVE','PLACED','2026-10-31 19:11:20','2026-10-31 19:11:22'),(4,'CAN-2026-0004','Neha Jadhav','neha.jadhav04@example.com','9000000004','2002-06-13','Female','BE','DY Patil College','SPPU',2025,82.40,NULL,0,'Python','Pimpri','Pune','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(5,'CAN-2026-0005','Rohit Pawar','rohit.pawar05@example.com','9000000005','2002-03-03','Male','BTech','PCCOE','SPPU',2025,59.50,NULL,1,'Java','Pimpri','Pune','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(6,'CAN-2026-0006','Priya Desai','priya.desai06@example.com','9000000006','2001-09-17','Female','MCA','Fergusson College','SPPU',2024,76.10,NULL,0,'Python','Pune','Hyderabad','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(7,'CAN-2026-0007','Akash Kale','akash.kale07@example.com','9000000007','2002-10-11','Male','BE','Sinhgad College','SPPU',2025,64.80,NULL,0,'Testing','Pune','Pune','ACTIVE','PLACED','2026-10-31 19:11:20','2026-10-31 19:11:22'),(8,'CAN-2026-0008','Pooja Mali','pooja.mali08@example.com','9000000008','2002-02-24','Female','BCA','Indira College','SPPU',2025,72.60,NULL,0,'Data Analytics','Pune','Mumbai','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(9,'CAN-2026-0009','Sagar Chavan','sagar.chavan09@example.com','9000000009','2001-11-14','Male','BSc Computer Science','Nowrosjee Wadia College','SPPU',2024,66.30,NULL,0,'Python','Pune','Pune','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(10,'CAN-2026-0010','Komal Gaikwad','komal.gaikwad10@example.com','9000000010','2002-05-09','Female','BE','AISSMS','SPPU',2025,78.90,NULL,0,'Java','Pune','Bengaluru','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(11,'CAN-2026-0011','Vishal Khot','vishal.khot11@example.com','9000000011','2002-01-28','Male','BTech','VIT Pune','SPPU',2025,69.40,NULL,0,'Java','Pune','Pune','ACTIVE','PLACED','2026-10-31 19:11:20','2026-10-31 19:11:22'),(12,'CAN-2026-0012','Anjali Bhosale','anjali.bhosale12@example.com','9000000012','2002-07-30','Female','MCA','IMCC Pune','SPPU',2025,80.10,NULL,0,'Python','Pune','Hyderabad','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(13,'CAN-2026-0013','Kunal Wagh','kunal.wagh13@example.com','9000000013','2001-04-05','Male','BE','MET Nashik','SPPU',2024,62.70,NULL,0,'Testing','Nashik','Pune','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(14,'CAN-2026-0014','Rutuja Salunkhe','rutuja.salunkhe14@example.com','9000000014','2002-09-23','Female','BTech','PCCOE','SPPU',2025,85.00,NULL,0,'Data Analytics','Pimpri','Pune','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(15,'CAN-2026-0015','Nikhil Patil','nikhil.patil15@example.com','9000000015','2001-03-12','Male','MCA','DY Patil','SPPU',2024,73.30,NULL,0,'Java','Pune','Mumbai','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(16,'CAN-2026-0016','Shruti Joshi','shruti.joshi16@example.com','9000000016','2002-12-16','Female','BE','MIT WPU','MIT WPU',2025,67.90,NULL,0,'Python','Pune','Pune','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(17,'CAN-2026-0017','Omkar Mane','omkar.mane17@example.com','9000000017','2002-06-06','Male','BTech','Sinhgad College','SPPU',2025,61.20,NULL,1,'Java','Pune','Pune','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(18,'CAN-2026-0018','Sayali Patil','sayali.patil18@example.com','9000000018','2002-10-19','Female','BCA','Modern College','SPPU',2025,77.50,NULL,0,'Testing','Pune','Mumbai','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(19,'CAN-2026-0019','Aditya Jagtap','aditya.jagtap19@example.com','9000000019','2001-08-08','Male','BE','AISSMS','SPPU',2024,70.60,NULL,0,'Java','Pune','Bengaluru','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(20,'CAN-2026-0020','Mansi Kulkarni','mansi.kulkarni20@example.com','9000000020','2002-11-11','Female','MCA','IMCC Pune','SPPU',2025,83.20,NULL,0,'Python','Pune','Pune','ACTIVE','NOT_PLACED','2026-10-31 19:11:20','2026-10-31 19:11:20'),(21,'CAN4f62cdbe65214cb793cb4032ec7','Rahul Sharma','rahul@gmail.com','1234567890',NULL,NULL,'bsc',NULL,NULL,2027,90.00,NULL,2,NULL,NULL,NULL,'ACTIVE','NOT_PLACED','2026-11-01 19:30:51','2026-11-01 19:30:51'),(22,'CANd79a44d727f540608e5ed1efdac','Dnyaneshwari kamthe','kamthednyaneshwari42@gmail.com','8180057583',NULL,NULL,'BSC cs',NULL,NULL,2027,90.00,NULL,0,NULL,NULL,NULL,'ACTIVE','NOT_PLACED','2026-11-02 14:48:52','2026-11-02 14:48:52'),(25,'CAN8bb0a9e9e90d41598f5cbca323e','sakshi jadhav','sakshi@gmail.com','2345678976',NULL,NULL,'BSc computer science',NULL,NULL,2027,90.00,NULL,1,NULL,NULL,NULL,'ACTIVE','NOT_PLACED','2026-11-02 17:31:42','2026-11-02 17:31:42');
/*!40000 ALTER TABLE `candidates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companies` (
  `company_id` int NOT NULL AUTO_INCREMENT,
  `company_code` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `company_name` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `industry` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `website` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `location` varchar(120) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_type` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hr_name` varchar(120) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hr_email` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hr_mobile` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ACTIVE',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`company_id`),
  UNIQUE KEY `company_code` (`company_code`),
  KEY `idx_companies_name` (`company_name`),
  KEY `idx_companies_status` (`status`),
  CONSTRAINT `chk_company_status` CHECK ((`status` in (_utf8mb4'ACTIVE',_utf8mb4'INACTIVE')))
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'COM-001','TechNova Solutions','IT Services','https://example.com/technova','Pune','MID_SIZE','Meera Shah','meera.hr@example.com','9100000001','ACTIVE','2026-10-31 19:11:21'),(2,'COM-002','BluePeak Systems','Software','https://example.com/bluepeak','Bengaluru','MNC','Rakesh Nair','rakesh.hr@example.com','9100000002','ACTIVE','2026-10-31 19:11:21'),(3,'COM-003','DataSphere Labs','Analytics','https://example.com/datasphere','Pune','STARTUP','Anita Rao','anita.hr@example.com','9100000003','ACTIVE','2026-10-31 19:11:21'),(4,'COM-004','QualityFirst Tech','Testing','https://example.com/qualityfirst','Mumbai','MID_SIZE','Kavita Mehta','kavita.hr@example.com','9100000004','ACTIVE','2026-10-31 19:11:21'),(5,'COM-005','CloudBridge India','Cloud & Software','https://example.com/cloudbridge','Hyderabad','MNC','Arun Das','arun.hr@example.com','9100000005','ACTIVE','2026-10-31 19:11:21'),(6,'COM-006','NexGen Infotech','IT Services','https://example.com/nexgen','Pune','CONSULTANCY','Priyanka Sen','priyanka.hr@example.com','9100000006','ACTIVE','2026-10-31 19:11:21'),(7,'COM-007','FinEdge Technologies','FinTech','https://example.com/finedge','Mumbai','MNC','Harish Jain','harish.hr@example.com','9100000007','ACTIVE','2026-10-31 19:11:21'),(8,'COM-008','RetailIQ Labs','Retail Analytics','https://example.com/retailiq','Chennai','STARTUP','Divya Krishnan','divya.hr@example.com','9100000008','ACTIVE','2026-10-31 19:11:21'),(9,'COMda85b19bb79d4d838e6e5ee61e5','infosys','IT','www.infosys.com','pune','MNC','smit sharma','smit@gmail.com','1234567890','ACTIVE','2026-11-01 19:36:35'),(10,'COMbceb180feb9d4c1d92ab40d988a','kiran academy','IT','https://tka.com','karve nagar','MNC','kiran','kirann@tka.com','1234556788','ACTIVE','2026-11-02 14:59:43'),(11,'COM3f9ba53626b141e78a4725993e3','IBM','IT','https://ibm.com','pune,maharashtra','MNC','manuu','manuu@gmail.com','2586367631','ACTIVE','2026-11-02 17:39:21');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interview_feedback`
--

DROP TABLE IF EXISTS `interview_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interview_feedback` (
  `feedback_id` int NOT NULL AUTO_INCREMENT,
  `interview_id` int NOT NULL,
  `technical_rating` decimal(3,1) DEFAULT NULL,
  `communication_rating` decimal(3,1) DEFAULT NULL,
  `problem_solving_rating` decimal(3,1) DEFAULT NULL,
  `overall_rating` decimal(3,1) DEFAULT NULL,
  `recommendation` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remarks` text COLLATE utf8mb4_unicode_ci,
  `submitted_by` int NOT NULL,
  `submitted_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`feedback_id`),
  UNIQUE KEY `interview_id` (`interview_id`),
  KEY `fk_feedback_user` (`submitted_by`),
  KEY `idx_feedback_recommendation` (`recommendation`),
  CONSTRAINT `fk_feedback_interview` FOREIGN KEY (`interview_id`) REFERENCES `interview_rounds` (`interview_id`),
  CONSTRAINT `fk_feedback_user` FOREIGN KEY (`submitted_by`) REFERENCES `users` (`user_id`),
  CONSTRAINT `chk_feedback_communication` CHECK (((`communication_rating` is null) or ((`communication_rating` >= 0) and (`communication_rating` <= 5)))),
  CONSTRAINT `chk_feedback_overall` CHECK (((`overall_rating` is null) or ((`overall_rating` >= 0) and (`overall_rating` <= 5)))),
  CONSTRAINT `chk_feedback_problem_solving` CHECK (((`problem_solving_rating` is null) or ((`problem_solving_rating` >= 0) and (`problem_solving_rating` <= 5)))),
  CONSTRAINT `chk_feedback_recommendation` CHECK ((`recommendation` in (_utf8mb4'NEXT_ROUND',_utf8mb4'SELECTED',_utf8mb4'REJECTED',_utf8mb4'ON_HOLD'))),
  CONSTRAINT `chk_feedback_technical` CHECK (((`technical_rating` is null) or ((`technical_rating` >= 0) and (`technical_rating` <= 5))))
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview_feedback`
--

LOCK TABLES `interview_feedback` WRITE;
/*!40000 ALTER TABLE `interview_feedback` DISABLE KEYS */;
INSERT INTO `interview_feedback` VALUES (1,2,4.0,3.5,4.0,3.8,'NEXT_ROUND','Good fundamentals; continue to next stage.',2,'2026-10-31 19:11:21'),(2,3,4.0,3.5,4.0,3.8,'SELECTED','Recommended for selection.',3,'2026-10-31 19:11:21'),(3,4,4.0,3.5,4.0,3.8,'SELECTED','Recommended for selection.',2,'2026-10-31 19:11:21'),(4,6,4.0,3.5,4.0,3.8,'NEXT_ROUND','Good fundamentals; continue to next stage.',2,'2026-10-31 19:11:21'),(5,7,4.0,3.5,4.0,3.8,'SELECTED','Recommended for selection.',3,'2026-10-31 19:11:21'),(6,8,4.0,3.5,4.0,3.8,'SELECTED','Recommended for selection.',2,'2026-10-31 19:11:21'),(7,10,4.0,3.5,4.0,3.8,'NEXT_ROUND','Good fundamentals; continue to next stage.',2,'2026-10-31 19:11:21'),(8,11,4.0,3.5,4.0,3.8,'SELECTED','Recommended for selection.',3,'2026-10-31 19:11:21'),(9,12,4.0,3.5,4.0,3.8,'SELECTED','Recommended for selection.',2,'2026-10-31 19:11:21'),(10,14,4.0,3.5,4.0,3.8,'NEXT_ROUND','Good fundamentals; continue to next stage.',2,'2026-10-31 19:11:22'),(11,15,4.0,3.5,4.0,3.8,'SELECTED','Recommended for selection.',3,'2026-10-31 19:11:22'),(12,16,4.0,3.5,4.0,3.8,'SELECTED','Recommended for selection.',2,'2026-10-31 19:11:22'),(13,18,4.0,3.5,4.0,3.8,'NEXT_ROUND','Good fundamentals; continue to next stage.',2,'2026-10-31 19:11:22'),(14,19,4.0,3.5,4.0,3.8,'SELECTED','Recommended for selection.',3,'2026-10-31 19:11:22'),(15,20,4.0,3.5,4.0,3.8,'SELECTED','Recommended for selection.',2,'2026-10-31 19:11:22'),(16,1,2.0,3.0,4.0,5.0,'selected','goos performance',2,'2026-11-02 12:34:32'),(17,17,4.0,4.0,4.0,4.0,'selected','very good performance',2,'2026-11-02 16:54:35'),(18,21,4.0,4.0,4.0,4.0,'selected','good attire ',4,'2026-11-02 18:02:50');
/*!40000 ALTER TABLE `interview_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interview_rounds`
--

DROP TABLE IF EXISTS `interview_rounds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interview_rounds` (
  `interview_id` int NOT NULL AUTO_INCREMENT,
  `application_id` int NOT NULL,
  `round_number` int NOT NULL,
  `round_type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `scheduled_date` date NOT NULL,
  `scheduled_time` time DEFAULT NULL,
  `mode` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `interviewer_name` varchar(120) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `meeting_or_location` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'SCHEDULED',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`interview_id`),
  UNIQUE KEY `uq_application_round` (`application_id`,`round_number`),
  KEY `idx_interviews_date` (`scheduled_date`),
  KEY `idx_interviews_status` (`status`),
  CONSTRAINT `fk_interview_application` FOREIGN KEY (`application_id`) REFERENCES `applications` (`application_id`),
  CONSTRAINT `chk_interview_mode` CHECK ((`mode` in (_utf8mb4'ONLINE',_utf8mb4'OFFLINE',_utf8mb4'TELEPHONIC'))),
  CONSTRAINT `chk_interview_round_number` CHECK ((`round_number` > 0)),
  CONSTRAINT `chk_interview_status` CHECK ((`status` in (_utf8mb4'SCHEDULED',_utf8mb4'COMPLETED',_utf8mb4'RESCHEDULED',_utf8mb4'CANCELLED',_utf8mb4'NO_SHOW')))
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview_rounds`
--

LOCK TABLES `interview_rounds` WRITE;
/*!40000 ALTER TABLE `interview_rounds` DISABLE KEYS */;
INSERT INTO `interview_rounds` VALUES (1,2,1,'TECHNICAL','2026-11-03','11:00:00','ONLINE','Interviewer 2','https://meet.example.com/rpms-2','SCHEDULED','2026-10-31 19:11:21'),(2,3,1,'TECHNICAL','2026-11-04','11:00:00','ONLINE','Interviewer 3','https://meet.example.com/rpms-3','COMPLETED','2026-10-31 19:11:21'),(3,4,1,'TECHNICAL','2026-11-05','11:00:00','ONLINE','Interviewer 4','https://meet.example.com/rpms-4','COMPLETED','2026-10-31 19:11:21'),(4,5,1,'TECHNICAL','2026-11-06','11:00:00','ONLINE','Interviewer 5','https://meet.example.com/rpms-5','COMPLETED','2026-10-31 19:11:21'),(5,10,1,'TECHNICAL','2026-11-11','11:00:00','ONLINE','Interviewer 5','https://meet.example.com/rpms-10','SCHEDULED','2026-10-31 19:11:21'),(6,11,1,'TECHNICAL','2026-11-12','11:00:00','ONLINE','Interviewer 1','https://meet.example.com/rpms-11','COMPLETED','2026-10-31 19:11:21'),(7,12,1,'TECHNICAL','2026-11-13','11:00:00','ONLINE','Interviewer 2','https://meet.example.com/rpms-12','COMPLETED','2026-10-31 19:11:21'),(8,13,1,'TECHNICAL','2026-11-14','11:00:00','ONLINE','Interviewer 3','https://meet.example.com/rpms-13','COMPLETED','2026-10-31 19:11:21'),(9,18,1,'TECHNICAL','2026-11-19','11:00:00','ONLINE','Interviewer 3','https://meet.example.com/rpms-18','SCHEDULED','2026-10-31 19:11:21'),(10,19,1,'TECHNICAL','2026-11-20','11:00:00','ONLINE','Interviewer 4','https://meet.example.com/rpms-19','COMPLETED','2026-10-31 19:11:21'),(11,20,1,'TECHNICAL','2026-11-01','11:00:00','ONLINE','Interviewer 5','https://meet.example.com/rpms-20','COMPLETED','2026-10-31 19:11:21'),(12,21,1,'TECHNICAL','2026-11-02','11:00:00','ONLINE','Interviewer 1','https://meet.example.com/rpms-21','COMPLETED','2026-10-31 19:11:21'),(13,26,1,'TECHNICAL','2026-11-07','11:00:00','ONLINE','Interviewer 1','https://meet.example.com/rpms-26','SCHEDULED','2026-10-31 19:11:21'),(14,27,1,'TECHNICAL','2026-11-08','11:00:00','ONLINE','Interviewer 2','https://meet.example.com/rpms-27','COMPLETED','2026-10-31 19:11:21'),(15,28,1,'TECHNICAL','2026-11-09','11:00:00','ONLINE','Interviewer 3','https://meet.example.com/rpms-28','COMPLETED','2026-10-31 19:11:21'),(16,29,1,'TECHNICAL','2026-11-10','11:00:00','ONLINE','Interviewer 4','https://meet.example.com/rpms-29','COMPLETED','2026-10-31 19:11:21'),(17,34,1,'TECHNICAL','2026-11-15','11:00:00','ONLINE','Interviewer 4','https://meet.example.com/rpms-34','SCHEDULED','2026-10-31 19:11:21'),(18,35,1,'TECHNICAL','2026-11-16','11:00:00','ONLINE','Interviewer 5','https://meet.example.com/rpms-35','COMPLETED','2026-10-31 19:11:21'),(19,36,1,'TECHNICAL','2026-11-17','11:00:00','ONLINE','Interviewer 1','https://meet.example.com/rpms-36','COMPLETED','2026-10-31 19:11:21'),(20,37,1,'TECHNICAL','2026-11-18','11:00:00','ONLINE','Interviewer 2','https://meet.example.com/rpms-37','COMPLETED','2026-10-31 19:11:21'),(21,1,2,'TECHNICAL','2026-11-05','10:30:00','ONLINE','amit sharma','pune','SCHEDULED','2026-11-01 19:58:41'),(22,5,2,'APTITUDE','2026-11-25','18:41:00','ONLINE','kiran ','https://zoom.com','SCHEDULED','2026-11-02 15:39:31'),(23,3,3,'TECHNICAL','2026-11-16','22:47:00','ONLINE','dsk','https://zoom.com','SCHEDULED','2026-11-02 17:47:01');
/*!40000 ALTER TABLE `interview_rounds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_skills`
--

DROP TABLE IF EXISTS `job_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_skills` (
  `job_skill_id` int NOT NULL AUTO_INCREMENT,
  `job_id` int NOT NULL,
  `skill_id` int NOT NULL,
  `minimum_level` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mandatory_flag` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`job_skill_id`),
  UNIQUE KEY `uq_job_skill` (`job_id`,`skill_id`),
  KEY `fk_job_skills_skill` (`skill_id`),
  CONSTRAINT `fk_job_skills_job` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`job_id`),
  CONSTRAINT `fk_job_skills_skill` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`skill_id`),
  CONSTRAINT `chk_job_skill_level` CHECK (((`minimum_level` is null) or (`minimum_level` in (_utf8mb4'BASIC',_utf8mb4'INTERMEDIATE',_utf8mb4'ADVANCED'))))
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_skills`
--

LOCK TABLES `job_skills` WRITE;
/*!40000 ALTER TABLE `job_skills` DISABLE KEYS */;
INSERT INTO `job_skills` VALUES (1,1,1,'INTERMEDIATE',1),(2,1,3,'INTERMEDIATE',1),(3,1,6,'BASIC',1),(4,2,2,'INTERMEDIATE',1),(5,2,3,'BASIC',1),(6,2,7,'BASIC',0),(7,3,10,'INTERMEDIATE',1),(8,3,3,'INTERMEDIATE',1),(9,3,2,'BASIC',0),(10,4,8,'INTERMEDIATE',1),(11,4,3,'BASIC',0),(12,5,1,'BASIC',0),(13,5,2,'BASIC',0),(14,5,3,'INTERMEDIATE',1),(15,6,1,'INTERMEDIATE',1),(16,6,3,'BASIC',1),(17,7,1,'ADVANCED',1),(18,7,3,'INTERMEDIATE',1),(19,7,6,'INTERMEDIATE',1),(20,8,10,'BASIC',1),(21,8,3,'BASIC',1),(22,9,3,'INTERMEDIATE',1),(23,9,7,'INTERMEDIATE',1),(24,10,8,'INTERMEDIATE',1),(25,10,9,'BASIC',1),(26,10,3,'BASIC',0),(27,11,3,'BASIC',1),(28,11,1,'BASIC',0),(29,11,2,'BASIC',0),(30,12,10,'INTERMEDIATE',1),(31,12,3,'INTERMEDIATE',1),(32,12,2,'BASIC',0);
/*!40000 ALTER TABLE `job_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobs` (
  `job_id` int NOT NULL AUTO_INCREMENT,
  `job_code` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `company_id` int NOT NULL,
  `job_title` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `employment_type` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'FULL_TIME',
  `location` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `openings` int NOT NULL,
  `min_package_lpa` decimal(6,2) DEFAULT NULL,
  `max_package_lpa` decimal(6,2) DEFAULT NULL,
  `min_percentage` decimal(5,2) DEFAULT NULL,
  `min_passing_year` smallint DEFAULT NULL,
  `max_passing_year` smallint DEFAULT NULL,
  `max_backlogs` int NOT NULL DEFAULT '0',
  `experience_min_years` decimal(4,1) NOT NULL DEFAULT '0.0',
  `qualification_rule` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `posted_date` date NOT NULL,
  `application_deadline` date NOT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'OPEN',
  `created_by` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`job_id`),
  UNIQUE KEY `job_code` (`job_code`),
  KEY `fk_jobs_created_by` (`created_by`),
  KEY `idx_jobs_company` (`company_id`),
  KEY `idx_jobs_status` (`status`),
  KEY `idx_jobs_deadline` (`application_deadline`),
  KEY `idx_jobs_location` (`location`),
  CONSTRAINT `fk_jobs_company` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`),
  CONSTRAINT `fk_jobs_created_by` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`),
  CONSTRAINT `chk_job_status` CHECK ((`status` in (_utf8mb4'DRAFT',_utf8mb4'OPEN',_utf8mb4'CLOSED',_utf8mb4'CANCELLED'))),
  CONSTRAINT `chk_jobs_backlogs` CHECK ((`max_backlogs` >= 0)),
  CONSTRAINT `chk_jobs_dates` CHECK ((`application_deadline` >= `posted_date`)),
  CONSTRAINT `chk_jobs_experience` CHECK ((`experience_min_years` >= 0)),
  CONSTRAINT `chk_jobs_openings` CHECK ((`openings` > 0)),
  CONSTRAINT `chk_jobs_package` CHECK (((`min_package_lpa` is null) or (`max_package_lpa` is null) or (`min_package_lpa` <= `max_package_lpa`))),
  CONSTRAINT `chk_jobs_percentage` CHECK (((`min_percentage` is null) or ((`min_percentage` >= 0) and (`min_percentage` <= 100))))
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (1,'JOB-2026-001',1,'Associate Java Developer','FULL_TIME','Pune',8,4.00,5.50,60.00,2024,2026,0,0.0,'BE/BTech/MCA','2026-10-01','2026-11-15','OPEN',2,'2026-10-31 19:11:21'),(2,'JOB-2026-002',2,'Python Developer Trainee','FULL_TIME','Bengaluru',10,4.20,6.00,65.00,2024,2026,0,0.0,'BE/BTech/MCA/BCA','2026-10-02','2026-11-20','OPEN',3,'2026-10-31 19:11:21'),(3,'JOB-2026-003',3,'Junior Data Analyst','FULL_TIME','Pune',6,4.00,5.00,60.00,2024,2026,0,0.0,'BE/BTech/BCA/BSc/MCA','2026-10-03','2026-11-18','OPEN',2,'2026-10-31 19:11:21'),(4,'JOB-2026-004',4,'QA Engineer Trainee','FULL_TIME','Mumbai',7,3.80,5.00,55.00,2024,2026,1,0.0,'Any Computer Graduate','2026-10-04','2026-11-22','OPEN',3,'2026-10-31 19:11:21'),(5,'JOB-2026-005',5,'Graduate Software Engineer','FULL_TIME','Hyderabad',12,5.00,7.00,65.00,2024,2026,0,0.0,'BE/BTech/MCA','2026-10-05','2026-11-25','OPEN',2,'2026-10-31 19:11:21'),(6,'JOB-2026-006',6,'Java Support Engineer','FULL_TIME','Pune',5,3.60,4.80,55.00,2023,2026,1,0.0,'BE/BTech/MCA/BCA','2026-09-15','2026-10-31','OPEN',3,'2026-10-31 19:11:21'),(7,'JOB-2026-007',7,'Software Engineer - Java','FULL_TIME','Mumbai',4,5.50,7.50,70.00,2024,2026,0,0.0,'BE/BTech/MCA','2026-09-20','2026-10-30','CLOSED',2,'2026-10-31 19:11:21'),(8,'JOB-2026-008',8,'Data Analytics Intern','INTERNSHIP','Chennai',10,2.40,3.00,60.00,2024,2026,0,0.0,'Any Graduate','2026-10-07','2026-11-30','OPEN',3,'2026-10-31 19:11:21'),(9,'JOB-2026-009',1,'SQL Developer Trainee','FULL_TIME','Pune',5,3.80,5.00,60.00,2024,2026,0,0.0,'BE/BTech/MCA/BCA','2026-10-08','2026-11-28','OPEN',2,'2026-10-31 19:11:21'),(10,'JOB-2026-010',4,'Automation Testing Trainee','FULL_TIME','Pune',5,4.00,5.20,60.00,2024,2026,0,0.0,'BE/BTech/MCA/BCA','2026-10-10','2026-11-29','OPEN',3,'2026-10-31 19:11:21'),(11,'JOB-2026-011',6,'Application Support Associate','FULL_TIME','Pune',6,3.50,4.50,55.00,2023,2026,1,0.0,'Any Computer Graduate','2026-10-11','2026-12-01','OPEN',2,'2026-10-31 19:11:21'),(12,'JOB-2026-012',3,'BI Analyst Trainee','FULL_TIME','Pune',4,4.50,5.80,65.00,2024,2026,0,0.0,'BE/BTech/BCA/BSc/MCA','2026-10-12','2026-12-05','OPEN',3,'2026-10-31 19:11:21'),(13,'JOBcfce5a3e1d4c46998fcef49577f',1,'java Developer','FULL_TIME','pune',5,3.00,5.00,60.00,2027,2028,2,0.0,'Bsc computer science','2026-11-01','2026-11-30','OPEN',2,'2026-11-01 19:45:15'),(14,'JOB292a6956737f406db954f9cf154',10,'java full stack intern','INTERNSHIP','karve nagar',1,2.90,7.70,75.00,2026,2027,1,2.0,'bsc cs','2026-11-01','2026-11-02','OPEN',1,'2026-11-02 15:14:27'),(15,'JOB3b2119bb2ed94dbfb630b13d455',5,'java full stack intern','PART_TIME','pune',5,4.00,5.00,79.00,2026,2027,2,3.0,'bsc cs','2026-11-09','2026-11-16','OPEN',1,'2026-11-02 17:42:29');
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `state_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `country_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'India',
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`location_id`),
  UNIQUE KEY `uq_location` (`city_name`,`state_name`,`country_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'Pune','Maharashtra','India',1),(2,'Mumbai','Maharashtra','India',1),(3,'Nashik','Maharashtra','India',1),(4,'Bengaluru','Karnataka','India',1),(5,'Hyderabad','Telangana','India',1),(6,'Chennai','Tamil Nadu','India',1);
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offers` (
  `offer_id` int NOT NULL AUTO_INCREMENT,
  `offer_code` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `application_id` int NOT NULL,
  `designation` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `offered_package_lpa` decimal(6,2) NOT NULL,
  `offer_date` date NOT NULL,
  `expected_joining_date` date DEFAULT NULL,
  `offer_status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'OFFERED',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`offer_id`),
  UNIQUE KEY `offer_code` (`offer_code`),
  UNIQUE KEY `application_id` (`application_id`),
  KEY `idx_offers_status` (`offer_status`),
  CONSTRAINT `fk_offer_application` FOREIGN KEY (`application_id`) REFERENCES `applications` (`application_id`),
  CONSTRAINT `chk_offer_package` CHECK ((`offered_package_lpa` >= 0)),
  CONSTRAINT `chk_offer_status` CHECK ((`offer_status` in (_utf8mb4'OFFERED',_utf8mb4'ACCEPTED',_utf8mb4'REJECTED',_utf8mb4'EXPIRED',_utf8mb4'WITHDRAWN')))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (1,'OFF-2026-001',5,'Associate Java Developer',4.30,'2026-11-11','2026-12-02','ACCEPTED','Sample offer record','2026-10-31 19:11:22'),(2,'OFF-2026-002',13,'QA Engineer Trainee',4.10,'2026-11-12','2026-12-03','ACCEPTED','Sample offer record','2026-10-31 19:11:22'),(3,'OFF-2026-003',21,'Associate Java Developer',4.30,'2026-11-13','2026-12-04','ACCEPTED','Sample offer record','2026-10-31 19:11:22'),(4,'OFF-2026-004',29,'Associate Java Developer',4.30,'2026-11-14','2026-12-05','OFFERED','Sample offer record','2026-10-31 19:11:22'),(5,'OFF-2026-005',4,'Junior Data Analyst',4.30,'2026-11-15','2026-12-06','ACCEPTED','Sample offer record','2026-10-31 19:11:22'),(6,'OFF-2026-006',12,'BI Analyst Trainee',4.80,'2026-11-16','2026-12-07','OFFERED','Sample offer record','2026-10-31 19:11:22'),(7,'OFFeb6a2fc0241a4d96a3cb9ade7ff',8,'java dev',1.50,'2026-11-09','2026-11-23','ACCEPTED',NULL,'2026-11-02 15:56:32'),(8,'OFFa2542bb9ff03469eb9da06ca712',18,'java dev',6.00,'2026-11-16','2026-11-23','REJECTED',NULL,'2026-11-02 17:49:31');
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `placements`
--

DROP TABLE IF EXISTS `placements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `placements` (
  `placement_id` int NOT NULL AUTO_INCREMENT,
  `candidate_id` int NOT NULL,
  `company_id` int NOT NULL,
  `job_id` int NOT NULL,
  `offer_id` int NOT NULL,
  `designation` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `final_package_lpa` decimal(6,2) NOT NULL,
  `expected_joining_date` date DEFAULT NULL,
  `actual_joining_date` date DEFAULT NULL,
  `joining_status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'PENDING',
  `placement_status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ACTIVE',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`placement_id`),
  UNIQUE KEY `offer_id` (`offer_id`),
  KEY `fk_placement_job` (`job_id`),
  KEY `idx_placements_candidate` (`candidate_id`),
  KEY `idx_placements_company` (`company_id`),
  KEY `idx_placements_joining_status` (`joining_status`),
  CONSTRAINT `fk_placement_candidate` FOREIGN KEY (`candidate_id`) REFERENCES `candidates` (`candidate_id`),
  CONSTRAINT `fk_placement_company` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`),
  CONSTRAINT `fk_placement_job` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`job_id`),
  CONSTRAINT `fk_placement_offer` FOREIGN KEY (`offer_id`) REFERENCES `offers` (`offer_id`),
  CONSTRAINT `chk_joining_status` CHECK ((`joining_status` in (_utf8mb4'PENDING',_utf8mb4'JOINED',_utf8mb4'NOT_JOINED',_utf8mb4'DEFERRED',_utf8mb4'NO_SHOW'))),
  CONSTRAINT `chk_placement_package` CHECK ((`final_package_lpa` >= 0)),
  CONSTRAINT `chk_placement_status` CHECK ((`placement_status` in (_utf8mb4'ACTIVE',_utf8mb4'CANCELLED')))
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `placements`
--

LOCK TABLES `placements` WRITE;
/*!40000 ALTER TABLE `placements` DISABLE KEYS */;
INSERT INTO `placements` VALUES (1,3,1,1,1,'Associate Java Developer',4.30,'2026-12-02','2026-12-02','JOINED','ACTIVE','2026-10-31 19:11:22'),(2,7,4,4,2,'QA Engineer Trainee',4.10,'2026-12-03','2026-12-03','JOINED','ACTIVE','2026-10-31 19:11:22'),(3,11,1,1,3,'Associate Java Developer',4.30,'2026-12-04','2026-12-04','JOINED','ACTIVE','2026-10-31 19:11:22'),(4,22,10,1,7,'java dev',4.00,'2026-11-09','2026-11-16','JOINED','ACTIVE','2026-11-02 16:11:40'),(8,8,3,6,4,'java dev',5.00,'2026-11-09','2026-11-16','JOINED','ACTIVE','2026-11-02 16:20:51'),(12,22,10,13,5,'java dev',8.00,'2026-11-16','2026-11-30','JOINED','ACTIVE','2026-11-02 16:25:20'),(15,5,9,9,6,'java dev',9.00,'2026-11-09','2026-11-16','JOINED','ACTIVE','2026-11-02 16:32:02'),(16,12,8,12,8,'java dev',8.00,'2026-11-19','2026-11-24','NOT_JOINED','ACTIVE','2026-11-02 17:51:55');
/*!40000 ALTER TABLE `placements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualifications`
--

DROP TABLE IF EXISTS `qualifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qualifications` (
  `qualification_id` int NOT NULL AUTO_INCREMENT,
  `qualification_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`qualification_id`),
  UNIQUE KEY `qualification_name` (`qualification_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualifications`
--

LOCK TABLES `qualifications` WRITE;
/*!40000 ALTER TABLE `qualifications` DISABLE KEYS */;
INSERT INTO `qualifications` VALUES (1,'BE',1,'2026-10-31 19:11:20'),(2,'BTech',1,'2026-10-31 19:11:20'),(3,'MCA',1,'2026-10-31 19:11:20'),(4,'BCA',1,'2026-10-31 19:11:20'),(5,'BSc Computer Science',1,'2026-10-31 19:11:20'),(6,'MSc Computer Science',1,'2026-10-31 19:11:20');
/*!40000 ALTER TABLE `qualifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_code` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role_name` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_code` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN','Administrator',1,'2026-10-31 19:11:20'),(2,'RECRUITER','Recruiter',1,'2026-10-31 19:11:20'),(3,'PLACEMENT_OFFICER','Placement Officer',1,'2026-10-31 19:11:20');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skills` (
  `skill_id` int NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `category` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`skill_id`),
  UNIQUE KEY `skill_name` (`skill_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (1,'Java','Programming',1),(2,'Python','Programming',1),(3,'SQL','Database',1),(4,'HTML','Web',1),(5,'CSS','Web',1),(6,'JDBC','Database Connectivity',1),(7,'MySQL','Database',1),(8,'Manual Testing','Testing',1),(9,'Selenium','Testing',1),(10,'Data Analytics','Analytics',1);
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `full_name` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` int NOT NULL,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ACTIVE',
  `last_login_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_users_role` (`role_id`),
  CONSTRAINT `fk_users_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `chk_user_status` CHECK ((`status` in (_utf8mb4'ACTIVE',_utf8mb4'INACTIVE',_utf8mb4'LOCKED')))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2y$10$DEMO_ADMIN_HASH_ONLY','System Admin','admin@example.com',1,'ACTIVE',NULL,'2026-10-31 19:11:20'),(2,'amit.recruiter','$2y$10$DEMO_AMIT_HASH_ONLY','Amit Kulkarni','amit.recruiter@example.com',2,'ACTIVE',NULL,'2026-10-31 19:11:20'),(3,'sneha.recruiter','$2y$10$DEMO_SNEHA_HASH_ONLY','Sneha Patil','sneha.recruiter@example.com',2,'ACTIVE',NULL,'2026-10-31 19:11:20'),(4,'neha.placement','$2y$10$DEMO_NEHA_HASH_ONLY','Neha Deshmukh','neha.placement@example.com',3,'ACTIVE',NULL,'2026-10-31 19:11:20'),(5,'rahul.placement','$2y$10$DEMO_RAHUL_HASH_ONLY','Rahul Joshi','rahul.placement@example.com',3,'ACTIVE',NULL,'2026-10-31 19:11:20');
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

-- Dump completed on 2026-11-02 20:18:26
