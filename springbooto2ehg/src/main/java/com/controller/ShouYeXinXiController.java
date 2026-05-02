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
import java.util.Comparator;
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
import com.entity.JiaolianEntity;
import com.entity.JiaxiaoxinxiEntity;
import com.entity.NewsEntity;
import com.entity.view.ShouYeXinXiView;

import com.service.ShouYeXinXiService;
import com.service.JiaolianService;
import com.service.JiaxiaoxinxiService;
import com.service.NewsService;
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
    @Autowired
    private JiaolianService jiaolianService;
    @Autowired
    private JiaxiaoxinxiService jiaxiaoxinxiService;
    @Autowired
    private NewsService newsService;


    


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
        ew1.eq("leixing", "驾校概况").in("zhuangtai", Arrays.asList("已发布", "启用", "显示")).orderBy("paixu", true);
        List<ShouYeXinXiEntity> jiaxiaogaikuang = shouYeXinXiService.selectList(ew1);
        map.put("jiaxiaogaikuang", jiaxiaogaikuang);
        
        // 获取教练信息
        EntityWrapper<ShouYeXinXiEntity> ew2 = new EntityWrapper<ShouYeXinXiEntity>();
        ew2.eq("leixing", "教练信息").in("zhuangtai", Arrays.asList("已发布", "启用", "显示")).orderBy("paixu", true);
        List<ShouYeXinXiEntity> jiaolianxinxi = shouYeXinXiService.selectList(ew2);
        map.put("jiaolianxinxi", jiaolianxinxi);
        
        // 获取报名须知
        EntityWrapper<ShouYeXinXiEntity> ew3 = new EntityWrapper<ShouYeXinXiEntity>();
        ew3.eq("leixing", "报名须知").in("zhuangtai", Arrays.asList("已发布", "启用", "显示")).orderBy("paixu", true);
        List<ShouYeXinXiEntity> baomingxuzhi = shouYeXinXiService.selectList(ew3);
        map.put("baomingxuzhi", baomingxuzhi);
        
        return R.ok().put("data", map);
    }

    /**
     * 首页信息聚合列表
     */
    @RequestMapping("/aggregatePage")
    public R aggregatePage(@RequestParam Map<String, Object> params, HttpServletRequest request){
        String leixing = params.get("leixing") == null ? null : params.get("leixing").toString();
        String biaoti = params.get("biaoti") == null ? null : params.get("biaoti").toString();
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());

        List<Map<String, Object>> merged = new ArrayList<>();

        List<JiaxiaoxinxiEntity> schoolList = jiaxiaoxinxiService.selectList(new EntityWrapper<JiaxiaoxinxiEntity>().orderBy("addtime", false));
        for (JiaxiaoxinxiEntity item : schoolList) {
            StringBuilder contentBuilder = new StringBuilder();
            if (StringUtils.isNotBlank(item.getJiaxiaoleixing())) {
                contentBuilder.append("驾校类型：").append(item.getJiaxiaoleixing());
            }
            if (StringUtils.isNotBlank(item.getJiaxiaodizhi())) {
                if (contentBuilder.length() > 0) {
                    contentBuilder.append("\n");
                }
                contentBuilder.append("驾校地址：").append(item.getJiaxiaodizhi());
            }
            if (item.getBaomingfeiyong() != null) {
                if (contentBuilder.length() > 0) {
                    contentBuilder.append("\n");
                }
                contentBuilder.append("报名费用：").append(item.getBaomingfeiyong());
            }
            if (StringUtils.isNotBlank(item.getJiaxiaodianhua())) {
                if (contentBuilder.length() > 0) {
                    contentBuilder.append("\n");
                }
                contentBuilder.append("驾校电话：").append(item.getJiaxiaodianhua());
            }
            if (StringUtils.isNotBlank(item.getJiaxiaojieshao())) {
                if (contentBuilder.length() > 0) {
                    contentBuilder.append("\n");
                }
                contentBuilder.append(item.getJiaxiaojieshao());
            }
            merged.add(buildAggregateRow(
                    item.getId(),
                    "jiaxiaoxinxi",
                    "驾校概况",
                    item.getJiaxiaomingcheng(),
                    contentBuilder.toString(),
                    "-",
                    item.getAddtime()
            ));
        }

        List<JiaolianEntity> coachList = jiaolianService.selectList(new EntityWrapper<JiaolianEntity>().orderBy("addtime", false));
        for (JiaolianEntity item : coachList) {
            String title = StringUtils.defaultIfBlank(item.getJiaolianxingming(), "教练") + "介绍";
            StringBuilder contentBuilder = new StringBuilder();
            if (StringUtils.isNotBlank(item.getGerenjianjie())) {
                contentBuilder.append(item.getGerenjianjie());
            }
            if (StringUtils.isNotBlank(item.getJiaoxuetese())) {
                if (contentBuilder.length() > 0) {
                    contentBuilder.append("\n");
                }
                contentBuilder.append(item.getJiaoxuetese());
            }
            merged.add(buildAggregateRow(
                    item.getId(),
                    "jiaolian",
                    "教练信息",
                    title,
                    contentBuilder.toString(),
                    item.getZhuangtai(),
                    item.getAddtime()
            ));
        }

        List<NewsEntity> noticeList = newsService.selectList(new EntityWrapper<NewsEntity>().orderBy("addtime", false));
        for (NewsEntity item : noticeList) {
            String content = StringUtils.isNotBlank(item.getContent()) ? item.getContent() : item.getIntroduction();
            merged.add(buildAggregateRow(
                    item.getId(),
                    "news",
                    "报名须知",
                    item.getTitle(),
                    content,
                    item.getZhuangtai(),
                    item.getAddtime()
            ));
        }

        List<Map<String, Object>> filtered = new ArrayList<>();
        for (Map<String, Object> row : merged) {
            String rowLeixing = row.get("leixing") == null ? "" : row.get("leixing").toString();
            String rowBiaoti = row.get("biaoti") == null ? "" : row.get("biaoti").toString();
            if (StringUtils.isNotBlank(leixing) && !rowLeixing.equals(leixing)) {
                continue;
            }
            if (StringUtils.isNotBlank(biaoti) && !rowBiaoti.contains(biaoti)) {
                continue;
            }
            filtered.add(row);
        }

        filtered.sort(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> left, Map<String, Object> right) {
                Date leftDate = (Date) left.get("addtime");
                Date rightDate = (Date) right.get("addtime");
                if (leftDate == null && rightDate == null) {
                    return 0;
                }
                if (leftDate == null) {
                    return 1;
                }
                if (rightDate == null) {
                    return -1;
                }
                return rightDate.compareTo(leftDate);
            }
        });

        int total = filtered.size();
        int fromIndex = Math.max((page - 1) * limit, 0);
        int toIndex = Math.min(fromIndex + limit, total);
        List<Map<String, Object>> pageList = fromIndex >= total ? new ArrayList<Map<String, Object>>() : filtered.subList(fromIndex, toIndex);

        return R.ok().put("data", new PageUtils(pageList, total, limit, page));
    }

    private Map<String, Object> buildAggregateRow(Long id, String sourceTable, String leixing, String biaoti, String neirong, String zhuangtai, Date addtime) {
        Map<String, Object> row = new HashMap<>();
        row.put("id", id);
        row.put("sourceId", id);
        row.put("sourceTable", sourceTable);
        row.put("leixing", leixing);
        row.put("biaoti", biaoti);
        row.put("neirong", neirong);
        row.put("zhuangtai", zhuangtai);
        row.put("addtime", addtime);
        return row;
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
