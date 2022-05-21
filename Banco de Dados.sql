create database spotify;
use spotify;

-- MariaDB dump 10.19  Distrib 10.4.24-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: spotify
-- ------------------------------------------------------
-- Server version	10.4.24-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `albun`
--

DROP TABLE IF EXISTS `albun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `albun` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `artista` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albun`
--

LOCK TABLES `albun` WRITE;
/*!40000 ALTER TABLE `albun` DISABLE KEYS */;
INSERT INTO `albun` VALUES (1,'Single','Single'),(6,'The Girl','Flux Zone'),(7,'Black Sabbath 2009 Remaster','Black Sabbath'),(8,'Underground','Flux Zone'),(9,'Black Sabbath','Black Sabbath'),(11,'Currents','Tame Impala'),(12,'Absolute Greatest','Queen'),(13,'Piece of Mind','Iron Maiden');
/*!40000 ALTER TABLE `albun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musica`
--

DROP TABLE IF EXISTS `musica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `musica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `artista` varchar(200) NOT NULL,
  `caminho` varchar(250) NOT NULL,
  `estilo` varchar(100) NOT NULL,
  `albun_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musica`
--

LOCK TABLES `musica` WRITE;
/*!40000 ALTER TABLE `musica` DISABLE KEYS */;
INSERT INTO `musica` VALUES (1,'N.I.B','Black Sabbath','musica/8.mp3','Rock',7),(2,'The Underground','Flux Zone','musica/1.mp3','house',1),(4,'The Less I Know The Better','Tame Impala','musica/2.mp3','Alternative',11),(6,'Under Pressure ','Queen','musica/4.mp3','Rock',12),(7,'The Trooper','Iron Maiden','musica/3.mp3','Rock',13);
/*!40000 ALTER TABLE `musica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `dono_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'teste',2),(2,'rock',2),(4,'House',1),(5,'pera ai',1),(6,'lo-fi',8),(7,'',8),(8,'',8),(9,'a',8),(10,'boa',1),(11,'nova playlist',2),(13,'Primeira playlist',9),(16,'testando',10);
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_musica`
--

DROP TABLE IF EXISTS `playlist_musica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist_musica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `musica_id` int(11) NOT NULL,
  `playlist_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_musica`
--

LOCK TABLES `playlist_musica` WRITE;
/*!40000 ALTER TABLE `playlist_musica` DISABLE KEYS */;
INSERT INTO `playlist_musica` VALUES (1,1,1),(2,1,3),(3,1,3),(4,1,3),(5,1,3),(6,2,4),(7,2,13),(8,2,14),(9,2,15),(10,2,16),(11,2,4),(12,2,4),(13,4,10);
/*!40000 ALTER TABLE `playlist_musica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `funcao` varchar(10) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(70) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Conta Admin','1','root','5970F1FE287D819DC05B370709F6206E88C0642091773A68BA8FDE60DE2686EB'),(2,'Conta Usuario','0','user','5970F1FE287D819DC05B370709F6206E88C0642091773A68BA8FDE60DE2686EB'),(8,'André Voronhuk','0','voronhuk','5970F1FE287D819DC05B370709F6206E88C0642091773A68BA8FDE60DE2686EB'),(9,'Andre Voronhuk','1','voris','5970F1FE287D819DC05B370709F6206E88C0642091773A68BA8FDE60DE2686EB'),(10,'teste','0','teste','0351DC89488A475577B5C48756FE5D3CBC8551857EC196AE60168C07742A103C');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-21 18:18:33
