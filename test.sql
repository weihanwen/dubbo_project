/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-08-17 16:19:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `smart_user`
-- ----------------------------
DROP TABLE IF EXISTS `smart_user`;
CREATE TABLE `smart_user` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `version` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of smart_user
-- ----------------------------

-- ----------------------------
-- Table structure for `smart_user_2`
-- ----------------------------
DROP TABLE IF EXISTS `smart_user_2`;
CREATE TABLE `smart_user_2` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `my_version` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of smart_user_2
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_goods`
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(100) DEFAULT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `stock_number` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES ('1', '商品1', 'http://p1.meituan.net/460.280/deal/128d216bdd335aa0b90a024302116e7f151858.jpg@460w_280h_1e_1c', '1', '6');
INSERT INTO `tb_goods` VALUES ('2', '商品2', 'http://p1.meituan.net/deal/91f427279e000e01e53d63207eebc84d173497.jpg@460w_280h_1e_1c', '7', '0');
INSERT INTO `tb_goods` VALUES ('3', '商品3', 'http://p0.meituan.net/460.280/deal/fdd87d203a41df83e36bdf554ae345b563109.jpg@460w_280h_1e_1c', '2', '0');

-- ----------------------------
-- Table structure for `tb_logger`
-- ----------------------------
DROP TABLE IF EXISTS `tb_logger`;
CREATE TABLE `tb_logger` (
  `logger_id` varchar(100) NOT NULL,
  `path` varchar(100) DEFAULT NULL,
  `requestmap` text,
  `result` text,
  `longtime` varchar(100) DEFAULT NULL COMMENT '单位秒',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`logger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_logger
-- ----------------------------
INSERT INTO `tb_logger` VALUES ('1502785288791', 'http://localhost:8080/dubbo_project/updateStock.do', '{\"ss\":[\"1\"],\"dd\":[\"2\"]}', '1', '102', '2017-08-15 16:13:12');
INSERT INTO `tb_logger` VALUES ('1502785573393', 'http://localhost:8080/dubbo_project/updateStock.do', '{\"ss\":[\"1\"],\"dd\":[\"2\"]}', 'public synchronized java.lang.Integer com.jiuyuvip.controller.UserController.updateStock(java.lang.Integer,java.lang.Integer)', '3', '2017-08-15 16:10:40');
INSERT INTO `tb_logger` VALUES ('1502785748356', 'http://localhost:8080/dubbo_project/updateStock.do', '{}', '1', '99', '2017-08-15 16:16:02');
INSERT INTO `tb_logger` VALUES ('1502786073393', 'http://localhost:8080/dubbo_project/updateStock.do', '{}', '1', '100', '2017-08-15 16:27:03');
INSERT INTO `tb_logger` VALUES ('1502786562043', 'http://localhost:8080/dubbo_project/updateStock.do', '{}', '1', '7', '2017-08-15 16:29:23');
INSERT INTO `tb_logger` VALUES ('1502786799121', 'http://localhost:8080/dubbo_project/updateStock.do', '{}', '1', '147', '2017-08-15 16:37:27');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` char(30) NOT NULL,
  `USER_PASSWORD` char(10) NOT NULL,
  `USER_EMAIL` char(30) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `IDX_NAME` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('10', '1', '11', '1');
