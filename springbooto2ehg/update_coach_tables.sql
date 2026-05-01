-- 更新课程安排表，添加上课状态字段
ALTER TABLE `kechenganpai` ADD COLUMN `shangkezt` varchar(200) DEFAULT '待上课' COMMENT '上课状态' AFTER `jiaolianxingming`;

-- 更新现有数据，为新字段添加默认值
UPDATE `kechenganpai` SET `shangkezt` = '待上课' WHERE `shangkezt` IS NULL;

-- 添加教练排班同步记录表
CREATE TABLE `paibanshiyong` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoliangonghao` varchar(200) NOT NULL COMMENT '教练工号',
  `jiaolianxingming` varchar(200) NOT NULL COMMENT '教练姓名',
  `paibanshijian` datetime DEFAULT NULL COMMENT '排班时间',
  `paibanqingkuang` longtext COMMENT '排班情况',
  `tongbusj` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '同步时间',
  `tongbuzhuangtai` varchar(200) DEFAULT '已同步' COMMENT '同步状态',
  PRIMARY KEY (`id`),
  KEY `jiaoliangonghao` (`jiaoliangonghao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教练排班使用';
