package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.ShouYeXinXiEntity;
import com.entity.view.ShouYeXinXiView;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

/**
 * 首页信息
 * 数据库访问层
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
public interface ShouYeXinXiDao extends BaseMapper<ShouYeXinXiEntity> {

    List<ShouYeXinXiView> selectListView(Wrapper<ShouYeXinXiEntity> wrapper);

    List<ShouYeXinXiView> selectListView(Pagination page, Wrapper<ShouYeXinXiEntity> wrapper);

    ShouYeXinXiView selectView(Wrapper<ShouYeXinXiEntity> wrapper);

}