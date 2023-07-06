-- MySQL dump 10.14  Distrib 5.5.67-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: straw
-- ------------------------------------------------------
-- Server version	5.5.67-MariaDB

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL COMMENT '答案正文',
  `user_id` int(11) NOT NULL COMMENT '作者id',
  `user_nick_name` varchar(20) NOT NULL COMMENT '作者昵称',
  `question_id` int(11) NOT NULL COMMENT '问题id',
  `is_accepted` int(1) NOT NULL COMMENT '是否已采纳，0-未采纳，1-已采纳',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `class_info`
--

DROP TABLE IF EXISTS `class_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '班级名称',
  `invite_code` varchar(255) NOT NULL COMMENT '邀请码',
  `is_enabled` tinyint(4) NOT NULL COMMENT '是否启用，0-禁用，1-启用',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_info`
--

LOCK TABLES `class_info` WRITE;
/*!40000 ALTER TABLE `class_info` DISABLE KEYS */;
INSERT INTO `class_info` VALUES (1,'JSD1912','JSD1912-876840',0,'2020-09-04 22:32:57','2020-09-04 22:32:58'),(2,'JSD2001','JSD2001-706246',0,'2020-09-04 22:33:00','2020-09-04 22:32:59'),(3,'JSD2002','JSD2002-525416',0,'2020-09-04 22:32:59','2020-09-04 22:33:01'),(4,'JSD2003','JSD2003-005803',1,'2020-09-04 22:33:02','2020-09-04 22:33:01'),(5,'JSD2004','JSD2004-128537',1,'2020-09-04 22:33:02','2020-09-04 22:33:03'),(6,'JSD2005','JSD2005-452485',1,'2020-09-04 22:33:05','2020-09-04 22:33:04'),(7,'JSD2006','JSD2006-666666',1,'2020-10-09 23:46:05','2020-10-09 23:46:06');
/*!40000 ALTER TABLE `class_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '作者id',
  `user_nick_name` varchar(20) NOT NULL COMMENT '作者昵称',
  `answer_id` int(11) NOT NULL COMMENT '答案id',
  `content` text NOT NULL COMMENT '正文',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) NOT NULL COMMENT '权限标识',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'/web/index','访问主页','2020-09-04 22:29:32','2020-09-04 22:29:30'),(2,'/question/post','发布问题','2020-09-04 22:29:53','2020-09-04 22:29:53'),(3,'/question/upload','发布问题时提交附件','2020-09-04 22:30:28','2020-09-04 22:30:30'),(4,'/question/detail','查看问题详情','2020-09-04 22:30:50','2020-09-04 22:30:48'),(5,'/answer/post','发表答案','2020-09-04 22:31:14','2020-09-04 22:31:15'),(6,'/comment/post','发表评论','2020-09-04 22:31:35','2020-09-04 22:31:36');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '标题',
  `content` longtext NOT NULL COMMENT '正文',
  `user_id` int(11) NOT NULL COMMENT '作者id',
  `user_nick_name` varchar(20) NOT NULL COMMENT '作者昵称',
  `status` int(1) NOT NULL COMMENT '状态，0-未回复，1-未解决，2-已解决',
  `hits` int(11) NOT NULL COMMENT '点击量',
  `tag_ids` varchar(255) DEFAULT NULL COMMENT '问题的各标签id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question_tag`
--

DROP TABLE IF EXISTS `question_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  `question_id` int(11) NOT NULL COMMENT '问题id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN','2020-09-04 22:32:05','2020-09-04 22:32:06'),(2,'ROLE_STUDENT','2020-09-04 22:32:06','2020-09-04 22:32:07'),(3,'ROLE_TEACHER','2020-09-04 22:32:08','2020-09-04 22:32:08');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1,1,'2020-09-04 22:33:32','2020-09-04 22:33:33'),(2,1,2,'2020-09-04 22:33:34','2020-09-04 22:33:34'),(3,1,3,'2020-09-04 22:33:35','2020-09-04 22:33:36'),(4,1,4,'2020-09-04 22:33:37','2020-09-04 22:33:37'),(5,1,5,'2020-09-04 22:33:38','2020-09-04 22:33:39'),(6,2,1,'2020-09-04 22:33:39','2020-09-04 22:33:40'),(8,2,3,'2020-09-04 22:33:41','2020-09-04 22:33:41'),(9,2,4,'2020-09-04 22:33:42','2020-09-04 22:33:42'),(11,3,1,'2020-09-04 22:33:43','2020-09-04 22:33:44'),(13,3,3,'2020-09-04 22:33:44','2020-09-04 22:33:45'),(14,3,4,'2020-09-04 22:33:46','2020-09-04 22:33:46'),(15,3,5,'2020-09-04 22:33:47','2020-09-04 22:33:47'),(16,1,6,'2020-09-04 22:33:48','2020-09-04 22:33:49'),(17,2,2,'2020-09-04 22:33:50','2020-09-04 22:33:50');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '标签名称',
  `created_user` varchar(255) NOT NULL COMMENT '创建人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (21,'Java基础','admin','2020-09-04 22:36:23','2020-09-04 22:36:24'),(22,'Java OOP','admin','2020-09-04 22:36:25','2020-09-04 22:36:25'),(23,'Java SE','admin','2020-09-04 22:36:26','2020-09-04 22:36:27'),(24,'WebServer','admin','2020-09-04 22:36:27','2020-09-04 22:36:28'),(25,'二进制','admin','2020-09-04 22:36:28','2020-09-04 22:36:29'),(26,'Web','admin','2020-09-04 22:36:30','2020-09-04 22:36:30'),(27,'MySQL','admin','2020-09-04 22:36:31','2020-09-04 22:36:32'),(28,'Servlet','admin','2020-09-04 22:36:32','2020-09-04 22:36:33'),(29,'Spring','admin','2020-09-04 22:36:34','2020-09-04 22:36:34'),(30,'SpringMVC','admin','2020-09-04 22:36:35','2020-09-04 22:36:36'),(31,'Mybatis','admin','2020-09-04 22:36:36','2020-09-04 22:36:37'),(32,'Ajax','admin','2020-09-04 22:36:38','2020-09-04 22:36:38'),(33,'SpringBoot','admin','2020-09-04 22:36:39','2020-09-04 22:36:40'),(34,'SpringCloud','admin','2020-09-04 22:36:40','2020-09-04 22:36:41'),(35,'面试题','admin','2020-09-04 22:36:43','2020-09-04 22:36:43'),(36,'搜索引擎','admin','2020-09-04 22:36:44','2020-09-04 22:36:45'),(37,'Docker','admin','2020-09-04 22:36:45','2020-09-04 22:36:46'),(38,'Linux','admin','2020-09-04 22:36:47','2020-09-04 22:36:48'),(39,'CentOS','admin','2020-09-04 22:36:49','2020-09-04 22:36:49'),(40,'Dubbol','admin','2020-09-04 22:36:50','2020-09-04 22:36:51');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL COMMENT '用户名，学生注册时，使用手机号码作为用户名',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `password` char(68) NOT NULL COMMENT '密码',
  `gender` int(1) DEFAULT NULL COMMENT '性别，0-女，1-男',
  `day_of_birth` date DEFAULT NULL COMMENT '生日',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  `is_enabled` int(1) DEFAULT NULL COMMENT '是否启用，0-禁用，1-启用',
  `is_locked` int(1) DEFAULT NULL COMMENT '是否已锁定，0-未锁定，1-已锁定',
  `account_type` int(1) DEFAULT NULL COMMENT '账号类型，0-学生，1-老师',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_question`
--

DROP TABLE IF EXISTS `user_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '作者id',
  `question_id` int(11) NOT NULL COMMENT '问题id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1,'2020-09-04 22:36:58','2020-09-04 22:36:58'),(2,1,2,'2020-09-04 22:36:59','2020-09-04 22:37:00'),(3,1,3,'2020-09-04 22:37:00','2020-09-04 22:37:01'),(4,2,1,'2020-09-04 22:37:01','2020-09-04 22:37:02'),(5,2,2,'2020-09-04 22:37:02','2020-09-04 22:37:03'),(6,2,3,'2020-09-04 22:37:04','2020-09-04 22:37:04'),(7,3,1,'2020-09-04 22:37:06','2020-09-04 22:37:07'),(8,3,2,'2020-09-04 22:37:07','2020-09-04 22:37:08'),(9,3,3,'2020-09-04 22:37:09','2020-09-04 22:37:09'),(10,4,1,'2020-09-04 22:37:10','2020-09-04 22:37:11'),(11,4,2,'2020-09-04 22:37:12','2020-09-04 22:37:12'),(12,4,3,'2020-09-04 22:37:13','2020-09-04 22:37:13'),(13,5,1,'2020-09-04 22:37:15','2020-09-04 22:37:16'),(14,5,2,'2020-09-04 22:37:16','2020-09-04 22:37:17'),(15,5,3,'2020-09-04 22:37:18','2020-09-04 22:37:18'),(16,6,1,'2020-09-04 22:37:19','2020-09-04 22:37:19'),(17,6,2,'2020-09-04 22:37:20','2020-09-04 22:37:21'),(18,6,3,'2020-09-04 22:37:21','2020-09-04 22:37:22'),(19,7,1,'2020-09-04 22:37:23','2020-09-04 22:37:23'),(20,7,2,'2020-09-04 22:37:25','2020-09-04 22:37:26'),(21,7,3,'2020-09-04 22:37:26','2020-09-04 22:37:27'),(22,8,1,'2020-09-04 22:37:28','2020-09-04 22:37:28'),(23,8,2,'2020-09-04 22:37:29','2020-09-04 22:37:30'),(24,8,3,'2020-09-04 22:37:30','2020-09-04 22:37:31'),(25,9,2,'2020-09-04 22:37:31','2020-09-04 22:37:32'),(26,10,2,'2020-09-04 22:37:33','2020-09-04 22:37:33'),(27,11,2,'2020-09-04 22:37:34','2020-09-04 22:37:35'),(28,12,2,'2020-09-04 22:37:35','2020-09-04 22:37:36'),(29,13,2,'2020-09-04 22:37:37','2020-09-04 22:37:37'),(30,14,2,'2020-09-04 22:37:38','2020-09-04 22:37:39');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-04 22:39:06