-- ============================================
-- 驾校管理信息系统 - 数据库调整脚本
-- 根据需求文档进行调整
-- 执行前请先备份数据库
-- ============================================

USE `springbooto2ehg`;

-- ============================================
-- 1. 排班规则表
-- ============================================
DROP TABLE IF EXISTS `paibanguize`;
CREATE TABLE `paibanguize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `guizemingcheng` varchar(200) DEFAULT NULL COMMENT '规则名称',
  `kaishishijian` time DEFAULT NULL COMMENT '开始时间',
  `jieshushijian` time DEFAULT NULL COMMENT '结束时间',
  `keyuyuerenshu` int(11) DEFAULT NULL COMMENT '可预约人数',
  `shiyongjiaolian` varchar(200) DEFAULT NULL COMMENT '适用教练',
  `zhuangtai` varchar(200) DEFAULT '启用' COMMENT '状态',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='排班规则';

-- 插入测试数据
INSERT INTO `paibanguize` (`id`, `guizemingcheng`, `kaishishijian`, `jieshushijian`, `keyuyuerenshu`, `shiyongjiaolian`, `zhuangtai`, `addtime`) VALUES 
(1, '上午班', '08:00:00', '12:00:00', 4, '全部教练', '启用', NOW()),
(2, '下午班', '14:00:00', '18:00:00', 4, '全部教练', '启用', NOW()),
(3, '晚班', '19:00:00', '21:00:00', 3, '全部教练', '启用', NOW());

-- ============================================
-- 2. 首页信息表
-- ============================================
DROP TABLE IF EXISTS `shouyexinxi`;
CREATE TABLE `shouyexinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `leixing` varchar(200) DEFAULT NULL COMMENT '信息类型',
  `biaoti` varchar(200) DEFAULT NULL COMMENT '标题',
  `neirong` longtext COMMENT '内容',
  `tupian` varchar(200) DEFAULT NULL COMMENT '图片',
  `paixu` int(11) DEFAULT 0 COMMENT '排序',
  `zhuangtai` varchar(200) DEFAULT '显示' COMMENT '状态',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页信息';

-- 插入测试数据
INSERT INTO `shouyexinxi` (`id`, `leixing`, `biaoti`, `neirong`, `tupian`, `paixu`, `zhuangtai`, `addtime`) VALUES 
(1, '驾校概况', '驾校介绍', '<p>本校是一所经交通部门认证的正规驾校，拥有多年的办学历史和丰富的教学经验。学校占地100余亩，拥有标准化的训练场地和完善的教学设施。</p><p>办学理念：以人为本，安全第一，服务至上</p><p>教学目标：培养合格的驾驶员，提高道路交通安全意识</p>', 'upload/jiaxiaojieshao.jpg', 1, '显示', NOW()),
(2, '驾校概况', '驾校荣誉', '<p>省级优秀驾校</p><p>AAA级信用单位</p><p>文明诚信驾校</p>', 'upload/jiaxiaorongyu.jpg', 2, '显示', NOW());

