/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : customer_service

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-03-23 09:35:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for associated_question_relation
-- ----------------------------
DROP TABLE IF EXISTS `associated_question_relation`;
CREATE TABLE `associated_question_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `standard_question_id` int(11) DEFAULT NULL COMMENT '问题ID',
  `associated_question_id` int(11) DEFAULT NULL COMMENT '被关联问题ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of associated_question_relation
-- ----------------------------

-- ----------------------------
-- Table structure for conversation
-- ----------------------------
DROP TABLE IF EXISTS `conversation`;
CREATE TABLE `conversation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '会话ID',
  `send` varchar(32) DEFAULT NULL COMMENT '发送者',
  `receive` varchar(32) DEFAULT NULL COMMENT '接收者',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of conversation
-- ----------------------------

-- ----------------------------
-- Table structure for customer_service_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_service_info`;
CREATE TABLE `customer_service_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客服人员ID',
  `company_id` int(11) DEFAULT NULL,
  `account` varchar(32) DEFAULT NULL COMMENT '登录账号',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `role` varchar(16) DEFAULT NULL COMMENT '角色(1超级管理员，2普通客服，3工单客服)',
  `max_reception` int(8) DEFAULT NULL COMMENT '最大接待量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_service_info
-- ----------------------------

-- ----------------------------
-- Table structure for knowledge_point
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_point`;
CREATE TABLE `knowledge_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '知识点ID',
  `title` varchar(64) DEFAULT NULL COMMENT '知识点标题',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` varchar(2) DEFAULT NULL COMMENT '状态（0失效，1有效）',
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  `content` varchar(255) DEFAULT NULL COMMENT '知识点内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of knowledge_point
-- ----------------------------

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作日志ID',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人ID',
  `action` varchar(64) DEFAULT NULL COMMENT '动作',
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for question_statistics
-- ----------------------------
DROP TABLE IF EXISTS `question_statistics`;
CREATE TABLE `question_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题统计ID',
  `question_id` int(11) DEFAULT NULL COMMENT '问题ID',
  `good_num` int(11) DEFAULT NULL COMMENT '好评数',
  `bad_num` int(11) DEFAULT NULL COMMENT '差评数',
  `solved_num` int(11) DEFAULT NULL COMMENT '解决数',
  `matched_num` int(11) DEFAULT NULL COMMENT '匹配数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for question_study
-- ----------------------------
DROP TABLE IF EXISTS `question_study`;
CREATE TABLE `question_study` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题学习ID',
  `title` varchar(255) DEFAULT NULL COMMENT '问题标题',
  `ask_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '提问日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_study
-- ----------------------------

-- ----------------------------
-- Table structure for similar_question_relation
-- ----------------------------
DROP TABLE IF EXISTS `similar_question_relation`;
CREATE TABLE `similar_question_relation` (
  `id` int(11) NOT NULL COMMENT '相似问题ID',
  `standrad_question_id` int(11) DEFAULT NULL COMMENT '标准问题ID',
  `title` varchar(255) DEFAULT NULL COMMENT '相似问题标题',
  `similar_question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of similar_question_relation
-- ----------------------------

-- ----------------------------
-- Table structure for standard_question
-- ----------------------------
DROP TABLE IF EXISTS `standard_question`;
CREATE TABLE `standard_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题ID',
  `knowledge_point_id` int(11) DEFAULT NULL COMMENT '知识点ID',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  `status` int(2) DEFAULT NULL COMMENT '状态（0失效，1有效）',
  `title` varchar(255) DEFAULT NULL COMMENT '问题标题',
  `answer` varchar(255) DEFAULT NULL COMMENT '问题答案',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of standard_question
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '企业账号ID',
  `company_name` varchar(32) NOT NULL COMMENT '企业名称',
  `account` varchar(32) NOT NULL COMMENT '账号邮箱',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `company_domain` varchar(32) NOT NULL COMMENT '企业域名',
  `mobile` varchar(11) NOT NULL,
  `email` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '杭州石峦', 'admin', 'admin', 'sluan', '15905818733', '1051156101@qq.com');
