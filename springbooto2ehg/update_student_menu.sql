-- 创建视频表
DROP TABLE IF EXISTS `shipin`;
CREATE TABLE `shipin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shipinbiaoti` varchar(200) NOT NULL COMMENT '视频标题',
  `shipinleixing` varchar(200) DEFAULT NULL COMMENT '视频类型',
  `fengmian` varchar(200) DEFAULT NULL COMMENT '封面',
  `shipin` varchar(200) DEFAULT NULL COMMENT '视频',
  `faburen` varchar(200) DEFAULT NULL COMMENT '发布人',
  `fabushijian` datetime DEFAULT NULL COMMENT '发布时间',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  `discussnum` int(11) DEFAULT '0' COMMENT '评论数',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  `dianzannum` int(11) DEFAULT '0' COMMENT '点赞数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='视频学习';

-- 创建视频评论表
DROP TABLE IF EXISTS `shipinpinglun`;
CREATE TABLE `shipinpinglun` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `nickname` varchar(200) DEFAULT NULL COMMENT '用户名',
  `content` varchar(500) NOT NULL COMMENT '评论内容',
  `reply` varchar(500) DEFAULT NULL COMMENT '回复内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='视频评论';

-- 插入示例视频数据
INSERT INTO `shipin` (`id`, `addtime`, `shipinbiaoti`, `shipinleixing`, `fengmian`, `shipin`, `faburen`, `fabushijian`, `clicknum`, `discussnum`, `storeupnum`, `dianzannum`) VALUES
(1, '2022-04-19 16:18:12', '科目一理论讲解第一章', '科目一', 'upload/shipin_fengmian1.jpg', 'upload/shipin1.mp4', '管理员', '2022-04-19 16:18:12', 100, 10, 5, 20),
(2, '2022-04-19 16:18:12', '科目一理论讲解第二章', '科目一', 'upload/shipin_fengmian2.jpg', 'upload/shipin2.mp4', '管理员', '2022-04-19 16:18:12', 80, 5, 3, 15),
(3, '2022-04-19 16:18:12', '科目四理论讲解第一章', '科目四', 'upload/shipin_fengmian3.jpg', 'upload/shipin3.mp4', '管理员', '2022-04-19 16:18:12', 120, 15, 8, 25);
