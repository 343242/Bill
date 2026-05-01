package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.XiTongCanShuEntity;
import com.entity.view.XiTongCanShuView;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 系统参数
 * 服务层接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
public interface XiTongCanShuService extends IService<XiTongCanShuEntity> {

    /**
    * 查询分页数据
    */
    PageUtils queryPage(Map<String, Object> params);

    /**
    * 查询分页数据（带Wrapper）
    */
    PageUtils queryPage(Map<String, Object> params, Wrapper<XiTongCanShuEntity> wrapper);

    /**
    * 查询所有数据
    */
    List<XiTongCanShuView> selectListView(Wrapper<XiTongCanShuEntity> wrapper);

    /**
    * 查询详情数据
    */
    XiTongCanShuView selectView(Wrapper<XiTongCanShuEntity> wrapper);
}