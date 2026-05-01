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

import com.dao.XiTongCanShuDao;
import com.entity.XiTongCanShuEntity;
import com.service.XiTongCanShuService;
import com.entity.view.XiTongCanShuView;

/**
 * 系统参数
 * 服务实现类
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@Service("xiTongCanShuService")
public class XiTongCanShuServiceImpl extends ServiceImpl<XiTongCanShuDao, XiTongCanShuEntity> implements XiTongCanShuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XiTongCanShuEntity> page = this.selectPage(
                new Query<XiTongCanShuEntity>(params).getPage(),
                new EntityWrapper<XiTongCanShuEntity>()
        );
        return new PageUtils(page);
    }    
    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<XiTongCanShuEntity> wrapper) {
        Page<XiTongCanShuView> page = new Query<XiTongCanShuView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }
    @Override
    public List<XiTongCanShuView> selectListView(Wrapper<XiTongCanShuEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }    
    @Override
    public XiTongCanShuView selectView(Wrapper<XiTongCanShuEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}