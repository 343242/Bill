package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.PaiBanGuiZeEntity;
import com.entity.view.PaiBanGuiZeView;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

/**
 * 排班规则
 * 数据库访问层
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
public interface PaiBanGuiZeDao extends BaseMapper<PaiBanGuiZeEntity> {

    List<PaiBanGuiZeView> selectListView(Wrapper<PaiBanGuiZeEntity> wrapper);

    List<PaiBanGuiZeView> selectListView(Pagination page, Wrapper<PaiBanGuiZeEntity> wrapper);

    PaiBanGuiZeView selectView(Wrapper<PaiBanGuiZeEntity> wrapper);

}