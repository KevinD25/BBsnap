-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Gegenereerd op: 08 nov 2018 om 12:47
-- Serverversie: 10.1.23-MariaDB-9+deb9u1
-- PHP-versie: 7.0.30-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `IOT`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Foto`
--

CREATE TABLE `Foto` (
  `id` int(11) NOT NULL,
  `lesid` int(11) NOT NULL,
  `foto` blob NOT NULL,
  `uur` datetime NOT NULL,
  `naam` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Klas`
--

CREATE TABLE `Klas` (
  `id` int(11) NOT NULL,
  `richtingid` int(11) NOT NULL,
  `naam` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Leerling`
--

CREATE TABLE `Leerling` (
  `id` int(11) NOT NULL,
  `naam` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `LeerlingKlas`
--

CREATE TABLE `LeerlingKlas` (
  `klasid` int(11) NOT NULL,
  `leerlingid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Les`
--

CREATE TABLE `Les` (
  `id` int(11) NOT NULL,
  `lokaalid` int(11) NOT NULL,
  `vakid` int(11) NOT NULL,
  `klasid` int(11) NOT NULL,
  `starttijd` datetime NOT NULL,
  `eindtijd` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Lokaal`
--

CREATE TABLE `Lokaal` (
  `id` int(11) NOT NULL,
  `naam` varchar(255) NOT NULL,
  `gebouw` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Prof`
--

CREATE TABLE `Prof` (
  `id` int(11) NOT NULL,
  `naam` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Richting`
--

CREATE TABLE `Richting` (
  `id` int(11) NOT NULL,
  `naam` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `Vak`
--

CREATE TABLE `Vak` (
  `id` int(11) NOT NULL,
  `profid` int(11) NOT NULL,
  `naam` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `Foto`
--
ALTER TABLE `Foto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `lesid` (`lesid`);

--
-- Indexen voor tabel `Klas`
--
ALTER TABLE `Klas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `richtingid` (`richtingid`);

--
-- Indexen voor tabel `Leerling`
--
ALTER TABLE `Leerling`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `LeerlingKlas`
--
ALTER TABLE `LeerlingKlas`
  ADD KEY `klasid` (`klasid`),
  ADD KEY `leerlingid` (`leerlingid`);

--
-- Indexen voor tabel `Les`
--
ALTER TABLE `Les`
  ADD PRIMARY KEY (`id`),
  ADD KEY `lokaalid` (`lokaalid`),
  ADD KEY `klasid` (`klasid`),
  ADD KEY `vakid` (`vakid`);

--
-- Indexen voor tabel `Lokaal`
--
ALTER TABLE `Lokaal`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `Prof`
--
ALTER TABLE `Prof`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `Richting`
--
ALTER TABLE `Richting`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `Vak`
--
ALTER TABLE `Vak`
  ADD PRIMARY KEY (`id`),
  ADD KEY `profid` (`profid`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `Foto`
--
ALTER TABLE `Foto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT voor een tabel `Klas`
--
ALTER TABLE `Klas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT voor een tabel `Leerling`
--
ALTER TABLE `Leerling`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT voor een tabel `Les`
--
ALTER TABLE `Les`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT voor een tabel `Lokaal`
--
ALTER TABLE `Lokaal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT voor een tabel `Prof`
--
ALTER TABLE `Prof`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT voor een tabel `Richting`
--
ALTER TABLE `Richting`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT voor een tabel `Vak`
--
ALTER TABLE `Vak`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `Foto`
--
ALTER TABLE `Foto`
  ADD CONSTRAINT `Foto_ibfk_1` FOREIGN KEY (`lesid`) REFERENCES `Les` (`id`);

--
-- Beperkingen voor tabel `Klas`
--
ALTER TABLE `Klas`
  ADD CONSTRAINT `Klas_ibfk_1` FOREIGN KEY (`richtingid`) REFERENCES `Richting` (`id`);

--
-- Beperkingen voor tabel `LeerlingKlas`
--
ALTER TABLE `LeerlingKlas`
  ADD CONSTRAINT `LeerlingKlas_ibfk_1` FOREIGN KEY (`klasid`) REFERENCES `Klas` (`id`),
  ADD CONSTRAINT `LeerlingKlas_ibfk_2` FOREIGN KEY (`leerlingid`) REFERENCES `Leerling` (`id`);

--
-- Beperkingen voor tabel `Les`
--
ALTER TABLE `Les`
  ADD CONSTRAINT `Les_ibfk_1` FOREIGN KEY (`lokaalid`) REFERENCES `Lokaal` (`id`),
  ADD CONSTRAINT `Les_ibfk_2` FOREIGN KEY (`klasid`) REFERENCES `Klas` (`id`),
  ADD CONSTRAINT `Les_ibfk_3` FOREIGN KEY (`vakid`) REFERENCES `Vak` (`id`);

--
-- Beperkingen voor tabel `Vak`
--
ALTER TABLE `Vak`
  ADD CONSTRAINT `Vak_ibfk_1` FOREIGN KEY (`profid`) REFERENCES `Prof` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
