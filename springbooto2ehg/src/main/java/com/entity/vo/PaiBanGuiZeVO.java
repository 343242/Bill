package com.entity.vo;

import com.entity.PaiBanGuiZeEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 排班规则
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 */
public class PaiBanGuiZeVO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
		 				/**
	 * 规则名称
	 */
	private String guizemingcheng;
	/**
	 * 开始时间
	 */
	private String kaishishijian;
	/**
	 * 结束时间
	 */
	private String jieshushijian;
	/**
	 * 可预约人数
	 */
	private Integer keyuyuerenshu;
	/**
	 * 适用教练
	 */
	private String shiyongjiaolian;
	/**
	 * 状态
	 */
	private String zhuangtai;
				
	
	/**
	 * 设置：规则名称
	 */
	 
	public void setGuizemingcheng(String guizemingcheng) {
		this.guizemingcheng = guizemingcheng;
	}
	
	/**
	 * 获取：规则名称
	 */
	public String getGuizemingcheng() {
		return guizemingcheng;
	}
			
	
	/**
	 * 设置：开始时间
	 */
	 
	public void setKaishishijian(String kaishishijian) {
		this.kaishishijian = kaishishijian;
	}
	
	/**
	 * 获取：开始时间
	 */
	public String getKaishishijian() {
		return kaishishijian;
	}
			
	
	/**
	 * 设置：结束时间
	 */
	 
	public void setJieshushijian(String jieshushijian) {
		this.jieshushijian = jieshushijian;
	}
	
	/**
	 * 获取：结束时间
	 */
	public String getJieshushijian() {
		return jieshushijian;
	}
			
	
	/**
	 * 设置：可预约人数
	 */
	 
	public void setKeyuyuerenshu(Integer keyuyuerenshu) {
		this.keyuyuerenshu = keyuyuerenshu;
	}
	
	/**
	 * 获取：可预约人数
	 */
	public Integer getKeyuyuerenshu() {
		return keyuyuerenshu;
	}
			
	
	/**
	 * 设置：适用教练
	 */
	 
	public void setShiyongjiaolian(String shiyongjiaolian) {
		this.shiyongjiaolian = shiyongjiaolian;
	}
	
	/**
	 * 获取：适用教练
	 */
	public String getShiyongjiaolian() {
		return shiyongjiaolian;
	}
			
	
	/**
	 * 设置：状态
	 */
	 
	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	
	/**
	 * 获取：状态
	 */
	public String getZhuangtai() {
		return zhuangtai;
	}
			
}
