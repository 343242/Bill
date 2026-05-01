package com.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.annotation.IgnoreAuth;
import com.service.XueyuanService;
import com.service.JiaolianService;
import com.service.YuyueliancheService;
import com.service.ExamrecordService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.YuyueliancheEntity;
import com.entity.ExamrecordEntity;
import com.utils.R;

/**
 * 首页数据统计
 * 后端接口
 */
@RequestMapping("/home")
@RestController
public class HomeController {
    
    @Autowired
    private XueyuanService xueyuanService;
    
    @Autowired
    private JiaolianService jiaolianService;
    
    @Autowired
    private YuyueliancheService yuyueliancheService;
    
    @Autowired
    private ExamrecordService examrecordService;
    
    /**
     * 获取首页统计数据
     */
    @IgnoreAuth
    @RequestMapping("/stats")
    public R getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // 在校学员数量
        long xueyuanCount = xueyuanService.selectCount(null);
        stats.put("xueyuanCount", xueyuanCount);
        
        // 教练数量
        long jiaolianCount = jiaolianService.selectCount(null);
        stats.put("jiaolianCount", jiaolianCount);
        
        // 今日预约数量
        // 这里需要根据实际日期过滤，暂时使用全量数据
        long todayAppointmentCount = yuyueliancheService.selectCount(null);
        stats.put("todayAppointmentCount", todayAppointmentCount);
        
        // 本月考试数量
        // 这里需要根据实际月份过滤，暂时使用全量数据
        long thisMonthExamCount = examrecordService.selectCount(null);
        stats.put("thisMonthExamCount", thisMonthExamCount);
        
        return R.ok().put("data", stats);
    }
    
    /**
     * 获取近7日活跃趋势数据
     */
    @IgnoreAuth
    @RequestMapping("/activity")
    public R getActivity() {
        // 这里需要根据实际业务逻辑获取近7日的活跃数据
        // 暂时返回模拟数据
        int[] activityData = {45, 60, 75, 55, 80, 90, 65};
        return R.ok().put("data", activityData);
    }
}
