/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : example

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 19/08/2023 16:46:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for following_group
-- ----------------------------
DROP TABLE IF EXISTS `following_group`;
CREATE TABLE `following_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NULL DEFAULT NULL,
  `groupCode` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分组类型: 0-默认关注, 1-特别关注, 2-用户自定义关注',
  `groupName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of following_group
-- ----------------------------
INSERT INTO `following_group` VALUES (1, NULL, '0', '默认关注', '2023-08-18 09:32:42');
INSERT INTO `following_group` VALUES (2, NULL, '1', '特别关注', '2023-08-18 09:32:57');
INSERT INTO `following_group` VALUES (3, 1, '2', '陈江的关注', '2023-08-19 14:51:01');

-- ----------------------------
-- Table structure for following_user
-- ----------------------------
DROP TABLE IF EXISTS `following_user`;
CREATE TABLE `following_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL COMMENT '用户id',
  `followingId` bigint(20) NULL DEFAULT NULL COMMENT '关注的用户的id',
  `groupId` bigint(20) NOT NULL COMMENT '关注分组的id (将关注的用户放入哪个分组)',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of following_user
-- ----------------------------
INSERT INTO `following_user` VALUES (1, 1, 2, 1, '2023-08-18 09:35:47');
INSERT INTO `following_user` VALUES (2, 1, 3, 2, '2023-08-18 09:35:59');
INSERT INTO `following_user` VALUES (3, 4, 2, 1, '2023-08-18 11:33:14');
INSERT INTO `following_user` VALUES (4, 4, 1, 1, '2023-08-19 16:24:05');
INSERT INTO `following_user` VALUES (5, 5, 1, 2, '2023-08-19 16:24:18');
INSERT INTO `following_user` VALUES (6, 1, 4, 3, '2023-08-19 16:40:39');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '陈江', '2023-08-18 09:35:20');
INSERT INTO `user` VALUES (2, '张三', '2023-08-18 09:35:33');
INSERT INTO `user` VALUES (3, '李四', '2023-08-18 09:35:36');
INSERT INTO `user` VALUES (4, '王五', '2023-08-18 11:32:59');
INSERT INTO `user` VALUES (5, '赵六', '2023-08-19 16:23:56');

SET FOREIGN_KEY_CHECKS = 1;
