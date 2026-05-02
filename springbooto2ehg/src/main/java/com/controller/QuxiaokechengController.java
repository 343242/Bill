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

import com.entity.KechenganpaiEntity;
import com.entity.QuxiaokechengEntity;
import com.entity.YuyueliancheEntity;
import com.entity.view.QuxiaokechengView;

import com.service.KechenganpaiService;
import com.service.QuxiaokechengService;
import com.service.YuyueliancheService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 取消课程
 * 后端接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@RestController
@RequestMapping("/quxiaokecheng")
public class QuxiaokechengController {
    @Autowired
    private QuxiaokechengService quxiaokechengService;
    @Autowired
    private KechenganpaiService kechenganpaiService;
    @Autowired
    private YuyueliancheService yuyueliancheService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QuxiaokechengEntity quxiaokecheng,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("users")) {
            syncAdminCancelableBookings();
        }
		if(tableName.equals("jiaolian")) {
			quxiaokecheng.setJiaoliangonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xueyuan")) {
			quxiaokecheng.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<QuxiaokechengEntity> ew = new EntityWrapper<QuxiaokechengEntity>();
        if(tableName.equals("users")) {
            ew.ge("yuyueshijian", new Date());
            ew.in("lianchewanchengzhuangtai", Arrays.asList("未练车", "待完成", "待上课"));
            ew.in("sfsh", Arrays.asList("已通过", "是"));
            ew.orderBy("yuyueshijian", true);
        }
		PageUtils page = quxiaokechengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, quxiaokecheng), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QuxiaokechengEntity quxiaokecheng, 
		HttpServletRequest request){
        EntityWrapper<QuxiaokechengEntity> ew = new EntityWrapper<QuxiaokechengEntity>();
		PageUtils page = quxiaokechengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, quxiaokecheng), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QuxiaokechengEntity quxiaokecheng){
       	EntityWrapper<QuxiaokechengEntity> ew = new EntityWrapper<QuxiaokechengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( quxiaokecheng, "quxiaokecheng")); 
        return R.ok().put("data", quxiaokechengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QuxiaokechengEntity quxiaokecheng){
        EntityWrapper< QuxiaokechengEntity> ew = new EntityWrapper< QuxiaokechengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( quxiaokecheng, "quxiaokecheng")); 
		QuxiaokechengView quxiaokechengView =  quxiaokechengService.selectView(ew);
		return R.ok("查询取消课程成功").put("data", quxiaokechengView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QuxiaokechengEntity quxiaokecheng = quxiaokechengService.selectById(id);
        return R.ok().put("data", quxiaokecheng);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QuxiaokechengEntity quxiaokecheng = quxiaokechengService.selectById(id);
        return R.ok().put("data", quxiaokecheng);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QuxiaokechengEntity quxiaokecheng, HttpServletRequest request){
		quxiaokecheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		//ValidatorUtils.validateEntity(quxiaokecheng);
        if(StringUtils.isBlank(quxiaokecheng.getLianchewanchengzhuangtai())) {
            quxiaokecheng.setLianchewanchengzhuangtai("待完成");
        }
        quxiaokechengService.insert(quxiaokecheng);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QuxiaokechengEntity quxiaokecheng, HttpServletRequest request){
		quxiaokecheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		//ValidatorUtils.validateEntity(quxiaokecheng);
        if(StringUtils.isBlank(quxiaokecheng.getLianchewanchengzhuangtai())) {
            quxiaokecheng.setLianchewanchengzhuangtai("待完成");
        }
        quxiaokechengService.insert(quxiaokecheng);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody QuxiaokechengEntity quxiaokecheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(quxiaokecheng);
        quxiaokechengService.updateById(quxiaokecheng);//全部更新
        return R.ok();
    }

    /**
     * 管理员取消预约
     */
    @RequestMapping("/cancelBooking")
    public R cancelBooking(@RequestBody QuxiaokechengEntity quxiaokecheng, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(!"users".equals(tableName)) {
            return R.error("仅管理员可执行此操作");
        }
        if(quxiaokecheng.getId() == null) {
            return R.error("缺少取消记录编号");
        }
        String reason = StringUtils.trimToEmpty(quxiaokecheng.getShuoming());
        if(StringUtils.isBlank(reason)) {
            return R.error("请填写取消预约的原因说明");
        }
        if(reason.length() < 50 || reason.length() > 200) {
            return R.error("取消预约原因需控制在50到200字");
        }

        QuxiaokechengEntity record = quxiaokechengService.selectById(quxiaokecheng.getId());
        if(record == null) {
            return R.error("取消记录不存在");
        }
        if(!"未练车".equals(normalizePracticeStatus(record.getLianchewanchengzhuangtai()))) {
            return R.error("当前记录不允许再次取消预约");
        }

        KechenganpaiEntity schedule = null;
        if(record.getKechenganpaiid() != null) {
            schedule = kechenganpaiService.selectById(record.getKechenganpaiid());
        }
        if(schedule == null && record.getYuyueshijian() != null) {
            EntityWrapper<KechenganpaiEntity> scheduleWrapper = new EntityWrapper<KechenganpaiEntity>();
            scheduleWrapper.eq("zhanghao", record.getZhanghao());
            scheduleWrapper.eq("jiaoliangonghao", record.getJiaoliangonghao());
            scheduleWrapper.eq("kechengmingcheng", record.getKechengmingcheng());
            scheduleWrapper.eq("kechengshijian", record.getYuyueshijian());
            schedule = kechenganpaiService.selectOne(scheduleWrapper);
        }
        if(schedule != null) {
            schedule.setShangkezt("已取消");
            kechenganpaiService.updateById(schedule);
            record.setKechenganpaiid(schedule.getId());
        }

        record.setLianchewanchengzhuangtai("已取消");
        record.setShuoming(reason);
        record.setShhf(reason);
        quxiaokechengService.updateById(record);
        return R.ok("取消预约成功");
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        quxiaokechengService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<QuxiaokechengEntity> wrapper = new EntityWrapper<QuxiaokechengEntity>();
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

		int count = quxiaokechengService.selectCount(wrapper);
		return R.ok().put("count", count);
	}

    private void syncAdminCancelableBookings() {
        Map<Long, YuyueliancheEntity> appointmentMap = new HashMap<Long, YuyueliancheEntity>();
        mergeApprovedAppointments(appointmentMap, "status", "已通过");
        mergeApprovedAppointments(appointmentMap, "sfsh", "已通过");
        mergeApprovedAppointments(appointmentMap, "sfsh", "是");
        List<YuyueliancheEntity> appointmentList = new ArrayList<YuyueliancheEntity>(appointmentMap.values());
        for (YuyueliancheEntity appointment : appointmentList) {
            if(appointment.getLiancheshijian() == null) {
                continue;
            }
            QuxiaokechengEntity record = findCancellationRecord(appointment);
            boolean isNewRecord = false;
            if(record == null) {
                record = new QuxiaokechengEntity();
                record.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
                isNewRecord = true;
            }
            mergeAppointmentIntoCancellationRecord(record, appointment);
            if(isNewRecord) {
                quxiaokechengService.insert(record);
            } else {
                quxiaokechengService.updateById(record);
            }
        }
    }

    private void mergeApprovedAppointments(Map<Long, YuyueliancheEntity> appointmentMap, String fieldName, String fieldValue) {
        EntityWrapper<YuyueliancheEntity> wrapper = new EntityWrapper<YuyueliancheEntity>();
        wrapper.ge("liancheshijian", new Date());
        wrapper.eq(fieldName, fieldValue);
        List<YuyueliancheEntity> appointmentList = yuyueliancheService.selectList(wrapper);
        if(appointmentList == null) {
            return;
        }
        for (YuyueliancheEntity appointment : appointmentList) {
            appointmentMap.put(appointment.getId(), appointment);
        }
    }

    private QuxiaokechengEntity findCancellationRecord(YuyueliancheEntity appointment) {
        EntityWrapper<QuxiaokechengEntity> wrapper = new EntityWrapper<QuxiaokechengEntity>();
        wrapper.eq("zhanghao", appointment.getZhanghao());
        wrapper.eq("jiaoliangonghao", appointment.getJiaoliangonghao());
        wrapper.eq("yuyueshijian", appointment.getLiancheshijian());
        List<QuxiaokechengEntity> recordList = quxiaokechengService.selectList(wrapper);
        if(recordList == null || recordList.isEmpty()) {
            return null;
        }
        return recordList.get(0);
    }

    private void mergeAppointmentIntoCancellationRecord(QuxiaokechengEntity record, YuyueliancheEntity appointment) {
        record.setYuyueshijian(appointment.getLiancheshijian());
        record.setJiaoliangonghao(appointment.getJiaoliangonghao());
        record.setJiaolianxingming(appointment.getJiaolianxingming());
        record.setZhanghao(appointment.getZhanghao());
        record.setXingming(appointment.getXingming());
        record.setSfsh("已通过");
        if(StringUtils.isBlank(record.getLianchewanchengzhuangtai())) {
            record.setLianchewanchengzhuangtai("未练车");
        } else if("待完成".equals(record.getLianchewanchengzhuangtai()) || "待上课".equals(record.getLianchewanchengzhuangtai())) {
            record.setLianchewanchengzhuangtai("未练车");
        }

        KechenganpaiEntity schedule = matchScheduleForAppointment(appointment);
        if(schedule != null) {
            record.setKechenganpaiid(schedule.getId());
            if(StringUtils.isBlank(record.getKechengmingcheng())) {
                record.setKechengmingcheng(StringUtils.defaultIfBlank(schedule.getKechengmingcheng(), "练车预约"));
            }
            if(StringUtils.isBlank(record.getKemuleixing())) {
                record.setKemuleixing(StringUtils.defaultIfBlank(schedule.getKemuleixing(), "科目二/三"));
            }
        }
        if(StringUtils.isBlank(record.getKechengmingcheng())) {
            record.setKechengmingcheng("练车预约");
        }
        if(StringUtils.isBlank(record.getKemuleixing())) {
            record.setKemuleixing("科目二/三");
        }
    }

    private KechenganpaiEntity matchScheduleForAppointment(YuyueliancheEntity appointment) {
        EntityWrapper<KechenganpaiEntity> exactWrapper = new EntityWrapper<KechenganpaiEntity>();
        exactWrapper.eq("jiaoliangonghao", appointment.getJiaoliangonghao());
        exactWrapper.eq("kechengshijian", appointment.getLiancheshijian());
        KechenganpaiEntity schedule = kechenganpaiService.selectOne(exactWrapper);
        if(schedule != null) {
            return schedule;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<KechenganpaiEntity> dateWrapper = new EntityWrapper<KechenganpaiEntity>();
        dateWrapper.eq("jiaoliangonghao", appointment.getJiaoliangonghao());
        dateWrapper.eq("paibanriqi", sdf.format(appointment.getLiancheshijian()));
        List<KechenganpaiEntity> scheduleList = kechenganpaiService.selectList(dateWrapper);
        if(scheduleList == null || scheduleList.isEmpty()) {
            return null;
        }
        return scheduleList.get(0);
    }

    private String normalizePracticeStatus(String status) {
        if("待上课".equals(status) || "待完成".equals(status)) {
            return "未练车";
        }
        return status;
    }








}
