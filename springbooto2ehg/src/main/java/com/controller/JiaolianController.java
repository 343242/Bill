package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Set;
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

import com.entity.JiaolianEntity;
import com.entity.KechenganpaiEntity;
import com.entity.XueyuanEntity;
import com.entity.TokenEntity;
import com.entity.view.JiaolianView;
import com.service.KechenganpaiService;
import com.service.XueyuanService;
import java.util.ArrayList;

import com.service.JiaolianService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 教练
 * 后端接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@RestController
@RequestMapping("/jiaolian")
public class JiaolianController {
    @Autowired
    private JiaolianService jiaolianService;


    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		JiaolianEntity user = jiaolianService.selectOne(new EntityWrapper<JiaolianEntity>().eq("jiaoliangonghao", username));
		if(user==null || !user.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}
		
		String token = tokenService.generateToken(user.getId(), username,"jiaolian",  "教练" );
		return R.ok().put("token", token);
	}
	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody JiaolianEntity jiaolian){
    	//ValidatorUtils.validateEntity(jiaolian);
    	JiaolianEntity user = jiaolianService.selectOne(new EntityWrapper<JiaolianEntity>().eq("jiaoliangonghao", jiaolian.getJiaoliangonghao()));
		if(user!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		jiaolian.setId(uId);
        jiaolianService.insert(jiaolian);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
	 * 获取用户的session用户信息
	 */
    @IgnoreAuth
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        if(id == null) {
        	// 从token中获取用户信息
        	String token = request.getHeader("Token");
        	if(token != null) {
        		TokenEntity tokenEntity = tokenService.getTokenEntity(token);
        		if(tokenEntity != null) {
        			id = tokenEntity.getUserid();
        		}
        	}
        }
        if(id == null) {
        	return R.error(401, "请先登录");
        }
        JiaolianEntity user = jiaolianService.selectById(id);
        return R.ok().put("data", user);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	JiaolianEntity user = jiaolianService.selectOne(new EntityWrapper<JiaolianEntity>().eq("jiaoliangonghao", username));
    	if(user==null) {
    		return R.error("账号不存在");
    	}
        user.setMima("123456");
        jiaolianService.updateById(user);
        return R.ok("密码已重置为：123456");
    }


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiaolianEntity jiaolian,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaolian")) {
			jiaolian.setJiaoliangonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xueyuan")) {
			// 学员只能查看自己的教练（通过课程安排关联）
			String zhanghao = (String)request.getSession().getAttribute("username");
			EntityWrapper<KechenganpaiEntity> kew = new EntityWrapper<KechenganpaiEntity>();
			kew.eq("zhanghao", zhanghao);
			List<KechenganpaiEntity> kechengList = kechenganpaiService.selectList(kew);
			Set<String> gonghaoSet = new HashSet<>();
			for(KechenganpaiEntity ke : kechengList) {
				if(ke.getJiaoliangonghao() != null && !ke.getJiaoliangonghao().isEmpty()) {
					gonghaoSet.add(ke.getJiaoliangonghao());
				}
			}
			if(gonghaoSet.isEmpty()) {
				return R.ok().put("data", new PageUtils(new ArrayList<>(), 0, 10, 1));
			}
			EntityWrapper<JiaolianEntity> ew = new EntityWrapper<JiaolianEntity>();
			ew.in("jiaoliangonghao", new ArrayList<>(gonghaoSet));
			PageUtils page = jiaolianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaolian), params), params));
			return R.ok().put("data", page);
		}
        EntityWrapper<JiaolianEntity> ew = new EntityWrapper<JiaolianEntity>();
		PageUtils page = jiaolianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaolian), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiaolianEntity jiaolian, 
		HttpServletRequest request){
		Object tableNameObj = request.getSession().getAttribute("tableName");
		/*if(tableNameObj != null) {
			String tableName = tableNameObj.toString();
			if(tableName.equals("jiaolian")) {
				jiaolian.setJiaoliangonghao((String)request.getSession().getAttribute("username"));
			}
			if(tableName.equals("xueyuan")) {
				String zhanghao = (String)request.getSession().getAttribute("username");
				EntityWrapper<KechenganpaiEntity> kew = new EntityWrapper<KechenganpaiEntity>();
				kew.eq("zhanghao", zhanghao);
				List<KechenganpaiEntity> kechengList = kechenganpaiService.selectList(kew);
				Set<String> gonghaoSet = new HashSet<>();
				for(KechenganpaiEntity ke : kechengList) {
					if(ke.getJiaoliangonghao() != null && !ke.getJiaoliangonghao().isEmpty()) {
						gonghaoSet.add(ke.getJiaoliangonghao());
					}
				}
				if(gonghaoSet.isEmpty()) {
					return R.ok().put("data", new PageUtils(new ArrayList<>(), 0, 10, 1));
				}
				EntityWrapper<JiaolianEntity> ew = new EntityWrapper<JiaolianEntity>();
				ew.in("jiaoliangonghao", new ArrayList<>(gonghaoSet));
				PageUtils page = jiaolianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaolian), params), params));
				return R.ok().put("data", page);
			}
		}*/
        EntityWrapper<JiaolianEntity> ew = new EntityWrapper<JiaolianEntity>();
		PageUtils page = jiaolianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaolian), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiaolianEntity jiaolian){
       	EntityWrapper<JiaolianEntity> ew = new EntityWrapper<JiaolianEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiaolian, "jiaolian")); 
        return R.ok().put("data", jiaolianService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiaolianEntity jiaolian){
        EntityWrapper< JiaolianEntity> ew = new EntityWrapper< JiaolianEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiaolian, "jiaolian")); 
		JiaolianView jiaolianView =  jiaolianService.selectView(ew);
		return R.ok("查询教练成功").put("data", jiaolianView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiaolianEntity jiaolian = jiaolianService.selectById(id);
        return R.ok().put("data", jiaolian);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiaolianEntity jiaolian = jiaolianService.selectById(id);
        return R.ok().put("data", jiaolian);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiaolianEntity jiaolian, HttpServletRequest request){
    	jiaolian.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiaolian);
    	JiaolianEntity user = jiaolianService.selectOne(new EntityWrapper<JiaolianEntity>().eq("jiaoliangonghao", jiaolian.getJiaoliangonghao()));
		if(user!=null) {
			return R.error("用户已存在");
		}
		jiaolian.setId(new Date().getTime());
        jiaolianService.insert(jiaolian);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiaolianEntity jiaolian, HttpServletRequest request){
    	jiaolian.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiaolian);
    	JiaolianEntity user = jiaolianService.selectOne(new EntityWrapper<JiaolianEntity>().eq("jiaoliangonghao", jiaolian.getJiaoliangonghao()));
		if(user!=null) {
			return R.error("用户已存在");
		}
		jiaolian.setId(new Date().getTime());
        jiaolianService.insert(jiaolian);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody JiaolianEntity jiaolian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiaolian);
        jiaolianService.updateById(jiaolian);//全部更新
        EntityWrapper<TokenEntity> tokenWrapper=new EntityWrapper<TokenEntity>();
        tokenWrapper.eq("userid", jiaolian.getId());
        tokenWrapper.eq("tablename", "jiaolian");
        TokenEntity token =tokenService.selectOne(tokenWrapper);
        token.setUsername(jiaolian.getJiaoliangonghao());
        tokenService.updateById(token);
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiaolianService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<JiaolianEntity> wrapper = new EntityWrapper<JiaolianEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = jiaolianService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
    
    @Autowired
    private KechenganpaiService kechenganpaiService;
    
    /**
     * 获取个人排班
     */
    @RequestMapping("/getSchedule")
    public R getSchedule(HttpServletRequest request,
                       @RequestParam(required = false) String shangkezt){
        String jiaoliangonghao = (String) request.getSession().getAttribute("username");
        EntityWrapper<KechenganpaiEntity> ew = new EntityWrapper<KechenganpaiEntity>();
        ew.eq("jiaoliangonghao", jiaoliangonghao);
        if(shangkezt != null && !shangkezt.isEmpty()){
            ew.eq("shangkezt", shangkezt);
        }
        ew.orderBy("kechengshijian", true);
        List<KechenganpaiEntity> scheduleList = kechenganpaiService.selectList(ew);
        return R.ok().put("data", scheduleList);
    }

    /**
     * 更新排班状态
     */
    @RequestMapping("/updateScheduleStatus")
    public R updateScheduleStatus(@RequestBody KechenganpaiEntity kechenganpai, HttpServletRequest request) {
        String jiaoliangonghao = (String) request.getSession().getAttribute("username");
        if(!kechenganpai.getJiaoliangonghao().equals(jiaoliangonghao)){
            return R.error("无权操作此排班");
        }
        kechenganpaiService.updateById(kechenganpai);
        return R.ok("排班状态更新成功");
    }

    /**
     * 获取待上课数量
     */
    @RequestMapping("/getPendingCount")
    public R getPendingCount(HttpServletRequest request) {
        String jiaoliangonghao = (String) request.getSession().getAttribute("username");
        EntityWrapper<KechenganpaiEntity> ew = new EntityWrapper<KechenganpaiEntity>();
        ew.eq("jiaoliangonghao", jiaoliangonghao);
        ew.eq("shangkezt", "待上课");
        int count = kechenganpaiService.selectCount(ew);
        return R.ok().put("count", count);
    }

    /**
     * 更新个人信息
     */
    @RequestMapping("/updateProfile")
    public R updateProfile(@RequestBody JiaolianEntity jiaolian, HttpServletRequest request) {
        String jiaoliangonghao = (String) request.getSession().getAttribute("username");
        JiaolianEntity currentUser = jiaolianService.selectOne(new EntityWrapper<JiaolianEntity>().eq("jiaoliangonghao", jiaoliangonghao));
        if(currentUser == null){
            return R.error("用户不存在");
        }

        // 只允许更新部分字段
        if(jiaolian.getJiaolianxingming() != null){
            currentUser.setJiaolianxingming(jiaolian.getJiaolianxingming());
        }
        if(jiaolian.getXingbie() != null){
            currentUser.setXingbie(jiaolian.getXingbie());
        }
        if(jiaolian.getNianling() != null){
            currentUser.setNianling(jiaolian.getNianling());
        }
        if(jiaolian.getShouji() != null){
            currentUser.setShouji(jiaolian.getShouji());
        }
        if(jiaolian.getZhidaokemu() != null){
            currentUser.setZhidaokemu(jiaolian.getZhidaokemu());
        }
        if(jiaolian.getTouxiang() != null){
            currentUser.setTouxiang(jiaolian.getTouxiang());
        }
        if(jiaolian.getGerenjianjie() != null){
            currentUser.setGerenjianjie(jiaolian.getGerenjianjie());
        }
        if(jiaolian.getJiaoxuetese() != null){
            currentUser.setJiaoxuetese(jiaolian.getJiaoxuetese());
        }

        jiaolianService.updateById(currentUser);
        return R.ok("个人信息更新成功");
    }
    
    @Autowired
    private XueyuanService xueyuanService;
    
    /**
     * 获取学员列表
     */
    @RequestMapping("/getStudents")
    public R getStudents(HttpServletRequest request) {
        String jiaoliangonghao = (String) request.getSession().getAttribute("username");
        // 这里需要根据实际业务逻辑查询该教练所带的学员
        // 假设我们通过课程安排来关联教练和学员
        EntityWrapper<KechenganpaiEntity> ew = new EntityWrapper<KechenganpaiEntity>();
        ew.eq("jiaoliangonghao", jiaoliangonghao);
        List<KechenganpaiEntity> kechengList = kechenganpaiService.selectList(ew);
        
        Set<String> zhanghaoSet = new HashSet<>();
        for (KechenganpaiEntity kecheng : kechengList) {
            if(kecheng.getZhanghao() != null && !kecheng.getZhanghao().isEmpty()) {
                zhanghaoSet.add(kecheng.getZhanghao());
            }
        }
        
        List<XueyuanEntity> studentList = new ArrayList<>();
        for (String zhanghao : zhanghaoSet) {
            XueyuanEntity student = xueyuanService.selectOne(new EntityWrapper<XueyuanEntity>().eq("zhanghao", zhanghao));
            if (student != null) {
                studentList.add(student);
            }
        }
        
        return R.ok().put("data", studentList);
    }
	







}
