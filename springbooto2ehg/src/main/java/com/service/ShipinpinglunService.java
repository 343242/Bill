package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.ShipinpinglunEntity;
import com.utils.PageUtils;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.Map;

/**
 * 视频评论
 * 服务层
 */
public interface ShipinpinglunService extends IService<ShipinpinglunEntity> {
    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPage(Map<String, Object> params, Wrapper<ShipinpinglunEntity> wrapper);
}
