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

import com.entity.ShipinpinglunEntity;

import com.service.ShipinpinglunService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 视频评论
 * 后端接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@RestController
@RequestMapping("/shipinpinglun")
public class ShipinpinglunController {
    @Autowired
    private ShipinpinglunService shipinpinglunService;


    
    @Autowired
    private TokenService tokenService;
    
    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShipinpinglunEntity shipinpinglun,
		HttpServletRequest request){
        EntityWrapper<ShipinpinglunEntity> ew = new EntityWrapper<ShipinpinglunEntity>();
		PageUtils page = shipinpinglunService.queryPage(params);

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShipinpinglunEntity shipinpinglun,
		HttpServletRequest request){
        EntityWrapper<ShipinpinglunEntity> ew = new EntityWrapper<ShipinpinglunEntity>();
		PageUtils page = shipinpinglunService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shipinpinglun), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShipinpinglunEntity shipinpinglun){
        	EntityWrapper<ShipinpinglunEntity> ew = new EntityWrapper<ShipinpinglunEntity>();
       	ew.allEq(MPUtil.allEQMapPre( shipinpinglun, "shipinpinglun")); 
        return R.ok().put("data", shipinpinglunService.selectList(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShipinpinglunEntity shipinpinglun){
        EntityWrapper< ShipinpinglunEntity> ew = new EntityWrapper< ShipinpinglunEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shipinpinglun, "shipinpinglun")); 
		ShipinpinglunEntity shipinpinglunEntity = shipinpinglunService.selectOne(ew);
		return R.ok("查询视频评论成功").put("data", shipinpinglunEntity);
    }
    
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShipinpinglunEntity shipinpinglun = shipinpinglunService.selectById(id);
        return R.ok().put("data", shipinpinglun);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShipinpinglunEntity shipinpinglun = shipinpinglunService.selectById(id);
        return R.ok().put("data", shipinpinglun);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShipinpinglunEntity shipinpinglun, HttpServletRequest request){
    	shipinpinglun.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shipinpinglun);
    	shipinpinglun.setPingluntime(new Date());
        shipinpinglunService.insert(shipinpinglun);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShipinpinglunEntity shipinpinglun, HttpServletRequest request){
    	shipinpinglun.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(shipinpinglun);
    	shipinpinglun.setPingluntime(new Date());
    	// 保存评论人id
    	shipinpinglun.setPinglunrenid((Long)request.getSession().getAttribute("userId"));
        shipinpinglunService.insert(shipinpinglun);
        return R.ok();
    }

    /**
     * 回复评论
     */
    @RequestMapping("/reply")
    public R reply(@RequestBody ShipinpinglunEntity shipinpinglun, HttpServletRequest request){
    	// 创建新的评论作为回复
    	ShipinpinglunEntity replyComment = new ShipinpinglunEntity();
    	replyComment.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	replyComment.setShipinid(shipinpinglun.getShipinid());
    	replyComment.setPinglunneirong(shipinpinglun.getPinglunneirong());
    	replyComment.setPinglunrenid((Long)request.getSession().getAttribute("userId"));
    	replyComment.setPinglunren(shipinpinglun.getPinglunren());
    	replyComment.setPingluntime(new Date());
    	replyComment.setParentid(shipinpinglun.getParentid()); // 父评论id
        shipinpinglunService.insert(replyComment);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ShipinpinglunEntity shipinpinglun, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shipinpinglun);
        shipinpinglunService.updateById(shipinpinglun);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shipinpinglunService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<ShipinpinglunEntity> wrapper = new EntityWrapper<ShipinpinglunEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = shipinpinglunService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
    






}
