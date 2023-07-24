-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 09, 2019 at 06:33 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spp`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` varchar(5) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `nama` varchar(40) DEFAULT NULL,
  `nip` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `password`, `nama`, `nip`) VALUES
('0', 's', '081212323', '0989877');

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

CREATE TABLE `petugas` (
  `id` varchar(5) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `nama` varchar(40) DEFAULT NULL,
  `nip` varchar(18) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`id`, `password`, `nama`, `nip`) VALUES
('12345', '654321', 'Fajar', '123456789012345678'),
('12346', '123456', 'Bima', '123456789012345677'),
('23456', 'JJ', 'Zikri', '876509871234568907'),
('34567', 'o', 'Bintang', '765432768909876543'),
('54321', 'cyankslalu', 'Rasel saepudin bin sutisna', '123456789012345679'),
('trsna', '123456', 'Trisna', '123345678901234567');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nama` varchar(50) DEFAULT NULL,
  `kelas` varchar(20) DEFAULT NULL,
  `nis` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nama`, `kelas`, `nis`) VALUES
('Arya Disastra', 'X Animasi C', '1234567890'),
('Aris Rizaldo', 'X Animasi C', '43');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `nis` varchar(12) NOT NULL,
  `bulan` varchar(600) NOT NULL,
  `tahunajaran` varchar(9) NOT NULL,
  `harga` int(11) NOT NULL,
  `id` int(200) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `id_petugas` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`nis`, `bulan`, `tahunajaran`, `harga`, `id`, `tanggal`, `id_petugas`) VALUES
('1234567890', '[Januari]', '2018-2019', 10000, 2, '2019-05-04', 'trsna'),
('43', '[Januari]', '2018-2019', 10000, 3, '2019-05-09', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `walikelas`
--

CREATE TABLE `walikelas` (
  `id` varchar(3) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `nama` varchar(40) DEFAULT NULL,
  `kelas` varchar(20) DEFAULT NULL,
  `nip` varchar(18) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `walikelas`
--

INSERT INTO `walikelas` (`id`, `password`, `nama`, `kelas`, `nip`) VALUES
('123', '321', 'IK', 'X Animasi C', '7');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nip` (`nip`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nis`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `nis` (`nis`,`bulan`,`tahunajaran`,`harga`),
  ADD KEY `id_petugas` (`id_petugas`);

--
-- Indexes for table `walikelas`
--
ALTER TABLE `walikelas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nip` (`nip`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
