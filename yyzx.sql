/*
 Navicat Premium Dump SQL

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 90300 (9.3.0)
 Source Host           : localhost:3306
 Source Schema         : yyzx

 Target Server Type    : MySQL
 Target Server Version : 90300 (9.3.0)
 File Encoding         : 65001

 Date: 12/09/2025 10:25:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for backdown
-- ----------------------------
DROP TABLE IF EXISTS `backdown`;
CREATE TABLE `backdown` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remarks` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `is_deleted` int NOT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `customer_id` int NOT NULL COMMENT '客户ID',
  `retreattime` date NOT NULL COMMENT '退住时间',
  `retreattype` int NOT NULL COMMENT '退住类型 0-正常退住  1-死亡退住 2-保留床位',
  `retreatreason` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '退住原因',
  `auditstatus` int NOT NULL COMMENT '审批状态  0-已提交 1-同意  2-拒绝',
  `auditperson` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '审批人',
  `audittime` date DEFAULT NULL COMMENT '审批时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of backdown
-- ----------------------------
BEGIN;
INSERT INTO `backdown` (`id`, `remarks`, `is_deleted`, `customer_id`, `retreattime`, `retreattype`, `retreatreason`, `auditstatus`, `auditperson`, `audittime`) VALUES (1, '暂无', 0, 40, '2023-12-17', 2, '', 2, '1', '2025-09-11');
INSERT INTO `backdown` (`id`, `remarks`, `is_deleted`, `customer_id`, `retreattime`, `retreattype`, `retreatreason`, `auditstatus`, `auditperson`, `audittime`) VALUES (2, NULL, 0, 39, '2023-12-19', 0, '', 1, '1', '2025-09-11');
INSERT INTO `backdown` (`id`, `remarks`, `is_deleted`, `customer_id`, `retreattime`, `retreattype`, `retreatreason`, `auditstatus`, `auditperson`, `audittime`) VALUES (3, NULL, 0, 33, '2023-12-20', 0, '', 0, '1', '2024-01-03');
COMMIT;

-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_no` int NOT NULL COMMENT '房间编号',
  `bed_status` int NOT NULL COMMENT '房间状态 1：空闲  2：有人  3：外出',
  `remarks` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `bed_no` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '床位编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bed
-- ----------------------------
BEGIN;
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (6, 1001, 2, NULL, '1001-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (7, 1001, 2, NULL, '1001-2');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (8, 1002, 1, NULL, '1002-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (9, 1002, 2, NULL, '1002-2');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (10, 1003, 1, NULL, '1003-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (11, 1004, 2, NULL, '1004-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (12, 1005, 1, NULL, '1005-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (13, 1005, 1, NULL, '1005-2');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (14, 1006, 1, NULL, '1006-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (15, 1006, 2, NULL, '1006-2');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (16, 1007, 1, NULL, '1007-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (17, 1007, 1, NULL, '1007-2');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (18, 1008, 1, NULL, '1008-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (19, 1009, 1, NULL, '1009-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (21, 1009, 1, NULL, '1009-2');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (22, 1010, 2, NULL, '1010-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (23, 1010, 1, NULL, '1010-2');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (24, 1011, 1, NULL, '1011-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (25, 1012, 1, NULL, '1012-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (26, 1012, 2, NULL, '1012-2');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (27, 1013, 1, NULL, '1013-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (28, 1014, 1, NULL, '1014-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (29, 1015, 1, NULL, '1015-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (30, 1015, 1, NULL, '1015-2');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (31, 1016, 1, NULL, '1016-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (32, 1017, 2, NULL, '1017-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (33, 1018, 1, NULL, '1018-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (34, 2001, 1, NULL, '2001-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (35, 2002, 2, NULL, '2002-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (36, 2003, 1, NULL, '2003-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (37, 2004, 1, NULL, '2004-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (38, 2005, 2, NULL, '2005-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (39, 2006, 1, NULL, '2006-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (40, 2007, 1, NULL, '2007-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (41, 2008, 1, NULL, '2008-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (42, 2009, 1, NULL, '2009-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (43, 2009, 1, NULL, '2009-2');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (44, 2010, 1, NULL, '2010-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (45, 2011, 1, NULL, '2011-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (46, 2012, 1, NULL, '2012-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (47, 2013, 1, NULL, '2013-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (48, 2014, 2, NULL, '2014-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (49, 2015, 1, NULL, '2015-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (50, 2016, 1, NULL, '2016-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (51, 2017, 2, NULL, '2017-1');
INSERT INTO `bed` (`id`, `room_no`, `bed_status`, `remarks`, `bed_no`) VALUES (52, 2018, 2, NULL, '2018-1');
COMMIT;

-- ----------------------------
-- Table structure for beddetails
-- ----------------------------
DROP TABLE IF EXISTS `beddetails`;
CREATE TABLE `beddetails` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `start_date` date DEFAULT NULL COMMENT '床位起始日期',
  `end_date` date DEFAULT NULL COMMENT '床位结束日期',
  `bed_details` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '床位详情信息',
  `customer_id` int DEFAULT NULL COMMENT '顾客ID',
  `bed_id` int DEFAULT NULL COMMENT '床位ID',
  `is_deleted` int DEFAULT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of beddetails
-- ----------------------------
BEGIN;
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (45, '2023-11-17', '2025-11-30', '606#1001-1', 27, 6, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (46, '2023-11-17', '2024-11-30', '606#1002-2', 28, 9, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (49, '2023-11-17', '2023-11-23', '606#2001-1', 30, 34, 1);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (50, '2023-11-17', '2025-11-27', '606#2002-1', 29, 35, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (51, '2023-11-17', '2025-11-29', '606#2017-1', 31, 51, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (52, '2023-11-17', '2023-11-21', '606#2015-1', 32, 49, 1);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (53, '2023-11-17', '2024-11-29', '606#2014-1', 33, 48, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (54, '2023-11-17', '2025-01-30', '606#1006-2', 34, 15, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (55, '2023-11-17', '2024-11-30', '606#1017-1', 35, 32, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (56, '2023-11-17', '2025-11-26', '606#1010-1', 36, 22, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (57, '2023-11-17', '2025-11-20', '606#1012-2', 37, 26, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (58, '2023-11-17', '2025-11-29', '606#2005-1', 38, 38, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (59, '2023-11-17', '2024-11-30', '606#1014-1', 39, 28, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (60, '2023-11-17', '2025-11-29', '606#2012-1', 40, 46, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (61, '2023-11-21', '2024-01-15', '606#2016-1', 32, 50, 1);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (62, '2023-11-23', '2025-12-31', '606#2018-1', 30, 52, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (63, '2024-01-03', '2024-02-03', '606#1004-1', 41, 11, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (64, '2024-01-03', '2024-02-16', '606#1005-1', 41, 12, 0);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (65, '2024-01-15', '2025-09-11', '606#1005-2', 32, 13, 1);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (66, '2025-09-11', '2025-09-11', '606#1001-2', 32, 7, 1);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (67, '2025-09-11', '2025-09-11', '606#1002-1', 32, 8, 1);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (68, '2025-09-11', '2025-09-12', '606#1001-2', 42, 7, 1);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (69, '2025-09-11', '2025-09-11', '606#1001-2', 32, 7, 1);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (70, '2025-09-11', '2025-09-11', '606#1002-1', 32, 8, 1);
INSERT INTO `beddetails` (`id`, `start_date`, `end_date`, `bed_details`, `customer_id`, `bed_id`, `is_deleted`) VALUES (71, '2025-09-11', '2027-11-10', '606#1001-2', 32, 7, 0);
COMMIT;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_deleted` int NOT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `customer_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '客户姓名',
  `customer_age` int NOT NULL COMMENT '年龄',
  `customer_sex` int NOT NULL COMMENT '性别  0：男  1：女',
  `idcard` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '身份证号',
  `room_no` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '房间号',
  `building_no` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '所属楼房',
  `checkin_date` date NOT NULL COMMENT '入住时间',
  `expiration_date` date NOT NULL COMMENT '合同到期时间',
  `contact_tel` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '联系电话',
  `bed_id` int NOT NULL COMMENT '床号',
  `psychosomatic_state` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '身心状况',
  `attention` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '注意事项',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `height` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '身高',
  `weight` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '体重',
  `blood_type` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '血型',
  `filepath` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '头像路径',
  `user_id` int DEFAULT NULL COMMENT '关联系统健康管家(护工)  无管家设置  -1',
  `level_id` int DEFAULT NULL COMMENT '护理等级',
  `family_member` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '家属',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `nurselevel_customer_fk` (`level_id`) USING BTREE,
  KEY `user_customer_fk` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of customer
-- ----------------------------
BEGIN;
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (27, 0, '刘茂元', 76, 0, '610104194707211657', '1001', '606', '2023-11-17', '2025-11-30', '18176756743', 6, '', NULL, '1947-07-21', NULL, NULL, 'O', '@/assets/tou.png', -1, NULL, '刘其元');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (28, 0, '高廉武', 83, 0, '610404194103010017', '1002', '606', '2023-11-17', '2024-11-30', '13767675481', 9, '', NULL, '1941-03-01', NULL, NULL, 'A', '@/assets/tou.png', 5, NULL, '高校蓝');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (29, 0, '刘玉珍', 72, 1, '610402194112240335', '2002', '606', '2023-11-17', '2025-11-27', '13534786754', 35, '', NULL, '1951-12-24', NULL, NULL, 'B', '@/assets/tou.png', -1, NULL, '刘宇');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (30, 0, '石秀英', 73, 1, '610422195102220029', '2018', '606', '2023-11-17', '2025-12-31', '13867541321', 52, '', NULL, '1951-02-22', NULL, NULL, 'O', '@/assets/tou.png', 5, NULL, '石杰颖');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (31, 0, '何风云', 62, 1, '610402196111166507', '2017', '606', '2023-11-17', '2025-11-29', '18765652312', 51, '', NULL, '1961-11-16', NULL, NULL, 'O', '@/assets/tou.png', 3, NULL, '何方');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (32, 0, '毛兰香', 73, 1, '61040419501227002x', '1001', '606', '2023-11-17', '2027-11-12', '15816164587', 7, '', NULL, '1950-12-27', NULL, NULL, 'O', '@/assets/tou.png', -1, NULL, '毛参');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (33, 0, '李艾青', 83, 1, '622801194104150240', '2014', '606', '2023-11-17', '2024-11-29', '18176572345', 48, '', NULL, '1941-04-14', NULL, NULL, 'B', '@/assets/tou.png', -1, NULL, '李天');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (34, 0, '刘海云', 64, 0, '510522196005042710', '1006', '606', '2023-11-17', '2025-01-30', '18798986545', 15, '', NULL, '1960-05-04', NULL, NULL, 'O', '@/assets/tou.png', -1, NULL, '刘高');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (35, 0, '王金堂', 58, 0, '610402196511056517', '1017', '606', '2023-11-17', '2024-11-30', '13423786754', 32, '', NULL, '1965-11-05', NULL, NULL, 'AB', '@/assets/tou.png', 3, 4, '王田雨');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (36, 0, '刘玉亭', 72, 0, '610123195204158510', '1010', '606', '2023-11-17', '2025-11-26', '18767560987', 22, '', NULL, '1952-04-15', NULL, NULL, 'O', '@/assets/tou.png', 5, 1, '刘晓');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (37, 0, '陈泉', 68, 0, '61040219560313030x', '1012', '606', '2023-11-17', '2025-11-20', '18567546712', 26, '', NULL, '1956-03-13', NULL, NULL, 'O', '@/assets/tou.png', -1, NULL, '陈荣光');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (38, 0, '苗月兰', 67, 1, '622725195701081429', '2005', '606', '2023-11-17', '2025-11-29', '18367541987', 38, '好', NULL, '1957-01-08', NULL, NULL, 'O', '@/assets/tou.png', 3, 1, '苗存义');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (39, 0, '张帆', 77, 0, '610402194701286518', '1014', '606', '2023-11-17', '2024-11-30', '17745189064', 28, '', NULL, '1947-01-28', NULL, NULL, 'O', '@/assets/tou.png', 2, 3, '张明');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (40, 0, '孙瑞英', 68, 1, '610404195603010027', '2012', '606', '2023-11-17', '2025-11-29', '18167564213', 46, '', NULL, '1956-03-01', NULL, NULL, 'O', '@/assets/tou.png', 2, 2, '孙石');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (41, 0, '许三多', 0, 0, '210330198902011510', '1004', '606', '2024-01-03', '2024-02-03', '13398760987', 11, '过敏', NULL, '2024-01-03', NULL, NULL, 'O', '@/assets/tou.png', 2, 1, '许多');
INSERT INTO `customer` (`id`, `is_deleted`, `customer_name`, `customer_age`, `customer_sex`, `idcard`, `room_no`, `building_no`, `checkin_date`, `expiration_date`, `contact_tel`, `bed_id`, `psychosomatic_state`, `attention`, `birthday`, `height`, `weight`, `blood_type`, `filepath`, `user_id`, `level_id`, `family_member`) VALUES (42, 1, 'qwq', 0, 0, '230206111111110717', '1001', '606', '2025-09-11', '2025-09-12', '111111', 7, 'qwq', NULL, '2025-09-02', NULL, NULL, 'O', '@/assets/tou.png', -1, NULL, 'qwq');
COMMIT;

-- ----------------------------
-- Table structure for customernurseitem
-- ----------------------------
DROP TABLE IF EXISTS `customernurseitem`;
CREATE TABLE `customernurseitem` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_id` int NOT NULL COMMENT '护理项目编号',
  `customer_id` int NOT NULL COMMENT '客户编号',
  `level_id` int DEFAULT NULL COMMENT '护理级别编号',
  `nurse_number` int NOT NULL COMMENT '购买数量',
  `is_deleted` int DEFAULT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `buy_time` date NOT NULL COMMENT '服务购买日期',
  `maturity_time` date NOT NULL COMMENT '服务到期日',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of customernurseitem
-- ----------------------------
BEGIN;
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (58, 1, 40, 2, -5, 0, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (59, 2, 40, 2, 56, 0, '2023-10-17', '2023-11-16');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (60, 3, 40, 2, 1, 0, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (61, 4, 40, 2, 80, 0, '2023-10-17', '2023-11-28');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (62, 1, 39, 3, 44, 0, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (63, 2, 39, 3, 100, 0, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (64, 3, 39, 3, 76, 0, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (65, 5, 39, 3, 80, 1, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (66, 6, 39, 3, 70, 1, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (67, 1, 36, NULL, 40, 0, '2023-10-21', '2024-01-21');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (68, 3, 36, NULL, 70, 0, '2023-10-21', '2024-01-21');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (69, 4, 36, NULL, 70, 1, '2023-10-21', '2024-01-21');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (70, 4, 36, NULL, 19, 0, '2023-10-21', '2024-01-21');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (71, 20, 40, NULL, 17, 0, '2023-10-23', '2024-01-23');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (72, 17, 40, NULL, 16, 0, '2023-10-23', '2024-01-23');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (73, 10, 40, NULL, 10, 0, '2023-10-23', '2024-01-23');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (74, 12, 40, NULL, 17, 0, '2023-10-23', '2024-01-23');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (75, 1, 35, 4, 98, 0, '2023-10-24', '2024-01-24');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (76, 2, 35, 4, 99, 0, '2023-10-24', '2024-01-24');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (77, 3, 35, 4, 100, 0, '2023-10-24', '2024-01-24');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (78, 5, 35, 4, 100, 0, '2023-10-24', '2024-01-24');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (79, 1, 41, 1, 0, 0, '2023-12-03', '2024-02-01');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (80, 2, 41, 1, 1, 0, '2023-12-03', '2024-03-03');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (81, 4, 41, 1, 1, 0, '2023-12-03', '2024-03-03');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (82, 5, 41, 1, 1, 0, '2023-12-03', '2024-03-03');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (83, 6, 41, 1, 1, 0, '2023-12-03', '2024-03-03');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (84, 20, 41, NULL, 1, 0, '2023-12-03', '2024-01-05');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (85, 1, 36, 1, 1, 0, '2023-12-15', '2024-03-15');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (86, 2, 36, 1, 1, 0, '2023-12-15', '2024-03-15');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (87, 4, 36, 1, 1, 0, '2023-12-15', '2024-03-15');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (88, 5, 36, 1, 1, 0, '2023-12-15', '2024-03-15');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (89, 6, 36, 1, 1, 0, '2023-12-15', '2024-01-17');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (90, 3, 41, NULL, 1, 0, '2023-12-15', '2024-01-17');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (91, 1, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (92, 2, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (93, 3, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (94, 4, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (95, 5, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (96, 6, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (97, 1, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (98, 2, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (99, 3, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (100, 4, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (101, 5, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (102, 6, 38, 1, 1, 1, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (103, 1, 38, 1, 1, 0, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (104, 2, 38, 1, 1, 0, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (105, 3, 38, 1, 1, 0, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (106, 4, 38, 1, 1, 0, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (107, 5, 38, 1, 1, 0, '2025-09-11', '2025-12-11');
INSERT INTO `customernurseitem` (`id`, `item_id`, `customer_id`, `level_id`, `nurse_number`, `is_deleted`, `buy_time`, `maturity_time`) VALUES (108, 6, 38, 1, 1, 0, '2025-09-11', '2025-12-11');
COMMIT;

-- ----------------------------
-- Table structure for customerpreference
-- ----------------------------
DROP TABLE IF EXISTS `customerpreference`;
CREATE TABLE `customerpreference` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '喜好ID',
  `customer_id` int DEFAULT NULL COMMENT '顾客ID',
  `preferences` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '饮食喜好',
  `attention` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '注意事项',
  `remark` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `is_deleted` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of customerpreference
-- ----------------------------
BEGIN;
INSERT INTO `customerpreference` (`id`, `customer_id`, `preferences`, `attention`, `remark`, `is_deleted`) VALUES (1, 29, '不吃辣，爱吃青菜，不爱吃肉', '风湿', '不辣多肉', 0);
INSERT INTO `customerpreference` (`id`, `customer_id`, `preferences`, `attention`, `remark`, `is_deleted`) VALUES (2, 30, '爱吃甜品，不爱吃青菜，口味清淡', '糖尿病，轻度脂肪肝', '少糖，多青菜', 0);
INSERT INTO `customerpreference` (`id`, `customer_id`, `preferences`, `attention`, `remark`, `is_deleted`) VALUES (3, 31, '爱吃辣，不吃内脏，口味清淡', '支气管炎', '多糖，少盐', 0);
INSERT INTO `customerpreference` (`id`, `customer_id`, `preferences`, `attention`, `remark`, `is_deleted`) VALUES (9, 41, '多糖', '不建议摄入过多糖分', '尽量少糖', 0);
COMMIT;

-- ----------------------------
-- Table structure for db_image_store
-- ----------------------------
DROP TABLE IF EXISTS `db_image_store`;
CREATE TABLE `db_image_store` (
  `uid` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of db_image_store
-- ----------------------------
BEGIN;
INSERT INTO `db_image_store` (`uid`, `name`, `time`) VALUES (1, '/cache/20250912/039d6d894dfe4cc7a0d8e59c70a6f72d', '2025-09-12 09:56:54');
INSERT INTO `db_image_store` (`uid`, `name`, `time`) VALUES (1, '/cache/20250912/7c6ffb842f2c44dd94de6ec6adf177fd', '2025-09-12 10:19:00');
INSERT INTO `db_image_store` (`uid`, `name`, `time`) VALUES (1, '/cache/20250912/e155960d8d844e119cf8185be42ecb9d', '2025-09-12 10:20:58');
INSERT INTO `db_image_store` (`uid`, `name`, `time`) VALUES (1, '/cache/20250912/0f6022247e224eaea1f1b6434f123685', '2025-09-12 10:21:10');
INSERT INTO `db_image_store` (`uid`, `name`, `time`) VALUES (1, '/cache/20250912/cd76b09f41ad487d80487f055403977a', '2025-09-12 10:21:18');
INSERT INTO `db_image_store` (`uid`, `name`, `time`) VALUES (1, '/cache/20250912/a1add92f61e64f17b63e4fbdfcbecf6c', '2025-09-12 10:21:28');
INSERT INTO `db_image_store` (`uid`, `name`, `time`) VALUES (1, '/cache/20250912/31ff775471204cdc867559e230a0755b', '2025-09-12 10:21:54');
INSERT INTO `db_image_store` (`uid`, `name`, `time`) VALUES (1, '/cache/20250912/e8843d192584473b93279408abe26653', '2025-09-12 10:22:01');
INSERT INTO `db_image_store` (`uid`, `name`, `time`) VALUES (1, '/cache/20250912/b29277fbba9a45dbaabb06d241faa79e', '2025-09-12 10:22:10');
INSERT INTO `db_image_store` (`uid`, `name`, `time`) VALUES (1, '/cache/20250912/e0c3c794ee3046d6a0a3ba6bf560692f', '2025-09-12 10:22:19');
INSERT INTO `db_image_store` (`uid`, `name`, `time`) VALUES (1, '/cache/20250912/059c213d48d340f3a3271eed5e89b42f', '2025-09-12 10:22:26');
COMMIT;

-- ----------------------------
-- Table structure for dietary
-- ----------------------------
DROP TABLE IF EXISTS `dietary`;
CREATE TABLE `dietary` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '膳食ID',
  `is_deleted` int DEFAULT NULL COMMENT '删除标志',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '膳食类型',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '膳食名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `Islamic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '清真',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片',
  `create_on` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dietary
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for dietary_calendar
-- ----------------------------
DROP TABLE IF EXISTS `dietary_calendar`;
CREATE TABLE `dietary_calendar` (
  `id` int NOT NULL COMMENT '膳食日历ID',
  `dietary_id` int DEFAULT NULL COMMENT '膳食编号',
  `dietary_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '早餐中餐晚餐',
  `week_day` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '星期',
  `taste` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '口味（多糖、多盐、少糖、少盐）',
  `create_on` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dietary_calendar
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '食品ID',
  `food_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '食品名称',
  `food_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '食品类型',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `is_halal` int DEFAULT NULL COMMENT '是否清真',
  `food_img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of food
-- ----------------------------
BEGIN;
INSERT INTO `food` (`id`, `food_name`, `food_type`, `price`, `is_halal`, `food_img`) VALUES (1, '面包', '主食', 15.00, 0, '20250912/e155960d8d844e119cf8185be42ecb9d');
INSERT INTO `food` (`id`, `food_name`, `food_type`, `price`, `is_halal`, `food_img`) VALUES (2, '白灼虾', '大荤', 20.00, 0, '20250912/0f6022247e224eaea1f1b6434f123685');
INSERT INTO `food` (`id`, `food_name`, `food_type`, `price`, `is_halal`, `food_img`) VALUES (3, '宫保鸡丁', '小荤', 20.00, 0, '20250912/cd76b09f41ad487d80487f055403977a');
INSERT INTO `food` (`id`, `food_name`, `food_type`, `price`, `is_halal`, `food_img`) VALUES (4, '白灼芥兰', '素材', 5.00, 0, '20250912/a1add92f61e64f17b63e4fbdfcbecf6c');
INSERT INTO `food` (`id`, `food_name`, `food_type`, `price`, `is_halal`, `food_img`) VALUES (5, '素烩汤', '汤', 5.00, 0, '20250912/31ff775471204cdc867559e230a0755b');
INSERT INTO `food` (`id`, `food_name`, `food_type`, `price`, `is_halal`, `food_img`) VALUES (6, '小米粥', '主食', 3.00, 0, '20250912/e8843d192584473b93279408abe26653');
INSERT INTO `food` (`id`, `food_name`, `food_type`, `price`, `is_halal`, `food_img`) VALUES (7, '红烧牛肉', '大荤', 25.00, 1, '20250912/b29277fbba9a45dbaabb06d241faa79e');
INSERT INTO `food` (`id`, `food_name`, `food_type`, `price`, `is_halal`, `food_img`) VALUES (8, '燕麦粥', '主食', 5.00, 0, '20250912/e0c3c794ee3046d6a0a3ba6bf560692f');
INSERT INTO `food` (`id`, `food_name`, `food_type`, `price`, `is_halal`, `food_img`) VALUES (9, '欢喜坨', '主食', 10.00, 0, '20250912/059c213d48d340f3a3271eed5e89b42f');
INSERT INTO `food` (`id`, `food_name`, `food_type`, `price`, `is_halal`, `food_img`) VALUES (10, '哈气小猫', '主食', 20.00, 1, '20250912/039d6d894dfe4cc7a0d8e59c70a6f72d');
INSERT INTO `food` (`id`, `food_name`, `food_type`, `price`, `is_halal`, `food_img`) VALUES (12, '败北圣女', '汤品', 20.00, 1, '20250912/7c6ffb842f2c44dd94de6ec6adf177fd');
COMMIT;

-- ----------------------------
-- Table structure for meal
-- ----------------------------
DROP TABLE IF EXISTS `meal`;
CREATE TABLE `meal` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '饮食ID',
  `week_day` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '星期几',
  `food_id` int DEFAULT NULL COMMENT '食品ID',
  `meal_type` int DEFAULT NULL COMMENT '食品类型（1.早餐、2.午餐、3.晚餐）',
  `taste` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '口味（多糖、多盐、少糖、少盐）',
  `is_deleted` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of meal
-- ----------------------------
BEGIN;
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (2, '周二', 1, 2, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (3, '周一', 1, 1, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (7, '周一', 7, 2, '少盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (8, '周一', 3, 2, '多盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (16, '周一', 6, 3, '少盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (22, '周二', 2, 2, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (23, '周二', 1, 1, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (24, '周二', 5, 1, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (26, '周二', 9, 2, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (27, '周二', 8, 3, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (28, '周二', 2, 3, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (29, '周二', 3, 2, '少盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (32, '周二', 3, 3, '多盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (33, '周二', 1, 3, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (34, '周三', 6, 1, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (35, '周三', 7, 3, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (36, '周三', 8, 3, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (37, '周三', 9, 2, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (39, '周三', 1, 3, '少盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (40, '周三', 2, 3, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (41, '周三', 3, 2, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (42, '周四', 5, 2, '少盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (43, '周四', 2, 1, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (44, '周四', 6, 1, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (45, '周四', 1, 2, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (47, '周四', 7, 3, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (48, '周四', 3, 3, '多盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (49, '周五', 5, 2, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (50, '周五', 2, 2, '少盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (51, '周五', 9, 3, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (52, '周五', 8, 3, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (54, '周五', 4, 1, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (55, '周五', 5, 1, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (56, '周六', 9, 2, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (57, '周六', 8, 3, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (58, '周六', 2, 3, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (59, '周六', 3, 2, '少盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (60, '周六', 5, 1, '多盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (62, '周六', 3, 3, '多盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (63, '周六', 1, 3, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (64, '周日', 6, 2, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (65, '周日', 7, 3, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (66, '周日', 8, 3, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (67, '周日', 9, 2, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (69, '周日', 1, 3, '少盐', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (70, '周日', 2, 3, '正常', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (72, '周一', 8, 1, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (73, '周一', 1, 3, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (74, '周一', 1, 2, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (75, '周日', 1, 1, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (76, '周日', 9, 1, '少糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (78, '周一', 2, 1, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (79, '周一', 9, 1, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (80, '周一', 4, 1, '多糖', 0);
INSERT INTO `meal` (`id`, `week_day`, `food_id`, `meal_type`, `taste`, `is_deleted`) VALUES (81, '周一', 10, 3, '正常', 0);
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menus_index` varchar(5) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '一级菜单索引',
  `title` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单标题',
  `icon` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单图标',
  `path` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '菜单路径',
  `parent_id` int DEFAULT NULL COMMENT '父级Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (1, '1', '床位管理', 'Operation', NULL, 0);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (2, '2', '客户管理', 'Avatar', NULL, 0);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (4, '4', '护理管理', 'SuitcaseLine', NULL, 0);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (5, '5', '健康管家', 'Coin', NULL, 0);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (6, '6', '基础数据维护', 'List', NULL, 0);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (7, NULL, '床位示意图', 'Picture', '/bed/bedMap', 1);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (8, NULL, '床位管理', 'Collection', '/bed/bedManage', 1);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (9, NULL, '入住登记', 'DArrowRight', '/customer/checkIn', 2);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (11, NULL, '退住登记', 'DArrowLeft', '/customer/checkOut', 2);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (12, NULL, '外出登记', 'Location', '/customer/outRecords', 2);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (17, NULL, '护理级别', 'Sort', '/nurse/nurseLevel', 4);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (18, NULL, '护理项目', 'ZoomIn', '/nurse/nurseItem', 4);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (19, NULL, '客户护理设置', 'Setting', '/nurse/nurseSetting', 4);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (20, NULL, '护理记录', 'ScaleToOriginal', '/nurse/nurseRecords', 4);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (21, NULL, '设置服务对象', 'Setting', '/health/serviceCustomerSetting', 5);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (22, '2', '健康管家', 'Coin', NULL, 0);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (23, NULL, '日常护理', 'Help', '/health/dailyNurse', 22);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (24, NULL, '服务关注', 'View', '/health/serviceCare', 5);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (25, NULL, '用户管理', 'User', '/user/listUser', 6);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (26, '1', '客户管理', 'User', NULL, 0);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (27, NULL, '外出登记', 'Location', '/customer/outRecords', 26);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (28, NULL, '退住登记', 'DArrowLeft', '/customer/checkOut', 26);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (29, NULL, '护理记录', 'ScaleToOriginal', '/nurse/nurseRecordsToUser', 22);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (30, NULL, '膳食管理', 'Setting', '/customer/preference', 2);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (31, NULL, '膳食日历', 'Picture', '/customer/meal', 2);
INSERT INTO `menu` (`id`, `menus_index`, `title`, `icon`, `path`, `parent_id`) VALUES (32, NULL, '膳食信息', 'Picture', '/food/listFood', 6);
COMMIT;

-- ----------------------------
-- Table structure for nursecontent
-- ----------------------------
DROP TABLE IF EXISTS `nursecontent`;
CREATE TABLE `nursecontent` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `serial_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '编号',
  `nursing_name` varchar(55) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
  `service_price` varchar(55) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '价格',
  `message` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT ' 描述',
  `status` int NOT NULL COMMENT '状态 1：启用；2：停用',
  `execution_cycle` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '执行周期',
  `execution_times` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '执行次数',
  `is_deleted` int DEFAULT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of nursecontent
-- ----------------------------
BEGIN;
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (1, 'HLXM0001', '吸氧', '60元/次', '', 1, '每天', '2', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (2, 'HLXM0002', '协助服药', '免费', '', 1, '每天', '3', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (3, 'HLXM0003', '测量血压', '免费', '', 1, '每天', '1', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (4, 'HLXM0004', '协助穿衣', '10元/次', NULL, 1, '每天', '2', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (5, 'HLXM0005', '床上洗头', '20元/次', NULL, 1, '每周', '3', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (6, 'HLXM0006', '健身', '10元/小时', '', 1, '每天', '1', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (7, 'HLXM0007', '清洁床铺', '10元/次', '', 1, '每周', '2', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (8, 'HLXM0008', '如厕服务', '20元/次', '', 1, '每天', '2', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (9, 'HLXM0009', '口腔清洁', '50元/次', '', 1, '每月', '2', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (10, 'HLXM0010', '假牙清洁', '20元/次', '', 1, '每周', '2', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (12, 'HLXM0012', '擦拭身体', '20元/次', '', 1, '每天', '1', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (14, 'HLXM0014', '体温测量', '免费', '', 1, '每天', '1', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (15, 'HLXM0015', '协助饮食', '50元/次', '', 2, '每天', '3', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (16, 'HLXM0016', '血压测量', '免费', '', 2, '每天', '1', 1);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (17, 'HLXM0017', '剪指甲', '12/次', '', 1, '每月', '2', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (20, 'HLXM0019', '协助外出', '100/次', '', 1, '每月', '4', 0);
INSERT INTO `nursecontent` (`id`, `serial_number`, `nursing_name`, `service_price`, `message`, `status`, `execution_cycle`, `execution_times`, `is_deleted`) VALUES (21, 'HLXM0020', '瑜伽辅导', '80/次', '', 2, '每周', '2', 0);
COMMIT;

-- ----------------------------
-- Table structure for nurselevel
-- ----------------------------
DROP TABLE IF EXISTS `nurselevel`;
CREATE TABLE `nurselevel` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `level_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '护理级别',
  `level_status` int DEFAULT NULL COMMENT '级别状态 1：启用；2：停用',
  `is_deleted` int DEFAULT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of nurselevel
-- ----------------------------
BEGIN;
INSERT INTO `nurselevel` (`id`, `level_name`, `level_status`, `is_deleted`) VALUES (1, '一级', 1, NULL);
INSERT INTO `nurselevel` (`id`, `level_name`, `level_status`, `is_deleted`) VALUES (2, '二级', 1, NULL);
INSERT INTO `nurselevel` (`id`, `level_name`, `level_status`, `is_deleted`) VALUES (3, '三级', 1, NULL);
INSERT INTO `nurselevel` (`id`, `level_name`, `level_status`, `is_deleted`) VALUES (4, '四级', 1, NULL);
INSERT INTO `nurselevel` (`id`, `level_name`, `level_status`, `is_deleted`) VALUES (5, '五级', 1, NULL);
INSERT INTO `nurselevel` (`id`, `level_name`, `level_status`, `is_deleted`) VALUES (7, '六级', 2, NULL);
INSERT INTO `nurselevel` (`id`, `level_name`, `level_status`, `is_deleted`) VALUES (8, 'x', 1, NULL);
INSERT INTO `nurselevel` (`id`, `level_name`, `level_status`, `is_deleted`) VALUES (9, '八级', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for nurselevelitem
-- ----------------------------
DROP TABLE IF EXISTS `nurselevelitem`;
CREATE TABLE `nurselevelitem` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `level_id` int NOT NULL COMMENT '关联护理级别',
  `item_id` int NOT NULL COMMENT '关联护理项目',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `nurselevel_id_fk` (`level_id`) USING BTREE,
  KEY `nurseItem_id_fk` (`item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of nurselevelitem
-- ----------------------------
BEGIN;
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (23, 1, 2);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (25, 1, 4);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (28, 2, 1);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (29, 2, 2);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (39, 3, 2);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (40, 3, 3);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (41, 3, 5);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (53, 4, 1);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (54, 4, 2);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (55, 4, 3);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (63, 1, 5);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (71, 4, 5);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (72, 3, 1);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (73, 3, 6);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (74, 1, 6);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (76, 8, 2);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (77, 8, 3);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (78, 8, 14);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (79, 9, 2);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (80, 9, 4);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (82, 9, 1);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (83, 2, 3);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (87, 2, 5);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (91, 1, 1);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (92, 1, 3);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (93, 5, 9);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (94, 5, 8);
INSERT INTO `nurselevelitem` (`id`, `level_id`, `item_id`) VALUES (95, 1, 9);
COMMIT;

-- ----------------------------
-- Table structure for nurserecord
-- ----------------------------
DROP TABLE IF EXISTS `nurserecord`;
CREATE TABLE `nurserecord` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_deleted` int NOT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `customer_id` int NOT NULL COMMENT '客户ID',
  `item_id` int NOT NULL COMMENT '护理项目ID',
  `nursing_time` datetime NOT NULL COMMENT '护理时间',
  `nursing_content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '护理内容',
  `nursing_count` int NOT NULL COMMENT '护理数量',
  `user_id` int NOT NULL COMMENT '护理人员ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of nurserecord
-- ----------------------------
BEGIN;
INSERT INTO `nurserecord` (`id`, `is_deleted`, `customer_id`, `item_id`, `nursing_time`, `nursing_content`, `nursing_count`, `user_id`) VALUES (9, 0, 40, 3, '2023-11-24 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` (`id`, `is_deleted`, `customer_id`, `item_id`, `nursing_time`, `nursing_content`, `nursing_count`, `user_id`) VALUES (10, 0, 40, 17, '2023-11-24 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` (`id`, `is_deleted`, `customer_id`, `item_id`, `nursing_time`, `nursing_content`, `nursing_count`, `user_id`) VALUES (11, 1, 40, 20, '2023-11-24 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` (`id`, `is_deleted`, `customer_id`, `item_id`, `nursing_time`, `nursing_content`, `nursing_count`, `user_id`) VALUES (12, 0, 35, 2, '2023-11-24 13:38:37', '按照医嘱进行喂药', 1, 3);
INSERT INTO `nurserecord` (`id`, `is_deleted`, `customer_id`, `item_id`, `nursing_time`, `nursing_content`, `nursing_count`, `user_id`) VALUES (13, 0, 35, 1, '2023-11-24 13:39:22', '仪器型号：XY09,吸氧服务', 1, 3);
INSERT INTO `nurserecord` (`id`, `is_deleted`, `customer_id`, `item_id`, `nursing_time`, `nursing_content`, `nursing_count`, `user_id`) VALUES (14, 0, 39, 1, '2023-11-24 18:05:24', '吸氧护理', 1, 2);
INSERT INTO `nurserecord` (`id`, `is_deleted`, `customer_id`, `item_id`, `nursing_time`, `nursing_content`, `nursing_count`, `user_id`) VALUES (15, 0, 40, 3, '2024-01-03 00:00:00', '测量血压', 1, 2);
INSERT INTO `nurserecord` (`id`, `is_deleted`, `customer_id`, `item_id`, `nursing_time`, `nursing_content`, `nursing_count`, `user_id`) VALUES (16, 0, 41, 1, '2024-01-16 00:00:00', '', 1, 2);
COMMIT;

-- ----------------------------
-- Table structure for outward
-- ----------------------------
DROP TABLE IF EXISTS `outward`;
CREATE TABLE `outward` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remarks` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `is_deleted` int NOT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `customer_id` int NOT NULL COMMENT '客户ID',
  `outgoingreason` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '外出事由',
  `outgoingtime` date NOT NULL COMMENT '外出时间',
  `expectedreturntime` date NOT NULL COMMENT '预计回院时间',
  `actualreturntime` date DEFAULT NULL COMMENT '实际回院时间',
  `escorted` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '陪同人',
  `relation` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '与老人关系',
  `escortedtel` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '陪同人电话',
  `auditstatus` int NOT NULL COMMENT '审批状态  0-已提交 1-同意  2-拒绝',
  `auditperson` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '审批人',
  `audittime` date DEFAULT NULL COMMENT '审批时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of outward
-- ----------------------------
BEGIN;
INSERT INTO `outward` (`id`, `remarks`, `is_deleted`, `customer_id`, `outgoingreason`, `outgoingtime`, `expectedreturntime`, `actualreturntime`, `escorted`, `relation`, `escortedtel`, `auditstatus`, `auditperson`, `audittime`) VALUES (1, NULL, 0, 40, '想家', '2023-12-17', '2023-12-20', NULL, '女儿', '母女', '13312341234', 1, '1', '2023-12-18');
INSERT INTO `outward` (`id`, `remarks`, `is_deleted`, `customer_id`, `outgoingreason`, `outgoingtime`, `expectedreturntime`, `actualreturntime`, `escorted`, `relation`, `escortedtel`, `auditstatus`, `auditperson`, `audittime`) VALUES (2, NULL, 0, 33, '出门', '2023-12-17', '2023-12-19', NULL, '儿子', NULL, '13812341234', 2, NULL, NULL);
INSERT INTO `outward` (`id`, `remarks`, `is_deleted`, `customer_id`, `outgoingreason`, `outgoingtime`, `expectedreturntime`, `actualreturntime`, `escorted`, `relation`, `escortedtel`, `auditstatus`, `auditperson`, `audittime`) VALUES (3, NULL, 1, 33, '出门', '2023-12-17', '2023-12-20', '2023-12-29', '儿子', NULL, '13812341234', 0, NULL, NULL);
INSERT INTO `outward` (`id`, `remarks`, `is_deleted`, `customer_id`, `outgoingreason`, `outgoingtime`, `expectedreturntime`, `actualreturntime`, `escorted`, `relation`, `escortedtel`, `auditstatus`, `auditperson`, `audittime`) VALUES (4, NULL, 1, 33, '出门', '2023-12-18', '2023-12-20', NULL, '儿子', NULL, '13312341234', 0, NULL, NULL);
INSERT INTO `outward` (`id`, `remarks`, `is_deleted`, `customer_id`, `outgoingreason`, `outgoingtime`, `expectedreturntime`, `actualreturntime`, `escorted`, `relation`, `escortedtel`, `auditstatus`, `auditperson`, `audittime`) VALUES (5, NULL, 0, 40, '外出业务', '2024-01-03', '2024-01-06', '2024-01-07', '儿子', NULL, '13819870987', 1, '1', '2024-01-03');
INSERT INTO `outward` (`id`, `remarks`, `is_deleted`, `customer_id`, `outgoingreason`, `outgoingtime`, `expectedreturntime`, `actualreturntime`, `escorted`, `relation`, `escortedtel`, `auditstatus`, `auditperson`, `audittime`) VALUES (6, NULL, 0, 39, '', '2024-01-15', '2024-01-17', NULL, '', '', '', 1, '1', '2025-09-11');
INSERT INTO `outward` (`id`, `remarks`, `is_deleted`, `customer_id`, `outgoingreason`, `outgoingtime`, `expectedreturntime`, `actualreturntime`, `escorted`, `relation`, `escortedtel`, `auditstatus`, `auditperson`, `audittime`) VALUES (7, NULL, 0, 41, '测试', '2025-09-11', '2025-09-12', NULL, '儿子', '', '123123123123', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_by` int DEFAULT NULL COMMENT '更新人',
  `is_deleted` int NOT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '系统角色名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `create_time`, `update_time`, `update_by`, `is_deleted`, `name`) VALUES (1, '2023-11-01 17:13:16.000000', NULL, NULL, 0, '系统管理员');
INSERT INTO `role` (`id`, `create_time`, `update_time`, `update_by`, `is_deleted`, `name`) VALUES (2, '2023-11-01 17:13:13.000000', NULL, NULL, 0, '健康管家');
COMMIT;

-- ----------------------------
-- Table structure for rolemenu
-- ----------------------------
DROP TABLE IF EXISTS `rolemenu`;
CREATE TABLE `rolemenu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int NOT NULL COMMENT '角色编号',
  `menu` int NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of rolemenu
-- ----------------------------
BEGIN;
INSERT INTO `rolemenu` (`id`, `role_id`, `menu`) VALUES (1, 1, 1);
INSERT INTO `rolemenu` (`id`, `role_id`, `menu`) VALUES (2, 1, 2);
INSERT INTO `rolemenu` (`id`, `role_id`, `menu`) VALUES (3, 1, 3);
INSERT INTO `rolemenu` (`id`, `role_id`, `menu`) VALUES (4, 1, 4);
INSERT INTO `rolemenu` (`id`, `role_id`, `menu`) VALUES (5, 1, 5);
INSERT INTO `rolemenu` (`id`, `role_id`, `menu`) VALUES (6, 1, 6);
INSERT INTO `rolemenu` (`id`, `role_id`, `menu`) VALUES (7, 2, 26);
INSERT INTO `rolemenu` (`id`, `role_id`, `menu`) VALUES (8, 2, 22);
COMMIT;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_floor` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '房间楼层',
  `room_no` int NOT NULL COMMENT '房间编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of room
-- ----------------------------
BEGIN;
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (1, '一楼', 1001);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (2, '一楼', 1002);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (3, '一楼', 1003);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (4, '一楼', 1004);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (5, '一楼', 1005);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (6, '一楼', 1006);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (7, '一楼', 1007);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (8, '一楼', 1008);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (9, '一楼', 1009);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (10, '一楼', 1010);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (11, '一楼', 1011);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (12, '一楼', 1012);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (13, '一楼', 1013);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (14, '一楼', 1014);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (15, '一楼', 1015);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (16, '一楼', 1016);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (17, '一楼', 1017);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (18, '一楼', 1018);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (19, '二楼', 2001);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (20, '二楼', 2002);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (21, '二楼', 2003);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (22, '二楼', 2004);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (23, '二楼', 2005);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (24, '二楼', 2006);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (25, '二楼', 2007);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (26, '二楼', 2008);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (27, '二楼', 2009);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (28, '二楼', 2010);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (29, '二楼', 2011);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (30, '二楼', 2012);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (31, '二楼', 2013);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (32, '二楼', 2014);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (33, '二楼', 2015);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (34, '二楼', 2016);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (35, '二楼', 2017);
INSERT INTO `room` (`id`, `room_floor`, `room_no`) VALUES (36, '二楼', 2018);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` date NOT NULL COMMENT '创建时间',
  `create_by` int NOT NULL COMMENT '创建者',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `update_by` int DEFAULT NULL COMMENT '更新者',
  `is_deleted` int NOT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `nickname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '真实姓名',
  `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '系统账号',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `sex` int NOT NULL COMMENT '性别（0：女  1：男）',
  `email` varchar(254) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '电话号码',
  `role_id` int NOT NULL COMMENT '系统角色编号（1-管理员 2-健康管家）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `create_time`, `create_by`, `update_time`, `update_by`, `is_deleted`, `nickname`, `username`, `password`, `sex`, `email`, `phone_number`, `role_id`) VALUES (1, '2023-11-02', 0, '2023-11-02', 0, 0, 'admin', 'admin', 'admin', 1, '1312255201@qq.com', '15663123413', 1);
INSERT INTO `user` (`id`, `create_time`, `create_by`, `update_time`, `update_by`, `is_deleted`, `nickname`, `username`, `password`, `sex`, `email`, `phone_number`, `role_id`) VALUES (2, '2023-11-03', 0, '2023-11-03', 0, 0, '吴伟', 'wuwei', '123456', 1, 'wuwei@s.cq', '13517178987', 2);
INSERT INTO `user` (`id`, `create_time`, `create_by`, `update_time`, `update_by`, `is_deleted`, `nickname`, `username`, `password`, `sex`, `email`, `phone_number`, `role_id`) VALUES (3, '2023-11-21', 0, '2023-11-21', 0, 0, '陈小明', 'chenxiaoming', '123456', 0, 'chenxiaoming@s.cq', '18767890987', 2);
INSERT INTO `user` (`id`, `create_time`, `create_by`, `update_time`, `update_by`, `is_deleted`, `nickname`, `username`, `password`, `sex`, `email`, `phone_number`, `role_id`) VALUES (4, '2023-11-21', 0, '2023-11-21', 0, 0, '张兴', 'zhangxing', '123456', 1, 'zhangxing@s.cq', '18878675643', 2);
INSERT INTO `user` (`id`, `create_time`, `create_by`, `update_time`, `update_by`, `is_deleted`, `nickname`, `username`, `password`, `sex`, `email`, `phone_number`, `role_id`) VALUES (5, '2023-11-21', 0, '2023-11-21', 0, 0, '王年华', 'wangnianhua', '123456', 0, 'wangnianhua@s.cq', '13412128976', 2);
INSERT INTO `user` (`id`, `create_time`, `create_by`, `update_time`, `update_by`, `is_deleted`, `nickname`, `username`, `password`, `sex`, `email`, `phone_number`, `role_id`) VALUES (6, '2023-11-21', 0, '2023-11-21', 0, 0, '吴优', 'wuyou', '123456', 0, 'wuyou@s.cq', '13567452789', 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