-- ============================================
-- 3. 系统参数表
-- ============================================
DROP TABLE IF EXISTS `xitongcanshu`;
CREATE TABLE `xitongcanshu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `canshumingcheng` varchar(200) DEFAULT NULL COMMENT '参数名称',
  `canshujian` varchar(200) DEFAULT NULL COMMENT '参数键',
  `canshuzhi` varchar(200) DEFAULT NULL COMMENT '参数值',
  `beizhu` varchar(500) DEFAULT NULL COMMENT '备注',
  `zhuangtai` varchar(200) DEFAULT '启用' COMMENT '状态',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数';

-- 插入测试数据
INSERT INTO `xitongcanshu` (`id`, `canshumingcheng`, `canshujian`, `canshuzhi`, `beizhu`, `zhuangtai`, `addtime`) VALUES 
(1, '预约名额配置', 'yuyue_mingee', '4', '每个时段最大可预约人数', '启用', NOW()),
(2, '取消预约限制', 'quxiao_shixiao', '2', '提前多少小时可以取消预约(小时)', '启用', NOW()),
(3, '考试时长', 'kaoshi_shichang', '60', '理论考试时长(分钟)', '启用', NOW()),
(4, '练车预约开放天数', 'yuyue_kaifang_tianshu', '7', '提前多少天开放预约', '启用', NOW());

-- ============================================
-- 4. 错题本表
-- ============================================
DROP TABLE IF EXISTS `cuotiban`;
CREATE TABLE `cuotiban` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `zhanghao` varchar(200) DEFAULT NULL COMMENT '学员账号',
  `timuid` bigint(20) DEFAULT NULL COMMENT '题目id',
  `timuneirong` longtext COMMENT '题目内容',
  `cuowudaan` varchar(500) DEFAULT NULL COMMENT '错误答案',
  `zhengquedaan` varchar(500) DEFAULT NULL COMMENT '正确答案',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='错题本';

-- 插入测试数据
INSERT INTO `cuotiban` (`id`, `zhanghao`, `timuid`, `timuneirong`, `cuowudaan`, `zhengquedaan`, `addtime`) VALUES 
(1, '111', 1, '机动车在夜间没有路灯、照明不良等情况下行驶时，应开启什么灯？', '近光灯', '示廓灯', NOW()),
(2, '111', 2, '驾驶机动车遇到沙尘、冰雹、雨、雾、结冰等气象条件如何行驶？', '加速行驶', '降低行驶速度', NOW());

-- ============================================
-- 5. 视频学习表
-- ============================================
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频学习';

-- 插入测试数据
INSERT INTO `shipin` (`id`, `addtime`, `biaoti`, `fenlei`, `lianjie`, `fengmian`, `miaoshu`, `dianzanshu`, `shoucangshu`, `clicknum`) VALUES 
(1, NOW(), '科目一交通标志详解', '科目一', 'upload/shipin1.mp4', 'upload/shipin1.jpg', '详细介绍科目一考试中常见的交通标志，包括指示标志、警告标志、禁令标志等。', 15, 8, 256),
(2, NOW(), '科目二倒车入库技巧', '科目二', 'upload/shipin2.mp4', 'upload/shipin2.jpg', '倒车入库是科目二最难的项目之一，本视频详细讲解操作步骤和技巧要点。', 22, 12, 345),
(3, NOW(), '科目三路考注意事项', '科目三', 'upload/shipin3.mp4', 'upload/shipin3.jpg', '路考是取得驾照的最后一道关卡，本视频总结常见扣分点和注意事项。', 18, 10, 289),
(4, NOW(), '安全驾驶基础教程', '安全教育', 'upload/shipin4.mp4', 'upload/shipin4.jpg', '安全驾驶是每个驾驶员必须掌握的知识，本视频介绍基本安全驾驶常识。', 25, 15, 412),
(5, NOW(), '夜间驾驶灯光使用', '安全教育', 'upload/shipin5.mp4', 'upload/shipin5.jpg', '夜间行车灯光使用是重点内容，本视频详细讲解各种灯光的正确使用方法。', 12, 6, 198);

-- ============================================
-- 6. 视频评论表
-- ============================================
DROP TABLE IF EXISTS `shipinpinglun`;
CREATE TABLE `shipinpinglun` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shipinid` bigint(20) DEFAULT NULL COMMENT '视频id',
  `pinglunneirong` longtext COMMENT '评论内容',
  `pinglunren` varchar(200) DEFAULT NULL COMMENT '评论人',
  `pingluntime` datetime DEFAULT NULL COMMENT '评论时间',
  `huifuneirong` longtext COMMENT '回复内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频评论';

-- 插入测试数据
INSERT INTO `shipinpinglun` (`id`, `addtime`, `shipinid`, `pinglunneirong`, `pinglunren`, `pingluntime`, `huifuneirong`) VALUES 
(1, NOW(), 1, '讲解非常详细，对我帮助很大！', '学员小王', NOW(), '感谢您的支持，我们会继续完善视频内容！'),
(2, NOW(), 1, '希望能再多一些实例分析', '学员小李', NOW(), NULL),
(3, NOW(), 2, '终于明白了倒车入库的技巧', '学员张三', NOW(), NULL),
(4, NOW(), 3, '路考注意事项总结很全面', '学员李四', NOW(), '祝您考试顺利！');

