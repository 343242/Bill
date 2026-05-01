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

import com.dao.RiZhiDao;
import com.entity.RiZhiEntity;
import com.service.RiZhiService;
import com.entity.view.RiZhiView;

/**
 * 系统日志
 * 服务实现类
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@Service("riZhiService")
public class RiZhiServiceImpl extends ServiceImpl<RiZhiDao, RiZhiEntity> implements RiZhiService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RiZhiEntity> page = this.selectPage(
                new Query<RiZhiEntity>(params).getPage(),
                new EntityWrapper<RiZhiEntity>()
        );
        return new PageUtils(page);
    }    
    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<RiZhiEntity> wrapper) {
        Page<RiZhiView> page = new Query<RiZhiView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }
    @Override
    public List<RiZhiView> selectListView(Wrapper<RiZhiEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }    
    @Override
    public RiZhiView selectView(Wrapper<RiZhiEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}