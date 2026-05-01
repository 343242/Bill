package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.PaiBanGuiZeEntity;
import com.entity.view.PaiBanGuiZeView;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 排班规则
 * 服务层接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
public interface PaiBanGuiZeService extends IService<PaiBanGuiZeEntity> {

    /**
    * 查询分页数据
    */
    PageUtils queryPage(Map<String, Object> params);

    /**
    * 查询分页数据（带Wrapper）
    */
    PageUtils queryPage(Map<String, Object> params, Wrapper<PaiBanGuiZeEntity> wrapper);

    /**
    * 查询所有数据
    */
    List<PaiBanGuiZeView> selectListView(Wrapper<PaiBanGuiZeEntity> wrapper);

    /**
    * 查询详情数据
    */
    PaiBanGuiZeView selectView(Wrapper<PaiBanGuiZeEntity> wrapper);
}