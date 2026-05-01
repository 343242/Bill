package com.controller;

import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.UserEntity;
import com.entity.JiaolianEntity;
import com.entity.XueyuanEntity;
import com.entity.JiaxiaoxinxiEntity;
import com.service.UserService;
import com.service.JiaolianService;
import com.service.XueyuanService;
import com.service.JiaxiaoxinxiService;
import com.service.KechenganpaiService;
import com.service.PaiBanGuiZeService;
import com.entity.KechenganpaiEntity;
import com.entity.PaiBanGuiZeEntity;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 管理员控制器
 * 后端接口
 */
@RequestMapping("/admin")
@RestController
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JiaolianService jiaolianService;
    
    @Autowired
    private XueyuanService xueyuanService;
    
    @Autowired
    private JiaxiaoxinxiService jiaxiaoxinxiService;
    
    @Autowired
    private KechenganpaiService kechenganpaiService;
    
    @Autowired
    private PaiBanGuiZeService paiBanGuiZeService;
    
    /**
     * 基础信息管理 - 用户列表
     */
    @RequestMapping("/userList")
    public R userList(@RequestParam Map<String, Object> params, UserEntity user) {
        EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();
        PageUtils page = userService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, user), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 基础信息管理 - 教练列表
     */
    @RequestMapping("/jiaolianList")
    public R jiaolianList(@RequestParam Map<String, Object> params, JiaolianEntity jiaolian) {
        EntityWrapper<JiaolianEntity> ew = new EntityWrapper<JiaolianEntity>();
        PageUtils page = jiaolianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, jiaolian), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 基础信息管理 - 学员列表
     */
    @RequestMapping("/xueyuanList")
    public R xueyuanList(@RequestParam Map<String, Object> params, XueyuanEntity xueyuan) {
        EntityWrapper<XueyuanEntity> ew = new EntityWrapper<XueyuanEntity>();
        PageUtils page = xueyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, xueyuan), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 基础信息管理 - 驾校信息
     */
    @RequestMapping("/jiaxiaoList")
    public R jiaxiaoList(@RequestParam Map<String, Object> params, JiaxiaoxinxiEntity jiaxiaoxinxi) {
        EntityWrapper<JiaxiaoxinxiEntity> ew = new EntityWrapper<JiaxiaoxinxiEntity>();
        PageUtils page = jiaxiaoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, jiaxiaoxinxi), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 教练排班统筹管理 - 排班与计划管理
     */
    @RequestMapping("/scheduleList")
    public R scheduleList(@RequestParam Map<String, Object> params, KechenganpaiEntity kechenganpai) {
        EntityWrapper<KechenganpaiEntity> ew = new EntityWrapper<KechenganpaiEntity>();
        PageUtils page = kechenganpaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, kechenganpai), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 教练排班统筹管理 - 排班规则管理
     */
    @RequestMapping("/scheduleRuleList")
    public R scheduleRuleList(@RequestParam Map<String, Object> params, PaiBanGuiZeEntity paiBanGuiZe) {
        EntityWrapper<PaiBanGuiZeEntity> ew = new EntityWrapper<PaiBanGuiZeEntity>();
        PageUtils page = paiBanGuiZeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, paiBanGuiZe), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 理论学习考试统筹管理 - 学习资源管理
     */
    @RequestMapping("/resourceList")
    public R resourceList(@RequestParam Map<String, Object> params) {
        // 这里可以根据实际业务逻辑查询学习资源
        return R.ok();
    }
    
    /**
     * 理论学习考试统筹管理 - 考试题库管理
     */
    @RequestMapping("/examQuestionList")
    public R examQuestionList(@RequestParam Map<String, Object> params) {
        // 这里可以根据实际业务逻辑查询考试题库
        return R.ok();
    }
    
    /**
     * 练车预约统筹管理 - 预约信息查询
     */
    @RequestMapping("/appointmentList")
    public R appointmentList(@RequestParam Map<String, Object> params) {
        // 这里可以根据实际业务逻辑查询预约信息
        return R.ok();
    }
}
