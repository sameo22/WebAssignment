-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: waesdev
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.18.04.4

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
-- Table structure for table `waes_json_diffs`
--

DROP TABLE IF EXISTS `waes_json_diffs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `waes_json_diffs` (
  `id_json_diff` varchar(45) NOT NULL,
  `json_diffs` json NOT NULL,
  `date_created` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id_json_diff`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waes_json_diffs`
--

LOCK TABLES `waes_json_diffs` WRITE;
/*!40000 ALTER TABLE `waes_json_diffs` DISABLE KEYS */;
INSERT INTO `waes_json_diffs` VALUES ('test1','{\"is_equal_size\": false, \"is_equal_content\": false}','2020-01-22 02:34:59.371820');
/*!40000 ALTER TABLE `waes_json_diffs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waes_left_jsons`
--

DROP TABLE IF EXISTS `waes_left_jsons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `waes_left_jsons` (
  `id_left_json` varchar(45) NOT NULL,
  `left_json` json DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `date_created` timestamp(6) NULL DEFAULT NULL,
  `date_updated` timestamp(6) NULL DEFAULT NULL,
  `base_64` longtext NOT NULL,
  PRIMARY KEY (`id_left_json`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waes_left_jsons`
--

LOCK TABLES `waes_left_jsons` WRITE;
/*!40000 ALTER TABLE `waes_left_jsons` DISABLE KEYS */;
INSERT INTO `waes_left_jsons` VALUES ('test1','{\"menu\": {\"items\": [{\"id\": \"Open\"}, {\"id\": \"OpenNew\", \"label\": \"Open New\"}, null, {\"id\": \"ZoomIn\", \"label\": \"Zoom In\"}, {\"id\": \"ZoomOut\", \"label\": \"Zoom Out\"}, {\"id\": \"OriginalView\", \"label\": \"Original View\"}, null, {\"id\": \"Quality\"}, {\"id\": \"Pause\"}, {\"id\": \"Mute\"}, null, {\"id\": \"Find\", \"label\": \"Find...\"}, {\"id\": \"FindAgain\", \"label\": \"Find Again\"}, {\"id\": \"Copy\"}, {\"id\": \"CopyAgain\", \"label\": \"Copy Again\"}, {\"id\": \"CopySVG\", \"label\": \"Copy SVG\"}, {\"id\": \"ViewSVG\", \"label\": \"View SVG\"}, {\"id\": \"ViewSource\", \"label\": \"View Source\"}, {\"id\": \"SaveAs\", \"label\": \"Save As\"}, null, {\"id\": \"Help\"}, {\"id\": \"About\", \"label\": \"About Adobe CVG Viewer...\"}], \"header\": \"SVG Viewer\"}}','PROCESSED','2020-01-22 02:34:55.742510','2020-01-22 02:34:59.392289','ew0KICAgICJtZW51Ijogew0KICAgICAgICAiaGVhZGVyIjogIlNWRyBWaWV3ZXIiLA0KICAgICAgICAiaXRlbXMiOiBbDQogICAgICAgICAgICB7DQogICAgICAgICAgICAgICAgImlkIjogIk9wZW4iDQogICAgICAgICAgICB9LA0KICAgICAgICAgICAgew0KICAgICAgICAgICAgICAgICJpZCI6ICJPcGVuTmV3IiwNCiAgICAgICAgICAgICAgICAibGFiZWwiOiAiT3BlbiBOZXciDQogICAgICAgICAgICB9LA0KICAgICAgICAgICAgbnVsbCwNCiAgICAgICAgICAgIHsNCiAgICAgICAgICAgICAgICAiaWQiOiAiWm9vbUluIiwNCiAgICAgICAgICAgICAgICAibGFiZWwiOiAiWm9vbSBJbiINCiAgICAgICAgICAgIH0sDQogICAgICAgICAgICB7DQogICAgICAgICAgICAgICAgImlkIjogIlpvb21PdXQiLA0KICAgICAgICAgICAgICAgICJsYWJlbCI6ICJab29tIE91dCINCiAgICAgICAgICAgIH0sDQogICAgICAgICAgICB7DQogICAgICAgICAgICAgICAgImlkIjogIk9yaWdpbmFsVmlldyIsDQogICAgICAgICAgICAgICAgImxhYmVsIjogIk9yaWdpbmFsIFZpZXciDQogICAgICAgICAgICB9LA0KICAgICAgICAgICAgbnVsbCwNCiAgICAgICAgICAgIHsNCiAgICAgICAgICAgICAgICAiaWQiOiAiUXVhbGl0eSINCiAgICAgICAgICAgIH0sDQogICAgICAgICAgICB7DQogICAgICAgICAgICAgICAgImlkIjogIlBhdXNlIg0KICAgICAgICAgICAgfSwNCiAgICAgICAgICAgIHsNCiAgICAgICAgICAgICAgICAiaWQiOiAiTXV0ZSINCiAgICAgICAgICAgIH0sDQogICAgICAgICAgICBudWxsLA0KICAgICAgICAgICAgew0KICAgICAgICAgICAgICAgICJpZCI6ICJGaW5kIiwNCiAgICAgICAgICAgICAgICAibGFiZWwiOiAiRmluZC4uLiINCiAgICAgICAgICAgIH0sDQogICAgICAgICAgICB7DQogICAgICAgICAgICAgICAgImlkIjogIkZpbmRBZ2FpbiIsDQogICAgICAgICAgICAgICAgImxhYmVsIjogIkZpbmQgQWdhaW4iDQogICAgICAgICAgICB9LA0KICAgICAgICAgICAgew0KICAgICAgICAgICAgICAgICJpZCI6ICJDb3B5Ig0KICAgICAgICAgICAgfSwNCiAgICAgICAgICAgIHsNCiAgICAgICAgICAgICAgICAiaWQiOiAiQ29weUFnYWluIiwNCiAgICAgICAgICAgICAgICAibGFiZWwiOiAiQ29weSBBZ2FpbiINCiAgICAgICAgICAgIH0sDQogICAgICAgICAgICB7DQogICAgICAgICAgICAgICAgImlkIjogIkNvcHlTVkciLA0KICAgICAgICAgICAgICAgICJsYWJlbCI6ICJDb3B5IFNWRyINCiAgICAgICAgICAgIH0sDQogICAgICAgICAgICB7DQogICAgICAgICAgICAgICAgImlkIjogIlZpZXdTVkciLA0KICAgICAgICAgICAgICAgICJsYWJlbCI6ICJWaWV3IFNWRyINCiAgICAgICAgICAgIH0sDQogICAgICAgICAgICB7DQogICAgICAgICAgICAgICAgImlkIjogIlZpZXdTb3VyY2UiLA0KICAgICAgICAgICAgICAgICJsYWJlbCI6ICJWaWV3IFNvdXJjZSINCiAgICAgICAgICAgIH0sDQogICAgICAgICAgICB7DQogICAgICAgICAgICAgICAgImlkIjogIlNhdmVBcyIsDQogICAgICAgICAgICAgICAgImxhYmVsIjogIlNhdmUgQXMiDQogICAgICAgICAgICB9LA0KICAgICAgICAgICAgbnVsbCwNCiAgICAgICAgICAgIHsNCiAgICAgICAgICAgICAgICAiaWQiOiAiSGVscCINCiAgICAgICAgICAgIH0sDQogICAgICAgICAgICB7DQogICAgICAgICAgICAgICAgImlkIjogIkFib3V0IiwNCiAgICAgICAgICAgICAgICAibGFiZWwiOiAiQWJvdXQgQWRvYmUgQ1ZHIFZpZXdlci4uLiINCiAgICAgICAgICAgIH0NCiAgICAgICAgXQ0KICAgIH0NCn0=');
/*!40000 ALTER TABLE `waes_left_jsons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waes_right_jsons`
--

DROP TABLE IF EXISTS `waes_right_jsons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `waes_right_jsons` (
  `id_right_json` varchar(45) NOT NULL,
  `right_json` json DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `date_created` timestamp(6) NULL DEFAULT NULL,
  `date_updated` timestamp(6) NULL DEFAULT NULL,
  `base_64` longtext NOT NULL,
  PRIMARY KEY (`id_right_json`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waes_right_jsons`
--

LOCK TABLES `waes_right_jsons` WRITE;
/*!40000 ALTER TABLE `waes_right_jsons` DISABLE KEYS */;
INSERT INTO `waes_right_jsons` VALUES ('test1','{\"key\": \"empty_value\", \"one\": \"two\"}','PROCESSED','2020-01-22 02:34:18.937961','2020-01-22 02:34:59.392330','ew0KICAgIm9uZSI6ICJ0d28iLA0KICAgImtleSI6ICJlbXB0eV92YWx1ZSINCn0=');
/*!40000 ALTER TABLE `waes_right_jsons` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-21 20:52:09
