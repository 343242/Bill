package com.entity.vo;

import com.entity.ShouYeXinXiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 首页信息
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 */
public class ShouYeXinXiVO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
		 				/**
	 * 信息类型
	 */
	private String leixing;
	/**
	 * 标题
	 */
	private String biaoti;
	/**
	 * 内容
	 */
	private String neirong;
	/**
	 * 图片
	 */
	private String tupian;
	/**
	 * 排序
	 */
	private Integer paixu;
	/**
	 * 状态
	 */
	private String zhuangtai;
				
	
	/**
	 * 设置：信息类型
	 */
	 
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	
	/**
	 * 获取：信息类型
	 */
	public String getLeixing() {
		return leixing;
	}
			
	
	/**
	 * 设置：标题
	 */
	 
	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}
	
	/**
	 * 获取：标题
	 */
	public String getBiaoti() {
		return biaoti;
	}
			
	
	/**
	 * 设置：内容
	 */
	 
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}
	
	/**
	 * 获取：内容
	 */
	public String getNeirong() {
		return neirong;
	}
			
	
	/**
	 * 设置：图片
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
			
	
	/**
	 * 设置：排序
	 */
	 
	public void setPaixu(Integer paixu) {
		this.paixu = paixu;
	}
	
	/**
	 * 获取：排序
	 */
	public Integer getPaixu() {
		return paixu;
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
