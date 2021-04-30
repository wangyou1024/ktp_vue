/*
 Navicat Premium Data Transfer

 Source Server         : wy
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : java_final

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 30/04/2021 11:07:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `aid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `grade` int(0) NULL DEFAULT NULL COMMENT '-1代表未批',
  `dateTime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE,
  INDEX `fk_au`(`uid`) USING BTREE,
  INDEX `fk_ah`(`hid`) USING BTREE,
  CONSTRAINT `fk_ah` FOREIGN KEY (`hid`) REFERENCES `homework` (`hid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_au` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('1606965239189', '1606908751270', '1606908151511', '11823020220', '李明', '你好', 99, '2020-12-3 11:13');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名字',
  `className` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inviteCode` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `semester` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `term` int(0) NULL DEFAULT NULL,
  `teacherHeader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacherName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1606141070905', '软件测试', '118230201-2', '504794', '2020-2021', 1, '1605956012583.jpg', '李进', 2);

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `fid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `aid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `realName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classify` int(0) NULL DEFAULT NULL COMMENT '0表示题目内容，1表示提交的作业',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1606961435357', NULL, '1606908151511', 'info.txt', '1606961435357.txt', 0);
INSERT INTO `file` VALUES ('1606965236351', '1606965239189', '1606908151511', 'info.txt', '1606965236351.txt', 1);

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework`  (
  `hid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `startDate` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `startTime` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `overDate` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `overTime` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `corrected` int(0) NULL DEFAULT NULL,
  `correcting` int(0) NULL DEFAULT NULL,
  `submit` int(0) NULL DEFAULT NULL,
  `quit` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`hid`) USING BTREE,
  INDEX `fk_hc`(`cid`) USING BTREE,
  CONSTRAINT `fk_hc` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('1606908151511', '1606141070905', '1号标题', '1号作业', '2020-12-2', '19:22', '2020-12-29', '24:00', 1, 0, 1, 0);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `mid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作业名称',
  `classify` int(0) NULL DEFAULT NULL COMMENT '0表示新作业；1表示催交作业',
  `publish` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '改送时间',
  PRIMARY KEY (`mid`) USING BTREE,
  INDEX `fk_mc`(`cid`) USING BTREE,
  INDEX `fk_mh`(`hid`) USING BTREE,
  CONSTRAINT `fk_mc` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_mh` FOREIGN KEY (`hid`) REFERENCES `homework` (`hid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1606908151614', '1606141070905', '1606908151511', '软件测试', '1号标题', 0, '2020-12-2');
INSERT INTO `message` VALUES ('1606908151727', '1606141070905', '1606908151511', '软件测试', '1号标题', 1, '2020-12-2');

-- ----------------------------
-- Table structure for re_user_course
-- ----------------------------
DROP TABLE IF EXISTS `re_user_course`;
CREATE TABLE `re_user_course`  (
  `ucid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教/学',
  `top` int(0) NULL DEFAULT NULL COMMENT '0表示不置顶，1表示置顶',
  `reorganize` int(0) NULL DEFAULT NULL COMMENT '0/1分别表示未归档/归档',
  `quit` int(0) NULL DEFAULT NULL COMMENT '0/1表示是否退课',
  PRIMARY KEY (`ucid`) USING BTREE,
  INDEX `fk_u`(`uid`) USING BTREE,
  INDEX `fk_c`(`cid`) USING BTREE,
  CONSTRAINT `fk_c` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_u` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of re_user_course
-- ----------------------------
INSERT INTO `re_user_course` VALUES ('1606141071081', '1605946026275', '1606141070905', '教', 1, 0, 0);
INSERT INTO `re_user_course` VALUES ('1606911003815', '1606908751270', '1606141070905', '学', 1, 0, 0);
INSERT INTO `re_user_course` VALUES ('1607234845987', '1607223918670', '1606141070905', '学', 1, 0, 0);

-- ----------------------------
-- Table structure for re_user_message
-- ----------------------------
DROP TABLE IF EXISTS `re_user_message`;
CREATE TABLE `re_user_message`  (
  `mid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isRead` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of re_user_message
-- ----------------------------
INSERT INTO `re_user_message` VALUES ('1606908151614', '1606908751270', 1);
INSERT INTO `re_user_message` VALUES ('1606908151727', '1606908751270', 0);
INSERT INTO `re_user_message` VALUES ('1606908151614', '1606908751270', 0);
INSERT INTO `re_user_message` VALUES ('1606908151614', '1607223918670', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话号码',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `school` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1605946026275', '12345678901', '$2a$10$FX2E3GdZ7eZoIs1MojWZWuOj59AYuqiE7XT.pC2ksZoVIMrgK8XaG', '李进', '重庆理工大学', '11823020220', '1605956012583.jpg');
INSERT INTO `user` VALUES ('1606908751270', '12345678902', '$2a$10$nbIRj5CuF1oIF4xVEvA6WOvcQtGVQP07BwbE3m1ARP4uVAM5kKQ4y', '李明', '重庆理工大学', '11823020220', '1606965531136.jpg');
INSERT INTO `user` VALUES ('1607223918670', '12345678903', '$2a$10$ENezLgfc3OMdgPRJEPEXY.mMeZamDRojkWCoqCJXQ1R.JNw1LFDDW', '张颖', '重庆理工大学', '11823020220', NULL);

SET FOREIGN_KEY_CHECKS = 1;
