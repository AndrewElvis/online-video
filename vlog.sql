/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : vlog

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 29/06/2020 19:09:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别ID',
  `title` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名称',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subscribe
-- ----------------------------
DROP TABLE IF EXISTS `subscribe`;
CREATE TABLE `subscribe`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关注id',
  `subscriber_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订阅者id',
  `subscribe_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被订阅者id',
  `gmt_create` datetime(0) NOT NULL COMMENT '关注时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `is_disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subscribe
-- ----------------------------
INSERT INTO `subscribe` VALUES ('1276187462724595714', '1273857412729692122', '1273855936364044290', '2020-06-26 00:16:16', '2020-06-26 00:16:16', 0, 0);
INSERT INTO `subscribe` VALUES ('1276858010899103746', '1273857412729692122', '1273857412729692161', '2020-06-27 20:40:47', '2020-06-27 20:40:47', 0, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `openid` char(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(2) NULL DEFAULT 0 COMMENT '性别 0 未知 ,1 女，2 男 ，',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `is_disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `fans_num` int(255) NULL DEFAULT 0,
  `subscribe_num` int(255) NULL DEFAULT 0,
  `is_admin` tinyint(1) NOT NULL DEFAULT 0 COMMENT '普通用户 0, 管理员 1, 默认 0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1273855936364044290', NULL, 'Eric', '123456', 'https://aliyun-oss-xyt.oss-cn-hangzhou.aliyuncs.com/2020/06/28/31e4d24eb7d547a480b5f56cce3c6a21Eric.jpeg', 'Wskr', 2, '13819178853', '2020-06-19 13:51:37', '2020-06-19 13:51:37', '2020-06-19 13:51:37', 0, 0, 1, 0, 1);
INSERT INTO `user` VALUES ('1273857412729692122', NULL, 'xyt', '123456', 'https://aliyun-oss-xyt.oss-cn-hangzhou.aliyuncs.com/1.JPG', 'Andrew', 0, '13819178853', '2020-06-19 13:51:37', '2020-06-19 13:57:29', '2020-06-19 13:57:29', 0, 0, 0, 2, 1);
INSERT INTO `user` VALUES ('1273857412729692161', NULL, 'Andrew', '123456', 'https://aliyun-oss-xyt.oss-cn-hangzhou.aliyuncs.com/2020/06/26/bcd295b7d1784221affe6c756442aa4efile.png', 'Andrew', 2, '13819178853', '2020-06-19 13:51:37', '2020-06-19 13:57:29', '2020-06-20 23:36:09', 0, 0, 1, 0, 1);
INSERT INTO `user` VALUES ('1274314622702252034', NULL, 'Elvis', '123456', 'https://aliyun-oss-xyt.oss-cn-hangzhou.aliyuncs.com/2020/06/20/4adf47f76bb644008f39288b70219333file.png', 'Elvis', 2, '18969932111', '2020-06-19 13:51:37', '2020-06-20 20:14:16', '2020-06-26 14:32:12', 0, 0, 0, 0, 0);
INSERT INTO `user` VALUES ('1277481554712567810', NULL, 'test', NULL, 'https://aliyun-oss-xyt.oss-cn-hangzhou.aliyuncs.com/2020/06/29/eda0991616d4440897bcc145c65a8102file.png', 'test', 2, '111', '2020-06-09 00:00:00', '2020-06-29 13:58:32', '2020-06-29 13:58:32', 1, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `category_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '分类类别ID',
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '上传用户ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频标题',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频封面',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频简介',
  `source_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '云端视频资源',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原始文件名称',
  `count` bigint(20) NOT NULL DEFAULT 0 COMMENT '播放次数',
  `likes` bigint(20) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `duration` float NOT NULL DEFAULT 0 COMMENT '视频时长(秒)',
  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频标签',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Normal' COMMENT 'Empty未上传 Transcoding转码中 Normal正常',
  `size` bigint(20) NOT NULL DEFAULT 0 COMMENT '视频原文件大小',
  `version` bigint(20) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('1', '0', '1273857412729692161', '测试视频', 'https://aliyun-oss-xyt.oss-cn-hangzhou.aliyuncs.com/2020/06/26/36a7fc5d120d4001b4723bd7d7ca232efile.png', '测试', '3554461d4f474183b80c326833da786b', '6 - What If I Want to Move Faster.mp4', 110, 11, 16, 'edu', 'Normal', 0, 1, '2020-06-26 14:32:12', '2020-06-26 23:06:25');
INSERT INTO `video` VALUES ('1276533512488361986', '0', '1273857412729692122', '测试视频2', 'https://aliyun-oss-xyt.oss-cn-hangzhou.aliyuncs.com/2020/06/26/761defcf18ad4e889c4f8bcf91abcd94file.png', '测试', '7060907ae68346afac2761d3db9cd312', '6 - What If I Want to Move Faster.mp4', 87, 8, 16, NULL, 'Normal', 0, 1, '2020-06-26 23:11:21', '2020-06-26 23:11:21');
INSERT INTO `video` VALUES ('1276746200560721922', '0', '1273857412729692161', '测试视频3', 'https://aliyun-oss-xyt.oss-cn-hangzhou.aliyuncs.com/2020/06/27/06116164695941a1b27fdd3123009730file.png', '测试', '103e7a62b25f47b3893b9635f8812bd8', '6 - What If I Want to Move Faster.mp4', 45, 4, 16, '美食&美景', 'Normal', 0, 1, '2020-06-27 13:16:30', '2020-06-27 13:16:30');
INSERT INTO `video` VALUES ('1276764955349151746', '0', '1273857412729692161', '测试视频4', 'https://aliyun-oss-xyt.oss-cn-hangzhou.aliyuncs.com/2020/06/27/2362b9d75e4e4403b71034e1cd68f1e2file.png', '测试', '903892f8f95d4a6097ad20d5b8281d71', '6 - What If I Want to Move Faster.mp4', 231, 23, 16, NULL, 'Normal', 0, 1, '2020-06-27 14:31:01', '2020-06-27 14:31:01');
INSERT INTO `video` VALUES ('1276765549589753857', '0', '1274314622702252034', '测试视频5', 'https://aliyun-oss-xyt.oss-cn-hangzhou.aliyuncs.com/2020/06/26/36a7fc5d120d4001b4723bd7d7ca232efile.png', '测试', '6ccc58b1fcc749cdab98e056a11e5999', '6 - What If I Want to Move Faster.mp4', 23, 2, 16, NULL, 'Normal', 0, 1, '2020-06-27 14:33:23', '2020-06-27 14:33:23');
INSERT INTO `video` VALUES ('1276903404760731649', '0', '1274314622702252034', '测试视频6', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '测试', '726f5e6be533473b9bd8119815c8459b', '6 - What If I Want to Move Faster.mp4', 98, 9, 16, 'food', 'Normal', 0, 1, '2020-06-27 23:41:10', '2020-06-27 23:41:10');
INSERT INTO `video` VALUES ('1276906077119582209', '0', '1274314622702252034', '测试视频7', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '测试', '25c4388f81494b13a70024122a5d24a5', '6 - What If I Want to Move Faster.mp4', 51, 23, 16, 'nature', 'Normal', 0, 1, '2020-06-27 23:51:47', '2020-06-27 23:51:47');
INSERT INTO `video` VALUES ('1277473731886817281', '0', '1273857412729692122', '测试视频8', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '测试', 'd1a61c3f039a4788a6ba01e2e6700fc4', '6 - What If I Want to Move Faster.mp4', 0, 0, 0, 'paper', 'Normal', 0, 1, '2020-06-29 13:27:27', '2020-06-29 13:27:27');

SET FOREIGN_KEY_CHECKS = 1;
