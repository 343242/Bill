-- 添加系统参数表
CREATE TABLE `xitongcanshu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `canshumingcheng` varchar(200) NOT NULL COMMENT '参数名称',
  `canshujian` varchar(200) NOT NULL COMMENT '参数键',
  `canshuzhi` varchar(200) NOT NULL COMMENT '参数值',
  `beizhu` varchar(200) DEFAULT NULL COMMENT '备注',
  `zhuangtai` varchar(200) DEFAULT '启用' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `canshujian` (`canshujian`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数';

-- 添加排班规则表
CREATE TABLE `paibanguize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `guizemingcheng` varchar(200) NOT NULL COMMENT '规则名称',
  `kaishishijian` time DEFAULT NULL COMMENT '开始时间',
  `jieshushijian` time DEFAULT NULL COMMENT '结束时间',
  `keyuyuerenshu` int(11) DEFAULT '5' COMMENT '可预约人数',
  `shiyongjiaolian` varchar(200) DEFAULT '所有教练' COMMENT '适用教练',
  `zhuangtai` varchar(200) DEFAULT '启用' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='排班规则';

-- 添加操作日志表
CREATE TABLE `rizhi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adminname` varchar(200) DEFAULT NULL COMMENT '管理员',
  `action` varchar(200) DEFAULT NULL COMMENT '操作',
  `ip` varchar(200) DEFAULT NULL COMMENT 'IP地址',
  `description` longtext COMMENT '操作描述',
  `result` varchar(200) DEFAULT '成功' COMMENT '操作结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- 插入系统参数数据
INSERT INTO `xitongcanshu` (`canshumingcheng`, `canshujian`, `canshuzhi`, `beizhu`) VALUES
('预约最大人数', 'max_appointment', '20', '每个教练每个时段最大预约人数'),
('考试通过分数线', 'pass_score', '90', '科目一和科目四考试通过分数线'),
('视频点赞上限', 'max_likes', '10', '每个用户每天视频点赞上限'),
('系统名称', 'system_name', '南京市雨花驾校管理信息系统', '系统显示名称'),
('联系电话', 'contact_phone', '025-12345678', '系统联系电话'),
('系统地址', 'system_address', '南京市雨花台区软件大道109号', '系统地址');

-- 插入排班规则数据
INSERT INTO `paibanguize` (`guizemingcheng`, `kaishishijian`, `jieshushijian`, `keyuyuerenshu`, `shiyongjiaolian`) VALUES
('上午班次', '08:00', '11:30', 5, '所有教练'),
('下午班次', '14:00', '17:30', 5, '所有教练'),
('晚班班次', '18:00', '20:30', 3, '所有教练');
