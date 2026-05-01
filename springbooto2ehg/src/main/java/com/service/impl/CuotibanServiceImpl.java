package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;

import com.dao.CuotibanDao;
import com.entity.CuotibanEntity;
import com.service.CuotibanService;

/**
 * 错题本
 * 服务实现层
 */
@Service("cuotibanService")
public class CuotibanServiceImpl extends ServiceImpl<CuotibanDao, CuotibanEntity> implements CuotibanService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CuotibanEntity> page = this.selectPage(
                new Query<CuotibanEntity>(params).getPage(),
                new EntityWrapper<CuotibanEntity>()
        );

        return new PageUtils(page);
    }

}
