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

import com.entity.StoreupEntity;
import com.entity.ShipinEntity;
import com.entity.view.StoreupView;

import com.service.StoreupService;
import com.service.ShipinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 收藏表
 * 后端接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:37
 */
@RestController
@RequestMapping("/storeup")
public class StoreupController {
    @Autowired
    private StoreupService storeupService;
    
    @Autowired
    private ShipinService shipinService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,StoreupEntity storeup,
		HttpServletRequest request){
    	if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
    		storeup.setUserid((Long)request.getSession().getAttribute("userId"));
    	}
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();
		PageUtils page = storeupService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, storeup), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,
		HttpServletRequest request){
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();

        // 处理查询参数 - 直接从params中获取并设置到ew中
        String tablename = params.get("tablename") != null ? params.get("tablename").toString() : "";
        String refid = params.get("refid") != null ? params.get("refid").toString() : "";
        String type = params.get("type") != null ? params.get("type").toString() : "";

        if(tablename != null && !tablename.isEmpty()) {
            ew.eq("tablename", tablename);
        }
        if(refid != null && !refid.isEmpty()) {
            ew.eq("refid", Long.parseLong(refid));
        }
        if(type != null && !type.isEmpty()) {
            ew.eq("type", type);
        }

        // 排序
        String sort = params.get("sort") != null ? params.get("sort").toString() : "addtime";
        String order = params.get("order") != null ? params.get("order").toString() : "desc";
        ew.orderBy(sort, "desc".equals(order));

        // 分页
        PageUtils page = storeupService.queryPage(params, ew);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( StoreupEntity storeup){
       	EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();
      	ew.allEq(MPUtil.allEQMapPre( storeup, "storeup")); 
        return R.ok().put("data", storeupService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(StoreupEntity storeup){
        EntityWrapper< StoreupEntity> ew = new EntityWrapper< StoreupEntity>();
 		ew.allEq(MPUtil.allEQMapPre( storeup, "storeup")); 
		StoreupView storeupView =  storeupService.selectView(ew);
		return R.ok("查询收藏表成功").put("data", storeupView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        StoreupEntity storeup = storeupService.selectById(id);
        return R.ok().put("data", storeup);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        StoreupEntity storeup = storeupService.selectById(id);
        return R.ok().put("data", storeup);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody StoreupEntity storeup, HttpServletRequest request){
    	storeup.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(storeup);
    	storeup.setUserid((Long)request.getSession().getAttribute("userId"));
        storeupService.insert(storeup);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody StoreupEntity storeup, HttpServletRequest request){
    	storeup.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	storeup.setUserid((Long)request.getSession().getAttribute("userId"));
    	
    	EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();
    	ew.eq("userid", storeup.getUserid());
    	ew.eq("refid", storeup.getRefid());
    	ew.eq("type", storeup.getType());
    	ew.eq("tablename", storeup.getTablename());
    	int count = storeupService.selectCount(ew);
    	if(count > 0) {
    	    return R.error("已操作过");
    	}
    	
    	// 根据 refid 获取视频信息，自动填充 name 和 picture 字段
    	if("shipin".equals(storeup.getTablename()) && storeup.getRefid() != null) {
    		ShipinEntity shipin = shipinService.selectById(storeup.getRefid());
    		if(shipin != null) {
    			if(storeup.getName() == null || storeup.getName().isEmpty()) {
    				storeup.setName(shipin.getBiaoti());
    			}
    			if(storeup.getPicture() == null || storeup.getPicture().isEmpty()) {
    				storeup.setPicture(shipin.getFengmian());
    			}
    		}
    	}
    	
        storeupService.insert(storeup);
        
        if("shipin".equals(storeup.getTablename()) && storeup.getRefid() != null) {
            ShipinEntity shipin = shipinService.selectById(storeup.getRefid());
            if(shipin != null) {
                if("1".equals(storeup.getType())) {
                    shipin.setDianzanshu(shipin.getDianzanshu() == null ? 1 : shipin.getDianzanshu() + 1);
                } else if("2".equals(storeup.getType())) {
                    shipin.setShoucangshu(shipin.getShoucangshu() == null ? 1 : shipin.getShoucangshu() + 1);
                }
                shipinService.updateById(shipin);
            }
        }
        
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody StoreupEntity storeup, HttpServletRequest request){
        //ValidatorUtils.validateEntity(storeup);
        storeupService.updateById(storeup);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        storeupService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 根据类型删除
     */
    @RequestMapping("/deleteByType")
    public R deleteByType(@RequestBody StoreupEntity storeup, HttpServletRequest request){
        storeup.setUserid((Long)request.getSession().getAttribute("userId"));
        
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();
        ew.eq("userid", storeup.getUserid());
        ew.eq("refid", storeup.getRefid());
        ew.eq("type", storeup.getType());
        ew.eq("tablename", storeup.getTablename());
        
        StoreupEntity entity = storeupService.selectOne(ew);
        if(entity != null) {
            storeupService.deleteById(entity.getId());
            
            // 更新视频的点赞数或收藏数
            if("shipin".equals(storeup.getTablename()) && storeup.getRefid() != null) {
                ShipinEntity shipin = shipinService.selectById(storeup.getRefid());
                if(shipin != null) {
                    if("1".equals(storeup.getType())) {
                        shipin.setDianzanshu(Math.max(0, shipin.getDianzanshu() == null ? 0 : shipin.getDianzanshu() - 1));
                    } else if("2".equals(storeup.getType())) {
                        shipin.setShoucangshu(Math.max(0, shipin.getShoucangshu() == null ? 0 : shipin.getShoucangshu() - 1));
                    }
                    shipinService.updateById(shipin);
                }
            }
            
            return R.ok();
        }
        
        return R.error("操作失败");
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
		
		Wrapper<StoreupEntity> wrapper = new EntityWrapper<StoreupEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}
		if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
    		wrapper.eq("userid", (Long)request.getSession().getAttribute("userId"));
    	}


		int count = storeupService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}
