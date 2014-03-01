-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 18, 2013 at 03:43 PM
-- Server version: 5.6.11
-- PHP Version: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `angelhack`
--
CREATE DATABASE IF NOT EXISTS `angelhack` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `angelhack`;

-- --------------------------------------------------------

--
-- Table structure for table `causes`
--

CREATE TABLE IF NOT EXISTS `causes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `image` mediumblob NOT NULL,
  `city` varchar(50) NOT NULL,
  `tag_1` varchar(50) NOT NULL,
  `tag_2` varchar(50) NOT NULL,
  `tag_3` varchar(50) NOT NULL,
  `votes` int(11) NOT NULL DEFAULT '0',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=35 ;

--
-- Dumping data for table `causes`
--

INSERT INTO `causes` (`id`, `title`, `description`, `image`, `city`, `tag_1`, `tag_2`, `tag_3`, `votes`, `timestamp`) VALUES
(5, 'aj', 'asdasd', '', 'asdasd', 'asdasd', 'adas', 'asdsa', -5, '2013-11-16 22:48:20'),
(6, 'sg', 'dasd', '', 'sdvedf', 'dfv', 'vdf', 'dvf', 32, '2013-11-17 11:23:42'),
(7, 'dfg', 'dff', '', 'ffg', 'fff', 'ffg', 'ffg', 2, '2013-11-17 07:53:00'),
(8, 'sg', 'dasd', '', 'sdvedf', 'dfv', 'vdf', 'dvf', 24, '2013-11-17 08:06:26'),
(9, '', '', '', '', '', '', '', 2, '2013-11-17 07:55:10'),
(10, '', '', '', '', '', '', '', 23, '2013-11-17 08:06:21'),
(11, 'yuud', 'ddd', '', 'dd', 'd', '', '', 1, '2013-11-17 07:18:02'),
(12, 'spider', 'makad', '', '', '', '', '', 0, '2013-11-16 13:32:55'),
(13, 'pallav', 'hashx', '', 'hdj', '', '', '', 1, '2013-11-17 07:49:48'),
(14, 'asdas', 'asdasd', '', 'd', 'asd', 'ads', 'asd', 0, '2013-11-16 18:23:27'),
(15, 'asdsa', 'dasd', '', 'dd', 'adsa', 'd', 'dd', 0, '2013-11-16 18:25:48'),
(16, 'abcds\n', 'rabd ok n', '', 'Hyderabad', 'lodu', '', '', 1, '2013-11-17 10:25:08'),
(17, 'asdsa', 'dasd', '', 'dd', 'adsa', 'd', 'dd', 0, '2013-11-16 19:36:31'),
(18, 'ysuhc', 'chi opp', '', 'cvnm', '', '', '', 0, '2013-11-16 23:07:05'),
(19, 'shhg', 'cgb', '', '', '', '', '', 0, '2013-11-16 23:33:18'),
(20, 'ghjj', 'fgh', '', 'fgj', 'vgn', '', '', 0, '2013-11-16 23:34:27'),
(21, '', '', '', '', '', '', '', 0, '2013-11-17 04:46:36'),
(22, 'hhgg', 'fgh', '', 'fhj', 'ggh', '', '', 0, '2013-11-17 07:57:04'),
(23, 'fg', 'fg', '', 'fg', 'fg', '', '', 24, '2013-11-17 07:57:53'),
(24, 'dgh', 'xcv', '', '', '', '', '', 0, '2013-11-17 07:58:50'),
(25, 'a', 'g', '', 'n', 'mnb', '', '', 0, '2013-11-17 08:06:44'),
(26, 'a', 'g', '', 'n', 'mnb', '', '', 0, '2013-11-17 08:06:46'),
(27, 'a', 'g', '', 'n', 'mnb', '', '', 0, '2013-11-17 08:07:03'),
(28, 'build a school in the village of alindi', 'There are thousands of children deprived of quality education in the village of alindi. the local people have decided to build a school for children. donations are required.\nplease contact 9999999999 for donations.', '', 'pune', 'education', 'school', 'children', 0, '2013-11-17 09:33:54'),
(29, 'Build a school in the village of Alindi near Pune', 'Alindi is a small village 50 kms from the city of Pune. There are about 5000 kids in the age of education. The local people have taken it upon themselves to build a school with the help of NGO - Madad.\r\nContact: 9999999999', '', 'Pune', 'education', 'children', 'school', 110, '2013-11-17 12:27:21'),
(30, 'test', 'desc', '', 'city', 'hello', '', '', 0, '2013-11-17 10:19:50'),
(31, 'test', 'desc', '', 'city', 'hello', '', '', 0, '2013-11-17 10:19:54'),
(32, 'test', 'desc', '', 'city', 'hello', '', '', 0, '2013-11-17 10:20:13'),
(33, 'test', 'desc', '', 'city', 'hello', '', '', 0, '2013-11-17 10:20:14'),
(34, 'MIS', 'cause created', '', 'hyderabad', 'lecture', '', '', 27, '2013-11-17 11:49:43');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cause_id` int(11) NOT NULL,
  `comment` varchar(1000) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `cause_id`, `comment`, `timestamp`, `username`) VALUES
(14, 29, 'f yt? fbj', '2013-11-17 12:27:27', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE IF NOT EXISTS `events` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `cause_id` int(11) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `name` varchar(100) NOT NULL,
  `venue` varchar(1000) NOT NULL,
  `time` time NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `cause_id`, `description`, `name`, `venue`, `time`, `date`) VALUES
(1, 1, 'IJDWHJEHR', '', '', '00:00:00', '0000-00-00'),
(2, 1, 'byurl', 'abv', 'indiagate', '00:00:00', '0000-00-00'),
(3, 5, 'vhj', 'ahah', '', '00:00:00', '0000-00-00'),
(4, 6, 'hsd uhh', 'dydu', 'djcfj', '00:00:00', '0000-00-00'),
(5, 6, 'xvb', 'kcm,jc', 'xcf', '00:00:00', '0000-00-00'),
(6, 7, 'sd', 'a', 'ad', '00:00:00', '0000-00-00'),
(7, 7, 'b', 'a', 'c', '12:30:00', '0000-00-00'),
(8, 23, 'dhh', 'ryu', '', '00:00:00', '0000-00-00'),
(9, 23, '', 'fgh', '', '00:00:00', '0000-00-00'),
(10, 23, 'xcv', 'fgh', 'dv', '00:00:00', '0000-00-00'),
(11, 23, '', 'c.f. g', '', '00:00:00', '0000-00-00'),
(12, 23, '', '', '', '00:00:00', '0000-00-00'),
(13, 23, 'jdj', 'us', 'zm', '00:00:00', '0000-00-00'),
(14, 7, 'id', 'gr', 'lfm', '00:00:00', '0000-00-00'),
(15, 23, 'gg', 'ttty', '', '00:00:00', '0000-00-00'),
(16, 29, 'the board of. directors will meet in Pune.', 'Meet with board of directors', 'gupta bakery', '07:30:00', '0000-00-00'),
(17, 29, 'test event', 'abcd', 'iiit', '02:30:00', '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user`, `password`, `email`) VALUES
('', '', ''),
('admin', 'admin', ''),
('sharad', '12345678', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
