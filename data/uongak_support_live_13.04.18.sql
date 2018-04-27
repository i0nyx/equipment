-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Апр 13 2018 г., 15:42
-- Версия сервера: 5.7.16-log
-- Версия PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `uongak_support_live`
--

-- --------------------------------------------------------

--
-- Структура таблицы `cartridge_repair`
--

CREATE TABLE `cartridge_repair` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `part` varchar(255) DEFAULT NULL,
  `type_work` varchar(255) DEFAULT NULL,
  `who` varchar(255) DEFAULT NULL,
  `receivedRepair_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `cartridge_repair`
--

INSERT INTO `cartridge_repair` (`id`, `date`, `part`, `type_work`, `who`, `receivedRepair_id`) VALUES
(1, '2018-03-19 11:29:55', 'тонер', 'FUELING', '', 1),
(2, '2018-03-19 11:34:56', 'тонер', 'FUELING', '', 4),
(3, '2018-03-19 11:36:20', 'тонер', 'FUELING', '', 5),
(4, '2018-03-19 11:39:51', 'тонер', 'FUELING', '', 8),
(5, '2018-03-28 07:43:55', 'тонер', 'FUELING', '', 12),
(6, '2018-03-29 06:24:01', 'тонер', 'FUELING', '', 13),
(7, '2018-04-03 07:05:25', 'тонер', 'FUELING', '', 16),
(8, '2018-04-06 07:09:56', 'тонер + фотовал', 'SUBSTITUTION_FUELING', '', 17),
(9, '2018-04-06 07:10:14', 'тонер', 'FUELING', '', 18),
(10, '2018-04-06 09:55:06', 'тонер', 'FUELING', '', 19),
(11, '2018-04-13 13:35:58', 'тонер', 'FUELING', '', 21);

-- --------------------------------------------------------

--
-- Структура таблицы `computer_repair`
--

CREATE TABLE `computer_repair` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `description_of_work` varchar(255) DEFAULT NULL,
  `who` varchar(255) DEFAULT NULL,
  `receivedRepair_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `computer_repair`
--

INSERT INTO `computer_repair` (`id`, `date`, `description_of_work`, `who`, `receivedRepair_id`) VALUES
(1, '2018-03-19 11:31:32', 'Ремонт зарядного устройства', '', 2),
(2, '2018-03-19 11:33:40', 'Переустановка windows', '', 3),
(3, '2018-03-19 11:37:27', 'чистка', '', 6),
(4, '2018-03-19 11:38:58', 'чистка', '', 7),
(5, '2018-03-19 11:40:49', 'переустановка windows', '', 9),
(6, '2018-03-23 07:45:50', 'переустановка windows', '', 11),
(7, '2018-03-28 06:49:39', 'переустановка windows', '', 10),
(8, '2018-04-02 11:45:00', 'переустановка win', '', 15),
(9, '2018-04-13 13:36:53', 'Переустановка win', '', 20);

-- --------------------------------------------------------

--
-- Структура таблицы `equipments`
--

