package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.PaiBanGuiZeDao;
import com.entity.PaiBanGuiZeEntity;
import com.service.PaiBanGuiZeService;
import com.entity.view.PaiBanGuiZeView;

/**
 * 排班规则
 * 服务实现类
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@Service("paiBanGuiZeService")
public class PaiBanGuiZeServiceImpl extends ServiceImpl<PaiBanGuiZeDao, PaiBanGuiZeEntity> implements PaiBanGuiZeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PaiBanGuiZeEntity> page = this.selectPage(
                new Query<PaiBanGuiZeEntity>(params).getPage(),
                new EntityWrapper<PaiBanGuiZeEntity>()
        );
        return new PageUtils(page);
    }    
    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<PaiBanGuiZeEntity> wrapper) {
        Page<PaiBanGuiZeView> page = new Query<PaiBanGuiZeView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }
    @Override
    public List<PaiBanGuiZeView> selectListView(Wrapper<PaiBanGuiZeEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }    
    @Override
    public PaiBanGuiZeView selectView(Wrapper<PaiBanGuiZeEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}