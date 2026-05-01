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

import com.entity.PaiBanGuiZeEntity;
import com.entity.view.PaiBanGuiZeView;

import com.service.PaiBanGuiZeService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 排班规则
 * 后端接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@RestController
@RequestMapping("/paibanguize")
public class PaiBanGuiZeController {
    @Autowired
    private PaiBanGuiZeService paiBanGuiZeService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PaiBanGuiZeEntity paiBanGuiZe,
		HttpServletRequest request){
        EntityWrapper<PaiBanGuiZeEntity> ew = new EntityWrapper<PaiBanGuiZeEntity>();
		PageUtils page = paiBanGuiZeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, paiBanGuiZe), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,PaiBanGuiZeEntity paiBanGuiZe, 
		HttpServletRequest request){
        EntityWrapper<PaiBanGuiZeEntity> ew = new EntityWrapper<PaiBanGuiZeEntity>();
		PageUtils page = paiBanGuiZeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, paiBanGuiZe), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PaiBanGuiZeEntity paiBanGuiZe){
        	EntityWrapper<PaiBanGuiZeEntity> ew = new EntityWrapper<PaiBanGuiZeEntity>();
       	ew.allEq(MPUtil.allEQMapPre( paiBanGuiZe, "paiBanGuiZe")); 
        return R.ok().put("data", paiBanGuiZeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PaiBanGuiZeEntity paiBanGuiZe){
        EntityWrapper< PaiBanGuiZeEntity> ew = new EntityWrapper< PaiBanGuiZeEntity>();
		ew.allEq(MPUtil.allEQMapPre( paiBanGuiZe, "paiBanGuiZe")); 
		PaiBanGuiZeView paiBanGuiZeView =  paiBanGuiZeService.selectView(ew);
		return R.ok("查询排班规则成功").put("data", paiBanGuiZeView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PaiBanGuiZeEntity paiBanGuiZe = paiBanGuiZeService.selectById(id);
        return R.ok().put("data", paiBanGuiZe);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PaiBanGuiZeEntity paiBanGuiZe = paiBanGuiZeService.selectById(id);
        return R.ok().put("data", paiBanGuiZe);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PaiBanGuiZeEntity paiBanGuiZe, HttpServletRequest request){
    	paiBanGuiZe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(paiBanGuiZe);
        paiBanGuiZeService.insert(paiBanGuiZe);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody PaiBanGuiZeEntity paiBanGuiZe, HttpServletRequest request){
    	paiBanGuiZe.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(paiBanGuiZe);
        paiBanGuiZeService.insert(paiBanGuiZe);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PaiBanGuiZeEntity paiBanGuiZe, HttpServletRequest request){
        //ValidatorUtils.validateEntity(paiBanGuiZe);
        paiBanGuiZeService.updateById(paiBanGuiZe);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        paiBanGuiZeService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<PaiBanGuiZeEntity> wrapper = new EntityWrapper<PaiBanGuiZeEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = paiBanGuiZeService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}