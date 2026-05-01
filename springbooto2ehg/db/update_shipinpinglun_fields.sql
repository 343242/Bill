-- 为 shipinpinglun 表添加新字段
ALTER TABLE shipinpinglun ADD COLUMN pinglunrenid BIGINT DEFAULT NULL COMMENT '评论人id';
ALTER TABLE shipinpinglun ADD COLUMN parentid BIGINT DEFAULT NULL COMMENT '父评论id';
