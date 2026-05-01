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

import com.dao.ShipinDao;
import com.entity.ShipinEntity;
import com.service.ShipinService;
import com.entity.view.ShipinView;

/**
 * 视频学习
 * 服务实现类
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@Service("shipinService")
public class ShipinServiceImpl extends ServiceImpl<ShipinDao, ShipinEntity> implements ShipinService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShipinEntity> page = this.selectPage(
                new Query<ShipinEntity>(params).getPage(),
                new EntityWrapper<ShipinEntity>()
        );
        return new PageUtils(page);
    }    
    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ShipinEntity> wrapper) {
        Page<ShipinEntity> page = new Query<ShipinEntity>(params).getPage();
        page.setRecords(baseMapper.selectPage(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }
    @Override
    public List<ShipinView> selectListView(Wrapper<ShipinEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }    
    @Override
    public ShipinView selectView(Wrapper<ShipinEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}