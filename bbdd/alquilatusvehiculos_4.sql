-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Mar 29, 2024 at 06:39 PM
-- Server version: 8.0.36
-- PHP Version: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `alquilatusvehiculos`
--

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nif` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`id`, `apellidos`, `email`, `nif`, `nombre`, `password`, `rol`) VALUES
(1, 'Pérez', 'juan.perez@ejemplo.com', '12345678A', 'Juan', '', 'Particular'),
(2, 'López', 'maria.lopez@empresa.com', '87654321B', 'María', '', 'Empresa'),
(3, 'García', 'pedro.garcia@autonomo.com', '98765432C', 'Pedro', '', 'Autónomo'),
(4, 'Fernández', 'ana.fernandez@gmail.com', '12345678D', 'Ana', '', 'Particular'),
(5, 'Sánchez', 'david.sanchez@hotmail.com', '87654321E', 'David', '', 'Particular'),
(6, 'Martínez', 'laura.martinez@outlook.com', '98765432F', 'Laura', '', 'Empresa'),
(7, 'Ruiz', 'alejandro.ruiz@yahoo.com', '12345678G', 'Alejandro', '', 'Particular'),
(8, 'Jiménez', 'sandra.jimenez@icloud.com', '87654321H', 'Sandra', '', 'Autónomo'),
(9, 'Hernández', 'miguel.hernandez@empresanueva.com', '98765432I', 'Miguel', '', 'Empresa'),
(10, 'González', 'isabel.gonzalez@live.com', '12345678J', 'Isabel', '', 'Particular');

-- --------------------------------------------------------

--
-- Table structure for table `contrato_alquiler`
--

CREATE TABLE `contrato_alquiler` (
  `id` bigint NOT NULL,
  `fecha_fin` varchar(255) DEFAULT NULL,
  `fecha_inicio` varchar(255) DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `oficina_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `contrato_alquiler`
--

INSERT INTO `contrato_alquiler` (`id`, `fecha_fin`, `fecha_inicio`, `cliente_id`, `oficina_id`) VALUES
(1, '2024-03-25', '2024-03-22', 1, 1),
(2, '2024-03-26', '2024-03-23', 2, 2),
(3, '2024-03-27', '2024-03-24', 3, 3),
(4, '2024-03-28', '2024-03-25', 4, 4),
(5, '2024-03-29', '2024-03-26', 5, 5),
(6, '2024-03-30', '2024-03-27', 6, 6),
(7, '2024-03-31', '2024-03-28', 1, 7),
(8, '2024-04-01', '2024-03-29', 2, 8),
(9, '2024-04-02', '2024-03-30', 4, 9),
(10, '2024-04-03', '2024-03-31', 3, 8);

-- --------------------------------------------------------

--
-- Table structure for table `contrato_alquiler_vehiculo`
--

CREATE TABLE `contrato_alquiler_vehiculo` (
  `id` bigint NOT NULL,
  `contrato_alquiler_id` bigint DEFAULT NULL,
  `vehiculo_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `contrato_alquiler_vehiculo`
--

INSERT INTO `contrato_alquiler_vehiculo` (`id`, `contrato_alquiler_id`, `vehiculo_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 2, 3),
(4, 3, 4),
(5, 4, 5),
(6, 5, 6),
(7, 6, 7),
(8, 6, 8),
(9, 7, 9),
(10, 8, 8);

-- --------------------------------------------------------

--
-- Table structure for table `oficina`
--

CREATE TABLE `oficina` (
  `id` bigint NOT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `codigo_postal` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `oficina`
--

INSERT INTO `oficina` (`id`, `ciudad`, `codigo_postal`, `direccion`, `telefono`) VALUES
(1, 'Madrid', '28013', 'Calle Mayor, 12', '912345678'),
(2, 'Barcelona', '08001', 'Gran Vía, 54', '931234567'),
(3, 'Valencia', '46001', 'Plaza del Ayuntamiento, 1', '963456789'),
(4, 'Sevilla', '41001', 'Avenida de la Constitución, 10', '954567890'),
(5, 'Málaga', '29001', 'Paseo del Mar, 20', '975678901'),
(6, 'Zaragoza', '50001', 'Calle del Carmen, 30', '976789012'),
(7, 'Bilbao', '48001', 'Calle de la Cruz, 40', '947890123'),
(8, 'Granada', '18001', 'Calle del Sol, 50', '988901234'),
(9, 'Palma de Mallorca', '07001', 'Plaza de España, 60', '971234567'),
(10, 'Murcia', '30001', 'Calle de la Independencia, 70', '968345678');

-- --------------------------------------------------------

--
-- Table structure for table `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id` bigint NOT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `vehiculo`
--

INSERT INTO `vehiculo` (`id`, `marca`, `matricula`, `modelo`) VALUES
(1, 'Toyota', '1234 ABC', 'Yaris'),
(2, 'Ford', '5678 XYZ', 'Mondeo'),
(3, 'Volkswagen', '9012 DEF', 'Tiguan'),
(4, 'Peugeot', '3456 GHI', '208'),
(5, 'Renault', '7890 JKL', 'ZOE'),
(6, 'Citroën', '1234 MNO', 'C5 Aircross'),
(7, 'Fiat', '5678 PQR', 'Fullback'),
(8, 'Opel', '9012 RST', 'GT'),
(9, 'Hyundai', '3456 STU', 'Staria'),
(10, 'Kia', '7890 VWX', 'Sportage');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_cmxo70m08n43599l3h0h07cc6` (`email`),
  ADD UNIQUE KEY `UK_42hc3bgo75s70bee20y1d58yv` (`nif`);

--
-- Indexes for table `contrato_alquiler`
--
ALTER TABLE `contrato_alquiler`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnn8mwlbm06forw2khkvk0maiv` (`cliente_id`),
  ADD KEY `FKs0nc9cxdffdiydilxjb85xgv1` (`oficina_id`);

--
-- Indexes for table `contrato_alquiler_vehiculo`
--
ALTER TABLE `contrato_alquiler_vehiculo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKccnfla8extxfyfbwawisbhl1w` (`contrato_alquiler_id`),
  ADD KEY `FK1ohnaw9tc2qrlks50wiss14tk` (`vehiculo_id`);

--
-- Indexes for table `oficina`
--
ALTER TABLE `oficina`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_deqhio123mhkt6x7insisbx3x` (`matricula`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `contrato_alquiler`
--
ALTER TABLE `contrato_alquiler`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `contrato_alquiler_vehiculo`
--
ALTER TABLE `contrato_alquiler_vehiculo`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `oficina`
--
ALTER TABLE `oficina`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `vehiculo`
--
ALTER TABLE `vehiculo`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contrato_alquiler`
--
ALTER TABLE `contrato_alquiler`
  ADD CONSTRAINT `FKnn8mwlbm06forw2khkvk0maiv` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FKs0nc9cxdffdiydilxjb85xgv1` FOREIGN KEY (`oficina_id`) REFERENCES `oficina` (`id`);

--
-- Constraints for table `contrato_alquiler_vehiculo`
--
ALTER TABLE `contrato_alquiler_vehiculo`
  ADD CONSTRAINT `FK1ohnaw9tc2qrlks50wiss14tk` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`id`),
  ADD CONSTRAINT `FKccnfla8extxfyfbwawisbhl1w` FOREIGN KEY (`contrato_alquiler_id`) REFERENCES `contrato_alquiler` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
