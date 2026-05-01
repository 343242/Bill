-- 视频学习模块SQL脚本
-- 需要在数据库中执行以下SQL

-- 创建视频学习表
DROP TABLE IF EXISTS `shipin`;
CREATE TABLE `shipin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `biaoti` varchar(200) DEFAULT NULL COMMENT '视频标题',
  `fenlei` varchar(200) DEFAULT NULL COMMENT '视频分类',
  `lianjie` varchar(200) DEFAULT NULL COMMENT '视频链接',
  `fengmian` varchar(200) DEFAULT NULL COMMENT '视频封面',
  `miaoshu` longtext COMMENT '视频描述',
  `dianzanshu` int(11) DEFAULT '0' COMMENT '点赞数',
  `shoucangshu` int(11) DEFAULT '0' COMMENT '收藏数',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='视频学习';

-- 插入测试数据
INSERT INTO `shipin` VALUES (1,'2022-04-20 10:00:00','科目二考试技巧详解','科目二','upload/shipin1.mp4','upload/shipin1.jpg','详细介绍科目二考试的各个项目技巧，包括倒车入库、侧方停车、曲线行驶、直角转弯等','5','3','120');
INSERT INTO `shipin` VALUES (2,'2022-04-20 11:00:00','科目三路考注意事项','科目三','upload/shipin2.mp4','upload/shipin2.jpg','科目三路考常见问题及应对方法，注意事项总结','8','5','200');
INSERT INTO `shipin` VALUES (3,'2022-04-20 12:00:00','交通标志识别教学','科目一','upload/shipin3.mp4','upload/shipin3.jpg','常见交通标志的识别方法及含义','12','8','350');
INSERT INTO `shipin` VALUES (4,'2022-04-20 13:00:00','安全驾驶基础知识','安全教育','upload/shipin4.mp4','upload/shipin4.jpg','安全驾驶的基本知识和技巧','15','10','400');
INSERT INTO `shipin` VALUES (5,'2022-04-20 14:00:00','夜间行车灯光使用','安全教育','upload/shipin5.mp4','upload/shipin5.jpg','夜间行车的灯光使用技巧和注意事项','6','4','180');
INSERT INTO `shipin` VALUES (6,'2022-04-20 15:00:00','雨雾天气驾驶技巧','安全教育','upload/shipin6.mp4','upload/shipin6.jpg','雨雾天气下的驾驶技巧和注意事项','7','5','220');

-- 创建视频评论表
DROP TABLE IF EXISTS `shipinpinglun`;
CREATE TABLE `shipinpinglun` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shipinid` bigint(20) DEFAULT NULL COMMENT '视频id',
  `pinglunneirong` longtext COMMENT '评论内容',
  `pinglunren` varchar(200) DEFAULT NULL COMMENT '评论人',
  `pingluntime` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='视频评论';

-- 插入测试评论数据
INSERT INTO `shipinpinglun` VALUES (1,'2022-04-20 10:30:00',1,'讲解很详细，对我帮助很大！','学员小王','2022-04-20 10:30:00');
INSERT INTO `shipinpinglun` VALUES (2,'2022-04-20 10:45:00',1,'希望能有更多关于倒车入库的技巧','学员小李','2022-04-20 10:45:00');
INSERT INTO `shipinpinglun` VALUES (3,'2022-04-20 11:30:00',2,'路考注意事项总结得很全面','学员张三','2022-04-20 11:30:00');
INSERT INTO `shipinpinglun` VALUES (4,'2022-04-20 12:15:00',3,'交通标志识别太有用了','学员李四','2022-04-20 12:15:00');
INSERT INTO `shipinpinglun` VALUES (5,'2022-04-20 14:30:00',4,'安全驾驶真的很重要','学员王五','2022-04-20 14:30:00');
