-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 17, 2021 at 08:24 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `secretariatatm`
--

-- --------------------------------------------------------

--
-- Table structure for table `angajati`
--

CREATE TABLE `angajati` (
  `ID_Angajat` int(11) NOT NULL,
  `FK_Functia` int(11) NOT NULL,
  `Nume` varchar(20) NOT NULL,
  `Prenume` varchar(20) NOT NULL,
  `Salariu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `dotari`
--

CREATE TABLE `dotari` (
  `ID_Dotare` int(11) NOT NULL,
  `Denumire` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `dotari_sali_de_clasa`
--

CREATE TABLE `dotari_sali_de_clasa` (
  `ID` int(11) NOT NULL,
  `FK_Sala` int(11) NOT NULL,
  `FK_Dotare` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `facultati`
--

CREATE TABLE `facultati` (
  `ID_Facultate` int(11) NOT NULL,
  `Denumire` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `functii`
--

CREATE TABLE `functii` (
  `ID_Functie` int(11) NOT NULL,
  `Denumire` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `grupe_studiu`
--

CREATE TABLE `grupe_studiu` (
  `ID_Grupa` int(11) NOT NULL,
  `FK_Indrumator` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `materii`
--

CREATE TABLE `materii` (
  `ID_Materie` int(11) NOT NULL,
  `Nr_Credite` int(11) NOT NULL,
  `FK_Profesor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `note`
--

CREATE TABLE `note` (
  `ID_Nota` int(11) NOT NULL,
  `Valoare` int(11) NOT NULL,
  `FK_Materie` int(11) NOT NULL,
  `Data` int(11) NOT NULL,
  `FK_Student` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `orar`
--

CREATE TABLE `orar` (
  `Ora` datetime NOT NULL,
  `FK_Grupa` int(11) NOT NULL,
  `FK_Sala` int(11) NOT NULL,
  `FK_Ore` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `ore`
--

CREATE TABLE `ore` (
  `ID_Ora` int(11) NOT NULL,
  `Tip_Ora` varchar(20) NOT NULL,
  `FK_Materie` int(11) NOT NULL,
  `FK_Titular` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rapoarte`
--

CREATE TABLE `rapoarte` (
  `ID_Raport` int(11) NOT NULL,
  `FK_TipRaport` int(11) NOT NULL,
  `Data` datetime NOT NULL,
  `FK_Student` int(11) NOT NULL,
  `FK_Supervizor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sali_de_clasa`
--

CREATE TABLE `sali_de_clasa` (
  `ID_Sala` int(11) NOT NULL,
  `Denumire` varchar(20) NOT NULL,
  `Capacitate` int(11) NOT NULL,
  `Tip_Sala` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `specializari`
--

CREATE TABLE `specializari` (
  `ID_Specializare` int(11) NOT NULL,
  `Denumire` varchar(50) NOT NULL,
  `FK_Facultate` int(11) NOT NULL,
  `FK_SecretarFacultate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `studenti`
--

CREATE TABLE `studenti` (
  `ID_Student` int(11) NOT NULL,
  `Nume` varchar(50) NOT NULL,
  `Prenume` varchar(50) NOT NULL,
  `FK_Specializare` int(11) NOT NULL,
  `FK_Grupa` int(11) NOT NULL,
  `Solda` int(11) NOT NULL,
  `An_de_Studiu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tip_rapoarte`
--

CREATE TABLE `tip_rapoarte` (
  `ID_TipRaport` int(11) NOT NULL,
  `Tip_Raport` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `angajati`
--
ALTER TABLE `angajati`
  ADD PRIMARY KEY (`ID_Angajat`),
  ADD KEY `FK_Functia` (`FK_Functia`);

--
-- Indexes for table `dotari`
--
ALTER TABLE `dotari`
  ADD PRIMARY KEY (`ID_Dotare`);

--
-- Indexes for table `dotari_sali_de_clasa`
--
ALTER TABLE `dotari_sali_de_clasa`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_Sala` (`FK_Sala`),
  ADD KEY `KF_Dotare` (`FK_Dotare`);

--
-- Indexes for table `facultati`
--
ALTER TABLE `facultati`
  ADD PRIMARY KEY (`ID_Facultate`);

--
-- Indexes for table `functii`
--
ALTER TABLE `functii`
  ADD PRIMARY KEY (`ID_Functie`);

--
-- Indexes for table `grupe_studiu`
--
ALTER TABLE `grupe_studiu`
  ADD PRIMARY KEY (`ID_Grupa`),
  ADD KEY `FK_Indrumator` (`FK_Indrumator`);

--
-- Indexes for table `materii`
--
ALTER TABLE `materii`
  ADD PRIMARY KEY (`ID_Materie`),
  ADD KEY `FK_Profesor` (`FK_Profesor`);

--
-- Indexes for table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`ID_Nota`),
  ADD KEY `FK_Materia` (`FK_Materie`),
  ADD KEY `FK_Studentul` (`FK_Student`);

--
-- Indexes for table `orar`
--
ALTER TABLE `orar`
  ADD KEY `FK_Ora` (`FK_Ore`),
  ADD KEY `FK_SalaClasa` (`FK_Sala`),
  ADD KEY `FK_GrupaStudiu` (`FK_Grupa`);

--
-- Indexes for table `ore`
--
ALTER TABLE `ore`
  ADD PRIMARY KEY (`ID_Ora`),
  ADD KEY `FK_Materie` (`FK_Materie`),
  ADD KEY `FK_Titular` (`FK_Titular`);

--
-- Indexes for table `rapoarte`
--
ALTER TABLE `rapoarte`
  ADD PRIMARY KEY (`ID_Raport`),
  ADD KEY `FK_Supervizor` (`FK_Supervizor`),
  ADD KEY `FK_StudentRaport` (`FK_Student`),
  ADD KEY `FK_TipRaport` (`FK_TipRaport`);

--
-- Indexes for table `sali_de_clasa`
--
ALTER TABLE `sali_de_clasa`
  ADD PRIMARY KEY (`ID_Sala`);

--
-- Indexes for table `specializari`
--
ALTER TABLE `specializari`
  ADD PRIMARY KEY (`ID_Specializare`),
  ADD KEY `FK_Facultate` (`FK_Facultate`),
  ADD KEY `FK_SecretarFacultate` (`FK_SecretarFacultate`);

--
-- Indexes for table `studenti`
--
ALTER TABLE `studenti`
  ADD PRIMARY KEY (`ID_Student`),
  ADD KEY `FK_Specializare` (`FK_Specializare`),
  ADD KEY `FK_Grupa` (`FK_Grupa`);

--
-- Indexes for table `tip_rapoarte`
--
ALTER TABLE `tip_rapoarte`
  ADD PRIMARY KEY (`ID_TipRaport`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `angajati`
--
ALTER TABLE `angajati`
  ADD CONSTRAINT `FK_Functia` FOREIGN KEY (`FK_Functia`) REFERENCES `functii` (`ID_Functie`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `dotari_sali_de_clasa`
--
ALTER TABLE `dotari_sali_de_clasa`
  ADD CONSTRAINT `FK_Sala` FOREIGN KEY (`FK_Sala`) REFERENCES `sali_de_clasa` (`ID_Sala`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `KF_Dotare` FOREIGN KEY (`FK_Dotare`) REFERENCES `dotari` (`ID_Dotare`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `grupe_studiu`
--
ALTER TABLE `grupe_studiu`
  ADD CONSTRAINT `FK_Indrumator` FOREIGN KEY (`FK_Indrumator`) REFERENCES `angajati` (`ID_Angajat`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `materii`
--
ALTER TABLE `materii`
  ADD CONSTRAINT `FK_Profesor` FOREIGN KEY (`FK_Profesor`) REFERENCES `angajati` (`ID_Angajat`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `FK_Materia` FOREIGN KEY (`FK_Materie`) REFERENCES `materii` (`ID_Materie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_Studentul` FOREIGN KEY (`FK_Student`) REFERENCES `studenti` (`ID_Student`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `orar`
--
ALTER TABLE `orar`
  ADD CONSTRAINT `FK_GrupaStudiu` FOREIGN KEY (`FK_Grupa`) REFERENCES `grupe_studiu` (`ID_Grupa`),
  ADD CONSTRAINT `FK_Ora` FOREIGN KEY (`FK_Ore`) REFERENCES `ore` (`ID_Ora`),
  ADD CONSTRAINT `FK_SalaClasa` FOREIGN KEY (`FK_Sala`) REFERENCES `sali_de_clasa` (`ID_Sala`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ore`
--
ALTER TABLE `ore`
  ADD CONSTRAINT `FK_Materie` FOREIGN KEY (`FK_Materie`) REFERENCES `materii` (`ID_Materie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_Titular` FOREIGN KEY (`FK_Titular`) REFERENCES `angajati` (`ID_Angajat`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rapoarte`
--
ALTER TABLE `rapoarte`
  ADD CONSTRAINT `FK_StudentRaport` FOREIGN KEY (`FK_Student`) REFERENCES `studenti` (`ID_Student`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_Supervizor` FOREIGN KEY (`FK_Supervizor`) REFERENCES `angajati` (`ID_Angajat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_TipRaport` FOREIGN KEY (`FK_TipRaport`) REFERENCES `tip_rapoarte` (`ID_TipRaport`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `specializari`
--
ALTER TABLE `specializari`
  ADD CONSTRAINT `FK_Facultate` FOREIGN KEY (`FK_Facultate`) REFERENCES `facultati` (`ID_Facultate`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_SecretarFacultate` FOREIGN KEY (`FK_SecretarFacultate`) REFERENCES `angajati` (`ID_Angajat`);

--
-- Constraints for table `studenti`
--
ALTER TABLE `studenti`
  ADD CONSTRAINT `FK_Grupa` FOREIGN KEY (`FK_Grupa`) REFERENCES `grupe_studiu` (`ID_Grupa`),
  ADD CONSTRAINT `FK_Specializare` FOREIGN KEY (`FK_Specializare`) REFERENCES `specializari` (`ID_Specializare`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