-- ============================================
-- 7. 更新菜单表数据
-- ============================================
-- 更新菜单表中的菜单JSON配置
UPDATE menu SET menujson = '[{\"backMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"学员\",\"menuJump\":\"列表\",\"tableName\":\"xueyuan\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"教练\",\"menuJump\":\"列表\",\"tableName\":\"jiaolian\"},{\"appFrontIcon\":\"cuIcon-discover\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"驾校信息\",\"menuJump\":\"列表\",\"tableName\":\"jiaxiaoxinxi\"},{\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"报名须知\",\"menuJump\":\"列表\",\"tableName\":\"news\"},{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\",\"删除\",\"审核\",\"报表\"],\"menu\":\"报名信息\",\"menuJump\":\"列表\",\"tableName\":\"baomingxinxi\"},{\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"车辆信息\",\"menuJump\":\"列表\",\"tableName\":\"cheliangxinxi\"},{\"appFrontIcon\":\"cuIcon-list\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"财务信息\",\"menuJump\":\"列表\",\"tableName\":\"caiwuxinxi\"]}],\"menu\":\"基础信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-settings\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"排班规则管理\",\"menuJump\":\"列表\",\"tableName\":\"paiBanguiZe\"},{\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"排班计划管理\",\"menuJump\":\"列表\",\"tableName\":\"kechenganpai\"]}],\"menu\":\"教练排班统筹管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-video\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"理论视频管理\",\"menuJump\":\"列表\",\"tableName\":\"shipin\"},{\"appFrontIcon\":\"cuIcon-message\",\"buttons\":[\"查看\",\"回复\",\"删除\"],\"menu\":\"视频评论管理\",\"menuJump\":\"列表\",\"tableName\":\"shipinpinglun\"},{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"练习题库管理\",\"menuJump\":\"列表\",\"tableName\":\"exampaper\"},{\"appFrontIcon\":\"cuIcon-taxi\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"试题管理\",\"tableName\":\"examquestion\"},{\"appFrontIcon\":\"cuIcon-attentionfavor\",\"buttons\":[\"查看\",\"删除\"],\"menu\":\"考试记录\",\"tableName\":\"examrecord\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\",\"删除\",\"修改\"],\"menu\":\"错题本管理\",\"tableName\":\"cuotiban\"]}],\"menu\":\"理论学习考试统筹管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"查看\",\"修改\"],\"menu\":\"预约名额配置\",\"menuJump\":\"列表\",\"tableName\":\"xiTongCanShu\"},{\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"查看\",\"删除\",\"审核\"],\"menu\":\"预约信息查询\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"},{\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"查看\",\"删除\",\"审核\"],\"menu\":\"预约取消管理\",\"menuJump\":\"列表\",\"tableName\":\"quxiaokecheng\"]}],\"menu\":\"练车预约统筹管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-newshot\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"首页信息管理\",\"menuJump\":\"列表\",\"tableName\":\"shouYeXinXi\"},{\"appFrontIcon\":\"cuIcon-card\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"首页轮播图\",\"menuJump\":\"列表\",\"tableName\":\"config\"]}],\"menu\":\"首页信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-settings\",\"buttons\":[\"查看\",\"修改\"],\"menu\":\"系统参数配置\",\"menuJump\":\"列表\",\"tableName\":\"xiTongCanShu\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\",\"删除\"],\"menu\":\"操作日志\",\"menuJump\":\"列表\",\"tableName\":\"riZhi\"},{\"appFrontIcon\":\"cuIcon-attentionfavor\",\"buttons\":[\"查看\",\"编辑名称\",\"编辑父级\",\"删除\"],\"menu\":\"菜单列表\",\"tableName\":\"menu\"},{\"appFrontIcon\":\"cuIcon-pic\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"资源备份\",\"tableName\":\"ziyuanbeifen\"]}],\"menu\":\"系统管理\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-newshot\",\"buttons\":[\"查看\"],\"menu\":\"驾校概况\",\"menuJump\":\"列表\",\"tableName\":\"jiaxiaoxinxi\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\"],\"menu\":\"教练信息\",\"menuJump\":\"列表\",\"tableName\":\"jiaolian\"},{\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"查看\"],\"menu\":\"报名须知\",\"menuJump\":\"列表\",\"tableName\":\"news\"]}],\"menu\":\"首页信息浏览\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"管理员\",\"tableName\":\"users\"},{\"backMenu\":[],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-person\",\"buttons\":[\"查看\",\"修改\"],\"menu\":\"个人信息\",\"menuJump\":\"列表\",\"tableName\":\"xueyuan\"]}],\"menu\":\"个人信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-video\",\"buttons\":[\"查看\"],\"menu\":\"理论视频学习\",\"menuJump\":\"列表\",\"tableName\":\"shipin\"},{\"appFrontIcon\":\"cuIcon-favor\",\"buttons\":[\"查看\",\"删除\"],\"menu\":\"视频互动管理\",\"menuJump\":\"列表\",\"tableName\":\"storeup\"},{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\"],\"menu\":\"练习题库\",\"menuJump\":\"列表\",\"tableName\":\"exampaper\"},{\"appFrontIcon\":\"cuIcon-attentionfavor\",\"buttons\":[\"查看\"],\"menu\":\"理论考试\",\"menuJump\":\"列表\",\"tableName\":\"examrecord\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\"],\"menu\":\"错题本\",\"menuJump\":\"列表\",\"tableName\":\"cuotiban\"]}],\"menu\":\"理论学习考试\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"新增\",\"查看\",\"删除\"],\"menu\":\"时段预约申请\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"},{\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"查看\"],\"menu\":\"预约记录查询\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"]}],\"menu\":\"练车预约申请\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"是\",\"hasFrontRegister\":\"是\",\"roleName\":\"学员\",\"tableName\":\"xueyuan\"},{\"backMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-person\",\"buttons\":[\"查看\",\"修改\"],\"menu\":\"个人信息\",\"menuJump\":\"列表\",\"tableName\":\"jiaolian\"]}],\"menu\":\"个人信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"查看\",\"修改\"],\"menu\":\"个人排班查询\",\"menuJump\":\"列表\",\"tableName\":\"kechenganpai\"]}],\"menu\":\"教练排班查看管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-rank\",\"buttons\":[\"查看\"],\"menu\":\"学员管理\",\"menuJump\":\"列表\",\"tableName\":\"xueyuan\"]}],\"menu\":\"学员管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"查看\",\"审核\"],\"menu\":\"预约审核\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"},{\"appFrontIcon\":\"cuIcon-time\",\"buttons\":[\"查看\"],\"menu\":\"待审核预约查询\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"},{\"appFrontIcon\":\"cuIcon-card\",\"buttons\":[\"查看\"],\"menu\":\"已确认预约列表\",\"menuJump\":\"列表\",\"tableName\":\"yuyuelianche\"]}],\"menu\":\"练车预约审核\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-newshot\",\"buttons\":[\"查看\"],\"menu\":\"驾校概况\",\"menuJump\":\"列表\",\"tableName\":\"jiaxiaoxinxi\"},{\"appFrontIcon\":\"cuIcon-brand\",\"buttons\":[\"查看\"],\"menu\":\"教练信息\",\"menuJump\":\"列表\",\"tableName\":\"jiaolian\"]}],\"menu\":\"首页信息浏览\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"是\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"教练\",\"tableName\":\"jiaolian\"}]' WHERE id = 1;

