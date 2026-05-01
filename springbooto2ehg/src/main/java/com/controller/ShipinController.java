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

import com.entity.ShipinEntity;
import com.entity.ShipinpinglunEntity;
import com.entity.view.ShipinView;
import com.service.ShipinpinglunService;

import com.service.ShipinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 视频学习
 * 后端接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@RestController
@RequestMapping("/shipin")
public class ShipinController {
    @Autowired
    private ShipinService shipinService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShipinEntity shipin,
		HttpServletRequest request){
        EntityWrapper<ShipinEntity> ew = new EntityWrapper<ShipinEntity>();
		// 处理查询参数
		if(params.get("biaoti") != null && StringUtils.isNotBlank(params.get("biaoti").toString())) {
			ew.like("biaoti", params.get("biaoti").toString());
		}
		if(params.get("fenlei") != null && StringUtils.isNotBlank(params.get("fenlei").toString())) {
			ew.eq("fenlei", params.get("fenlei").toString());
		}
		PageUtils page = shipinService.queryPage(params, MPUtil.sort(MPUtil.between(ew, params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShipinEntity shipin, 
		HttpServletRequest request){
        EntityWrapper<ShipinEntity> ew = new EntityWrapper<ShipinEntity>();
		PageUtils page = shipinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shipin), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShipinEntity shipin){
        	EntityWrapper<ShipinEntity> ew = new EntityWrapper<ShipinEntity>();
       	ew.allEq(MPUtil.allEQMapPre( shipin, "shipin")); 
        return R.ok().put("data", shipinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShipinEntity shipin){
        EntityWrapper< ShipinEntity> ew = new EntityWrapper< ShipinEntity>();
		ew.allEq(MPUtil.allEQMapPre( shipin, "shipin")); 
		ShipinView shipinView =  shipinService.selectView(ew);
		return R.ok("查询视频学习成功").put("data", shipinView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShipinEntity shipin = shipinService.selectById(id);
        return R.ok().put("data", shipin);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShipinEntity shipin = shipinService.selectById(id);
        return R.ok().put("data", shipin);
    }
    



    /**
     * 后端保存(仅管理员)
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShipinEntity shipin, HttpServletRequest request){
        String role = (String) request.getSession().getAttribute("role");
        if(role == null || !role.equals("管理员")) {
            return R.error("无权限操作");
        }
    	shipin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	if(shipin.getDianzanshu() == null) shipin.setDianzanshu(0);
    	if(shipin.getShoucangshu() == null) shipin.setShoucangshu(0);
    	if(shipin.getClicknum() == null) shipin.setClicknum(0);
        shipinService.insert(shipin);
        return R.ok();
    }
    
    /**
     * 前端保存(仅管理员)
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShipinEntity shipin, HttpServletRequest request){
        String role = (String) request.getSession().getAttribute("role");
        if(role == null || !role.equals("管理员")) {
            return R.error("无权限操作");
        }
    	shipin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	if(shipin.getDianzanshu() == null) shipin.setDianzanshu(0);
    	if(shipin.getShoucangshu() == null) shipin.setShoucangshu(0);
    	if(shipin.getClicknum() == null) shipin.setClicknum(0);
        shipinService.insert(shipin);
        return R.ok();
    }

    /**
     * 修改(仅管理员或更新点击数)
     */
    @RequestMapping("/update")
    public R update(@RequestBody ShipinEntity shipin, HttpServletRequest request){
        String role = (String) request.getSession().getAttribute("role");
        if(role == null || !role.equals("管理员")) {
            ShipinEntity oldShipin = shipinService.selectById(shipin.getId());
            if(oldShipin != null && shipin.getClicknum() != null) {
                oldShipin.setClicknum(shipin.getClicknum());
                shipinService.updateById(oldShipin);
                return R.ok();
            }
            return R.error("无权限操作");
        }
        shipinService.updateById(shipin);
        return R.ok();
    }
    

    /**
     * 删除(仅管理员)
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids, HttpServletRequest request){
        String role = (String) request.getSession().getAttribute("role");
        if(role == null || !role.equals("管理员")) {
            return R.error("无权限操作");
        }
        shipinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 点赞
     */
    @RequestMapping("/dianzan")
    public R dianzan(@RequestBody Long id){
        ShipinEntity shipin = shipinService.selectById(id);
        shipin.setDianzanshu(shipin.getDianzanshu()+1);
        shipinService.updateById(shipin);
        return R.ok();
    }
    
    /**
     * 收藏
     */
    @RequestMapping("/shoucang")
    public R shoucang(@RequestBody Long id){
        ShipinEntity shipin = shipinService.selectById(id);
        shipin.setShoucangshu(shipin.getShoucangshu()+1);
        shipinService.updateById(shipin);
        return R.ok();
    }
    
    /**
     * 获取视频评论
     */
    @RequestMapping("/getComments/{id}")
    public R getComments(@PathVariable("id") Long id){
        EntityWrapper<ShipinpinglunEntity> ew = new EntityWrapper<ShipinpinglunEntity>();
        ew.eq("shipinid", id);
        ew.orderBy("pingluntime", false);
        List<ShipinpinglunEntity> comments = shipinpinglunService.selectList(ew);
        return R.ok().put("data", comments);
    }
    
    @Autowired
    private ShipinpinglunService shipinpinglunService;
    
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
		
		Wrapper<ShipinEntity> wrapper = new EntityWrapper<ShipinEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = shipinService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}