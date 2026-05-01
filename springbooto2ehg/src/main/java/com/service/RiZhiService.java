package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.RiZhiEntity;
import com.entity.view.RiZhiView;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * 服务层接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
public interface RiZhiService extends IService<RiZhiEntity> {

    /**
    * 查询分页数据
    */
    PageUtils queryPage(Map<String, Object> params);

    /**
    * 查询分页数据（带Wrapper）
    */
    PageUtils queryPage(Map<String, Object> params, Wrapper<RiZhiEntity> wrapper);

    /**
    * 查询所有数据
    */
    List<RiZhiView> selectListView(Wrapper<RiZhiEntity> wrapper);

    /**
    * 查询详情数据
    */
    RiZhiView selectView(Wrapper<RiZhiEntity> wrapper);
}