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

import com.dao.ShouYeXinXiDao;
import com.entity.ShouYeXinXiEntity;
import com.service.ShouYeXinXiService;
import com.entity.view.ShouYeXinXiView;

/**
 * 首页信息
 * 服务实现类
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@Service("shouYeXinXiService")
public class ShouYeXinXiServiceImpl extends ServiceImpl<ShouYeXinXiDao, ShouYeXinXiEntity> implements ShouYeXinXiService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShouYeXinXiEntity> page = this.selectPage(
                new Query<ShouYeXinXiEntity>(params).getPage(),
                new EntityWrapper<ShouYeXinXiEntity>()
        );
        return new PageUtils(page);
    }    
    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ShouYeXinXiEntity> wrapper) {
        Page<ShouYeXinXiView> page = new Query<ShouYeXinXiView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }
    @Override
    public List<ShouYeXinXiView> selectListView(Wrapper<ShouYeXinXiEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }    
    @Override
    public ShouYeXinXiView selectView(Wrapper<ShouYeXinXiEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}