CREATE TABLE `equipments` (
  `id` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cabinet` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `equipments`
--

INSERT INTO `equipments` (`id`, `brand`, `cabinet`, `code`, `model`, `name`, `type`, `user_id`) VALUES
(1, 'HP', NULL, 'УОНГАК000001', 'p1005', 'картридж', 'CARTRIDGE', NULL),
(2, 'Samsung', '203', 'УОНГАК000003', '3119', 'картридж', 'CARTRIDGE', NULL),
(3, 'сис.блок', NULL, 'УОНГАК000005', '', 'компьютер', 'COMPUTER', NULL),
(4, 'HP', '205(К-4)', 'УОНГАК000006', 'p1005', 'картридж', 'CARTRIDGE', NULL),
(5, 'Toshiba', NULL, 'УОНГАК000007', 'Sattellite A300', 'ноутбук', 'NOTEBOOK', NULL),
(6, 'Asus', NULL, 'УОНГАК000008', 'A3000', 'ноутбук', 'NOTEBOOK', NULL),
(7, 'HP', '206', 'УОНГАК000009', 'p1005', 'картридж', 'CARTRIDGE', NULL),
(8, 'сис.блок', '208', 'УОНГАК0000010', '', 'компьютер', 'COMPUTER', NULL),
(9, 'сис.блок', '208', 'УОНГАК0000011', '', 'компьютер', 'COMPUTER', NULL),
(10, 'HP', NULL, 'УОНГАК0000012', 'Q2612', 'картридж', 'CARTRIDGE', NULL),
(11, 'сис.блок', '208', 'УОНГАК0000013', '', 'компьютер', 'COMPUTER', NULL),
(12, 'HP', '101', 'УОНГАК0000014', 'p1005', 'картридж', 'CARTRIDGE', NULL),
(13, 'сис.блок', '208', 'УОНГАК0000015', '', 'компьютер', 'COMPUTER', NULL),
(14, 'HP', '110', 'УОНГАК0000016', 'p1005', 'картридж', 'CARTRIDGE', NULL),
(15, 'HP', '202(К-4)', 'УОНГАК000004', 'p1005', 'картридж', 'CARTRIDGE', NULL),
(16, 'HP', '202(К-4)', 'УОНГАК0000017', 'p1005', 'картридж', 'CARTRIDGE', NULL),
(17, 'сис.блок', '71 (К-4)', 'УОНГАК0000018', '', 'компьютер', 'COMPUTER', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `descriptions` varchar(255) NOT NULL,
  `date_end` datetime DEFAULT NULL,
  `state` bit(1) DEFAULT b'0',
  `date_start` datetime DEFAULT NULL,
  `whom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `printer_repair`
--

CREATE TABLE `printer_repair` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `description_of_work` varchar(255) DEFAULT NULL,
  `who` varchar(255) DEFAULT NULL,
  `receivedRepair_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `received_repair`
--

CREATE TABLE `received_repair` (
  `id` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `state` bit(1) DEFAULT b'0',
  `whose` varchar(255) DEFAULT NULL,
  `equipment_id` int(11) NOT NULL,
  `support_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `received_repair`
--

INSERT INTO `received_repair` (`id`, `comment`, `date`, `state`, `whose`, `equipment_id`, `support_id`) VALUES
(1, '', '2018-02-16 05:50:00', b'1', '203', 2, NULL),
(2, '', '2018-02-28 06:00:00', b'1', 'метод', 5, NULL),
(3, '', '2018-03-05 05:10:00', b'1', 'метод', 3, NULL),
(4, '', '2018-03-06 06:30:00', b'1', 'гл.бух', 4, NULL),
(5, '', '2018-03-12 06:50:00', b'1', 'Зюзикова (206)', 7, NULL),
(6, '', '2018-03-12 08:30:00', b'1', 'Генута (208)', 8, NULL),
(7, '', '2018-03-13 06:30:00', b'1', 'Генута (208)', 9, NULL),
(8, '', '2018-03-13 07:00:00', b'1', 'метод', 10, NULL),
(9, '', '2018-03-14 06:00:00', b'1', 'Генута (208)', 11, NULL),
(10, '', '2018-03-23 07:44:42', b'1', 'Генута (208)', 8, NULL),
(11, '', '2018-03-23 07:45:16', b'1', '209', 6, NULL),
(12, '', '2018-03-28 06:49:12', b'1', 'Гизовская', 12, NULL),
(13, '', '2018-03-29 06:08:13', b'1', 'метод', 10, NULL),
(16, '', '2018-04-03 06:47:32', b'1', 'Сазанович', 14, NULL),
(15, '', '2018-03-30 08:00:00', b'1', 'Генута (208)', 13, NULL),
(17, '', '2018-04-05 12:10:00', b'1', 'Бухгалтерия', 15, NULL),
(18, '', '2018-04-05 12:10:00', b'1', 'Бухгалтерия', 16, NULL),
(19, '', '2018-04-06 09:37:37', b'1', 'гл.Бух', 4, NULL),
(20, 'Не работает автокад', '2018-04-09 07:10:00', b'1', 'Генута (208)', 13, NULL),
(21, '', '2018-04-18 09:10:00', b'1', 'метод', 10, NULL),
(22, 'Не работают usb порты', '2018-04-13 12:30:00', b'0', 'Статюкевич', 17, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `support`
--

CREATE TABLE `support` (
  `id` int(11) NOT NULL,
  `cabinet` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `urgently` bit(1) DEFAULT b'0',
  `last_name` varchar(255) DEFAULT NULL,
  `support_status` varchar(255) DEFAULT NULL,
  `support_type` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `cabinetNumber` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `password`, `cabinetNumber`, `firstName`, `lastName`, `phoneNumber`, `position`) VALUES
(1, NULL, NULL, '$2a$10$GL4zbbMwzF9UHif1lOvh6uioKf7OHtAR37zCs28qaw62rJUXVmBd2', '209', 'Сергей', 'Бузук', '9944882', 'Оператор ЭВМ');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `cartridge_repair`
--
ALTER TABLE `cartridge_repair`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKca5db0wjpg63o0ucbo3almdyq` (`receivedRepair_id`);

--
-- Индексы таблицы `computer_repair`
--
ALTER TABLE `computer_repair`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsvbva1i007c8i2wde2l50vqqr` (`receivedRepair_id`);

--
-- Индексы таблицы `equipments`
--
ALTER TABLE `equipments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_7fenrquevt41j1726xy6omw30` (`code`),
  ADD KEY `FK8eb1fsijfriydqwlwsiu1pke9` (`user_id`);

--
-- Индексы таблицы `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `printer_repair`
--
ALTER TABLE `printer_repair`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnitwmh1o76cptspx80jta85qv` (`receivedRepair_id`);

--
-- Индексы таблицы `received_repair`
--
ALTER TABLE `received_repair`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKorqtec3s8egrsa0jprfi48tn2` (`equipment_id`),
  ADD KEY `FKlb13cwjx48yll7dv0s2i6jd0` (`support_id`);

--
-- Индексы таблицы `support`
--
ALTER TABLE `support`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKog1o7qfuuwlsyy8qbqlmulmhv` (`user_id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_jolnwy9lwp82aoyavymxpolhl` (`phoneNumber`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `cartridge_repair`
--
ALTER TABLE `cartridge_repair`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT для таблицы `computer_repair`
--
ALTER TABLE `computer_repair`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT для таблицы `equipments`
--
ALTER TABLE `equipments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT для таблицы `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `printer_repair`
--
ALTER TABLE `printer_repair`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `received_repair`
--
ALTER TABLE `received_repair`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT для таблицы `support`
--
ALTER TABLE `support`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