-- ============================================
-- 8. 添加测试账号
-- ============================================
-- 管理员账号: abo / abo (已存在)
-- 学员账号: 111 / 111 (已存在)
-- 教练账号: 222 / 222 (已存在)

-- 如需添加更多学员
-- INSERT INTO xueyuan (zhanghao, mima, xingming, xingbie, nianling, shouji) VALUES ('stu001', '123456', '测试学员', '男', 20, '13800138001');

-- ============================================
-- 9. 更新课程安排表，添加排班相关字段
-- ============================================
-- 检查列是否存在，如果不存在则添加
SET @exist := (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'kechenganpai' AND COLUMN_NAME = 'paiBanriqi');
SET @sqlstmt := IF(@exist > 0, 'SELECT ''Column already exists'' as msg', 'ALTER TABLE kechenganpai ADD COLUMN paiBanriqi date DEFAULT NULL COMMENT ''排班日期'' AFTER shangkezt');
PREPARE stmt FROM @sqlstmt;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @exist := (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'kechenganpai' AND COLUMN_NAME = 'shijianduan');
SET @sqlstmt := IF(@exist > 0, 'SELECT ''Column already exists'' as msg', 'ALTER TABLE kechenganpai ADD COLUMN shijianduan varchar(200) DEFAULT NULL COMMENT ''时间段'' AFTER paiBanriqi');
PREPARE stmt FROM @sqlstmt;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- ============================================
-- 10. 更新预约练车表，添加状态字段
-- ============================================
SET @exist := (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'yuyuelianche' AND COLUMN_NAME = 'yuyuezhuangtai');
SET @sqlstmt := IF(@exist > 0, 'SELECT ''Column already exists'' as msg', 'ALTER TABLE yuyuelianche ADD COLUMN yuyuezhuangtai varchar(200) DEFAULT ''待审核'' COMMENT ''预约状态'' AFTER shhf');
PREPARE stmt FROM @sqlstmt;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- ============================================
-- 11. 更新视频评论表，添加回复字段
-- ============================================
SET @exist := (SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'shipinpinglun' AND COLUMN_NAME = 'huifuneirong');
SET @sqlstmt := IF(@exist > 0, 'SELECT ''Column already exists'' as msg', 'ALTER TABLE shipinpinglun ADD COLUMN huifuneirong longtext COMMENT ''回复内容'' AFTER pingluntime');
PREPARE stmt FROM @sqlstmt;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- ============================================
-- 执行完成
-- ============================================
