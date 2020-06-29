/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : vlog

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-06-29 12:28:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for subscribe
-- ----------------------------
DROP TABLE IF EXISTS `subscribe`;
CREATE TABLE `subscribe` (
  `id` char(19) NOT NULL COMMENT '关注id',
  `subscriber_id` char(19) DEFAULT NULL COMMENT '订阅者id',
  `subscribe_id` char(19) DEFAULT NULL COMMENT '被订阅者id',
  `gmt_create` datetime NOT NULL COMMENT '关注时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subscribe
-- ----------------------------
INSERT INTO `subscribe` VALUES ('1276187462724595714', '1273857412729692161', '1273855936364044290', '2020-06-26 00:16:16', '2020-06-26 00:16:16', '0', '0');
INSERT INTO `subscribe` VALUES ('1276858010899103746', '1273857412729692161', '1276857788387082242', '2020-06-27 20:40:47', '2020-06-27 20:40:47', '0', '0');
