package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ShouYeXinXiEntity;
import com.entity.view.ShouYeXinXiView;

import com.service.ShouYeXinXiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 首页信息
 * 后端接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@RestController
@RequestMapping("/shouyexinxi")
public class ShouYeXinXiController {
    @Autowired
    private ShouYeXinXiService shouYeXinXiService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShouYeXinXiEntity shouYeXinXi,
		HttpServletRequest request){
        EntityWrapper<ShouYeXinXiEntity> ew = new EntityWrapper<ShouYeXinXiEntity>();
		PageUtils page = shouYeXinXiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shouYeXinXi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShouYeXinXiEntity shouYeXinXi, 
		HttpServletRequest request){
        EntityWrapper<ShouYeXinXiEntity> ew = new EntityWrapper<ShouYeXinXiEntity>();
		PageUtils page = shouYeXinXiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shouYeXinXi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShouYeXinXiEntity shouYeXinXi){
        	EntityWrapper<ShouYeXinXiEntity> ew = new EntityWrapper<ShouYeXinXiEntity>();
       	ew.allEq(MPUtil.allEQMapPre( shouYeXinXi, "shouYeXinXi")); 
        return R.ok().put("data", shouYeXinXiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShouYeXinXiEntity shouYeXinXi){
        EntityWrapper< ShouYeXinXiEntity> ew = new EntityWrapper< ShouYeXinXiEntity>();
		ew.allEq(MPUtil.allEQMapPre( shouYeXinXi, "shouYeXinXi")); 
		ShouYeXinXiView shouYeXinXiView =  shouYeXinXiService.selectView(ew);
		return R.ok("查询首页信息成功").put("data", shouYeXinXiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShouYeXinXiEntity shouYeXinXi = shouYeXinXiService.selectById(id);
        return R.ok().put("data", shouYeXinXi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShouYeXinXiEntity shouYeXinXi = shouYeXinXiService.selectById(id);
        return R.ok().put("data", shouYeXinXi);
    }
    
    /**
     * 前端首页信息
     */
	@IgnoreAuth
    @RequestMapping("/shouye")
    public R shouye(){
        Map<String, Object> map = new HashMap<>();
        // 获取驾校概况
        EntityWrapper<ShouYeXinXiEntity> ew1 = new EntityWrapper<ShouYeXinXiEntity>();
        ew1.eq("leixing", "驾校概况").eq("zhuangtai", "启用").orderBy("paixu", true);
        List<ShouYeXinXiEntity> jiaxiaogaikuang = shouYeXinXiService.selectList(ew1);
        map.put("jiaxiaogaikuang", jiaxiaogaikuang);
        
        // 获取教练信息
        EntityWrapper<ShouYeXinXiEntity> ew2 = new EntityWrapper<ShouYeXinXiEntity>();
        ew2.eq("leixing", "教练信息").eq("zhuangtai", "启用").orderBy("paixu", true);
        List<ShouYeXinXiEntity> jiaolianxinxi = shouYeXinXiService.selectList(ew2);
        map.put("jiaolianxinxi", jiaolianxinxi);
        
        // 获取报名须知
        EntityWrapper<ShouYeXinXiEntity> ew3 = new EntityWrapper<ShouYeXinXiEntity>();
        ew3.eq("leixing", "报名须知").eq("zhuangtai", "启用").orderBy("paixu", true);
        List<ShouYeXinXiEntity> baomingxuzhi = shouYeXinXiService.selectList(ew3);
        map.put("baomingxuzhi", baomingxuzhi);
        
        return R.ok().put("data", map);
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShouYeXinXiEntity shouYeXinXi, HttpServletRequest request){
    	shouYeXinXi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shouYeXinXi);
        shouYeXinXiService.insert(shouYeXinXi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShouYeXinXiEntity shouYeXinXi, HttpServletRequest request){
    	shouYeXinXi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shouYeXinXi);
        shouYeXinXiService.insert(shouYeXinXi);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ShouYeXinXiEntity shouYeXinXi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shouYeXinXi);
        shouYeXinXiService.updateById(shouYeXinXi);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shouYeXinXiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
			 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<ShouYeXinXiEntity> wrapper = new EntityWrapper<ShouYeXinXiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = shouYeXinXiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}