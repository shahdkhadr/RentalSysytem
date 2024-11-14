-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 10, 2024 at 01:57 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rental`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `admin_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `admin_name`, `phone_number`) VALUES
(1, 'Alice Johnson', '555-0101'),
(2, 'Bob Smith', '555-0102'),
(3, 'Charlie Brown', '555-0103'),
(4, 'Diana Green', '555-0104'),
(5, 'Evan White', '555-0105'),
(6, 'Fiona Black', '555-0106'),
(7, 'George Blue', '555-0107'),
(8, 'Holly Red', '555-0108'),
(9, 'Ian Gray', '555-0109'),
(10, 'Jane Silver', '555-0110'),
(11, 'Kyle Adams', '555-0111'),
(12, 'Liam Brooks', '555-0112'),
(13, 'Mia Clark', '555-0113'),
(14, 'Nina Davis', '555-0114'),
(15, 'Oscar Evans', '555-0115'),
(16, 'Paul Fisher', '555-0116'),
(17, 'Quinn Garcia', '555-0117'),
(18, 'Rita Harris', '555-0118'),
(19, 'Sam Ingram', '555-0119'),
(20, 'Tina Jones', '555-0120'),
(21, 'Uma King', '555-0121'),
(22, 'Vera Lee', '555-0122'),
(23, 'Will Moore', '555-0123'),
(24, 'Xander Nelson', '555-0124'),
(25, 'Yara Owens', '555-0125'),
(26, 'Zoe Park', '555-0126'),
(27, 'Aaron Quinn', '555-0127'),
(28, 'Bella Ross', '555-0128'),
(29, 'Cody Scott', '555-0129'),
(30, 'Daisy Turner', '555-0130'),
(31, 'Eli Underwood', '555-0131'),
(32, 'Faith Vargas', '555-0132'),
(33, 'Grant Wells', '555-0133'),
(34, 'Holly Xavier', '555-0134'),
(35, 'Isaac Young', '555-0135'),
(36, 'Julia Zane', '555-0136'),
(37, 'Kyle Andrews', '555-0137'),
(38, 'Lily Brown', '555-0138'),
(39, 'Mason Carter', '555-0139'),
(40, 'Nora Diaz', '555-0140'),
(41, 'Owen Edwards', '555-0141'),
(42, 'Paula Fields', '555-0142'),
(43, 'Quentin Green', '555-0143'),
(44, 'Rachel Hughes', '555-0144'),
(45, 'Sean Irwin', '555-0145'),
(46, 'Tara Johnson', '555-0146'),
(47, 'Umar Kim', '555-0147'),
(48, 'Violet Lane', '555-0148'),
(49, 'Wyatt Moore', '555-0149'),
(50, 'Ximena Norton', '555-0150'),
(51, 'Yosef Oliver', '555-0151'),
(52, 'Zara Perez', '555-0152'),
(53, 'Amy Quinn', '555-0153'),
(54, 'Brandon Reed', '555-0154'),
(55, 'Cara Simpson', '555-0155'),
(56, 'David Thomas', '555-0156'),
(57, 'Ella Underwood', '555-0157'),
(58, 'Felix Vaughn', '555-0158'),
(59, 'Gloria Wilson', '555-0159'),
(60, 'Henry Xu', '555-0160'),
(61, 'Isabel York', '555-0161'),
(62, 'Jack Zane', '555-0162'),
(63, 'Kara Allen', '555-0163'),
(64, 'Leo Barnes', '555-0164'),
(65, 'Megan Clark', '555-0165'),
(66, 'Nathan Dawson', '555-0166'),
(67, 'Olivia Emerson', '555-0167'),
(68, 'Peter Franklin', '555-0168'),
(69, 'Quincy Garrett', '555-0169'),
(70, 'Rebecca Hale', '555-0170'),
(71, 'Steven Ingram', '555-0171'),
(72, 'Tracy Jackson', '555-0172'),
(73, 'Ursula Kline', '555-0173'),
(74, 'Victor Long', '555-0174'),
(75, 'Wendy Morris', '555-0175'),
(76, 'Xander Nelson', '555-0176'),
(77, 'Yvonne Owens', '555-0177'),
(78, 'Zachary Perez', '555-0178'),
(79, 'Allison Quinn', '555-0179'),
(80, 'Ben Ross', '555-0180'),
(81, 'Catherine Scott', '555-0181'),
(82, 'Derek Turner', '555-0182'),
(83, 'Emily Underwood', '555-0183'),
(84, 'Francis Vaughn', '555-0184'),
(85, 'Gina White', '555-0185'),
(86, 'Hank Xavier', '555-0186'),
(87, 'Irene Young', '555-0187'),
(88, 'Jake Zane', '555-0188'),
(89, 'Karen Adams', '555-0189'),
(90, 'Louis Brooks', '555-0190'),
(91, 'Molly Carter', '555-0191'),
(92, 'Neil Diaz', '555-0192'),
(93, 'Ophelia Edwards', '555-0193'),
(94, 'Patrick Fields', '555-0194'),
(95, 'Quinn Garcia', '555-0195'),
(96, 'Rose Hughes', '555-0196'),
(97, 'Scott Irwin', '555-0197'),
(98, 'Tina Johnson', '555-0198'),
(99, 'Uma King', '555-0199'),
(100, 'Victor Lee', '555-0200');

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `branch_id` int(11) NOT NULL,
  `branch_location` varchar(255) DEFAULT NULL,
  `branch_name` varchar(255) DEFAULT NULL,
  `contact_details` varchar(255) DEFAULT NULL,
  `payment_id` int(11) NOT NULL,
  `reservation_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`branch_id`, `branch_location`, `branch_name`, `contact_details`, `payment_id`, `reservation_id`) VALUES
(2, 'Nablus', 'Asira Branch', '1234', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `customer_address` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `driver_license` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `customer_address`, `customer_name`, `driver_license`, `phone_number`, `created_at`, `email`, `password`, `updated_at`, `role_id`) VALUES
(1, 'Nablus', 'abdullah', '1111', '0568347481', NULL, '', '', NULL, NULL),
(2, NULL, 'Super Admin', NULL, NULL, '2024-11-07 11:12:52.000000', 'super.admin@email.com', '$2a$10$.iyK8OVA6biGw3fVKLuUheJdFogn6Qy5dlhRS7ZlLWObdNcfPKvGO', '2024-11-07 11:12:52.000000', 2);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `department_id` int(11) NOT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  `department_position` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `employment_type` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `department_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `invoice_id` int(11) NOT NULL,
  `print_date` datetime(6) DEFAULT NULL,
  `total_amount` double NOT NULL,
  `employee_id` int(11) NOT NULL,
  `payment_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `notification_id` int(11) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `timestamp` time(6) DEFAULT NULL,
  `customer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `parking_stall`
