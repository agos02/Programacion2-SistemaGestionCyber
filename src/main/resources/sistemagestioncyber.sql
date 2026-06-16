-- Crear base de datos y seleccionarla
CREATE DATABASE IF NOT EXISTS sistemagestioncyber;
USE sistemagestioncyber;

-- Desactivar restricciones temporales
SET FOREIGN_KEY_CHECKS = 0;

-- Tabla clientes
DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `dni` char(8) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla computadoras
DROP TABLE IF EXISTS `computadoras`;
CREATE TABLE `computadoras` (
  `id_computadora` int NOT NULL AUTO_INCREMENT,
  `numero_pc` int NOT NULL,
  `estado` varchar(50) NOT NULL DEFAULT 'Libre',
  PRIMARY KEY (`id_computadora`),
  UNIQUE KEY `numero_pc` (`numero_pc`),
  CONSTRAINT `computadoras_chk_1` CHECK ((`estado` in ('Libre','Ocupada','Mantenimiento')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla productos
DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `stock` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla sesiones
DROP TABLE IF EXISTS `sesiones`;
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
  CONSTRAINT `sesiones_chk_1` CHECK ((`estado_sesion` in ('Activa','Finalizada')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabla cobros
DROP TABLE IF EXISTS `cobros`;
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

-- Tabla detalle_cobros
DROP TABLE IF EXISTS `detalle_cobros`;
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

-- Reactivar restricciones
SET FOREIGN_KEY_CHECKS = 1;
