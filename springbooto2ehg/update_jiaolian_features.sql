-- ============================================
-- 教练端功能扩展SQL脚本
-- ============================================

-- 1. 给教练表添加个人简介和教学特色字段
ALTER TABLE `jiaolian` ADD COLUMN `gerenjianjie` longtext COMMENT '个人简介' AFTER `touxiang`;
ALTER TABLE `jiaolian` ADD COLUMN `jiaoxuetese` longtext COMMENT '教学特色' AFTER `gerenjianjie`;

-- 2. 给预约练车表添加状态字段
ALTER TABLE `yuyuelianche` ADD COLUMN `status` varchar(200) DEFAULT '待审核' COMMENT '审核状态' AFTER `shhf`;

-- 3. 给课程安排表添加上课状态字段（用于排班管理）
ALTER TABLE `kechenganpai` ADD COLUMN `shangkezt` varchar(200) DEFAULT '待上课' COMMENT '上课状态' AFTER `jiaolianxingming`;

-- 4. 更新预约练车的状态字段（基于sfsh字段）
UPDATE `yuyuelianche` SET `status` = CASE WHEN `sfsh` = '是' THEN '已确认' WHEN `sfsh` = '否' THEN '已拒绝' ELSE '待审核' END WHERE `status` IS NULL OR `status` = '待审核';

-- 5. 初始化已存在的预约状态为待审核
UPDATE `yuyuelianche` SET `status` = '待审核' WHERE `sfsh` = '否' AND (`status` IS NULL OR `status` = '');