--

CREATE TABLE `parking_stall` (
  `stall_id` int(11) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `vehicle_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `status` enum('COMPLETED','FAILED','PENDING') DEFAULT NULL,
  `total_amount` double NOT NULL,
  `card_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_id`, `payment_date`, `payment_method`, `status`, `total_amount`, `card_type`) VALUES
(1, '2024-10-09 12:29:31.000000', 'onArrival', 'COMPLETED', 3000, NULL),
(12, '2024-12-12 02:00:00.000000', 'card', 'COMPLETED', 2000, 'PayPal'),
(13, '2024-12-12 02:00:00.000000', 'card', 'COMPLETED', 2000, 'PayPal'),
(14, '2024-12-12 02:00:00.000000', 'card', 'COMPLETED', 2000, 'PayPal'),
(15, '2024-12-12 02:00:00.000000', 'card', 'COMPLETED', 2000, 'PayPal'),
(16, '2024-12-12 02:00:00.000000', 'card', 'COMPLETED', 2000, 'PayPal'),
(17, '2024-12-12 02:00:00.000000', 'card', 'COMPLETED', 2000, 'PayPal');

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

CREATE TABLE `rental` (
  `rental_id` int(11) NOT NULL,
  `driver_license` varchar(255) DEFAULT NULL,
  `rental_end_date` date DEFAULT NULL,
  `rental_start_date` date DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL,
  `rental_prices_id` int(11) DEFAULT NULL,
  `reservation_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rental_prices`
--

CREATE TABLE `rental_prices` (
  `rental_id` int(11) NOT NULL,
  `price_per_day` double NOT NULL,
  `price_per_month` double NOT NULL,
  `price_per_week` double NOT NULL,
  `price_per_year` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `reservation_id` int(11) NOT NULL,
  `additional_services` varchar(255) DEFAULT NULL,
  `reservation_end_date` date DEFAULT NULL,
  `reservation_start_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `customer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservation_id`, `additional_services`, `reservation_end_date`, `reservation_start_date`, `status`, `customer_id`) VALUES
(1, 'non', '2024-10-10', '2024-10-01', 'PENDING', 1);

