/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : yj_vivo50

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 01/03/2023 21:59:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2034 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 'M', '0', '0', '', 'system', '系统管理目录', '0');
INSERT INTO `sys_menu` VALUES (100, '项目管理', 1, 1, 'project', 'system/project/index', 1, 'C', '0', '0', 'system:project:list', 'project', '项目管理菜单', '0');
INSERT INTO `sys_menu` VALUES (1001, '项目审核', 100, 1, '', NULL, 1, 'F', '0', '0', 'system:project:examine', '#', '', '0');
INSERT INTO `sys_menu` VALUES (1002, '项目删除', 100, 2, '', NULL, 1, 'F', '0', '0', 'system:project:delete', '#', '', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` int NULL DEFAULT 0 COMMENT 'del_flag',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'rootAdmin', 'root', '0', 0, '超级管理员 拥有所有权限');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `menu_id` bigint NOT NULL DEFAULT 0 COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '2' COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'http://rqdm2tx3d.hn-bkt.clouddn.com/headerimage.jpg' COMMENT '头像',
  `points` int NULL DEFAULT 0 COMMENT '用户积分',
  `cumulative_points` int NULL DEFAULT 0 COMMENT '用户累积积分',
  `last_sign_in_date` datetime NULL DEFAULT NULL COMMENT '用户最后一次签到的日期',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'zzz111', '$2a$10$JXpvQLypAc7WCE6EHSIJoekaW8GVAWXZgRO8BSCmXnUIfUI3aOwiy', '0', '23412332@qq.com', '12345678910', '1', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/02/20/ea9b45a30d8348f0b6f10e0c35b83461.png', 123, 1, '2023-02-26 00:15:59', 0);
INSERT INTO `sys_user` VALUES (2, 'text01', 'mmm', '$2a$10$EMNg6vKEQyY2JZ8g/da4ce.zsLSblGN7suV0NUYqcjXeKhOz6G3vO', '0', 'a@a.com', '13242344455', '0', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/2292dc0906a9411a92fe4e9a65cd1a6b.jpg', 9, 0, NULL, 0);
INSERT INTO `sys_user` VALUES (3, 'test', '普通用户', '$2a$10$JXpvQLypAc7WCE6EHSIJoekaW8GVAWXZgRO8BSCmXnUIfUI3aOwiy', '0', '1234@zzz.com', '12345678911', '1', 'http://rqdm2tx3d.hn-bkt.clouddn.com/headerimage.jpg', 123, 1, '2023-02-26 00:15:59', 0);
INSERT INTO `sys_user` VALUES (5, 'ddd', 'umk', '$2a$10$TzmEo/eHAsyTND8LKEIwE.ohYjLqpgQzHCGkfxmFZS6mcUwXbPF2u', '0', '123@c.com', '13009878923', '2', 'http://rqdm2tx3d.hn-bkt.clouddn.com/headerimage.jpg', 10, 0, NULL, 0);
INSERT INTO `sys_user` VALUES (6, 'text02', 'weww', '$2a$10$BKcqpXlZDfnAb8A8Q9dkfezh1awPD2i2SQ4aRmpI/vtOVr1kMTSR2', '0', 'aew@qq.com', '13400999900', '2', 'http://rqdm2tx3d.hn-bkt.clouddn.com/headerimage.jpg', 0, 0, NULL, 0);
INSERT INTO `sys_user` VALUES (7, 'text', 'hase', '$2a$10$J4ITfknk9f0hKvAcFQQ7LexUykxAldUAvwDaEJEEnGR1vvvT2mIuC', '0', '1234@qq.com', '13600000089', '0', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/7e2ece37bd3748f78b331be30d68b35b.jpg', 10, 0, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint NOT NULL DEFAULT 0 COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1);

-- ----------------------------
-- Table structure for yj_project
-- ----------------------------
DROP TABLE IF EXISTS `yj_project`;
CREATE TABLE `yj_project`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '众筹项目id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '描述',
  `pictures` json NULL COMMENT '证明图片(使用oss存储)',
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目简介',
  `thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缩略图(使用oss存储)',
  `current` bigint NULL DEFAULT 0 COMMENT '当前进度',
  `target` bigint NULL DEFAULT NULL COMMENT '众筹目标',
  `is_top` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否被置顶 (0否, 1是)',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '状态 (0已发布, 1草稿, 2正在审核)',
  `view_count` bigint NULL DEFAULT 0 COMMENT '浏览量',
  `is_comment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '是否允许评论 (1是, 0否)',
  `create_by` bigint NULL DEFAULT NULL COMMENT '发布人id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `examine_by` bigint NULL DEFAULT NULL COMMENT '审核人id',
  `examine_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志(0未删除, 1已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yj_project
-- ----------------------------
INSERT INTO `yj_project` VALUES (1, 'vivo50_1', '今天是肯德基疯狂星期四！转发这条消息到10个群，就有机会获得肯德基全家桶！我已经试过了，真的有用！不信你试试看！', '{\"pictures\": [\"http://rqdm2tx3d.hn-bkt.clouddn.com/2023/02/20/ea9b45a30d8348f0b6f10e0c35b83461.png\", \"http://rqdm2tx3d.hn-bkt.clouddn.com/2023/02/28/2f3696a62b4e4947a64fca6981865c47.jpg\", \"http://rqdm2tx3d.hn-bkt.clouddn.com/2023/02/20/1630320747669.jpg\"]}', '转发到10个群', 'http://rqdm2tx3d.hn-bkt.clouddn.com/headerimage.jpg', 1, 100, '0', '0', 10, '', 1, '2023-02-28 23:10:48', NULL, NULL, '0');
INSERT INTO `yj_project` VALUES (2, 'vivo50_2', '今天是肯德基疯狂星期四！你知道吗？每年有多少只鸡被吃掉？答案是：太多了！所以，为了拯救鸡的生命，我们应该多吃肯德基！转我100元，我帮你下单一个超值套餐！以上文案来自newbing, 微软全责XD', '{\"pictures\": [\"http://rqdm2tx3d.hn-bkt.clouddn.com/2023/02/20/ea9b45a30d8348f0b6f10e0c35b83461.png\", \"http://rqdm2tx3d.hn-bkt.clouddn.com/2023/02/28/2f3696a62b4e4947a64fca6981865c47.jpg\"]}', '为了拯救鸡的生命', 'http://rqdm2tx3d.hn-bkt.clouddn.com/headerimage.jpg', 0, 150, '0', '0', 7, '', 1, '2023-02-28 23:30:58', NULL, NULL, '0');
INSERT INTO `yj_project` VALUES (3, 'vivo50_3', '今天是肯德基疯狂星期四！你听说过那个传说吗？据说在每个月的第一个星期四，如果你在肯德基点了15块香辣热骨鸡，就会遇到真爱。不信？试试看吧！v我50，我们一起去验证这个传说吧！以上文案来自newbing, 微软全责XD', '{\"pictures\": [\"http://rqdm2tx3d.hn-bkt.clouddn.com/2023/02/20/ea9b45a30d8348f0b6f10e0c35b83461.png\"]}', '传说', 'http://rqdm2tx3d.hn-bkt.clouddn.com/headerimage.jpg', 1, 250, '0', '0', 1010, '', 1, '2023-02-28 23:40:29', NULL, NULL, '0');
INSERT INTO `yj_project` VALUES (4, 'dfsdvs', 'vd', NULL, 'fv', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/02/28/a447e1d744924cfa98e1741addbd4295.jpg', 0, 12, '0', '1', 0, '0', 2, '2023-02-28 23:48:49', NULL, NULL, '1');
INSERT INTO `yj_project` VALUES (5, '测试_草稿', '测试_草稿', '{\"pictures\": [\"http://rqdm2tx3d.hn-bkt.clouddn.com/2023/02/20/ea9b45a30d8348f0b6f10e0c35b83461.png\"]}', '测试_草稿', 'http://rqdm2tx3d.hn-bkt.clouddn.com/headerimage.jpg', 0, 999, '0', '1', 0, '', 1, '2023-03-01 00:08:55', NULL, NULL, '0');
INSERT INTO `yj_project` VALUES (6, 'dsvdf', 'dfvdfsvdv', NULL, 'fdsvfdsv', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/a569f01b743b4b5fbf5f8d30b38dc5be.jpg', 0, 1, '0', '1', 0, '1', 2, '2023-03-01 18:40:01', NULL, NULL, '0');
INSERT INTO `yj_project` VALUES (7, '的测试脚本', ' 长时间课程的女生借款', NULL, '的上传接口', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/fa8e0bd0c05b4a59b2a9c5cf20895023.jpg', 0, 12, '0', '1', 0, '1', 2, '2023-03-01 19:09:43', NULL, NULL, '0');
INSERT INTO `yj_project` VALUES (8, '曾经是你尽快', '才能见到萨克参加', NULL, '的接触的手机号', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/37933907de9343b88115208360b58cb4.jpg', 10, 90, '0', '0', 10, '1', 2, '2023-03-01 19:11:09', 1, '2023-03-01 20:46:26', '0');
INSERT INTO `yj_project` VALUES (9, '年级', '初始登记', NULL, '但是绝不会就此', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/33450891ef6a43d991da415322be4a4a.jpg', 0, 33, '0', '1', 0, '1', 6, '2023-03-01 20:50:18', NULL, NULL, '0');
INSERT INTO `yj_project` VALUES (10, '参加答辩成绩', '初始登记2', NULL, '出道不久速度比较快', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/33450891ef6a43d991da415322be4a4a.jpg', 0, 33, '0', '1', 0, '1', 6, '2023-03-01 20:50:37', NULL, NULL, '0');
INSERT INTO `yj_project` VALUES (11, '额外服务·1', '炽道', NULL, '威威CDC', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/358f99e18e1e40319b38008a0a9eb263.jpg', 0, 11, '0', '1', 0, '1', 6, '2023-03-01 20:51:06', NULL, NULL, '0');
INSERT INTO `yj_project` VALUES (12, '额外服务·222', '炽道', NULL, '威威CDC', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/358f99e18e1e40319b38008a0a9eb263.jpg', 0, 11, '0', '1', 0, '1', 6, '2023-03-01 20:51:11', NULL, NULL, '0');
INSERT INTO `yj_project` VALUES (13, '那绝对是可能就', '参加答辩环节', NULL, '从社牛dsc', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/9f045e4ac98e42198efa7d3ef9419eec.jpg', 0, 13, '0', '2', 0, '0', 7, '2023-03-01 21:18:39', NULL, NULL, '1');
INSERT INTO `yj_project` VALUES (14, '那绝对是可能就12', '参加答辩环节的我', NULL, '从社牛参数', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/9f045e4ac98e42198efa7d3ef9419eec.jpg', 0, 132, '0', '1', 0, '0', 7, '2023-03-01 21:18:54', 1, '2023-03-01 21:32:27', '1');
INSERT INTO `yj_project` VALUES (15, '稳定dewe', '都能参加快男数据库', NULL, '花很多时间ewwed', 'http://rqdm2tx3d.hn-bkt.clouddn.com/2023/03/01/9f045e4ac98e42198efa7d3ef9419eec.jpg', 0, 120, '0', '0', 0, '1', 7, '2023-03-01 21:19:18', 1, '2023-03-01 21:32:16', '1');

SET FOREIGN_KEY_CHECKS = 1;
