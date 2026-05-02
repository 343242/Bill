/*
 Navicat MySQL Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80404 (8.4.4)
 Source Host           : localhost:3306
 Source Schema         : springbooto2ehg

 Target Server Type    : MySQL
 Target Server Version : 80404 (8.4.4)
 File Encoding         : 65001

 Date: 28/04/2026 13:45:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for baomingxinxi
-- ----------------------------
DROP TABLE IF EXISTS `baomingxinxi`;
CREATE TABLE `baomingxinxi`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaxiaomingcheng` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '驾校名称',
  `jiaxiaoleixing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '驾校类型',
  `baomingfeiyong` float NULL DEFAULT NULL COMMENT '报名费用',
  `baomingshijian` datetime NULL DEFAULT NULL COMMENT '报名时间',
  `baomingshuoming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '报名说明',
  `zhanghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `shenfenzhenghaoma` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `baokaochexing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '报考车型',
  `jingbanren` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '经办人',
  `sfsh` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '否' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '审核回复',
  `ispay` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '未支付' COMMENT '是否支付',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '报名信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of baomingxinxi
-- ----------------------------
INSERT INTO `baomingxinxi` VALUES (41, '2022-04-20 00:18:12', '南京市雨花驾校', '1', 1, '2022-04-20 00:18:12', '报名说明1', 'zhangsan001', '张三', '320101199001010011', 'C1', '管理员', '是', '', '未支付');
INSERT INTO `baomingxinxi` VALUES (42, '2022-04-20 00:18:12', '驾校名称2', '驾校类型2', 2, '2022-04-20 00:18:12', '报名说明2', '账号2', '姓名2', '身份证号2', 'C2', '经办人2', '是', '', '未支付');
INSERT INTO `baomingxinxi` VALUES (43, '2022-04-20 00:18:12', '驾校名称3', '驾校类型3', 3, '2022-04-20 00:18:12', '报名说明3', '账号3', '姓名3', '身份证号3', 'C1', '经办人3', '是', '', '未支付');
INSERT INTO `baomingxinxi` VALUES (44, '2022-04-20 00:18:12', '驾校名称4', '驾校类型4', 4, '2022-04-20 00:18:12', '报名说明4', '账号4', '姓名4', '身份证号4', 'C2', '经办人4', '是', '', '未支付');
INSERT INTO `baomingxinxi` VALUES (45, '2022-04-20 00:18:12', '驾校名称5', '驾校类型5', 5, '2022-04-20 00:18:12', '报名说明5', '账号5', '姓名5', '身份证号5', 'C1', '经办人5', '是', '', '未支付');
INSERT INTO `baomingxinxi` VALUES (46, '2022-04-20 00:18:12', '驾校名称6', '驾校类型6', 6, '2022-04-20 00:18:12', '报名说明6', '账号6', '姓名6', '身份证号6', 'C2', '经办人6', '是', '', '未支付');

-- ----------------------------
-- Table structure for caiwuxinxi
-- ----------------------------
DROP TABLE IF EXISTS `caiwuxinxi`;
CREATE TABLE `caiwuxinxi`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhangdanmingcheng` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账单名称',
  `zhangdanleixing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账单类型',
  `zhangdanjine` float NULL DEFAULT NULL COMMENT '账单金额',
  `zhangdanmiaoshu` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '账单描述',
  `tianjiariqi` date NULL DEFAULT NULL COMMENT '添加日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 167 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '财务信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of caiwuxinxi
-- ----------------------------
INSERT INTO `caiwuxinxi` VALUES (161, '2022-04-20 00:18:13', '账单名称1', '开支', 1, '账单描述1', '2022-04-20');
INSERT INTO `caiwuxinxi` VALUES (162, '2022-04-20 00:18:13', '账单名称2', '开支', 2, '账单描述2', '2022-04-20');
INSERT INTO `caiwuxinxi` VALUES (163, '2022-04-20 00:18:13', '账单名称3', '开支', 3, '账单描述3', '2022-04-20');
INSERT INTO `caiwuxinxi` VALUES (164, '2022-04-20 00:18:13', '账单名称4', '开支', 4, '账单描述4', '2022-04-20');
INSERT INTO `caiwuxinxi` VALUES (165, '2022-04-20 00:18:13', '账单名称5', '开支', 5, '账单描述5', '2022-04-20');
INSERT INTO `caiwuxinxi` VALUES (166, '2022-04-20 00:18:13', '账单名称6', '开支', 6, '账单描述6', '2022-04-20');

-- ----------------------------
-- Table structure for cheliangxinxi
-- ----------------------------
DROP TABLE IF EXISTS `cheliangxinxi`;
CREATE TABLE `cheliangxinxi`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `chexing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '车型',
  `pinpai` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `chepaihao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `shiyongshizhang` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '使用时长',
  `weixiuxinxi` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '维修信息',
  `zhaopian` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '照片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '车辆信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cheliangxinxi
-- ----------------------------
INSERT INTO `cheliangxinxi` VALUES (51, '2022-04-20 00:18:12', '车型1', '品牌1', '车牌号1', '使用时长1', '维修信息1', 'upload/cheliangxinxi_zhaopian1.jpg');
INSERT INTO `cheliangxinxi` VALUES (52, '2022-04-20 00:18:12', '车型2', '品牌2', '车牌号2', '使用时长2', '维修信息2', 'upload/cheliangxinxi_zhaopian2.jpg');
INSERT INTO `cheliangxinxi` VALUES (53, '2022-04-20 00:18:12', '车型3', '品牌3', '车牌号3', '使用时长3', '维修信息3', 'upload/cheliangxinxi_zhaopian3.jpg');
INSERT INTO `cheliangxinxi` VALUES (54, '2022-04-20 00:18:12', '车型4', '品牌4', '车牌号4', '使用时长4', '维修信息4', 'upload/cheliangxinxi_zhaopian4.jpg');
INSERT INTO `cheliangxinxi` VALUES (55, '2022-04-20 00:18:12', '车型5', '品牌5', '车牌号5', '使用时长5', '维修信息5', 'upload/cheliangxinxi_zhaopian5.jpg');
INSERT INTO `cheliangxinxi` VALUES (56, '2022-04-20 00:18:12', '车型6', '品牌6', '车牌号6', '使用时长6', '维修信息6', 'upload/cheliangxinxi_zhaopian6.jpg');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '配置文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, 'picture1', 'upload/picture1.jpg');
INSERT INTO `config` VALUES (2, 'picture2', 'upload/picture2.jpg');
INSERT INTO `config` VALUES (3, 'picture3', 'upload/picture3.jpg');

-- ----------------------------
-- Table structure for cuotiban
-- ----------------------------
DROP TABLE IF EXISTS `cuotiban`;
CREATE TABLE `cuotiban`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `zhanghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '学员账号',
  `timuid` bigint NULL DEFAULT NULL COMMENT '题目id',
  `timuneirong` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '题目内容',
  `cuowudaan` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '错误答案',
  `zhengquedaan` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '正确答案',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '错题本' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cuotiban
-- ----------------------------

-- ----------------------------
-- Table structure for discussjiaxiaoxinxi
-- ----------------------------
DROP TABLE IF EXISTS `discussjiaxiaoxinxi`;
CREATE TABLE `discussjiaxiaoxinxi`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint NOT NULL COMMENT '关联表id',
  `userid` bigint NOT NULL COMMENT '用户id',
  `nickname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '评论内容',
  `reply` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 257 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '驾校信息评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discussjiaxiaoxinxi
-- ----------------------------
INSERT INTO `discussjiaxiaoxinxi` VALUES (251, '2022-04-20 00:18:13', 1, 1, '用户名1', '评论内容1', '回复内容1');
INSERT INTO `discussjiaxiaoxinxi` VALUES (252, '2022-04-20 00:18:13', 2, 2, '用户名2', '评论内容2', '回复内容2');
INSERT INTO `discussjiaxiaoxinxi` VALUES (253, '2022-04-20 00:18:13', 3, 3, '用户名3', '评论内容3', '回复内容3');
INSERT INTO `discussjiaxiaoxinxi` VALUES (254, '2022-04-20 00:18:13', 4, 4, '用户名4', '评论内容4', '回复内容4');
INSERT INTO `discussjiaxiaoxinxi` VALUES (255, '2022-04-20 00:18:13', 5, 5, '用户名5', '评论内容5', '回复内容5');
INSERT INTO `discussjiaxiaoxinxi` VALUES (256, '2022-04-20 00:18:13', 6, 6, '用户名6', '评论内容6', '回复内容6');

-- ----------------------------
-- Table structure for exampaper
-- ----------------------------
DROP TABLE IF EXISTS `exampaper`;
CREATE TABLE `exampaper`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '练习题库名称',
  `time` int NOT NULL COMMENT '考试时长(分钟)',
  `status` int NOT NULL DEFAULT 0 COMMENT '练习题库状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777197249931 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '练习题库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exampaper
-- ----------------------------
INSERT INTO `exampaper` VALUES (1, '2022-04-20 00:18:13', '十万个为什么', 60, 1);
INSERT INTO `exampaper` VALUES (1777197249930, '2026-04-26 17:54:09', '科目一', 100, 1);

-- ----------------------------
-- Table structure for examquestion
-- ----------------------------
DROP TABLE IF EXISTS `examquestion`;
CREATE TABLE `examquestion`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `paperid` bigint NOT NULL COMMENT '所属练习题库id（外键）',
  `papername` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '练习题库名称',
  `questionname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '试题名称',
  `options` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '选项，json字符串',
  `score` bigint NULL DEFAULT 0 COMMENT '分值',
  `answer` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '正确答案',
  `analysis` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '答案解析',
  `type` bigint NULL DEFAULT 0 COMMENT '试题类型，0：单选题 1：多选题 2：判断题 3：填空题（暂不考虑多项填空）',
  `sequence` bigint NULL DEFAULT 100 COMMENT '试题排序，值越大排越前面',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777302975525 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '试题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of examquestion
-- ----------------------------
INSERT INTO `examquestion` VALUES (1, '2022-04-20 00:18:13', 1, '十万个为什么', '下面动物不属于昆虫的是（）。', '[{\"text\":\"A.苍蝇\",\"code\":\"A\"},{\"text\":\"B.蜜蜂\",\"code\":\"B\"},{\"text\":\"C.蜂鸟\",\"code\":\"C\"}]', 20, 'C', '蜂鸟', 0, 1);
INSERT INTO `examquestion` VALUES (2, '2022-04-20 00:18:13', 1, '十万个为什么', '油着火后可以用水扑灭。', '[{\"text\":\"A.对\",\"code\":\"A\"},{\"text\":\"B.错\",\"code\":\"B\"}]', 20, 'B', '油着火后不可以用水扑灭', 2, 2);
INSERT INTO `examquestion` VALUES (3, '2022-04-20 00:18:13', 1, '十万个为什么', '地球是个球体，中间是（ ）。', '[]', 30, '赤道', '赤道', 3, 3);
INSERT INTO `examquestion` VALUES (4, '2022-04-20 00:18:13', 1, '十万个为什么', '下面动物中会流汗的有（ ）。', '[{\"text\":\"A.马\",\"code\":\"A\"},{\"text\":\"B.猫\",\"code\":\"B\"},{\"text\":\"C.狗\",\"code\":\"C\"}]', 30, 'A,B', '狗不会流汗', 1, 4);
INSERT INTO `examquestion` VALUES (1777197590577, '2026-04-26 17:59:50', 1777197249930, '科目一', '油着火后可以用水扑灭。', '[{\"code\":\"A\",\"text\":\"A.对\"},{\"code\":\"B\",\"text\":\"B.错\"}]', 20, 'B', '油着火后不可以用水扑灭', 2, 2);
INSERT INTO `examquestion` VALUES (1777197590674, '2026-04-26 17:59:50', 1777197249930, '科目一', '下面动物不属于昆虫的是（）。', '[{\"code\":\"A\",\"text\":\"A.苍蝇\"},{\"code\":\"B\",\"text\":\"B.蜜蜂\"},{\"code\":\"C\",\"text\":\"C.蜂鸟\"}]', 20, 'C', '蜂鸟属于鸟类', 0, 1);
INSERT INTO `examquestion` VALUES (1777197590869, '2026-04-26 17:59:50', 1777197249930, '科目一', '下面动物中会流汗的有（）。', '[{\"code\":\"A\",\"text\":\"A.马\"},{\"code\":\"B\",\"text\":\"B.猫\"},{\"code\":\"C\",\"text\":\"C.狗\"}]', 30, 'A,B', '狗不会流汗', 1, 3);
INSERT INTO `examquestion` VALUES (1777302975378, '2026-04-27 23:16:15', 1777197249930, '科目一', '下面动物中会流汗的有（）。', '[{\"code\":\"A\",\"text\":\"A.马\"},{\"code\":\"B\",\"text\":\"B.猫\"},{\"code\":\"C\",\"text\":\"C.狗\"}]', 30, 'A,B', '狗不会流汗', 1, 3);
INSERT INTO `examquestion` VALUES (1777302975410, '2026-04-27 23:16:15', 1777197249930, '科目一', '下面动物不属于昆虫的是（）。', '[{\"code\":\"A\",\"text\":\"A.苍蝇\"},{\"code\":\"B\",\"text\":\"B.蜜蜂\"},{\"code\":\"C\",\"text\":\"C.蜂鸟\"}]', 20, 'C', '蜂鸟属于鸟类', 0, 1);
INSERT INTO `examquestion` VALUES (1777302975524, '2026-04-27 23:16:15', 1777197249930, '科目一', '油着火后可以用水扑灭。', '[{\"code\":\"A\",\"text\":\"A.对\"},{\"code\":\"B\",\"text\":\"B.错\"}]', 20, 'B', '油着火后不可以用水扑灭', 2, 2);

-- ----------------------------
-- Table structure for examrecord
-- ----------------------------
DROP TABLE IF EXISTS `examrecord`;
CREATE TABLE `examrecord`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint NOT NULL COMMENT '用户id',
  `username` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `paperid` bigint NOT NULL COMMENT '练习题库id（外键）',
  `papername` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '练习题库名称',
  `questionid` bigint NOT NULL COMMENT '试题id（外键）',
  `questionname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '试题名称',
  `options` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '选项，json字符串',
  `score` bigint NULL DEFAULT 0 COMMENT '分值',
  `answer` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '正确答案',
  `analysis` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '答案解析',
  `myscore` bigint NOT NULL DEFAULT 0 COMMENT '试题得分',
  `myanswer` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '考生答案',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777300567309 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '考试记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of examrecord
-- ----------------------------
INSERT INTO `examrecord` VALUES (1777285640022, '2026-04-27 18:27:19', 11, '张三', 1, '十万个为什么', 1, '下面动物不属于昆虫的是（）。', '[{\"text\":\"A.苍蝇\",\"code\":\"A\"},{\"text\":\"B.蜜蜂\",\"code\":\"B\"},{\"text\":\"C.蜂鸟\",\"code\":\"C\"}]', 20, 'C', '蜂鸟', 0, 'A');
INSERT INTO `examrecord` VALUES (1777285644310, '2026-04-27 18:27:23', 11, '张三', 1, '十万个为什么', 2, '油着火后可以用水扑灭。', '[{\"text\":\"A.对\",\"code\":\"A\"},{\"text\":\"B.错\",\"code\":\"B\"}]', 20, 'B', '油着火后不可以用水扑灭', 0, 'A');
INSERT INTO `examrecord` VALUES (1777286029988, '2026-04-27 18:33:49', 11, '张三', 1, '十万个为什么', 1, '下面动物不属于昆虫的是（）。', '[{\"text\":\"A.苍蝇\",\"code\":\"A\"},{\"text\":\"B.蜜蜂\",\"code\":\"B\"},{\"text\":\"C.蜂鸟\",\"code\":\"C\"}]', 20, 'C', '蜂鸟', 0, 'A');
INSERT INTO `examrecord` VALUES (1777286033051, '2026-04-27 18:33:52', 11, '张三', 1, '十万个为什么', 2, '油着火后可以用水扑灭。', '[{\"text\":\"A.对\",\"code\":\"A\"},{\"text\":\"B.错\",\"code\":\"B\"}]', 20, 'B', '油着火后不可以用水扑灭', 20, 'B');
INSERT INTO `examrecord` VALUES (1777300561762, '2026-04-27 22:36:01', 11, '张三', 1777197249930, '科目一', 1, '下面动物不属于昆虫的是（）。', '[{\"text\":\"A.苍蝇\",\"code\":\"A\"},{\"text\":\"B.蜜蜂\",\"code\":\"B\"},{\"text\":\"C.蜂鸟\",\"code\":\"C\"}]', 20, 'C', '蜂鸟', 0, 'A');
INSERT INTO `examrecord` VALUES (1777300567308, '2026-04-27 22:36:06', 11, '张三', 1777197249930, '科目一', 2, '油着火后可以用水扑灭。', '[{\"text\":\"A.对\",\"code\":\"A\"},{\"text\":\"B.错\",\"code\":\"B\"}]', 20, 'B', '油着火后不可以用水扑灭', 20, 'B');

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '帖子标题',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '帖子内容',
  `parentid` bigint NULL DEFAULT NULL COMMENT '父节点id',
  `userid` bigint NOT NULL COMMENT '用户id',
  `username` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `isdone` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 177 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '论坛交流' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of forum
-- ----------------------------
INSERT INTO `forum` VALUES (171, '2022-04-20 00:18:13', '帖子标题1', '帖子内容1', 0, 1, '用户名1', '开放');
INSERT INTO `forum` VALUES (172, '2022-04-20 00:18:13', '帖子标题2', '帖子内容2', 0, 2, '用户名2', '开放');
INSERT INTO `forum` VALUES (173, '2022-04-20 00:18:13', '帖子标题3', '帖子内容3', 0, 3, '用户名3', '开放');
INSERT INTO `forum` VALUES (174, '2022-04-20 00:18:13', '帖子标题4', '帖子内容4', 0, 4, '用户名4', '开放');
INSERT INTO `forum` VALUES (175, '2022-04-20 00:18:13', '帖子标题5', '帖子内容5', 0, 5, '用户名5', '开放');
INSERT INTO `forum` VALUES (176, '2022-04-20 00:18:13', '帖子标题6', '帖子内容6', 0, 6, '用户名6', '开放');

-- ----------------------------
-- Table structure for genghuanjiaolian
-- ----------------------------
DROP TABLE IF EXISTS `genghuanjiaolian`;
CREATE TABLE `genghuanjiaolian`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoliangonghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练工号',
  `jiaolianxingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练姓名',
  `genghuanjiaolian` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更换教练',
  `shenqingshijian` datetime NULL DEFAULT NULL COMMENT '申请时间',
  `genghuanyuanyin` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '更换原因',
  `zhanghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sfsh` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '否' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '审核回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '更换教练' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of genghuanjiaolian
-- ----------------------------
INSERT INTO `genghuanjiaolian` VALUES (71, '2022-04-20 00:18:12', '教练工号1', '教练姓名1', '更换教练1', '2022-04-20 00:18:12', '更换原因1', '账号1', '姓名1', '是', '');
INSERT INTO `genghuanjiaolian` VALUES (72, '2022-04-20 00:18:12', '教练工号2', '教练姓名2', '更换教练2', '2022-04-20 00:18:12', '更换原因2', '账号2', '姓名2', '是', '');
INSERT INTO `genghuanjiaolian` VALUES (73, '2022-04-20 00:18:12', '教练工号3', '教练姓名3', '更换教练3', '2022-04-20 00:18:12', '更换原因3', '账号3', '姓名3', '是', '');
INSERT INTO `genghuanjiaolian` VALUES (74, '2022-04-20 00:18:12', '教练工号4', '教练姓名4', '更换教练4', '2022-04-20 00:18:12', '更换原因4', '账号4', '姓名4', '是', '');
INSERT INTO `genghuanjiaolian` VALUES (75, '2022-04-20 00:18:12', '教练工号5', '教练姓名5', '更换教练5', '2022-04-20 00:18:12', '更换原因5', '账号5', '姓名5', '是', '');
INSERT INTO `genghuanjiaolian` VALUES (76, '2022-04-20 00:18:12', '教练工号6', '教练姓名6', '更换教练6', '2022-04-20 00:18:12', '更换原因6', '账号6', '姓名6', '是', '');

-- ----------------------------
-- Table structure for jiaochexinxi
-- ----------------------------
DROP TABLE IF EXISTS `jiaochexinxi`;
CREATE TABLE `jiaochexinxi`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `pinpai` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `chexing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '车型',
  `chepaihao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `jiaocheriqi` date NULL DEFAULT NULL COMMENT '缴车日期',
  `baosunmingxi` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '报损明细',
  `jiaoliangonghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练工号',
  `jiaolianxingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练姓名',
  `sfsh` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '否' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '审核回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 147 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '缴车信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiaochexinxi
-- ----------------------------
INSERT INTO `jiaochexinxi` VALUES (141, '2022-04-20 00:18:12', '品牌1', '车型1', '车牌号1', '2022-04-20', '报损明细1', '教练工号1', '教练姓名1', '是', '');
INSERT INTO `jiaochexinxi` VALUES (142, '2022-04-20 00:18:12', '品牌2', '车型2', '车牌号2', '2022-04-20', '报损明细2', '教练工号2', '教练姓名2', '是', '');
INSERT INTO `jiaochexinxi` VALUES (143, '2022-04-20 00:18:12', '品牌3', '车型3', '车牌号3', '2022-04-20', '报损明细3', '教练工号3', '教练姓名3', '是', '');
INSERT INTO `jiaochexinxi` VALUES (144, '2022-04-20 00:18:12', '品牌4', '车型4', '车牌号4', '2022-04-20', '报损明细4', '教练工号4', '教练姓名4', '是', '');
INSERT INTO `jiaochexinxi` VALUES (145, '2022-04-20 00:18:12', '品牌5', '车型5', '车牌号5', '2022-04-20', '报损明细5', '教练工号5', '教练姓名5', '是', '');
INSERT INTO `jiaochexinxi` VALUES (146, '2022-04-20 00:18:13', '品牌6', '车型6', '车牌号6', '2022-04-20', '报损明细6', '教练工号6', '教练姓名6', '是', '');

-- ----------------------------
-- Table structure for jiaolian
-- ----------------------------
DROP TABLE IF EXISTS `jiaolian`;
CREATE TABLE `jiaolian`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoliangonghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '教练工号',
  `mima` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `jiaolianxingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '教练姓名',
  `xingbie` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '性别',
  `nianling` int NULL DEFAULT NULL COMMENT '年龄',
  `shouji` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机',
  `zhidaokemu` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '指导科目',
  `touxiang` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像',
  `gerenjianjie` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '个人简介',
  `jiaoxuetese` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '教学特色',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `jiaoliangonghao`(`jiaoliangonghao` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777302731998 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '教练' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiaolian
-- ----------------------------
INSERT INTO `jiaolian` VALUES (21, '2022-04-20 00:18:12', 'c1tom001', '123456', 'tom', '男', 1, '13823888881', '指导科目1', 'upload/jiaolian_touxiang1.jpg', '个人简介1', '教学特色1');
INSERT INTO `jiaolian` VALUES (22, '2022-04-20 00:18:12', '教练工号2', '123456', '教练姓名2', '男', 2, '13823888882', '指导科目2', 'upload/jiaolian_touxiang2.jpg', '个人简介2', '教学特色2');
INSERT INTO `jiaolian` VALUES (23, '2022-04-20 00:18:12', '教练工号3', '123456', '教练姓名3', '男', 3, '13823888883', '指导科目3', 'upload/jiaolian_touxiang3.jpg', '个人简介3', '教学特色3');
INSERT INTO `jiaolian` VALUES (24, '2022-04-20 00:18:12', '教练工号4', '123456', '教练姓名4', '男', 4, '13823888884', '指导科目4', 'upload/jiaolian_touxiang4.jpg', '个人简介4', '教学特色4');
INSERT INTO `jiaolian` VALUES (25, '2022-04-20 00:18:12', '教练工号5', '123456', '教练姓名5', '男', 5, '13823888885', '指导科目5', 'upload/jiaolian_touxiang5.jpg', '个人简介5', '教学特色5');
INSERT INTO `jiaolian` VALUES (26, '2022-04-20 00:18:12', '教练工号6', '123456', '教练姓名6', '男', 6, '13823888886', '指导科目6', 'upload/jiaolian_touxiang6.jpg', '个人简介6', '教学特色6');
INSERT INTO `jiaolian` VALUES (1777302731997, '2026-04-27 23:12:12', 'c2mike002', '123456', 'mike', NULL, 40, '13568793853', 'C2', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for jiaolianfenpei
-- ----------------------------
DROP TABLE IF EXISTS `jiaolianfenpei`;
CREATE TABLE `jiaolianfenpei`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoliangonghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练工号',
  `jiaolianxingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练姓名',
  `xingbie` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '性别',
  `nianling` int NULL DEFAULT NULL COMMENT '年龄',
  `shouji` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机',
  `zhidaokemu` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '指导科目',
  `fenpeishijian` datetime NULL DEFAULT NULL COMMENT '分配时间',
  `beizhu` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `zhanghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '教练分配' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiaolianfenpei
-- ----------------------------
INSERT INTO `jiaolianfenpei` VALUES (61, '2022-04-20 00:18:12', '教练工号1', '教练姓名1', '性别1', 1, '13823888881', '指导科目1', '2022-04-20 00:18:12', '备注1', '账号1', '姓名1');
INSERT INTO `jiaolianfenpei` VALUES (62, '2022-04-20 00:18:12', '教练工号2', '教练姓名2', '性别2', 2, '13823888882', '指导科目2', '2022-04-20 00:18:12', '备注2', '账号2', '姓名2');
INSERT INTO `jiaolianfenpei` VALUES (63, '2022-04-20 00:18:12', '教练工号3', '教练姓名3', '性别3', 3, '13823888883', '指导科目3', '2022-04-20 00:18:12', '备注3', '账号3', '姓名3');
INSERT INTO `jiaolianfenpei` VALUES (64, '2022-04-20 00:18:12', '教练工号4', '教练姓名4', '性别4', 4, '13823888884', '指导科目4', '2022-04-20 00:18:12', '备注4', '账号4', '姓名4');
INSERT INTO `jiaolianfenpei` VALUES (65, '2022-04-20 00:18:12', '教练工号5', '教练姓名5', '性别5', 5, '13823888885', '指导科目5', '2022-04-20 00:18:12', '备注5', '账号5', '姓名5');
INSERT INTO `jiaolianfenpei` VALUES (66, '2022-04-20 00:18:12', '教练工号6', '教练姓名6', '性别6', 6, '13823888886', '指导科目6', '2022-04-20 00:18:12', '备注6', '账号6', '姓名6');

-- ----------------------------
-- Table structure for jiaxiaoxinxi
-- ----------------------------
DROP TABLE IF EXISTS `jiaxiaoxinxi`;
CREATE TABLE `jiaxiaoxinxi`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaxiaomingcheng` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '驾校名称',
  `jiaxiaoleixing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '驾校类型',
  `jiaxiaodizhi` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '驾校地址',
  `baomingfeiyong` float NULL DEFAULT NULL COMMENT '报名费用',
  `jiaxiaojieshao` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '驾校介绍',
  `jiaxiaodianhua` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '驾校电话',
  `jiaxiaotupian` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '驾校图片',
  `thumbsupnum` int NULL DEFAULT 0 COMMENT '赞',
  `crazilynum` int NULL DEFAULT 0 COMMENT '踩',
  `clicktime` datetime NULL DEFAULT NULL COMMENT '最近点击时间',
  `clicknum` int NULL DEFAULT 0 COMMENT '点击次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '驾校信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiaxiaoxinxi
-- ----------------------------
INSERT INTO `jiaxiaoxinxi` VALUES (31, '2022-04-20 00:18:12', '南京市雨花驾校C1', 'C1', '驾校地址1', 1, '<p>AAAAAAAA</p>', '13823888881', 'upload/jiaxiaoxinxi_jiaxiaotupian1.jpg', 8, 3, '2026-04-28 02:06:06', 18);
INSERT INTO `jiaxiaoxinxi` VALUES (32, '2022-04-20 00:18:12', '驾校名称2', '驾校类型2', '驾校地址2', 2, '驾校介绍2', '13823888882', 'upload/jiaxiaoxinxi_jiaxiaotupian2.jpg', 2, 2, '2026-04-28 00:26:38', 3);
INSERT INTO `jiaxiaoxinxi` VALUES (33, '2022-04-20 00:18:12', '驾校名称3', '驾校类型3', '驾校地址3', 3, '驾校介绍3', '13823888883', 'upload/jiaxiaoxinxi_jiaxiaotupian3.jpg', 3, 3, '2026-04-28 00:26:43', 4);
INSERT INTO `jiaxiaoxinxi` VALUES (34, '2022-04-20 00:18:12', '驾校名称4', '驾校类型4', '驾校地址4', 4, '驾校介绍4', '13823888884', 'upload/jiaxiaoxinxi_jiaxiaotupian4.jpg', 4, 4, '2022-04-20 00:18:12', 4);
INSERT INTO `jiaxiaoxinxi` VALUES (35, '2022-04-20 00:18:12', '驾校名称5', '驾校类型5', '驾校地址5', 5, '驾校介绍5', '13823888885', 'upload/jiaxiaoxinxi_jiaxiaotupian5.jpg', 5, 5, '2022-04-20 00:18:12', 5);
INSERT INTO `jiaxiaoxinxi` VALUES (36, '2022-04-20 00:18:12', '驾校名称6', '驾校类型6', '驾校地址6', 6, '驾校介绍6', '13823888886', 'upload/jiaxiaoxinxi_jiaxiaotupian6.jpg', 6, 6, '2022-04-20 00:18:12', 6);

-- ----------------------------
-- Table structure for kaoqindaka
-- ----------------------------
DROP TABLE IF EXISTS `kaoqindaka`;
CREATE TABLE `kaoqindaka`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoliangonghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练工号',
  `jiaolianxingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练姓名',
  `dakaleixing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '打卡类型',
  `dakadidian` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '打卡地点',
  `dakashijian` datetime NULL DEFAULT NULL COMMENT '打卡时间',
  `beizhu` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sfsh` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '否' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '审核回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 127 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '考勤打卡' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kaoqindaka
-- ----------------------------
INSERT INTO `kaoqindaka` VALUES (121, '2022-04-20 00:18:12', '教练工号1', '教练姓名1', '上班', '打卡地点1', '2022-04-20 00:18:12', '备注1', '是', '');
INSERT INTO `kaoqindaka` VALUES (122, '2022-04-20 00:18:12', '教练工号2', '教练姓名2', '上班', '打卡地点2', '2022-04-20 00:18:12', '备注2', '是', '');
INSERT INTO `kaoqindaka` VALUES (123, '2022-04-20 00:18:12', '教练工号3', '教练姓名3', '上班', '打卡地点3', '2022-04-20 00:18:12', '备注3', '是', '');
INSERT INTO `kaoqindaka` VALUES (124, '2022-04-20 00:18:12', '教练工号4', '教练姓名4', '上班', '打卡地点4', '2022-04-20 00:18:12', '备注4', '是', '');
INSERT INTO `kaoqindaka` VALUES (125, '2022-04-20 00:18:12', '教练工号5', '教练姓名5', '上班', '打卡地点5', '2022-04-20 00:18:12', '备注5', '是', '');
INSERT INTO `kaoqindaka` VALUES (126, '2022-04-20 00:18:12', '教练工号6', '教练姓名6', '上班', '打卡地点6', '2022-04-20 00:18:12', '备注6', '是', '');

-- ----------------------------
-- Table structure for kaoshiyuyue
-- ----------------------------
DROP TABLE IF EXISTS `kaoshiyuyue`;
CREATE TABLE `kaoshiyuyue`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kaoshikemu` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '考试科目',
  `kaoshichangci` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '考试场次',
  `kaoshididian` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '考试地点',
  `yuyueshijian` datetime NULL DEFAULT NULL COMMENT '预约时间',
  `yuyueshuoming` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '预约说明',
  `zhanghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sfsh` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '否' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '审核回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '考试预约' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kaoshiyuyue
-- ----------------------------
INSERT INTO `kaoshiyuyue` VALUES (111, '2022-04-20 00:18:12', '考试科目1', '考试场次1', '考试地点1', '2022-04-20 00:18:12', '预约说明1', '账号1', '姓名1', '是', '');
INSERT INTO `kaoshiyuyue` VALUES (112, '2022-04-20 00:18:12', '考试科目2', '考试场次2', '考试地点2', '2022-04-20 00:18:12', '预约说明2', '账号2', '姓名2', '是', '');
INSERT INTO `kaoshiyuyue` VALUES (113, '2022-04-20 00:18:12', '考试科目3', '考试场次3', '考试地点3', '2022-04-20 00:18:12', '预约说明3', '账号3', '姓名3', '是', '');
INSERT INTO `kaoshiyuyue` VALUES (114, '2022-04-20 00:18:12', '考试科目4', '考试场次4', '考试地点4', '2022-04-20 00:18:12', '预约说明4', '账号4', '姓名4', '是', '');
INSERT INTO `kaoshiyuyue` VALUES (115, '2022-04-20 00:18:12', '考试科目5', '考试场次5', '考试地点5', '2022-04-20 00:18:12', '预约说明5', '账号5', '姓名5', '是', '');
INSERT INTO `kaoshiyuyue` VALUES (116, '2022-04-20 00:18:12', '考试科目6', '考试场次6', '考试地点6', '2022-04-20 00:18:12', '预约说明6', '账号6', '姓名6', '是', '');

-- ----------------------------
-- Table structure for kechenganpai
-- ----------------------------
DROP TABLE IF EXISTS `kechenganpai`;
CREATE TABLE `kechenganpai`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `kemuleixing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '科目类型',
  `kechengneirong` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '课程内容',
  `kechengshijian` datetime NULL DEFAULT NULL COMMENT '课程时间',
  `zhanghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `jiaoliangonghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练工号',
  `jiaolianxingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练姓名',
  `shangkezt` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '待上课' COMMENT '上课状态',
  `paiBanriqi` date NULL DEFAULT NULL COMMENT '排班日期',
  `shijianduan` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '时间段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777301510844 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '课程安排' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kechenganpai
-- ----------------------------
INSERT INTO `kechenganpai` VALUES (91, '2022-04-20 00:18:12', '练车排班-上午班 (08:00-11:23)', '科目二/三', '课程内容1', '2022-04-20 00:18:12', '账号1', '姓名1', 'c1tom001', 'tom', '待上课', '2026-04-28', '上午班 (08:00-11:23)');
INSERT INTO `kechenganpai` VALUES (92, '2022-04-20 00:18:12', '课程名称2', '科目类型2', '课程内容2', '2022-04-20 00:18:12', '账号2', '姓名2', '教练工号2', '教练姓名2', '待上课', NULL, NULL);
INSERT INTO `kechenganpai` VALUES (93, '2022-04-20 00:18:12', '课程名称3', '科目类型3', '课程内容3', '2022-04-20 00:18:12', '账号3', '姓名3', '教练工号3', '教练姓名3', '待上课', NULL, NULL);
INSERT INTO `kechenganpai` VALUES (94, '2022-04-20 00:18:12', '课程名称4', '科目类型4', '课程内容4', '2022-04-20 00:18:12', '账号4', '姓名4', '教练工号4', '教练姓名4', '待上课', NULL, NULL);
INSERT INTO `kechenganpai` VALUES (95, '2022-04-20 00:18:12', '课程名称5', '科目类型5', '课程内容5', '2022-04-20 00:18:12', '账号5', '姓名5', '教练工号5', '教练姓名5', '待上课', NULL, NULL);
INSERT INTO `kechenganpai` VALUES (96, '2022-04-20 00:18:12', '课程名称6', '科目类型6', '课程内容6', '2022-04-20 00:18:12', '账号6', '姓名6', '教练工号6', '教练姓名6', '待上课', NULL, NULL);
INSERT INTO `kechenganpai` VALUES (1777109304265, '2026-04-25 17:28:23', '练车排班-上午班', '科目二/三', NULL, NULL, NULL, NULL, '1', '教练1', '待上课', '2026-04-28', '上午班 (08:00-12:00)');
INSERT INTO `kechenganpai` VALUES (1777228814948, '2026-04-27 02:40:14', '练车排班-上午班 (08:00-11:23)', '科目二/三', NULL, NULL, NULL, NULL, 'c1tom001', 'tom', '待上课', '2026-04-30', '上午班 (08:00-11:23)');
INSERT INTO `kechenganpai` VALUES (1777301510843, '2026-04-27 22:51:50', '练车排班-下午班 (14:00-18:00)', '科目二/三', NULL, NULL, NULL, NULL, 'c1tom001', 'tom', '待上课', '2026-04-30', '下午班 (14:00-18:00)');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `menujson` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '2022-04-20 00:18:13', '[{\"backMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"学员\",\"menuJump\":\"列表\",\"tableName\":\"xueyuan\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"教练\",\"menuJump\":\"列表\",\"tableName\":\"jiaolian\"},{\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"报名须知\",\"menuJump\":\"列表\",\"tableName\":\"news\"},{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"报表\"],\"menu\":\"报名信息\",\"menuJump\":\"列表\",\"tableName\":\"baomingxinxi\"}],\"menu\":\"基础信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-settings\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"排班规则管理\",\"menuJump\":\"列表\",\"tableName\":\"paiBanGuiZe\"},{\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"排班计划管理\",\"menuJump\":\"列表\",\"tableName\":\"kechenganpai\"}],\"menu\":\"教练排班统筹管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-video\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"理论视频管理\",\"menuJump\":\"列表\",\"tableName\":\"shipin\"},{\"appFrontIcon\":\"cuIcon-message\",\"buttons\":[\"查看\",\"回复\",\"删除\"],\"menu\":\"视频评论管理\",\"menuJump\":\"列表\",\"tableName\":\"shipinpinglun\"},{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"练习题库管理\",\"menuJump\":\"列表\",\"tableName\":\"exampaper\"},{\"appFrontIcon\":\"cuIcon-taxi\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"试题管理\",\"tableName\":\"examquestion\"},{\"appFrontIcon\":\"cuIcon-attentionfavor\",\"buttons\":[\"查看\",\"删除\"],\"menu\":\"考试记录\",\"tableName\":\"examrecord\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\",\"删除\"],\"menu\":\"错题本管理\",\"tableName\":\"examfailrecord\"}],\"menu\":\"理论学习考试统筹管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"查看\",\"修改\"],\"menu\":\"预约名额配置\",\"menuJump\":\"列表\",\"tableName\":\"xiTongCanShu\"},{\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"查看\",\"删除\",\"审核\"],\"menu\":\"预约信息查询\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"},{\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"查看\",\"新增\",\"修改\",\"删除\",\"审核\"],\"menu\":\"预约取消管理\",\"menuJump\":\"列表\",\"tableName\":\"quxiaokecheng\"}],\"menu\":\"练车预约统筹管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-newshot\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"首页信息管理\",\"menuJump\":\"列表\",\"tableName\":\"shouYeXinXi\"},{\"appFrontIcon\":\"cuIcon-card\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"首页轮播图\",\"menuJump\":\"列表\",\"tableName\":\"config\"}],\"menu\":\"首页信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-settings\",\"buttons\":[\"查看\",\"修改\"],\"menu\":\"系统参数配置\",\"menuJump\":\"列表\",\"tableName\":\"xiTongCanShu\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\",\"删除\"],\"menu\":\"操作日志\",\"menuJump\":\"列表\",\"tableName\":\"riZhi\"},{\"appFrontIcon\":\"cuIcon-attentionfavor\",\"buttons\":[\"查看\",\"编辑名称\",\"编辑父级\",\"删除\"],\"menu\":\"菜单列表\",\"tableName\":\"menu\"},{\"appFrontIcon\":\"cuIcon-pic\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"资源备份\",\"tableName\":\"ziyuanbeifen\"}],\"menu\":\"系统管理\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-newshot\",\"buttons\":[\"查看\"],\"menu\":\"驾校概况\",\"menuJump\":\"列表\",\"tableName\":\"jiaxiaoxinxi\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\"],\"menu\":\"教练信息\",\"menuJump\":\"列表\",\"tableName\":\"jiaolian\"},{\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"查看\"],\"menu\":\"报名须知\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"首页信息浏览\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"管理员\",\"tableName\":\"users\"},{\"backMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-video\",\"buttons\":[\"查看\"],\"menu\":\"理论视频学习\",\"menuJump\":\"列表\",\"tableName\":\"shipin\"},{\"appFrontIcon\":\"cuIcon-favor\",\"buttons\":[\"查看\",\"删除\"],\"menu\":\"视频互动管理\",\"menuJump\":\"列表\",\"tableName\":\"storeup\"},{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\"],\"menu\":\"练习题库\",\"menuJump\":\"列表\",\"tableName\":\"exampaper\"},{\"appFrontIcon\":\"cuIcon-attentionfavor\",\"buttons\":[\"查看\"],\"menu\":\"理论考试\",\"menuJump\":\"列表\",\"tableName\":\"examrecord\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\"],\"menu\":\"错题本\",\"menuJump\":\"列表\",\"tableName\":\"examfailrecord\"}],\"menu\":\"理论学习考试\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"时段预约申请\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"},{\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"查看\"],\"menu\":\"预约记录查询\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"}],\"menu\":\"练车预约申请\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-video\",\"buttons\":[\"查看\"],\"menu\":\"理论视频学习\",\"menuJump\":\"列表\",\"tableName\":\"shipin\"},{\"appFrontIcon\":\"cuIcon-favor\",\"buttons\":[\"查看\",\"删除\"],\"menu\":\"视频互动管理\",\"menuJump\":\"列表\",\"tableName\":\"storeup\"},{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\"],\"menu\":\"练习题库\",\"menuJump\":\"列表\",\"tableName\":\"exampaper\"},{\"appFrontIcon\":\"cuIcon-attentionfavor\",\"buttons\":[\"查看\"],\"menu\":\"理论考试\",\"menuJump\":\"列表\",\"tableName\":\"examrecord\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\"],\"menu\":\"错题本\",\"menuJump\":\"列表\",\"tableName\":\"examfailrecord\"}],\"menu\":\"理论学习考试\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"时段预约申请\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"},{\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"查看\"],\"menu\":\"预约记录查询\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"}],\"menu\":\"练车预约申请\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"是\",\"hasFrontRegister\":\"是\",\"roleName\":\"学员\",\"tableName\":\"xueyuan\"},{\"backMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"查看\",\"修改\"],\"menu\":\"个人排班查询\",\"menuJump\":\"列表\",\"tableName\":\"kechenganpai\"}],\"menu\":\"教练排班查看管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\"],\"menu\":\"学员管理\",\"menuJump\":\"列表\",\"tableName\":\"xueyuan\"}],\"menu\":\"学员管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"查看\",\"审核\"],\"menu\":\"预约审核\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"},{\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"查看\"],\"menu\":\"待审核预约查询\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"},{\"appFrontIcon\":\"cuIcon-card\",\"buttons\":[\"查看\"],\"menu\":\"已确认预约列表\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"}],\"menu\":\"练车预约审核\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-newshot\",\"buttons\":[\"查看\"],\"menu\":\"驾校概况\",\"menuJump\":\"列表\",\"tableName\":\"jiaxiaoxinxi\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\"],\"menu\":\"教练信息\",\"menuJump\":\"列表\",\"tableName\":\"jiaolian\"}],\"menu\":\"首页信息浏览\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"是\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"教练\",\"tableName\":\"jiaolian\"}]');

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint NOT NULL COMMENT '留言人id',
  `username` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '留言内容',
  `cpicture` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '留言图片',
  `reply` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '回复内容',
  `rpicture` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '回复图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 237 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '留言反馈' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messages
-- ----------------------------
INSERT INTO `messages` VALUES (231, '2022-04-20 00:18:13', 1, '用户名1', '留言内容1', 'upload/messages_cpicture1.jpg', '回复内容1', 'upload/messages_rpicture1.jpg');
INSERT INTO `messages` VALUES (232, '2022-04-20 00:18:13', 2, '用户名2', '留言内容2', 'upload/messages_cpicture2.jpg', '回复内容2', 'upload/messages_rpicture2.jpg');
INSERT INTO `messages` VALUES (233, '2022-04-20 00:18:13', 3, '用户名3', '留言内容3', 'upload/messages_cpicture3.jpg', '回复内容3', 'upload/messages_rpicture3.jpg');
INSERT INTO `messages` VALUES (234, '2022-04-20 00:18:13', 4, '用户名4', '留言内容4', 'upload/messages_cpicture4.jpg', '回复内容4', 'upload/messages_rpicture4.jpg');
INSERT INTO `messages` VALUES (235, '2022-04-20 00:18:13', 5, '用户名5', '留言内容5', 'upload/messages_cpicture5.jpg', '回复内容5', 'upload/messages_rpicture5.jpg');
INSERT INTO `messages` VALUES (236, '2022-04-20 00:18:13', 6, '用户名6', '留言内容6', 'upload/messages_cpicture6.jpg', '回复内容6', 'upload/messages_rpicture6.jpg');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标题',
  `introduction` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '简介',
  `picture` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '图片',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 227 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '新闻公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (221, '2022-04-20 00:18:13', '有梦想，就要努力去实现', 'ASJFKHKFCL;SX', 'upload/news_picture1.jpg', '<p>YDOCMXLDL</p>');
INSERT INTO `news` VALUES (222, '2022-04-20 00:18:13', '又是一年毕业季', '又是一年毕业季，感慨万千，还记的自己刚进学校那时候的情景，我拖着沉重的行李箱站在偌大的教学楼前面，感叹自己未来的日子即将在这个陌生的校园里度过，而如今斗转星移，浮光掠影，弹指之间，那些青葱岁月如同白驹过隙般悄然从指缝溜走。过去的种种在胸口交集纠结，像打翻的五味瓶，甜蜜，酸楚，苦涩，一并涌上心头。', 'upload/news_picture2.jpg', '<p>又是一年毕业季，感慨万千，还记的自己刚进学校那时候的情景，我拖着沉重的行李箱站在偌大的教学楼前面，感叹自己未来的日子即将在这个陌生的校园里度过，而如今斗转星移，浮光掠影，弹指之间，那些青葱岁月如同白驹过隙般悄然从指缝溜走。</p><p>过去的种种在胸口交集纠结，像打翻的五味瓶，甜蜜，酸楚，苦涩，一并涌上心头。一直都是晚会的忠实参与者，无论是台前还是幕后，忽然间，角色转变，那种感觉确实难以用语言表达。</p><p>	过去的三年，总是默默地期盼着这个好雨时节，因为这时候，会有灿烂的阳光，会有满目的百花争艳，会有香甜的冰激凌，这是个毕业的季节，当时不经世事的我们会殷切地期待学校那一大堆的活动，期待穿上绚丽的演出服或者礼仪服，站在大礼堂镁光灯下尽情挥洒我们的澎拜的激情。</p><p>百感交集，隔岸观火与身临其境的感觉竟是如此不同。从来没想过一场晚会送走的是我们自己的时候会是怎样的感情，毕业就真的意味着结束吗?倔强的我们不愿意承认，谢谢学弟学妹们慷慨的将这次的主题定为“我们在这里”。我知道，这可能是他们对我们这些过来人的尊敬和施舍。</p><p>没有为这场晚会排练、奔波，没有为班级、学生会、文学院出点力，还真有点不习惯，百般无奈中，用“工作忙”个万能的借口来搪塞自己，欺骗别人。其实自己心里明白，那只是在逃避，只是不愿面对繁华落幕后的萧条和落寞。大四了，大家各奔东西，想凑齐班上的人真的是难上加难，敏燕从越南回来，刚落地就匆匆回了学校，那么恋家的人也启程回来了，睿睿学姐也是从家赶来跟我们团圆。大家—如既往的寒暄、打趣、调侃对方，似乎一切又回到了当初的单纯美好。</p><p>看着舞台上活泼可爱的学弟学妹们，如同一群机灵的小精灵，清澈的眼神，稚嫩的肢体，轻快地步伐，用他们那热情洋溢的舞姿渲染着在场的每一个人，我知道，我不应该羡慕嫉妒他们，不应该顾自怜惜逝去的青春，不应该感叹夕阳无限好，曾经，我们也拥有过，曾经，我们也年轻过，曾经，我们也灿烂过。我深深地告诉自己，人生的每个阶段都是美的，年轻有年轻的活力，成熟也有成熟的魅力。多—份稳重、淡然、优雅，也是漫漫时光掠影遗留下的.珍贵赏赐。</p>');
INSERT INTO `news` VALUES (223, '2022-04-20 00:18:13', '挫折路上，坚持常在心间', '回头看看，你会不会发现，曾经的你在这里摔倒过;回头看看，你是否发现，一次次地重复着，却从没爬起过。而如今，让我们把视线转向前方，那一道道金色的弧线，是流星飞逝的痕迹，或是成功运行的轨道。今天的你，是否要扬帆起航，让幸福来敲门?清晨的太阳撒向大地，神奇的宇宙赋予它神奇的色彩，大自然沐浴着春光，世界因太阳的照射而精彩，林中百鸟啾啾，河水轻轻流淌，汇成清宁的山间小调。', 'upload/news_picture3.jpg', '<p>回头看看，你会不会发现，曾经的你在这里摔倒过;回头看看，你是否发现，一次次地重复着，却从没爬起过。而如今，让我们把视线转向前方，那一道道金色的弧线，是流星飞逝的痕迹，或是成功运行的轨道。今天的你，是否要扬帆起航，让幸福来敲门?</p><p>清晨的太阳撒向大地，神奇的宇宙赋予它神奇的色彩，大自然沐浴着春光，世界因太阳的照射而精彩，林中百鸟啾啾，河水轻轻流淌，汇成清宁的山间小调。</p><p>是的，面对道途上那无情的嘲讽，面对步伐中那重复的摔跤，面对激流与硬石之间猛烈的碰撞，我们必须选择那富于阴雨，却最终见到彩虹的荆棘路。也许，经历了那暴风雨的洗礼，我们便会变得自信，幸福也随之而来。</p><p>司马迁屡遭羞辱，却依然在狱中撰写《史记》，作为一名史学家，不因王权而极度赞赏，也不因卑微而极度批判，然而他在坚持自己操守的同时，却依然要受统治阶级的阻碍，他似乎无权选择自己的本职。但是，他不顾于此，只是在面对道途的阻隔之时，他依然选择了走下去的信念。终于一部开山巨作《史记》诞生，为后人留下一份馈赠，也许在他完成毕生的杰作之时，他微微地笑了，没有什么比梦想实现更快乐的了......</p><p>	或许正如“长风破浪会有时，直挂云帆济沧海”一般，欣欣然地走向看似深渊的崎岖路，而在一番耕耘之后，便会发现这里另有一番天地。也许这就是困难与快乐的交融。</p><p>也许在形形色色的社会中，我们常能看到一份坚持，一份自信，但这里却还有一类人。这类人在暴风雨来临之际，只会闪躲，从未懂得这也是一种历炼，这何尝不是一份快乐。在阴暗的角落里，总是独自在哭，带着伤愁，看不到一点希望。</p><p>我们不能堕落于此，而要像海燕那般，在苍茫的大海上，高傲地飞翔，任何事物都无法阻挡，任何事都是幸福快乐的。</p>');
INSERT INTO `news` VALUES (224, '2022-04-20 00:18:13', '挫折是另一个生命的开端', '当遇到挫折或失败，你是看见失败还是看见机会?挫折是我们每个人成长的必经之路，它不是你想有就有，想没有就没有的。有句名言说的好，如果你想一生摆脱苦难，你就得是神或者是死尸。这句话形象地说明了挫折是伴随着人生的，是谁都逃不掉的。', 'upload/news_picture4.jpg', '<p>当遇到挫折或失败，你是看见失败还是看见机会?</p><p>挫折是我们每个人成长的必经之路，它不是你想有就有，想没有就没有的。有句名言说的好，如果你想一生摆脱苦难，你就得是神或者是死尸。这句话形象地说明了挫折是伴随着人生的，是谁都逃不掉的。</p><p>人生在世，从古到今，不分天子平民，机遇虽有不同，但总不免有身陷困境或遭遇难题之处，这时候唯有通权达变，才能使人转危为安，甚至反败为胜。</p><p>大部分的人，一生当中，最痛苦的经验是失去所爱的人，其次是丢掉一份工作。其实，经得起考验的人，就算是被开除也不会惊慌，要学会面对。</p><p>	“塞翁失马，焉知非福。”人生的道路，并不是每一步都迈向成功，这就是追求的意义。我们还要认识到一点，挫折作为一种情绪状态和一种个人体验，各人的耐受性是大不相同的，有的人经历了一次次挫折，就能够坚忍不拔，百折不挠;有的人稍遇挫折便意志消沉，一蹶不振。所以，挫折感是一种主观感受，因为人的目的和需要不同，成功标准不同，所以同一种活动对于不同的人可能会造成不同的挫折感受。</p><p>凡事皆以平常心来看待，对于生命顺逆不要太执著。能够“破我执”是很高层的人生境界。</p><p>人事的艰难就是一种考验。就像—支剑要有磨刀来磨，剑才会利:一块璞玉要有粗石来磨，才会发出耀眼的光芒。我们能够做到的，只是如何减少、避免那些由于自身的原因所造成的挫折，而在遇到痛苦和挫折之后，则力求化解痛苦，争取幸福。我们要知道，痛苦和挫折是双重性的，它既是我们人生中难以完全避免的，也是我们在争取成功时，不可缺少的一种动力。因为我认为，推动我们奋斗的力量，不仅仅是对成功的渴望，还有为摆脱痛苦和挫折而进行的奋斗。</p>');
INSERT INTO `news` VALUES (225, '2022-04-20 00:18:13', '你要去相信，没有到不了的明天', '有梦想就去努力，因为在这一辈子里面，现在不去勇敢的努力，也许就再也没有机会了。你要去相信，一定要相信，没有到不了的明天。不要被命运打败，让自己变得更强大。不管你现在是一个人走在异乡的街道上始终没有找到一丝归属感，还是你在跟朋友们一起吃饭开心址笑着的时候闪过一丝落寞。', 'upload/news_picture5.jpg', '<p>有梦想就去努力，因为在这一辈子里面，现在不去勇敢的努力，也许就再也没有机会了。你要去相信，一定要相信，没有到不了的明天。不要被命运打败，让自己变得更强大。</p><p>不管你现在是一个人走在异乡的街道上始终没有找到一丝归属感，还是你在跟朋友们一起吃饭开心址笑着的时候闪过一丝落寞。</p><p>	不管你现在是在图书馆里背着怎么也看不进去的英语单词，还是你现在迷茫地看不清未来的方向不知道要往哪走。</p><p>不管你现在是在努力着去实现梦想却没能拉近与梦想的距离，还是你已经慢慢地找不到自己的梦想了。</p><p>你都要去相信，没有到不了的明天。</p><p>	有的时候你的梦想太大，别人说你的梦想根本不可能实现;有的时候你的梦想又太小，又有人说你胸无大志;有的时候你对死党说着将来要去环游世界的梦想，却换来他的不屑一顾，于是你再也不提自己的梦想;有的时候你突然说起将来要开个小店的愿望，却发现你讲述的那个人，并没有听到你在说什么。</p><p>不过又能怎么样呢，未来始终是自己的，梦想始终是自己的，没有人会来帮你实现它。</p><p>也许很多时候我们只是需要朋友的一句鼓励，一句安慰，却也得不到。但是相信我，世界上还有很多人，只是想要和你说说话。</p><p>因为我们都一样。一样的被人说成固执，一样的在追逐他们眼里根本不在意的东西。</p><p>所以，又有什么关系呢，别人始终不是你、不能懂你的心情，你又何必多去解释呢。这个世界会来阻止你，困难也会接踵而至，其实真正关键的只有自己，有没有那个倔强。</p><p>这个世界上没有不带伤的人，真正能治愈自己的，只有自己。</p>');
INSERT INTO `news` VALUES (226, '2022-04-20 00:18:13', '离开是一种痛苦，是一种勇气，但同样也是一个考验，是一个新的开端', '无穷无尽是离愁，天涯海角遍寻思。当离别在即之时，当面对着相濡以沫兄弟般的朋友时，当面对着经历了四年的磨合而形成的真挚友谊之时，我内心激动无语，说一声再见，道一声珍重都很难出口。回想自己四年大学的风风雨雨，回想我们曾经共同经历的岁月流年，我感谢大家的相扶相依，感谢朋友们的莫大支持与帮助。虽然舍不得，但离别的脚步却不因我们的挚情而停滞。', 'upload/news_picture6.jpg', '<p>无穷无尽是离愁，天涯海角遍寻思。当离别在即之时，当面对着相濡以沫兄弟般的朋友时，当面对着经历了四年的磨合而形成的真挚友谊之时，我内心激动无语，说一声再见，道一声珍重都很难出口。回想自己四年大学的风风雨雨，回想我们曾经共同经历的岁月流年，我感谢大家的相扶相依，感谢朋友们的莫大支持与帮助。虽然舍不得，但离别的脚步却不因我们的挚情而停滞。离别的确是一种痛苦，但同样也是我们走入社会，走向新环境、新领域的一个开端，希望大家在以后新的工作岗位上能够确定自己的新起点，坚持不懈，向着更新、更高的目标前进，因为人生最美好的东西永远都在最前方!</p><p>忆往昔峥嵘岁月，看今朝潮起潮落，望未来任重而道远。作为新时代的我们，就应在失败时，能拼搏奋起，去谱写人生的辉煌。在成功时，亦能居安思危，不沉湎于一时的荣耀、鲜花和掌声中，时时刻刻怀着一颗积极寻找自己新的奶酪的心，处变不惊、成败不渝，始终踏着自己坚实的步伐，从零开始，不断向前迈进，这样才能在这风起云涌、变幻莫测的社会大潮中成为真正的弄潮儿!</p>');

-- ----------------------------
-- Table structure for paibanguize
-- ----------------------------
DROP TABLE IF EXISTS `paibanguize`;
CREATE TABLE `paibanguize`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `guizemingcheng` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `kaishishijian` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `jieshushijian` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `keyuyuerenshu` int NULL DEFAULT NULL COMMENT '可预约人数',
  `shiyongjiaolian` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '适用教练',
  `zhuangtai` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '启用' COMMENT '状态',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777110842570 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '排班规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paibanguize
-- ----------------------------
INSERT INTO `paibanguize` VALUES (1, '上午班', '08:00', '11:23', 4, 'c1tom001', '启用', '2026-04-25 11:21:08');
INSERT INTO `paibanguize` VALUES (2, '下午班', '14:00', '18:00', 4, 'c1tom001', '启用', '2026-04-25 11:21:08');
INSERT INTO `paibanguize` VALUES (3, '晚班', '19:00:00', '21:00:00', 3, '全部教练', '启用', '2026-04-25 11:21:08');
INSERT INTO `paibanguize` VALUES (1777109587422, '上午', '17:33', '17:35', NULL, '全部教练', '启用', '2026-04-25 17:33:06');
INSERT INTO `paibanguize` VALUES (1777109668187, '测试规则1', '08:00', '12:00', NULL, '所有教练', '启用', '2026-04-25 17:34:27');
INSERT INTO `paibanguize` VALUES (1777110842569, '测试规则API', '09:00', '11:00', NULL, '全部教练', '启用', '2026-04-25 17:54:02');

-- ----------------------------
-- Table structure for paibanshiyong
-- ----------------------------
DROP TABLE IF EXISTS `paibanshiyong`;
CREATE TABLE `paibanshiyong`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoliangonghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '教练工号',
  `jiaolianxingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '教练姓名',
  `paibanshijian` datetime NULL DEFAULT NULL COMMENT '排班时间',
  `paibanqingkuang` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '排班情况',
  `tongbusj` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '同步时间',
  `tongbuzhuangtai` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '已同步' COMMENT '同步状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `jiaoliangonghao`(`jiaoliangonghao` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '教练排班使用' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paibanshiyong
-- ----------------------------

-- ----------------------------
-- Table structure for qingjiashenqing
-- ----------------------------
DROP TABLE IF EXISTS `qingjiashenqing`;
CREATE TABLE `qingjiashenqing`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoliangonghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练工号',
  `jiaolianxingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练姓名',
  `qingjiashijian` datetime NULL DEFAULT NULL COMMENT '请假时间',
  `jiezhishijian` datetime NULL DEFAULT NULL COMMENT '截止时间',
  `qingjiashiyou` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '请假事由',
  `sfsh` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '否' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '审核回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 137 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '请假申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qingjiashenqing
-- ----------------------------
INSERT INTO `qingjiashenqing` VALUES (131, '2022-04-20 00:18:12', '教练工号1', '教练姓名1', '2022-04-20 00:18:12', '2022-04-20 00:18:12', '请假事由1', '是', '');
INSERT INTO `qingjiashenqing` VALUES (132, '2022-04-20 00:18:12', '教练工号2', '教练姓名2', '2022-04-20 00:18:12', '2022-04-20 00:18:12', '请假事由2', '是', '');
INSERT INTO `qingjiashenqing` VALUES (133, '2022-04-20 00:18:12', '教练工号3', '教练姓名3', '2022-04-20 00:18:12', '2022-04-20 00:18:12', '请假事由3', '是', '');
INSERT INTO `qingjiashenqing` VALUES (134, '2022-04-20 00:18:12', '教练工号4', '教练姓名4', '2022-04-20 00:18:12', '2022-04-20 00:18:12', '请假事由4', '是', '');
INSERT INTO `qingjiashenqing` VALUES (135, '2022-04-20 00:18:12', '教练工号5', '教练姓名5', '2022-04-20 00:18:12', '2022-04-20 00:18:12', '请假事由5', '是', '');
INSERT INTO `qingjiashenqing` VALUES (136, '2022-04-20 00:18:12', '教练工号6', '教练姓名6', '2022-04-20 00:18:12', '2022-04-20 00:18:12', '请假事由6', '是', '');

-- ----------------------------
-- Table structure for quxiaokecheng
-- ----------------------------
DROP TABLE IF EXISTS `quxiaokecheng`;
CREATE TABLE `quxiaokecheng`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `kechengmingcheng` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `kemuleixing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '科目类型',
  `quxiaoshijian` datetime NULL DEFAULT NULL COMMENT '取消时间',
  `quxiaoyuanyin` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '取消原因',
  `jiaoliangonghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练工号',
  `jiaolianxingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练姓名',
  `zhanghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sfsh` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '否' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '审核回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777226475635 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '取消课程' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of quxiaokecheng
-- ----------------------------
INSERT INTO `quxiaokecheng` VALUES (101, '2022-04-20 00:18:12', 'C1科二', '科目二', '2026-04-27 01:59:49', '教练请假', 'c1tom001', 'tom', 'zhangsan001', '张三', '是', '');
INSERT INTO `quxiaokecheng` VALUES (102, '2022-04-20 00:18:12', '课程名称2', '科目类型2', '2022-04-20 00:18:12', '取消原因2', '教练工号2', '教练姓名2', '账号2', '姓名2', '是', '');
INSERT INTO `quxiaokecheng` VALUES (103, '2022-04-20 00:18:12', '课程名称3', '科目类型3', '2022-04-20 00:18:12', '取消原因3', '教练工号3', '教练姓名3', '账号3', '姓名3', '是', '');
INSERT INTO `quxiaokecheng` VALUES (104, '2022-04-20 00:18:12', '课程名称4', '科目类型4', '2022-04-20 00:18:12', '取消原因4', '教练工号4', '教练姓名4', '账号4', '姓名4', '是', '');
INSERT INTO `quxiaokecheng` VALUES (105, '2022-04-20 00:18:12', '课程名称5', '科目类型5', '2022-04-20 00:18:12', '取消原因5', '教练工号5', '教练姓名5', '账号5', '姓名5', '是', '');
INSERT INTO `quxiaokecheng` VALUES (106, '2022-04-20 00:18:12', '课程名称6', '科目类型6', '2022-04-20 00:18:12', '取消原因6', '教练工号6', '教练姓名6', '账号6', '姓名6', '是', '');
INSERT INTO `quxiaokecheng` VALUES (1777226475634, '2026-04-27 02:01:15', 'C2', '科三', '2026-04-27 02:00:38', '', '222', '222', '222', '222', '否', '');

-- ----------------------------
-- Table structure for rizhi
-- ----------------------------
DROP TABLE IF EXISTS `rizhi`;
CREATE TABLE `rizhi`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adminname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '管理员',
  `action` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '操作',
  `ip` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `description` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '操作描述',
  `result` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '成功' COMMENT '操作结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rizhi
-- ----------------------------

-- ----------------------------
-- Table structure for shipin
-- ----------------------------
DROP TABLE IF EXISTS `shipin`;
CREATE TABLE `shipin`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `biaoti` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '视频标题',
  `fenlei` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '视频分类',
  `lianjie` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '视频链接',
  `shipinwenjian` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '视频文件',
  `fengmian` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '视频封面',
  `miaoshu` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '视频描述',
  `dianzanshu` int NULL DEFAULT 0 COMMENT '点赞数',
  `shoucangshu` int NULL DEFAULT 0 COMMENT '收藏数',
  `clicknum` int NULL DEFAULT 0 COMMENT '点击次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777172794549 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '视频学习' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shipin
-- ----------------------------
INSERT INTO `shipin` VALUES (1, '2026-04-25 11:21:08', '科目一交通标志详解', '科目一', 'upload/shipin1.mp4', '1777301565415.mp4', 'upload/shipin1.jpg', '详细介绍科目一考试中常见的交通标志，包括指示标志、警告标志、禁令标志等。', 16, 9, 256);
INSERT INTO `shipin` VALUES (2, '2026-04-25 11:21:08', '科目二倒车入库技巧', '科目二', 'upload/shipin2.mp4', NULL, 'upload/shipin2.jpg', '倒车入库是科目二最难的项目之一，本视频详细讲解操作步骤和技巧要点。', 22, 12, 345);
INSERT INTO `shipin` VALUES (3, '2026-04-25 11:21:08', '科目三路考注意事项', '科目三', 'upload/shipin3.mp4', NULL, 'upload/shipin3.jpg', '路考是取得驾照的最后一道关卡，本视频总结常见扣分点和注意事项。', 18, 10, 289);
INSERT INTO `shipin` VALUES (1777133489920, '2026-04-26 00:11:29', '111', '科目一', NULL, NULL, NULL, '测试', 0, 0, 0);

-- ----------------------------
-- Table structure for shipinpinglun
-- ----------------------------
DROP TABLE IF EXISTS `shipinpinglun`;
CREATE TABLE `shipinpinglun`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shipinid` bigint NULL DEFAULT NULL COMMENT '视频id',
  `pinglunneirong` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '评论内容',
  `pinglunren` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '评论人',
  `pingluntime` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `huifuneirong` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '回复内容',
  `pinglunrenid` bigint NULL DEFAULT NULL COMMENT '评论人id',
  `parentid` bigint NULL DEFAULT NULL COMMENT '父评论id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '视频评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shipinpinglun
-- ----------------------------
INSERT INTO `shipinpinglun` VALUES (9, '2026-04-26 19:54:00', 1, '111', '用户', '2026-04-26 19:54:00', NULL, 11, NULL);
INSERT INTO `shipinpinglun` VALUES (10, '2026-04-26 19:54:04', 1, '222', '用户', '2026-04-26 19:54:05', NULL, 11, 9);
INSERT INTO `shipinpinglun` VALUES (11, '2026-04-26 19:54:10', 1, '333', '用户', '2026-04-26 19:54:10', NULL, 11, 9);
INSERT INTO `shipinpinglun` VALUES (12, '2026-04-27 02:02:21', 1, '1', '用户', '2026-04-27 02:02:22', NULL, 11, NULL);

-- ----------------------------
-- Table structure for shouyexinxi
-- ----------------------------
DROP TABLE IF EXISTS `shouyexinxi`;
CREATE TABLE `shouyexinxi`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `leixing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '信息类型',
  `biaoti` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标题',
  `neirong` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '内容',
  `tupian` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图片',
  `paixu` int NULL DEFAULT 0 COMMENT '排序',
  `zhuangtai` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '显示' COMMENT '状态',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '首页信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shouyexinxi
-- ----------------------------
INSERT INTO `shouyexinxi` VALUES (1, '驾校概况', '驾校介绍', '<p>本校是一所经交通部门认证的正规驾校，拥有多年的办学历史和丰富的教学经验。学校占地100余亩，拥有标准化的训练场地和完善的教学设施。</p><p>办学理念：以人为本，安全第一，服务至上</p><p>教学目标：培养合格的驾驶员，提高道路交通安全意识</p>', 'upload/jiaxiaojieshao.jpg', 1, '显示', '2026-04-25 11:21:08');
INSERT INTO `shouyexinxi` VALUES (2, '驾校概况', '驾校荣誉', '<p>省级优秀驾校</p><p>AAA级信用单位</p><p>文明诚信驾校</p>', 'upload/jiaxiaorongyu.jpg', 2, '显示', '2026-04-25 11:21:08');

-- ----------------------------
-- Table structure for storeup
-- ----------------------------
DROP TABLE IF EXISTS `storeup`;
CREATE TABLE `storeup`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint NOT NULL COMMENT '用户id',
  `refid` bigint NULL DEFAULT NULL COMMENT '收藏id',
  `tablename` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '表名',
  `name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '收藏名称',
  `picture` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '收藏图片',
  `type` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '1' COMMENT '类型(1:收藏,21:赞,22:踩)',
  `inteltype` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '推荐类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777313154411 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storeup
-- ----------------------------
INSERT INTO `storeup` VALUES (1777313111520, '2026-04-28 02:05:10', 11, 31, 'jiaxiaoxinxi', '南京市雨花驾校C1', 'upload/jiaxiaoxinxi_jiaxiaotupian1.jpg', '22', NULL);
INSERT INTO `storeup` VALUES (1777313113789, '2026-04-28 02:05:12', 11, 31, 'jiaxiaoxinxi', '南京市雨花驾校C1', 'upload/jiaxiaoxinxi_jiaxiaotupian1.jpg', '22', NULL);
INSERT INTO `storeup` VALUES (1777313116237, '2026-04-28 02:05:16', 11, 31, 'jiaxiaoxinxi', '南京市雨花驾校C1', 'upload/jiaxiaoxinxi_jiaxiaotupian1.jpg', '21', NULL);
INSERT INTO `storeup` VALUES (1777313116336, '2026-04-28 02:05:15', 11, 31, 'jiaxiaoxinxi', '南京市雨花驾校C1', 'upload/jiaxiaoxinxi_jiaxiaotupian1.jpg', '21', NULL);
INSERT INTO `storeup` VALUES (1777313117205, '2026-04-28 02:05:16', 11, 31, 'jiaxiaoxinxi', '南京市雨花驾校C1', 'upload/jiaxiaoxinxi_jiaxiaotupian1.jpg', '21', NULL);
INSERT INTO `storeup` VALUES (1777313117421, '2026-04-28 02:05:17', 11, 31, 'jiaxiaoxinxi', '南京市雨花驾校C1', 'upload/jiaxiaoxinxi_jiaxiaotupian1.jpg', '21', NULL);
INSERT INTO `storeup` VALUES (1777313117452, '2026-04-28 02:05:16', 11, 31, 'jiaxiaoxinxi', '南京市雨花驾校C1', 'upload/jiaxiaoxinxi_jiaxiaotupian1.jpg', '21', NULL);
INSERT INTO `storeup` VALUES (1777313117821, '2026-04-28 02:05:17', 11, 31, 'jiaxiaoxinxi', '南京市雨花驾校C1', 'upload/jiaxiaoxinxi_jiaxiaotupian1.jpg', '21', NULL);
INSERT INTO `storeup` VALUES (1777313123402, '2026-04-28 02:05:23', 11, 31, 'jiaxiaoxinxi', '南京市雨花驾校C1', 'upload/jiaxiaoxinxi_jiaxiaotupian1.jpg', '21', NULL);
INSERT INTO `storeup` VALUES (1777313154410, '2026-04-28 02:05:53', 11, 31, 'jiaxiaoxinxi', '南京市雨花驾校C1', 'upload/jiaxiaoxinxi_jiaxiaotupian1.jpg', '1', NULL);

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint NOT NULL COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `tablename` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '表名',
  `role` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '角色',
  `token` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = 'token表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (1, 1, 'abo', 'users', '管理员', 'igjmiv197ti8p014ifk83syux77b0bs7', '2022-04-20 00:21:56', '2026-04-28 14:41:05');
INSERT INTO `token` VALUES (4, 11, 'zhangsan001', 'xueyuan', '学员', 'mitmlb1cdbhb5591mabozguhf5z7duso', '2026-04-27 17:20:44', '2026-04-28 14:42:07');
INSERT INTO `token` VALUES (5, 21, 'c1tom001', 'jiaolian', '教练', '2cqjbn0vjahubjm9dtrqk3gpqj1o338l', '2026-04-27 17:25:15', '2026-04-28 14:42:53');
INSERT INTO `token` VALUES (6, 1777302731997, 'c2mike002', 'jiaolian', '教练', 'pk0sjr8or0nt2u8mc3y1vhmffuftbupo', '2026-04-27 23:12:31', '2026-04-28 00:12:32');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `role` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'abo', 'abo', '管理员', '2022-04-20 00:18:13');

-- ----------------------------
-- Table structure for xitongcanshu
-- ----------------------------
DROP TABLE IF EXISTS `xitongcanshu`;
CREATE TABLE `xitongcanshu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `canshumingcheng` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '参数名称',
  `canshujian` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '参数键',
  `canshuzhi` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `beizhu` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `zhuangtai` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '启用' COMMENT '状态',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777110843197 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '系统参数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xitongcanshu
-- ----------------------------
INSERT INTO `xitongcanshu` VALUES (1, '预约名额配置', 'yuyue_mingee', '4', '每个时段最大可预约人数', '启用', '2026-04-25 11:21:08');
INSERT INTO `xitongcanshu` VALUES (2, '取消预约限制', 'quxiao_shixiao', '2', '提前多少小时可以取消预约(小时)', '启用', '2026-04-25 11:21:08');
INSERT INTO `xitongcanshu` VALUES (3, '考试时长', 'kaoshi_shichang', '60', '理论考试时长(分钟)', '启用', '2026-04-25 11:21:08');
INSERT INTO `xitongcanshu` VALUES (4, '练车预约开放天数', 'yuyue_kaifang_tianshu', '7', '提前多少天开放预约', '启用', '2026-04-25 11:21:08');
INSERT INTO `xitongcanshu` VALUES (1777109562621, '预约名额配置', 'yuyue_mingee', '10', '每个教练排班时段最大可预约学员数', '启用', '2026-04-25 17:32:42');
INSERT INTO `xitongcanshu` VALUES (1777109675030, '每教练每时段预约名额', NULL, '4', '每个教练每个排班时段最多接受4位学员预约', '启用', '2026-04-25 17:34:35');
INSERT INTO `xitongcanshu` VALUES (1777110843196, '测试名额配置', 'test_quota', '5', '测试用', '启用', '2026-04-25 17:54:02');

-- ----------------------------
-- Table structure for xueyuan
-- ----------------------------
DROP TABLE IF EXISTS `xueyuan`;
CREATE TABLE `xueyuan`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhanghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '账号',
  `mima` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `xingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '姓名',
  `xingbie` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '性别',
  `nianling` int NULL DEFAULT NULL COMMENT '年龄',
  `shouji` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机',
  `lianxichexing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '练习车型',
  `jiatingzhuzhi` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `xueyuanzhuangtai` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '正常' COMMENT '学员状态',
  `touxiang` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `zhanghao`(`zhanghao` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777133995807 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '学员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xueyuan
-- ----------------------------
INSERT INTO `xueyuan` VALUES (11, '2022-04-20 00:18:12', 'zhangsan001', '123456', '张三', '男', 1, '13823888881', '车型1', '雨花区示范路1号', '正常', 'upload/xueyuan_touxiang1.jpg');
INSERT INTO `xueyuan` VALUES (12, '2022-04-20 00:18:12', '账号2', '123456', '姓名2', '男', 2, '13823888882', '车型2', '雨花区示范路2号', '正常', 'upload/xueyuan_touxiang2.jpg');
INSERT INTO `xueyuan` VALUES (13, '2022-04-20 00:18:12', '账号3', '123456', '姓名3', '男', 3, '13823888883', '车型3', '雨花区示范路3号', '休学', 'upload/xueyuan_touxiang3.jpg');
INSERT INTO `xueyuan` VALUES (14, '2022-04-20 00:18:12', '账号4', '123456', '姓名4', '男', 4, '13823888884', '车型4', '雨花区示范路4号', '结业', 'upload/xueyuan_touxiang4.jpg');
INSERT INTO `xueyuan` VALUES (1777133995806, '2026-04-26 00:19:55', 'lisi005', '123456', '1', '男', 1, '13928587031', '车型5', '雨花区示范路5号', '正常', '');

-- ----------------------------
-- Table structure for yuyuelianche
-- ----------------------------
DROP TABLE IF EXISTS `yuyuelianche`;
CREATE TABLE `yuyuelianche`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoliangonghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练工号',
  `jiaolianxingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '教练姓名',
  `liancheshijian` datetime NULL DEFAULT NULL COMMENT '练车时间',
  `yuyueshuoming` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '预约说明',
  `zhanghao` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `xingming` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sfsh` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '否' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '审核回复',
  `yuyuezhuangtai` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '待审核' COMMENT '预约状态',
  `status` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '待审核' COMMENT '审核状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1777301325177 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '预约练车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yuyuelianche
-- ----------------------------
INSERT INTO `yuyuelianche` VALUES (1777275166118, '2026-04-27 15:32:45', 'c1tom001', 'tom', '2026-04-27 15:32:41', '测试', 'zhangsan001', '张三', '已通过', '', '待审核', '已通过');
INSERT INTO `yuyuelianche` VALUES (1777275319067, '2026-04-27 15:35:18', 'c1tom001', 'tom', '2026-04-29 00:00:00', '测试', 'zhangsan001', '张三', '未通过', '', '待审核', '未通过');
INSERT INTO `yuyuelianche` VALUES (1777287223844, '2026-04-27 18:53:43', 'c1tom001', 'tom', '2026-04-30 15:00:00', '', 'zhangsan001', '张三', '已通过', '', '待审核', '已通过');
INSERT INTO `yuyuelianche` VALUES (1777301325176, '2026-04-27 22:48:44', 'c1tom001', 'tom', '2026-04-30 10:00:00', '', 'zhangsan001', '张三', '待审核', '', '待审核', '待审核');

-- ----------------------------
-- Table structure for ziyuanbeifen
-- ----------------------------
DROP TABLE IF EXISTS `ziyuanbeifen`;
CREATE TABLE `ziyuanbeifen`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ziyuanmingcheng` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `ziyuanleixing` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '资源类型',
  `ziyuanwenjian` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '资源文件',
  `ziyuanjianjie` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '资源简介',
  `beifenshijian` date NULL DEFAULT NULL COMMENT '备份时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 157 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '资源备份' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ziyuanbeifen
-- ----------------------------
INSERT INTO `ziyuanbeifen` VALUES (152, '2022-04-20 00:18:13', '资源名称2', '资源类型2', '', '资源简介2', '2022-04-20');
INSERT INTO `ziyuanbeifen` VALUES (153, '2022-04-20 00:18:13', '资源名称3', '资源类型3', '', '资源简介3', '2022-04-20');
INSERT INTO `ziyuanbeifen` VALUES (154, '2022-04-20 00:18:13', '资源名称4', '资源类型4', '', '资源简介4', '2022-04-20');
INSERT INTO `ziyuanbeifen` VALUES (155, '2022-04-20 00:18:13', '资源名称5', '资源类型5', '', '资源简介5', '2022-04-20');
INSERT INTO `ziyuanbeifen` VALUES (156, '2022-04-20 00:18:13', '资源名称6', '资源类型6', '', '资源简介6', '2022-04-20');

SET FOREIGN_KEY_CHECKS = 1;
