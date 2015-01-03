-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2015 at 02:23 AM
-- Server version: 5.5.20
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `todo`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `task_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=44 ;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `comment`, `created_at`, `updated_at`, `task_id`) VALUES
(29, 'It needs Virtual Host', '2015-01-02 15:43:41', '2015-01-02 15:43:41', 54),
(30, 'cant verify this on Android because server is on localhost and to access virtual host from mobile it requires some changes in config file of Android OS', '2015-01-02 15:50:03', '2015-01-02 15:50:03', 54),
(31, 'verified it on localhost browser', '2015-01-02 15:50:22', '2015-01-02 15:50:22', 54),
(32, 'virtual Host todo.com', '2015-01-02 15:50:34', '2015-01-02 15:50:34', 54),
(33, 'requires 2 full days to implement it', '2015-01-02 15:51:32', '2015-01-02 15:51:32', 64),
(34, 'but i''m ruuning out of time so :(', '2015-01-02 15:52:02', '2015-01-02 15:52:02', 64),
(35, 'Done', '2015-01-02 15:52:54', '2015-01-02 15:52:54', 55),
(36, 'its good! really liked the softDelete function in Laravel', '2015-01-02 15:53:37', '2015-01-02 15:53:37', 58),
(37, 'finally i deleted you :P', '2015-01-02 15:53:59', '2015-01-02 15:53:59', 59),
(38, 'tried JAXBElement concept but it works in J2EE', '2015-01-02 15:54:53', '2015-01-02 15:54:53', 63),
(39, 'JPA Concept', '2015-01-02 15:55:03', '2015-01-02 15:55:03', 63),
(40, 'I love POSTMAN API client', '2015-01-02 16:04:47', '2015-01-02 16:04:47', 55),
(41, 'HybridAuth implementation', '2015-01-02 16:30:59', '2015-01-02 16:30:59', 54),
(42, 'HybridAuth implementation', '2015-01-02 16:31:18', '2015-01-02 16:31:18', 54),
(43, 'Because my parents are coming on this weekend', '2015-01-02 16:47:02', '2015-01-02 16:47:02', 64);

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE IF NOT EXISTS `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `status` text COLLATE utf8_unicode_ci NOT NULL,
  `priority` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `id_2` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=66 ;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `title`, `description`, `status`, `priority`, `created_at`, `updated_at`, `deleted_at`, `email`) VALUES
(54, 'Google Authentication', 'Implementation of Google Authentication in such a way, so that user can access Todo list after successful login.', 'In-process', 'High', '2015-01-02 15:28:04', '2015-01-02 19:47:42', NULL, 'prankulgarg@outlook.com'),
(55, 'Get All Task API', 'Implementation of Get All Task API, to show all the task associated with the corresponding email Id.', 'Complete', 'High', '2015-01-02 15:30:13', '2015-01-02 19:47:51', NULL, 'prankulgarg@outlook.com'),
(56, 'Add Task API', 'Add task for the corresponding user.', 'Complete', 'High', '2015-01-02 15:30:52', '2015-01-02 16:42:30', NULL, 'prankulgarg@outlook.com'),
(57, 'Edit Task API', 'To edit the task on the basis of task_id.', 'Complete', 'High', '2015-01-02 15:31:29', '2015-01-02 19:48:00', NULL, 'prankulgarg@outlook.com'),
(58, 'Archive Task API', 'It means softDelete the task, so that we can access it later in Archived Task List', 'Complete', 'High', '2015-01-02 15:32:57', '2015-01-02 19:48:06', NULL, 'prankulgarg@outlook.com'),
(59, 'Delete Task API', 'forceDelete Task(Completely delete)', 'Complete', 'High', '2015-01-02 15:33:48', '2015-01-02 19:48:23', NULL, 'prankulgarg@outlook.com'),
(60, 'Share Task', 'To share the task, on social networking sites: like Gmail, whats app, facebook.', 'Complete', 'High', '2015-01-02 15:34:58', '2015-01-02 19:48:30', NULL, 'prankulgarg@outlook.com'),
(61, 'Share Tasks', 'To share all the task, in a Single Shot.', 'Complete', 'High', '2015-01-02 15:35:39', '2015-01-02 19:48:37', NULL, 'prankulgarg@outlook.com'),
(62, 'Comments API', 'Add and get all comments corresponding to the task.', 'Complete', 'High', '2015-01-02 15:36:42', '2015-01-02 19:48:46', NULL, 'prankulgarg@outlook.com'),
(63, 'Export Tasks in XML', 'just like a share feature.', 'Yet to start', 'Low', '2015-01-02 15:37:47', '2015-01-02 16:31:40', '2015-01-02 16:31:40', 'prankulgarg@outlook.com'),
(64, 'Client Management', 'just like a share feature.', 'Yet to start', 'Medium', '2015-01-02 15:38:14', '2015-01-02 19:48:53', NULL, 'prankulgarg@outlook.com'),
(65, 'Notification', 'Depends upon Client Management.', 'Yet to start', 'Medium', '2015-01-02 15:40:26', '2015-01-02 19:49:01', NULL, 'prankulgarg@outlook.com');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
