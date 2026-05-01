package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.ShipinpinglunDao;
import com.entity.ShipinpinglunEntity;
import com.service.ShipinpinglunService;

/**
 * 视频评论
 * 服务实现层
 */
@Service("shipinpinglunService")
public class ShipinpinglunServiceImpl extends ServiceImpl<ShipinpinglunDao, ShipinpinglunEntity> implements ShipinpinglunService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShipinpinglunEntity> page = this.selectPage(
                new Query<ShipinpinglunEntity>(params).getPage(),
                new EntityWrapper<ShipinpinglunEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<ShipinpinglunEntity> wrapper) {
        Page<ShipinpinglunEntity> page = this.selectPage(
                new Query<ShipinpinglunEntity>(params).getPage(),
                wrapper
        );
        return new PageUtils(page);
    }

}
