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

 Date: 27/06/2020 22:48:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
INSERT INTO `video` VALUES ('1276764955349151746', '0', '1273857412729692161', '测试视频4', 'https://aliyun-oss-xyt.oss-cn-hangzhou.aliyuncs.com/2020/06/27/2362b9d75e4e4403b71034e1cd68f1e2file.png', '测试', NULL, NULL, 231, 23, 16, NULL, 'Normal', 0, 1, '2020-06-27 14:31:01', '2020-06-27 14:31:01');
INSERT INTO `video` VALUES ('1276765549589753857', '0', '1274314622702252034', '测试视频5', 'hangzhou.aliyuncs.com/2020/06/26/36a7fc5d120d4001b4723bd7d7ca232efile.png', '测试', '6ccc58b1fcc749cdab98e056a11e5999', '6 - What If I Want to Move Faster.mp4', 23, 2, 16, NULL, 'Normal', 0, 1, '2020-06-27 14:33:23', '2020-06-27 14:33:23');

SET FOREIGN_KEY_CHECKS = 1;
