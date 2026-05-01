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

import com.entity.YuyueliancheEntity;
import com.entity.view.YuyueliancheView;
import com.entity.XiTongCanShuEntity;
import com.entity.KechenganpaiEntity;

import com.service.YuyueliancheService;
import com.service.XiTongCanShuService;
import com.service.KechenganpaiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 预约练车
 * 后端接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@RestController
@RequestMapping("/yuyuelianche")
public class YuyueliancheController {
    @Autowired
    private YuyueliancheService yuyueliancheService;
    
    @Autowired
    private XiTongCanShuService xiTongCanShuService;
    
    @Autowired
    private KechenganpaiService kechenganpaiService;


    

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuyueliancheEntity yuyuelianche,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaolian")) {
			yuyuelianche.setJiaoliangonghao((String)request.getSession().getAttribute("username"));

        }
		if(tableName.equals("xueyuan")) {
			yuyuelianche.setZhanghao((String)request.getSession().getAttribute("username"));
            yuyuelianche.setSfsh("已通过");
        }
        EntityWrapper<YuyueliancheEntity> ew = new EntityWrapper<YuyueliancheEntity>();
		PageUtils page = yuyueliancheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuelianche), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YuyueliancheEntity yuyuelianche, 
		HttpServletRequest request){
		Object tableNameObj = request.getSession().getAttribute("tableName");
		if(tableNameObj != null) {
			String tableName = tableNameObj.toString();
			if(tableName.equals("jiaolian")) {
				yuyuelianche.setJiaoliangonghao((String)request.getSession().getAttribute("username"));
			}
			if(tableName.equals("xueyuan")) {
				yuyuelianche.setZhanghao((String)request.getSession().getAttribute("username"));
			}
		}
        EntityWrapper<YuyueliancheEntity> ew = new EntityWrapper<YuyueliancheEntity>();
		PageUtils page = yuyueliancheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuelianche), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuyueliancheEntity yuyuelianche){
       	EntityWrapper<YuyueliancheEntity> ew = new EntityWrapper<YuyueliancheEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuyuelianche, "yuyuelianche")); 
        return R.ok().put("data", yuyueliancheService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuyueliancheEntity yuyuelianche){
        EntityWrapper< YuyueliancheEntity> ew = new EntityWrapper< YuyueliancheEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuyuelianche, "yuyuelianche")); 
		YuyueliancheView yuyueliancheView =  yuyueliancheService.selectView(ew);
		return R.ok("查询预约练车成功").put("data", yuyueliancheView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuyueliancheEntity yuyuelianche = yuyueliancheService.selectById(id);
        return R.ok().put("data", yuyuelianche);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuyueliancheEntity yuyuelianche = yuyueliancheService.selectById(id);
        return R.ok().put("data", yuyuelianche);
    }
    
    /**
     * 获取预约名额配置
     */
    @RequestMapping("/getQuotaConfig")
    public R getQuotaConfig(){
        EntityWrapper<XiTongCanShuEntity> ew = new EntityWrapper<XiTongCanShuEntity>();
        ew.eq("canshujian", "yuyue_mingee");
        ew.eq("zhuangtai", "启用");
        List<XiTongCanShuEntity> list = xiTongCanShuService.selectList(ew);
        int quota = 4; // 默认值
        if(list != null && list.size() > 0) {
            try {
                quota = Integer.parseInt(list.get(0).getCanshuzhi());
            } catch(Exception e) {}
        }
        return R.ok().put("quota", quota);
    }
    
    /**
     * 获取可预约的排班计划列表
     */
    @RequestMapping("/getAvailableSchedules")
    public R getAvailableSchedules(@RequestParam Map<String, Object> params, HttpServletRequest request){
        // 获取开放天数配置
        EntityWrapper<XiTongCanShuEntity> ew2 = new EntityWrapper<XiTongCanShuEntity>();
        ew2.eq("canshujian", "yuyue_kaifang_tianshu");
        ew2.eq("zhuangtai", "启用");
        List<XiTongCanShuEntity> configList = xiTongCanShuService.selectList(ew2);
        int openDays = 7; // 默认7天
        if(configList != null && configList.size() > 0) {
            try {
                openDays = Integer.parseInt(configList.get(0).getCanshuzhi());
            } catch(Exception e) {}
        }
        
        // 查询排班计划：排班日期在开放天数范围内
        EntityWrapper<KechenganpaiEntity> ew = new EntityWrapper<KechenganpaiEntity>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, openDays);
        String endDate = sdf.format(cal.getTime());
        ew.ge("paiBanriqi", today);
        ew.le("paiBanriqi", endDate);
        ew.orderBy("paiBanriqi, shijianduan", true);
        List<KechenganpaiEntity> scheduleList = kechenganpaiService.selectList(ew);
        
        return R.ok().put("scheduleList", scheduleList).put("openDays", openDays);
    }
    
    /**
     * 检查某个排班时段的剩余名额
     */
    @RequestMapping("/checkQuota")
    public R checkQuota(@RequestParam Long scheduleId, HttpServletRequest request){
        // 获取排班计划
        KechenganpaiEntity schedule = kechenganpaiService.selectById(scheduleId);
        if(schedule == null) {
            return R.error("排班计划不存在");
        }
        
        // 获取名额配置
        EntityWrapper<XiTongCanShuEntity> ew = new EntityWrapper<XiTongCanShuEntity>();
        ew.eq("canshujian", "yuyue_mingee");
        ew.eq("zhuangtai", "启用");
        List<XiTongCanShuEntity> configList = xiTongCanShuService.selectList(ew);
        int quota = 4;
        if(configList != null && configList.size() > 0) {
            try {
                quota = Integer.parseInt(configList.get(0).getCanshuzhi());
            } catch(Exception e) {}
        }
        
        // 统计该排班已确认的预约数量
        EntityWrapper<YuyueliancheEntity> ew2 = new EntityWrapper<YuyueliancheEntity>();
        ew2.eq("jiaoliangonghao", schedule.getJiaoliangonghao());
        // 使用排班日期和时间段匹配
        if(schedule.getPaiBanriqi() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(schedule.getPaiBanriqi());
            ew2.like("liancheshijian", dateStr);
        }
        ew2.ne("status", "已拒绝");
        int booked = yuyueliancheService.selectCount(ew2);
        
        int remaining = quota - booked;
        return R.ok().put("quota", quota).put("booked", booked).put("remaining", Math.max(remaining, 0));
    }



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YuyueliancheEntity yuyuelianche, HttpServletRequest request){
    	yuyuelianche.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	
    	// 设置默认审核状态
    	if(yuyuelianche.getStatus() == null || yuyuelianche.getStatus().isEmpty()) {
    		yuyuelianche.setStatus("待审核");
    	}
    	if(yuyuelianche.getSfsh() == null || yuyuelianche.getSfsh().isEmpty()) {
    		yuyuelianche.setSfsh("待审核");
    	}
    	
    	// 名额校验
    	if(yuyuelianche.getJiaoliangonghao() != null && yuyuelianche.getLiancheshijian() != null) {
    	    // 获取名额配置
    	    EntityWrapper<XiTongCanShuEntity> ew = new EntityWrapper<XiTongCanShuEntity>();
    	    ew.eq("canshujian", "yuyue_mingee");
    	    ew.eq("zhuangtai", "启用");
    	    List<XiTongCanShuEntity> configList = xiTongCanShuService.selectList(ew);
    	    int quota = 4;
    	    if(configList != null && configList.size() > 0) {
    	        try {
    	            quota = Integer.parseInt(configList.get(0).getCanshuzhi());
    	        } catch(Exception e) {}
    	    }
    	    
    	    // 统计该教练当天已预约数量（未拒绝的）
    	    EntityWrapper<YuyueliancheEntity> ew2 = new EntityWrapper<YuyueliancheEntity>();
    	    ew2.eq("jiaoliangonghao", yuyuelianche.getJiaoliangonghao());
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	    String dateStr = sdf.format(yuyuelianche.getLiancheshijian());
    	    ew2.like("liancheshijian", dateStr);
    	    ew2.ne("status", "已拒绝");
    	    int booked = yuyueliancheService.selectCount(ew2);
    	    
    	    if(booked >= quota) {
    	        return R.error("该教练此日期的预约名额已满（最多" + quota + "人），请选择其他日期或教练");
    	    }
    	}
    	
        yuyueliancheService.insert(yuyuelianche);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YuyueliancheEntity yuyuelianche, HttpServletRequest request){
    	yuyuelianche.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	
    	// 设置默认审核状态
    	if(yuyuelianche.getStatus() == null || yuyuelianche.getStatus().isEmpty()) {
    		yuyuelianche.setStatus("待审核");
    	}
    	if(yuyuelianche.getSfsh() == null || yuyuelianche.getSfsh().isEmpty()) {
    		yuyuelianche.setSfsh("待审核");
    	}
    	
    	// 名额校验
    	if(yuyuelianche.getJiaoliangonghao() != null && yuyuelianche.getLiancheshijian() != null) {
    	    EntityWrapper<XiTongCanShuEntity> ew = new EntityWrapper<XiTongCanShuEntity>();
    	    ew.eq("canshujian", "yuyue_mingee");
    	    ew.eq("zhuangtai", "启用");
    	    List<XiTongCanShuEntity> configList = xiTongCanShuService.selectList(ew);
    	    int quota = 4;
    	    if(configList != null && configList.size() > 0) {
    	        try {
    	            quota = Integer.parseInt(configList.get(0).getCanshuzhi());
    	        } catch(Exception e) {}
    	    }
    	    
    	    EntityWrapper<YuyueliancheEntity> ew2 = new EntityWrapper<YuyueliancheEntity>();
    	    ew2.eq("jiaoliangonghao", yuyuelianche.getJiaoliangonghao());
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	    String dateStr = sdf.format(yuyuelianche.getLiancheshijian());
    	    ew2.like("liancheshijian", dateStr);
    	    ew2.ne("status", "已拒绝");
    	    int booked = yuyueliancheService.selectCount(ew2);
    	    
    	    if(booked >= quota) {
    	        return R.error("该教练此日期的预约名额已满（最多" + quota + "人），请选择其他日期或教练");
    	    }
    	}
    	
        yuyueliancheService.insert(yuyuelianche);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody YuyueliancheEntity yuyuelianche, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuyuelianche);
        if (yuyuelianche.getSfsh().equals("是")) {
            yuyuelianche.setStatus("已通过");
            yuyuelianche.setSfsh("已通过");
        }
        if (yuyuelianche.getSfsh().equals("否")) {
            yuyuelianche.setStatus("未通过");
            yuyuelianche.setSfsh("未通过");
        }
        yuyueliancheService.updateById(yuyuelianche);//全部更新
        return R.ok();
    }
    
    /**
     * 审核
     */
    @RequestMapping("/sh")
    public R sh(@RequestBody YuyueliancheEntity yuyuelianche, HttpServletRequest request){
        YuyueliancheEntity oldEntity = yuyueliancheService.selectById(yuyuelianche.getId());
        String jiaoliangonghao = (String) request.getSession().getAttribute("username");

        if(!oldEntity.getJiaoliangonghao().equals(jiaoliangonghao)){
            return R.error("无权审核此预约");
        }

        // 同时更新sfsh和status字段
        if("是".equals(yuyuelianche.getSfsh())){
            yuyuelianche.setStatus("已确认");
        } else if("否".equals(yuyuelianche.getSfsh())){
            yuyuelianche.setStatus("已拒绝");
        }

        yuyueliancheService.updateById(yuyuelianche);
        return R.ok("预约审核成功");
    }

    /**
     * 获取待审核预约列表
     */
    @RequestMapping("/getPending")
    public R getPending(HttpServletRequest request,
                      @RequestParam Map<String, Object> params){
        String jiaoliangonghao = (String) request.getSession().getAttribute("username");
        EntityWrapper<YuyueliancheEntity> ew = new EntityWrapper<YuyueliancheEntity>();
        ew.eq("jiaoliangonghao", jiaoliangonghao);
        ew.eq("status", "待审核");
        ew.orderBy("addtime", false);
        PageUtils page = yuyueliancheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, new YuyueliancheEntity()), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 获取已确认预约列表
     */
    @RequestMapping("/getConfirmed")
    public R getConfirmed(@RequestParam Map<String, Object> params, YuyueliancheEntity yuyuelianche,
                          HttpServletRequest request){
        String jiaoliangonghao = (String) request.getSession().getAttribute("username");
        yuyuelianche.setJiaoliangonghao(jiaoliangonghao);
        yuyuelianche.setStatus("已通过");
        //yuyuelianche.setSfsh(null);
        EntityWrapper<YuyueliancheEntity> ew = new EntityWrapper<YuyueliancheEntity>();
        //ew.andNew("(status != '已确认' OR status IS NULL)");
        ew.orderBy("addtime", false);
        PageUtils page = yuyueliancheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuelianche), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 获取待审核数量
     */
    @RequestMapping("/getPendingCount")
    public R getPendingCount(HttpServletRequest request) {
        String jiaoliangonghao = (String) request.getSession().getAttribute("username");
        EntityWrapper<YuyueliancheEntity> ew = new EntityWrapper<YuyueliancheEntity>();
        ew.eq("jiaoliangonghao", jiaoliangonghao);
        ew.eq("status", "待审核");
        int count = yuyueliancheService.selectCount(ew);
        return R.ok().put("count", count);
    }

    /**
     * 获取已拒绝预约列表
     */
    @RequestMapping("/getRejected")
    public R getRejected(HttpServletRequest request,
                        @RequestParam Map<String, Object> params){
        String jiaoliangonghao = (String) request.getSession().getAttribute("username");
        EntityWrapper<YuyueliancheEntity> ew = new EntityWrapper<YuyueliancheEntity>();
        ew.eq("jiaoliangonghao", jiaoliangonghao);
        ew.eq("status", "已拒绝");
        ew.orderBy("addtime", false);
        PageUtils page = yuyueliancheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, new YuyueliancheEntity()), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 获取学员的待处理预约（审核未通过或未审核）
     */
    @RequestMapping("/getMyPending")
    public R getMyPending(@RequestParam Map<String, Object> params, YuyueliancheEntity yuyuelianche,
                        HttpServletRequest request){
        String zhanghao = (String) request.getSession().getAttribute("username");
        yuyuelianche.setZhanghao(zhanghao);
        //yuyuelianche.set(null);
        //yuyuelianche.setSfsh("");
        EntityWrapper<YuyueliancheEntity> ew = new EntityWrapper<YuyueliancheEntity>();
        ew.andNew("(sfsh != '已确认' OR sfsh IS NULL)");
        //ew.orderBy("addtime", false);
        PageUtils page = yuyueliancheService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuyuelianche), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 获取教练的未确认预约（审核未通过或未审核）
     */
    @RequestMapping("/getUnconfirmed")
    public R getUnconfirmed(@RequestParam Map<String, Object> params, YuyueliancheEntity yuyuelianche,
                          HttpServletRequest request){
        String jiaoliangonghao = (String) request.getSession().getAttribute("username");
        yuyuelianche.setJiaoliangonghao(jiaoliangonghao);
        yuyuelianche.setSfsh("待审核");

        EntityWrapper<YuyueliancheEntity> ew = new EntityWrapper<YuyueliancheEntity>();
        // 先添加基本条件（教练工号）
        ew = (EntityWrapper<YuyueliancheEntity>) MPUtil.likeOrEq(ew, yuyuelianche);
        // 再添加未确认条件
        ew.and("sfsh != '已确认' AND sfsh != '已拒绝'");
        ew.orderBy("addtime", false);

        
        PageUtils page = yuyueliancheService.queryPage(params, MPUtil.sort(MPUtil.between(ew, params), params));
        return R.ok().put("data", page);
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yuyueliancheService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<YuyueliancheEntity> wrapper = new EntityWrapper<YuyueliancheEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaolian")) {
			wrapper.eq("jiaoliangonghao", (String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xueyuan")) {
			wrapper.eq("zhanghao", (String)request.getSession().getAttribute("username"));
		}

		int count = yuyueliancheService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	

	
	

}
