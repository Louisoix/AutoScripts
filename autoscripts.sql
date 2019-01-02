/*
 Navicat MySQL Data Transfer

 Source Server         : Navicat
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : autoscripts

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 01/01/2019 16:45:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `ADMIN_ID` int(11) NOT NULL,
  `ADMIN_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ADMIN_PASSWORD` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NICK_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ADMIN_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Administrator information' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for designer
-- ----------------------------
DROP TABLE IF EXISTS `designer`;
CREATE TABLE `designer`  (
  `DESIGNER_ID` int(11) NOT NULL,
  `DESIGNER_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESIGNER_PASSWORD` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `COOKIES` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NICK_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'Nickname Not Set',
  PRIMARY KEY (`DESIGNER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'designer information' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for designer_bookmark
-- ----------------------------
DROP TABLE IF EXISTS `designer_bookmark`;
CREATE TABLE `designer_bookmark`  (
  `DESIGNER_ID` int(11) NOT NULL,
  `SCRIPT_ID` int(11) NOT NULL,
  PRIMARY KEY (`DESIGNER_ID`, `SCRIPT_ID`) USING BTREE,
  INDEX `B_SCRIPT_ID_idx`(`SCRIPT_ID`) USING BTREE,
  CONSTRAINT `B_DESINER_ID` FOREIGN KEY (`DESIGNER_ID`) REFERENCES `designer` (`DESIGNER_ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `B_SCRIPT_ID` FOREIGN KEY (`SCRIPT_ID`) REFERENCES `script` (`SCRIPT_ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for designer_result
-- ----------------------------
DROP TABLE IF EXISTS `designer_result`;
CREATE TABLE `designer_result`  (
  `DESIGNER_ID` int(11) NOT NULL,
  `RESULT_ID` int(11) NOT NULL,
  PRIMARY KEY (`DESIGNER_ID`, `RESULT_ID`) USING BTREE,
  INDEX `DESIGNER_ID_idx`(`DESIGNER_ID`) USING BTREE,
  INDEX `DRESULT_ID_idx`(`RESULT_ID`) USING BTREE,
  CONSTRAINT `DESIGNER_ID` FOREIGN KEY (`DESIGNER_ID`) REFERENCES `designer` (`DESIGNER_ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `DRESULT_ID` FOREIGN KEY (`RESULT_ID`) REFERENCES `result` (`RESULT_ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for designer_script
-- ----------------------------
DROP TABLE IF EXISTS `designer_script`;
CREATE TABLE `designer_script`  (
  `DESIGNER_ID` int(11) NOT NULL,
  `SCRIPT_ID` int(11) NOT NULL,
  PRIMARY KEY (`DESIGNER_ID`, `SCRIPT_ID`) USING BTREE,
  INDEX `SDESIGNER_ID_idx`(`DESIGNER_ID`) USING BTREE,
  INDEX `D_SCRIPT_ID_idx`(`SCRIPT_ID`) USING BTREE,
  CONSTRAINT `D_SCRIPT_ID` FOREIGN KEY (`SCRIPT_ID`) REFERENCES `script` (`SCRIPT_ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `SDESIGNER_ID` FOREIGN KEY (`DESIGNER_ID`) REFERENCES `designer` (`DESIGNER_ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for result
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result`  (
  `RESULT_ID` int(11) NOT NULL,
  `RESULT_PATH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RESULT_TIME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SCRIPT_ID` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`RESULT_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for script
-- ----------------------------
DROP TABLE IF EXISTS `script`;
CREATE TABLE `script`  (
  `SCRIPT_ID` int(11) NOT NULL,
  `SCRIPT_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PARAMETER` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LANGUAGE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FLAG` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PATH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(1023) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCRIPT_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'script data' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `USER_ID` int(11) NOT NULL,
  `USER_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_PASSWORD` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `COOKIES` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NICK_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'User information' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_bookmark
-- ----------------------------
DROP TABLE IF EXISTS `user_bookmark`;
CREATE TABLE `user_bookmark`  (
  `USER_ID` int(11) NOT NULL,
  `SCRIPT_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`, `SCRIPT_ID`) USING BTREE,
  INDEX `BSCRIPT_ID_idx`(`SCRIPT_ID`) USING BTREE,
  CONSTRAINT `BSCRIPT_ID` FOREIGN KEY (`SCRIPT_ID`) REFERENCES `script` (`SCRIPT_ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `BUSER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_result
-- ----------------------------
DROP TABLE IF EXISTS `user_result`;
CREATE TABLE `user_result`  (
  `USER_ID` int(11) NOT NULL,
  `RESULT_ID` int(11) NOT NULL,
  PRIMARY KEY (`RESULT_ID`, `USER_ID`) USING BTREE,
  INDEX `USER_ID_idx`(`USER_ID`) USING BTREE,
  INDEX `URESULT_ID_idx`(`RESULT_ID`) USING BTREE,
  CONSTRAINT `URESULT_ID` FOREIGN KEY (`RESULT_ID`) REFERENCES `result` (`RESULT_ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '1@1', '111111', 'Administrator');
SET FOREIGN_KEY_CHECKS = 1;
