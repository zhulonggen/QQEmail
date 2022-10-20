/*
 Navicat Premium Data Transfer

 Source Server         : test01
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : qq_email

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 13/09/2022 14:34:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for receive_email
-- ----------------------------
DROP TABLE IF EXISTS `receive_email`;
CREATE TABLE `receive_email`  (
  `receive_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '接收邮箱人的id',
  `receive_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接收人的email',
  `receive_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收的标题',
  `receive_date` datetime(0) NULL DEFAULT NULL COMMENT '接收时间',
  `receive_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收发送消息',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`receive_id`) USING BTREE,
  INDEX `user_receive_email`(`user_id`) USING BTREE,
  CONSTRAINT `user_receive_email` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 159 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of receive_email
-- ----------------------------
INSERT INTO `receive_email` VALUES (155, '3386683736@qq.com', '小朱真帅', '2022-09-05 19:55:25', '噜啦噜啦嘞绿', 4);
INSERT INTO `receive_email` VALUES (156, '3386683736@qq.com', '小朱真帅', '2022-09-05 19:55:35', '噜啦噜啦嘞绿', 4);
INSERT INTO `receive_email` VALUES (157, 'oracle-acct_ww@oracle.com', '您的 Oracle 帐户 - 确认您的电子邮件地址', '2022-09-11 10:43:39', NULL, 4);
INSERT INTO `receive_email` VALUES (158, 'oracle-acct_ww@oracle.com', '您的 Oracle 帐户 - 确认您的电子邮件地址', '2022-09-11 10:44:27', NULL, 4);

-- ----------------------------
-- Table structure for send_email
-- ----------------------------
DROP TABLE IF EXISTS `send_email`;
CREATE TABLE `send_email`  (
  `send_id` tinyint(11) NOT NULL AUTO_INCREMENT COMMENT '发送邮箱人的id',
  `send_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发送人的email',
  `send_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `send_date` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `send_content` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '发送内容',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`send_id`) USING BTREE,
  INDEX `user_send_email`(`user_id`) USING BTREE,
  CONSTRAINT `user_send_email` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of send_email
-- ----------------------------
INSERT INTO `send_email` VALUES (5, '2770085055@qq.com', '你好', '2022-09-01 11:16:34', '你好妮妮', 4);
INSERT INTO `send_email` VALUES (6, '2770085055@qq.com', 'hjb', '2022-09-01 19:14:50', 'vhj ', 4);
INSERT INTO `send_email` VALUES (7, '2940426826@qq.com', '你真帅', '2022-09-05 09:46:16', '123', 6);
INSERT INTO `send_email` VALUES (8, '2940426826@qq.com', '你真帅', '2022-09-05 09:46:37', '123', 6);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户qq邮箱',
  `user_pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户邮箱密码',
  `user_treePwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱的第三方，密码',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_id`(`user_id`, `user_treePwd`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (4, '2770085055@qq.com', '123456', 'blfkoldwahspddhj');
INSERT INTO `user` VALUES (5, '3345227890@qq.com', '111111', NULL);
INSERT INTO `user` VALUES (6, '2940426826@qq.com', '123123', 'avmqtmwdyukudehg');
INSERT INTO `user` VALUES (7, '3386683736@qq.com', '123123', NULL);

SET FOREIGN_KEY_CHECKS = 1;
