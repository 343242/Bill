package com.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ExamquestionEntity;
import com.entity.ExampaperEntity;
import com.entity.view.ExamquestionView;

import com.service.ExamquestionService;
import com.service.ExampaperService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;

/**
 * 试题表
 * 后端接口
 * @author 
 * @email 
 * @date 2022-04-20 00:17:37
 */
@RestController
@RequestMapping("/examquestion")
public class ExamquestionController {
    @Autowired
    private ExamquestionService examquestionService;

    @Autowired
    private ExampaperService exampaperService;


    


    /**
     * 后端列表
     */
    @IgnoreAuth
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ExamquestionEntity examquestion,
		HttpServletRequest request){
        EntityWrapper<ExamquestionEntity> ew = new EntityWrapper<ExamquestionEntity>();
		PageUtils page = examquestionService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, examquestion), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ExamquestionEntity examquestion, 
		HttpServletRequest request){
        EntityWrapper<ExamquestionEntity> ew = new EntityWrapper<ExamquestionEntity>();
		PageUtils page = examquestionService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, examquestion), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ExamquestionEntity examquestion){
       	EntityWrapper<ExamquestionEntity> ew = new EntityWrapper<ExamquestionEntity>();
      	ew.allEq(MPUtil.allEQMapPre( examquestion, "examquestion")); 
        return R.ok().put("data", examquestionService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ExamquestionEntity examquestion){
        EntityWrapper< ExamquestionEntity> ew = new EntityWrapper< ExamquestionEntity>();
 		ew.allEq(MPUtil.allEQMapPre( examquestion, "examquestion")); 
		ExamquestionView examquestionView =  examquestionService.selectView(ew);
		return R.ok("查询试题表成功").put("data", examquestionView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ExamquestionEntity examquestion = examquestionService.selectById(id);
        return R.ok().put("data", examquestion);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ExamquestionEntity examquestion = examquestionService.selectById(id);
        return R.ok().put("data", examquestion);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ExamquestionEntity examquestion, HttpServletRequest request){
    	examquestion.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(examquestion);
        examquestionService.insert(examquestion);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ExamquestionEntity examquestion, HttpServletRequest request){
    	examquestion.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(examquestion);
        examquestionService.insert(examquestion);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ExamquestionEntity examquestion, HttpServletRequest request){
        //ValidatorUtils.validateEntity(examquestion);
        examquestionService.updateById(examquestion);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        examquestionService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<ExamquestionEntity> wrapper = new EntityWrapper<ExamquestionEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = examquestionService.selectCount(wrapper);
		return R.ok().put("count", count);
	}

    @IgnoreAuth
    @RequestMapping("/template")
    public ResponseEntity<byte[]> downloadTemplate() {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("试题导入模板");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("练习题库名称");
            headerRow.createCell(1).setCellValue("试题名称");
            headerRow.createCell(2).setCellValue("试题类型(0单选/1多选/2判断/3填空)");
            headerRow.createCell(3).setCellValue("选项A内容");
            headerRow.createCell(4).setCellValue("选项B内容");
            headerRow.createCell(5).setCellValue("选项C内容");
            headerRow.createCell(6).setCellValue("选项D内容");
            headerRow.createCell(7).setCellValue("正确答案");
            headerRow.createCell(8).setCellValue("分值");
            headerRow.createCell(9).setCellValue("答案解析");
            headerRow.createCell(10).setCellValue("排序");

            Row exampleRow1 = sheet.createRow(1);
            exampleRow1.createCell(0).setCellValue("科目一理论考试");
            exampleRow1.createCell(1).setCellValue("下面动物不属于昆虫的是（）。");
            exampleRow1.createCell(2).setCellValue("0");
            exampleRow1.createCell(3).setCellValue("苍蝇");
            exampleRow1.createCell(4).setCellValue("蜜蜂");
            exampleRow1.createCell(5).setCellValue("蜂鸟");
            exampleRow1.createCell(6).setCellValue("");
            exampleRow1.createCell(7).setCellValue("C");
            exampleRow1.createCell(8).setCellValue("20");
            exampleRow1.createCell(9).setCellValue("蜂鸟属于鸟类");
            exampleRow1.createCell(10).setCellValue("1");

            Row exampleRow2 = sheet.createRow(2);
            exampleRow2.createCell(0).setCellValue("科目一理论考试");
            exampleRow2.createCell(1).setCellValue("油着火后可以用水扑灭。");
            exampleRow2.createCell(2).setCellValue("2");
            exampleRow2.createCell(3).setCellValue("对");
            exampleRow2.createCell(4).setCellValue("错");
            exampleRow2.createCell(5).setCellValue("");
            exampleRow2.createCell(6).setCellValue("");
            exampleRow2.createCell(7).setCellValue("B");
            exampleRow2.createCell(8).setCellValue("20");
            exampleRow2.createCell(9).setCellValue("油着火后不可以用水扑灭");
            exampleRow2.createCell(10).setCellValue("2");

            Row exampleRow3 = sheet.createRow(3);
            exampleRow3.createCell(0).setCellValue("科目一理论考试");
            exampleRow3.createCell(1).setCellValue("下面动物中会流汗的有（）。");
            exampleRow3.createCell(2).setCellValue("1");
            exampleRow3.createCell(3).setCellValue("马");
            exampleRow3.createCell(4).setCellValue("猫");
            exampleRow3.createCell(5).setCellValue("狗");
            exampleRow3.createCell(6).setCellValue("");
            exampleRow3.createCell(7).setCellValue("A,B");
            exampleRow3.createCell(8).setCellValue("30");
            exampleRow3.createCell(9).setCellValue("狗不会流汗");
            exampleRow3.createCell(10).setCellValue("3");

            for (int i = 0; i <= 10; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "examquestion_template.xlsx");
            return new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/batchImport")
    public R batchImport(@RequestParam("file") MultipartFile file, @RequestParam("paperid") Long paperid) {
        if (file.isEmpty()) {
            return R.error("上传文件不能为空");
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null || (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx"))) {
            return R.error("请上传Excel文件(.xls或.xlsx)");
        }
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            ExampaperEntity paper = exampaperService.selectById(paperid);
            if (paper == null) {
                return R.error("练习题库不存在");
            }

            int successCount = 0;
            int errorCount = 0;
            List<String> errorMessages = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String questionname = getCellValue(row, 1);
                if (StringUtils.isBlank(questionname)) {
                    continue;
                }

                String typeStr = getCellValue(row, 2);
                Long type = 0L;
                try {
                    type = Long.parseLong(typeStr.trim());
                } catch (NumberFormatException e) {
                    errorCount++;
                    errorMessages.add("第" + (i + 1) + "行：试题类型格式错误");
                    continue;
                }

                String optionA = getCellValue(row, 3);
                String optionB = getCellValue(row, 4);
                String optionC = getCellValue(row, 5);
                String optionD = getCellValue(row, 6);
                String answer = getCellValue(row, 7);
                String scoreStr = getCellValue(row, 8);
                String analysis = getCellValue(row, 9);
                String sequenceStr = getCellValue(row, 10);

                List<Map<String, String>> optionsList = new ArrayList<>();
                if (type == 2) {
                    if (StringUtils.isNotBlank(optionA)) {
                        optionsList.add(createOption("A", optionA));
                    }
                    if (StringUtils.isNotBlank(optionB)) {
                        optionsList.add(createOption("B", optionB));
                    }
                } else if (type == 3) {
                    // 填空题不需要选项
                } else {
                    if (StringUtils.isNotBlank(optionA)) {
                        optionsList.add(createOption("A", optionA));
                    }
                    if (StringUtils.isNotBlank(optionB)) {
                        optionsList.add(createOption("B", optionB));
                    }
                    if (StringUtils.isNotBlank(optionC)) {
                        optionsList.add(createOption("C", optionC));
                    }
                    if (StringUtils.isNotBlank(optionD)) {
                        optionsList.add(createOption("D", optionD));
                    }
                }

                String optionsJson = optionsList.isEmpty() ? "[]" : com.alibaba.fastjson.JSON.toJSONString(optionsList);

                Long score = 0L;
                try {
                    if (StringUtils.isNotBlank(scoreStr)) {
                        score = Long.parseLong(scoreStr.trim());
                    }
                } catch (NumberFormatException e) {
                    score = 0L;
                }

                Long sequence = 100L;
                try {
                    if (StringUtils.isNotBlank(sequenceStr)) {
                        sequence = Long.parseLong(sequenceStr.trim());
                    }
                } catch (NumberFormatException e) {
                    sequence = 100L;
                }

                ExamquestionEntity question = new ExamquestionEntity();
                question.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
                question.setPaperid(paperid);
                question.setPapername(paper.getName());
                question.setQuestionname(questionname);
                question.setType(type);
                question.setOptions(optionsJson);
                question.setAnswer(answer);
                question.setScore(score);
                question.setAnalysis(analysis);
                question.setSequence(sequence);
                question.setAddtime(new Date());

                examquestionService.insert(question);
                successCount++;
            }

            if (errorCount > 0) {
                return R.ok("导入完成，成功" + successCount + "条，失败" + errorCount + "条：" + String.join("；", errorMessages));
            }
            return R.ok("导入成功，共导入" + successCount + "条试题");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("导入失败：" + e.getMessage());
        }
    }

    private String getCellValue(Row row, int cellIndex) {
        if (row.getCell(cellIndex) == null) return "";
        row.getCell(cellIndex).setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
        return row.getCell(cellIndex).getStringCellValue();
    }

    private Map<String, String> createOption(String code, String text) {
        Map<String, String> option = new HashMap<>();
        option.put("code", code);
        option.put("text", code + "." + text);
        return option;
    }

}
