package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.ShipinEntity;
import com.entity.view.ShipinView;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

/**
 * 视频学习
 * 数据库访问层
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
public interface ShipinDao extends BaseMapper<ShipinEntity> {

    List<ShipinView> selectListView(Wrapper<ShipinEntity> wrapper);

    List<ShipinView> selectListView(Pagination page, Wrapper<ShipinEntity> wrapper);

    ShipinView selectView(Wrapper<ShipinEntity> wrapper);

}