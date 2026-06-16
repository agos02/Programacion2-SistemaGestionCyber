-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: sistemagestioncyber
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `dni` char(8) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cobros`
--

DROP TABLE IF EXISTS `cobros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cobros` (
  `id_ticket` int NOT NULL AUTO_INCREMENT,
  `id_sesion` int DEFAULT NULL,
  `monto_sesion` decimal(10,2) NOT NULL DEFAULT '0.00',
  `monto_productos` decimal(10,2) NOT NULL DEFAULT '0.00',
  `monto_total` decimal(10,2) NOT NULL,
  `forma_pago` varchar(50) NOT NULL,
  `fecha_pago` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_ticket`),
  KEY `id_sesion` (`id_sesion`),
  CONSTRAINT `cobros_ibfk_1` FOREIGN KEY (`id_sesion`) REFERENCES `sesiones` (`id_sesiones`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cobros`
--

LOCK TABLES `cobros` WRITE;
/*!40000 ALTER TABLE `cobros` DISABLE KEYS */;
/*!40000 ALTER TABLE `cobros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computadoras`
--

DROP TABLE IF EXISTS `computadoras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `computadoras` (
  `id_computadora` int NOT NULL AUTO_INCREMENT,
  `numero_pc` int NOT NULL,
  `estado` varchar(50) NOT NULL DEFAULT 'Libre',
  PRIMARY KEY (`id_computadora`),
  UNIQUE KEY `numero_pc` (`numero_pc`),
  CONSTRAINT `computadoras_chk_1` CHECK ((`estado` in (_utf8mb4'Libre',_utf8mb4'Ocupada',_utf8mb4'Mantenimiento')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computadoras`
--

LOCK TABLES `computadoras` WRITE;
/*!40000 ALTER TABLE `computadoras` DISABLE KEYS */;
/*!40000 ALTER TABLE `computadoras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_cobros`
--

DROP TABLE IF EXISTS `detalle_cobros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_cobros` (
  `id_cobro` int NOT NULL AUTO_INCREMENT,
  `id_ticket` int NOT NULL,
  `id_producto` int NOT NULL,
  `cantidad` int NOT NULL,
  PRIMARY KEY (`id_cobro`),
  KEY `id_ticket` (`id_ticket`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `detalle_cobros_ibfk_1` FOREIGN KEY (`id_ticket`) REFERENCES `cobros` (`id_ticket`) ON DELETE CASCADE,
  CONSTRAINT `detalle_cobros_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_cobros`
--

LOCK TABLES `detalle_cobros` WRITE;
/*!40000 ALTER TABLE `detalle_cobros` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_cobros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `stock` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesiones`
--

DROP TABLE IF EXISTS `sesiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sesiones` (
  `id_sesiones` int NOT NULL AUTO_INCREMENT,
  `id_computadora` int NOT NULL,
  `id_cliente` int NOT NULL,
  `fecha_inicio` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_fin` timestamp NULL DEFAULT NULL,
  `estado_sesion` varchar(50) NOT NULL DEFAULT 'Activa',
  PRIMARY KEY (`id_sesiones`),
  KEY `id_computadora` (`id_computadora`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `sesiones_ibfk_1` FOREIGN KEY (`id_computadora`) REFERENCES `computadoras` (`id_computadora`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `sesiones_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `sesiones_chk_1` CHECK ((`estado_sesion` in (_utf8mb4'Activa',_utf8mb4'Finalizada')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesiones`
--

LOCK TABLES `sesiones` WRITE;
/*!40000 ALTER TABLE `sesiones` DISABLE KEYS */;
/*!40000 ALTER TABLE `sesiones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-02 19:52:02
