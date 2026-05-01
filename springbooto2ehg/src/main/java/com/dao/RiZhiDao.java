package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.RiZhiEntity;
import com.entity.view.RiZhiView;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

/**
 * 系统日志
 * 数据库访问层
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
public interface RiZhiDao extends BaseMapper<RiZhiEntity> {

    List<RiZhiView> selectListView(Wrapper<RiZhiEntity> wrapper);

    List<RiZhiView> selectListView(Pagination page, Wrapper<RiZhiEntity> wrapper);

    RiZhiView selectView(Wrapper<RiZhiEntity> wrapper);

}