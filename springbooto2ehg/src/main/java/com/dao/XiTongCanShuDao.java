package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.XiTongCanShuEntity;
import com.entity.view.XiTongCanShuView;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

/**
 * 系统参数
 * 数据库访问层
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
public interface XiTongCanShuDao extends BaseMapper<XiTongCanShuEntity> {

    List<XiTongCanShuView> selectListView(Wrapper<XiTongCanShuEntity> wrapper);

    List<XiTongCanShuView> selectListView(Pagination page, Wrapper<XiTongCanShuEntity> wrapper);

    XiTongCanShuView selectView(Wrapper<XiTongCanShuEntity> wrapper);

}