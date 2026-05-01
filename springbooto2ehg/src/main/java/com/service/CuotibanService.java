package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.CuotibanEntity;
import com.utils.PageUtils;

import java.util.Map;

/**
 * 错题本
 * 服务层
 */
public interface CuotibanService extends IService<CuotibanEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
