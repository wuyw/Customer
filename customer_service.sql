/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : customer_service

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 01/04/2018 23:27:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for associated_question_relation
-- ----------------------------
DROP TABLE IF EXISTS `associated_question_relation`;
CREATE TABLE `associated_question_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `standard_question_id` int(11) NULL DEFAULT NULL COMMENT '问题ID',
  `associated_question_id` int(11) NULL DEFAULT NULL COMMENT '被关联问题ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '企业账号ID',
  `company_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号邮箱',
  `linkman` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业联系人',
  `company_domain` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业域名',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES (1, '杭州尔胡杨有限公司', NULL, 'admin', 'wxjhh', 'sluan', '17816869667', '53780@qq.com', NULL);
INSERT INTO `company_info` VALUES (4, '奇异', NULL, '131@163.com', 'sss', 'wxj', '17816869670', '1310877276@qq.com', '2018-03-30 11:18:53');

-- ----------------------------
-- Table structure for conversation
-- ----------------------------
DROP TABLE IF EXISTS `conversation`;
CREATE TABLE `conversation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '会话ID',
  `send` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送者',
  `receive` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收者',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for knowledge_point
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_point`;
CREATE TABLE `knowledge_point`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '知识点ID',
  `company_id` int(11) NULL DEFAULT NULL COMMENT '所属企业ID',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识点标题',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否永久有效（0否，1是）',
  `start_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '结束时间',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识点内容',
  `is_del` int(2) NULL DEFAULT NULL COMMENT '是否删除',
  `edit_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作日志ID',
  `operator_id` int(11) NULL DEFAULT NULL COMMENT '操作人ID',
  `action` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动作',
  `time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_statistics
-- ----------------------------
DROP TABLE IF EXISTS `question_statistics`;
CREATE TABLE `question_statistics`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题统计ID',
  `question_id` int(11) NULL DEFAULT NULL COMMENT '问题ID',
  `good_num` int(11) NULL DEFAULT NULL COMMENT '好评数',
  `bad_num` int(11) NULL DEFAULT NULL COMMENT '差评数',
  `solved_num` int(11) NULL DEFAULT NULL COMMENT '解决数',
  `matched_num` int(11) NULL DEFAULT NULL COMMENT '匹配数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_study
-- ----------------------------
DROP TABLE IF EXISTS `question_study`;
CREATE TABLE `question_study`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题学习ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题标题',
  `ask_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '提问日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for similar_question_relation
-- ----------------------------
DROP TABLE IF EXISTS `similar_question_relation`;
CREATE TABLE `similar_question_relation`  (
  `id` int(11) NOT NULL COMMENT '相似问题ID',
  `standrad_question_id` int(11) NULL DEFAULT NULL COMMENT '标准问题ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相似问题标题',
  `similar_question_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for standard_question
-- ----------------------------
DROP TABLE IF EXISTS `standard_question`;
CREATE TABLE `standard_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题ID',
  `knowledge_point_id` int(11) NULL DEFAULT NULL COMMENT '知识点ID',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `start_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '结束时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态（0失效，1有效）',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题标题',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题答案',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客服人员ID',
  `company_id` int(11) NULL DEFAULT NULL,
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色(1超级管理员，2普通客服，3工单客服)',
  `max_reception` int(8) NULL DEFAULT NULL COMMENT '最大接待量',
  `is_del` int(2) NULL DEFAULT NULL COMMENT '是否删除（0否，1是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 1, 'admin', '王小建', 'xjzz', '17816869670', 'ww2@163.com', 'admin', '1', 100, 0);
INSERT INTO `user_info` VALUES (2, 1, 'wxj', '张三', '小三', '3546789', '5555@455.com', '123456', '2', 10, 0);

SET FOREIGN_KEY_CHECKS = 1;
