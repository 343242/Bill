-- 添加视频表
CREATE TABLE `shipin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `biaoti` varchar(200) NOT NULL COMMENT '视频标题',
  `fenlei` varchar(200) NOT NULL COMMENT '视频分类',
  `lianjie` varchar(200) NOT NULL COMMENT '视频链接',
  `fengmian` varchar(200) NOT NULL COMMENT '视频封面',
  `miaoshu` longtext COMMENT '视频描述',
  `dianzanshu` int(11) DEFAULT '0' COMMENT '点赞数',
  `shoucangshu` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频学习';

-- 添加视频评论表
CREATE TABLE `shipinpinglun` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shipinid` bigint(20) NOT NULL COMMENT '视频id',
  `pinglunneirong` longtext NOT NULL COMMENT '评论内容',
  `pinglunren` varchar(200) NOT NULL COMMENT '评论人',
  `pingluntime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  PRIMARY KEY (`id`),
  KEY `shipinid` (`shipinid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频评论';

-- 添加错题本表
CREATE TABLE `cuotiban` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhanghao` varchar(200) NOT NULL COMMENT '账号',
  `xingming` varchar(200) NOT NULL COMMENT '姓名',
  `timu` longtext NOT NULL COMMENT '题目',
  `cuowudaan` varchar(200) NOT NULL COMMENT '错误答案',
  `zhengquedaan` varchar(200) NOT NULL COMMENT '正确答案',
  `shijian` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='错题本';

-- 添加预约练车表的status字段
ALTER TABLE `yuyuelianche` ADD COLUMN `status` varchar(200) DEFAULT '待审核' COMMENT '审核状态' AFTER `shhf`;

-- 插入视频数据
INSERT INTO `shipin` (`biaoti`, `fenlei`, `lianjie`, `fengmian`, `miaoshu`) VALUES
('科目一：交通信号与标志', '科目一', 'https://example.com/video1', 'upload/shipin_fengmian1.jpg', '详细讲解交通信号与标志的识别方法'),
('科目一：道路交通安全法律法规', '科目一', 'https://example.com/video2', 'upload/shipin_fengmian2.jpg', '全面解读道路交通安全法律法规'),
('科目四：安全文明驾驶常识', '科目四', 'https://example.com/video3', 'upload/shipin_fengmian3.jpg', '安全文明驾驶常识详解'),
('科目四：紧急情况处理', '科目四', 'https://example.com/video4', 'upload/shipin_fengmian4.jpg', '紧急情况处理方法与技巧');
