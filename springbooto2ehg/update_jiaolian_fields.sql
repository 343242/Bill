-- 更新教练表，添加个人简介和教学特色字段
ALTER TABLE `jiaolian` ADD COLUMN `gerenjianjie` longtext COMMENT '个人简介' AFTER `touxiang`;
ALTER TABLE `jiaolian` ADD COLUMN `jiaoxuetese` longtext COMMENT '教学特色' AFTER `gerenjianjie`;

-- 更新现有数据，为新字段添加默认值
UPDATE `jiaolian` SET `gerenjianjie` = CONCAT('个人简介', SUBSTRING_INDEX(`jiaoliangonghao`, '工号', -1)), `jiaoxuetese` = CONCAT('教学特色', SUBSTRING_INDEX(`jiaoliangonghao`, '工号', -1)) WHERE `gerenjianjie` IS NULL;