-- --------------------------------------------------------

--
-- Table structure for table `reservation_service`
--

CREATE TABLE `reservation_service` (
  `reservation_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` enum('ADMIN','SUPER_ADMIN','USER') NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `created_at`, `description`, `name`, `updated_at`) VALUES
(1, '2024-11-07 11:08:21.000000', 'Default user role', 'USER', '2024-11-07 11:08:21.000000'),
(2, '2024-11-07 11:08:21.000000', 'Administrator role', 'ADMIN', '2024-11-07 11:08:21.000000'),
(3, '2024-11-07 11:08:21.000000', NULL, 'SUPER_ADMIN', '2024-11-07 11:08:21.000000');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `dtype` varchar(31) NOT NULL,
  `vehicle_id` int(11) NOT NULL,
  `engine_capacity` double NOT NULL,
  `fuel_type` varchar(255) DEFAULT NULL,
  `has_air_conditioning` bit(1) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `plate_number` varchar(255) DEFAULT NULL,
  `vehicle_name` varchar(255) DEFAULT NULL,
  `vehicle_status` varchar(255) DEFAULT NULL,
  `has_wi_fi` bit(1) DEFAULT NULL,
  `num_of_seats` int(11) DEFAULT NULL,
  `num_of_doors` int(11) DEFAULT NULL,
  `has_side_car` bit(1) DEFAULT NULL,
  `motor_type` varchar(255) DEFAULT NULL,
  `cargo_capacity` int(11) DEFAULT NULL,
  `num_of_axles` int(11) DEFAULT NULL,
  `trailer_type` varchar(255) DEFAULT NULL,
  `rental_id` int(11) DEFAULT NULL,
  `reservation_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`dtype`, `vehicle_id`, `engine_capacity`, `fuel_type`, `has_air_conditioning`, `latitude`, `longitude`, `manufacturer`, `model`, `plate_number`, `vehicle_name`, `vehicle_status`, `has_wi_fi`, `num_of_seats`, `num_of_doors`, `has_side_car`, `motor_type`, `cargo_capacity`, `num_of_axles`, `trailer_type`, `rental_id`, `reservation_id`) VALUES
('CAR', 9, 2.5, 'Petrol', b'1', 30, 0, 'Toyota', 'Camry 2024', NULL, 'Toyota Camry', 'Available', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('CAR', 10, 2.5, 'Petrol', b'1', 30, 0, 'Toyota', 'Camry 2024', NULL, 'Toyota Camry', 'Available', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('CAR', 11, 2.5, 'Petrol', b'1', 30, 0, 'Toyota', 'Camry 2024', NULL, 'Toyota Camry', 'Available', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('CAR', 12, 2.5, 'Petrol', b'1', 30, 0, 'Toyota', 'Camry 2024', NULL, 'Toyota Camry', 'Available', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('BUS', 13, 2.5, 'Petrol', b'1', 30, 0, 'Toyota', 'Camry 2024', NULL, 'Toyota Camry', 'Available', b'0', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('TRUCK', 14, 2.5, 'Petrol', b'1', 30, 0, 'Toyota', 'Camry 2024', NULL, 'Toyota Camry', 'Available', NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vehicle_check`
--

