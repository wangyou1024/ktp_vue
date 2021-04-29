/*
 Navicat Premium Data Transfer

 Source Server         : wy
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : javaee

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 29/04/2021 21:56:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pattern` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentId` int(0) NULL DEFAULT NULL,
  `enabled` tinyint(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '/', NULL, 1, '所有', '');
INSERT INTO `menu` VALUES (2, '/home', 1, 1, '所有管理', 'Home');
INSERT INTO `menu` VALUES (3, '/db/**', 2, 1, '成绩管理', 'Score');
INSERT INTO `menu` VALUES (4, '/admin/**', 2, 1, '基本信息', 'Information');
INSERT INTO `menu` VALUES (5, '/student/**', 2, 1, '学生管理', 'Student');
INSERT INTO `menu` VALUES (6, '/home', 1, 1, '权限管理', 'Home');
INSERT INTO `menu` VALUES (7, '/user/**', 6, 1, '用户管理', 'UserManage');
INSERT INTO `menu` VALUES (8, '/teacher/**', 2, 1, '教师管理', 'Teacher');
INSERT INTO `menu` VALUES (9, '/teacher_personal', 2, 1, '教师信息', 'TeacherPersonal');

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `mid` int(0) NULL DEFAULT NULL,
  `rid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES (1, 3, 1);
INSERT INTO `menu_role` VALUES (2, 4, 2);
INSERT INTO `menu_role` VALUES (3, 5, 3);
INSERT INTO `menu_role` VALUES (4, 7, 2);
INSERT INTO `menu_role` VALUES (5, 8, 2);
INSERT INTO `menu_role` VALUES (6, 9, 4);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nameZh` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_dba', '数据库管理员');
INSERT INTO `role` VALUES (2, 'ROLE_admin', '系统管理员');
INSERT INTO `role` VALUES (3, 'ROLE_user', '用户');
INSERT INTO `role` VALUES (4, 'ROLE_teacher', '教师');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1611581104899 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '李明', 20, '男');
INSERT INTO `student` VALUES (2, '李子明', 20, '男');
INSERT INTO `student` VALUES (1611580940020, '李野', 88, '男');
INSERT INTO `student` VALUES (1611580989028, '李野', 88, '男');
INSERT INTO `student` VALUES (1611581094744, '李野', 88, '男');
INSERT INTO `student` VALUES (1611581101661, '李野', 88, '男');
INSERT INTO `student` VALUES (1611581104899, '李野', 88, '男');

-- ----------------------------
-- Table structure for student_copy
-- ----------------------------
DROP TABLE IF EXISTS `student_copy`;
CREATE TABLE `student_copy`  (
  `uid` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_copy
-- ----------------------------
INSERT INTO `student_copy` VALUES (23, '徐昊', 22, '男');
INSERT INTO `student_copy` VALUES (25, '阿旺', NULL, NULL);
INSERT INTO `student_copy` VALUES (27, '信息', 35, 'VC');
INSERT INTO `student_copy` VALUES (28, '徐哈', 22, '男');

-- ----------------------------
-- Table structure for student_count
-- ----------------------------
DROP TABLE IF EXISTS `student_count`;
CREATE TABLE `student_count`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `count` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_count
-- ----------------------------
INSERT INTO `student_count` VALUES (1, 3);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tid` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `racial` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `politics` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `household` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wechat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `graduate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salary` double(10, 2) NULL DEFAULT NULL,
  `college` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courses` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `career` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '李昊', '男', 55, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347401', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (2, '张力', '男', 23, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347402', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (3, '李炜光', '女', 35, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347403', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 20000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (4, '李煜', '男', 56, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347404', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (5, '王兴', '女', 47, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347405', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (6, '洪溪', '男', 56, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347406', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (7, '温蒂尼', '男', 20, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347407', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (8, '温情', '女', 39, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347408', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (9, '暹粒', '男', 40, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347409', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (10, '王刚', '男', 50, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347410', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (11, '李日常', '男', 35, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347411', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (12, '李晃悠', '男', 23, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347412', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (13, '江红', '女', 39, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347413', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (14, '梁家辉', '男', 23, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347414', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (15, '淡然', '女', 45, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347415', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (16, '赵明', '女 ', 46, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347416', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (17, '赵煦', '女', 56, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347417', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (18, '秦汉', '男', 12, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347418', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (19, '钱进', '男', 23, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347419', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (20, '鲁坦', '男', 56, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347420', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');
INSERT INTO `teacher` VALUES (21, '沈尹默', '女', 58, '2020-10-06', '汉族', '党员', '重庆', '重庆', '2889680858', '13635347490', '13635347421', '78654@qq.com', '重庆理工大学', '硕士', '讲师', 2000.00, '两江人工智能学院', '软件工程', '、、数据库设计、数据结构', '没有');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'userID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `enabled` tinyint(1) NULL DEFAULT 1,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '超级管理员', '18568887789', '029-82881234', '哈尔滨', 1, 'admin', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', NULL);
INSERT INTO `user` VALUES (2, '徐昊', '18568123489', '029-82123434', '重庆', 1, 'xuhao', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', NULL);
INSERT INTO `user` VALUES (3, '老王', '18568123666', '029-82111555', '广州', 1, 'laowang', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', NULL);
INSERT INTO `user` VALUES (8, '老张', NULL, NULL, NULL, 1, 'laozhang', '$2a$10$lv5kCzOsIVXVrYSOxykyGOUPpW.AIJ9.rAIVk6EJ6nxOyFGVmC0Zq', NULL);
INSERT INTO `user` VALUES (9, '老李', NULL, NULL, NULL, 0, 'laoli', '$2a$10$s3.qcTY9tw9egA.UoyBZ8Oyi6.NVAW84P6mN.A8wcgzXJQho.Ck9i', NULL);
INSERT INTO `user` VALUES (10, '小程', NULL, NULL, NULL, 1, 'xiaocheng', '$2a$10$9WvViONP7a.VcId3ktgg.Odj.WJExpc./j/eSNv2bSa8nArwfGGY2', NULL);
INSERT INTO `user` VALUES (11, '哈哈哈', NULL, NULL, NULL, 1, 'hhh', '$2a$10$30.wi4mGO6.wWgG2TEVw/O5glfMG1TWpgeEi7xK5Y5GvrQglQlH86', NULL);
INSERT INTO `user` VALUES (12, '李炜光', '13635347403', NULL, '重庆', 1, '13635347403', '$2a$10$W1yTmn2wsS1jWhnLt6KdnelGHR2RE5WeHzP1/noKK30r5sA94rJ96', NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NULL DEFAULT NULL,
  `rid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 1, 2);
INSERT INTO `user_role` VALUES (3, 2, 2);
INSERT INTO `user_role` VALUES (4, 3, 3);
INSERT INTO `user_role` VALUES (5, 1, 3);
INSERT INTO `user_role` VALUES (6, 12, 4);

-- ----------------------------
-- Table structure for user_teacher
-- ----------------------------
DROP TABLE IF EXISTS `user_teacher`;
CREATE TABLE `user_teacher`  (
  `utid` int(0) NOT NULL AUTO_INCREMENT,
  `tid` bigint(0) NOT NULL,
  `uid` int(0) NOT NULL,
  PRIMARY KEY (`utid`) USING BTREE,
  INDEX `fk_tid`(`tid`) USING BTREE,
  INDEX `fk_uid`(`uid`) USING BTREE,
  CONSTRAINT `fk_tid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_teacher
-- ----------------------------
INSERT INTO `user_teacher` VALUES (1, 3, 12);

SET FOREIGN_KEY_CHECKS = 1;