CREATE TABLE `vehicle_check` (
  `check_id` int(11) NOT NULL,
  `check_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `vehicle_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `_service`
--

CREATE TABLE `_service` (
  `service_id` int(11) NOT NULL,
  `service_cost` double NOT NULL,
  `service_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`branch_id`),
  ADD KEY `FKfj63st69d25im54m4rubj3u0k` (`payment_id`),
  ADD KEY `FKr2dbgbugm0ayo5yqqhwkqeftu` (`reservation_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`),
  ADD UNIQUE KEY `UK_dwk6cx0afu8bs9o4t536v1j5v` (`email`),
  ADD KEY `FK373k26sa2pjommr22j06s2a48` (`role_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`department_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`),
  ADD KEY `FKbejtwvg9bxus2mffsm3swj3u9` (`department_id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoice_id`),
  ADD UNIQUE KEY `UK5vvlr4mmb6jbwiu4dyqwevd0d` (`payment_id`),
  ADD KEY `FKau92vqwrrlsflir3v65262ucw` (`employee_id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`notification_id`),
  ADD KEY `FKduo4ugsictahhrd219y86wqho` (`customer_id`);

--
-- Indexes for table `parking_stall`
--
ALTER TABLE `parking_stall`
  ADD PRIMARY KEY (`stall_id`),
  ADD UNIQUE KEY `UKc3rk9sc98ffpd2k0o3anlj1ec` (`vehicle_id`),
  ADD KEY `FKhko55uug9qva84qcn2t4s18wo` (`branch_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`);

--
-- Indexes for table `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`rental_id`),
  ADD UNIQUE KEY `UKt02axvogqj1j05dllbubk1l0y` (`payment_id`),
  ADD UNIQUE KEY `UKht3rg31bbhjjgy5uh3d8hb65` (`rental_prices_id`),
  ADD UNIQUE KEY `UKt7ui3t2qj2e16yw5ebqallx14` (`reservation_id`);

--
-- Indexes for table `rental_prices`
--
ALTER TABLE `rental_prices`
  ADD PRIMARY KEY (`rental_id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `FK41v6ueo0hiran65w8y1cta2c2` (`customer_id`);

--
-- Indexes for table `reservation_service`
--
ALTER TABLE `reservation_service`
  ADD PRIMARY KEY (`reservation_id`,`service_id`),
  ADD KEY `FKkh3ippiorn7ycl4ywny970uo0` (`service_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`vehicle_id`),
  ADD KEY `FK3b4q8ymch0pgs9s0ehe7n35p1` (`rental_id`),
  ADD KEY `FK5sfpcqnsous1gd3sawy9ga0wd` (`reservation_id`);

--
-- Indexes for table `vehicle_check`
--
ALTER TABLE `vehicle_check`
  ADD PRIMARY KEY (`check_id`),
  ADD KEY `FKc4vmapfvthcuvqrp5jn42rh2e` (`vehicle_id`);

--
-- Indexes for table `_service`
--
ALTER TABLE `_service`
  ADD PRIMARY KEY (`service_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `branch`
--
ALTER TABLE `branch`
  MODIFY `branch_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoice_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `parking_stall`
--
ALTER TABLE `parking_stall`
  MODIFY `stall_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `rental`
--
ALTER TABLE `rental`
  MODIFY `rental_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rental_prices`
--
ALTER TABLE `rental_prices`
  MODIFY `rental_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `vehicle_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `vehicle_check`
--
ALTER TABLE `vehicle_check`
  MODIFY `check_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `_service`
--
ALTER TABLE `_service`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `branch`
--
ALTER TABLE `branch`
  ADD CONSTRAINT `FKfj63st69d25im54m4rubj3u0k` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`),
  ADD CONSTRAINT `FKr2dbgbugm0ayo5yqqhwkqeftu` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`);

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `FK373k26sa2pjommr22j06s2a48` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FKbejtwvg9bxus2mffsm3swj3u9` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`);

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `FKau92vqwrrlsflir3v65262ucw` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  ADD CONSTRAINT `FKbaxa82hce5x7dqj0sotnc1cxf` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`);

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FKduo4ugsictahhrd219y86wqho` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `parking_stall`
--
ALTER TABLE `parking_stall`
  ADD CONSTRAINT `FK2cd3ra9ktcugm9p9x0y19l3ep` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`vehicle_id`),
  ADD CONSTRAINT `FKhko55uug9qva84qcn2t4s18wo` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`branch_id`);

--
-- Constraints for table `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `FKcs5aqo1hvl6f7ft91myow5g6c` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`),
  ADD CONSTRAINT `FKl6vqiqj7s9rkg5byuly3v5am6` FOREIGN KEY (`rental_prices_id`) REFERENCES `rental_prices` (`rental_id`),
  ADD CONSTRAINT `FKo353gy01hghi2d5jh5xnm8wa3` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK41v6ueo0hiran65w8y1cta2c2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `reservation_service`
--
ALTER TABLE `reservation_service`
  ADD CONSTRAINT `FKkh3ippiorn7ycl4ywny970uo0` FOREIGN KEY (`service_id`) REFERENCES `_service` (`service_id`),
  ADD CONSTRAINT `FKky2gr8jk9fw121e8rxogc8ccm` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`);

--
-- Constraints for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `FK3b4q8ymch0pgs9s0ehe7n35p1` FOREIGN KEY (`rental_id`) REFERENCES `rental` (`rental_id`),
  ADD CONSTRAINT `FK5sfpcqnsous1gd3sawy9ga0wd` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`);

--
-- Constraints for table `vehicle_check`
--
ALTER TABLE `vehicle_check`
  ADD CONSTRAINT `FKc4vmapfvthcuvqrp5jn42rh2e` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`vehicle_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
