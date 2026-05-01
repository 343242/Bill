package com.entity.vo;

import com.entity.ShipinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 


/**
 * 视频学习
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
public class ShipinVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 
	/**
	 * 视频标题
	 */
	
	private String biaoti;
		
	/**
	 * 视频分类
	 */
	
	private String fenlei;
		
	/**
	 * 视频链接
	 */
	
	private String lianjie;
		
	/**
	 * 视频封面
	 */
	
	private String fengmian;
		
	/**
	 * 视频描述
	 */
	
	private String miaoshu;
		
	/**
	 * 点赞数
	 */
	
	private Integer dianzanshu;
		
	/**
	 * 收藏数
	 */
	
	private Integer shoucangshu;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
			

	/**
	 * 设置：视频标题
	 */
	 
	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	/**
	 * 获取：视频标题
	 */
	public String getBiaoti() {
		return biaoti;
	}
			

	/**
	 * 设置：视频分类
	 */
	 
	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}

	/**
	 * 获取：视频分类
	 */
	public String getFenlei() {
		return fenlei;
	}
			

	/**
	 * 设置：视频链接
	 */
	 
	public void setLianjie(String lianjie) {
		this.lianjie = lianjie;
	}

	/**
	 * 获取：视频链接
	 */
	public String getLianjie() {
		return lianjie;
	}
			

	/**
	 * 设置：视频封面
	 */
	 
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}

	/**
	 * 获取：视频封面
	 */
	public String getFengmian() {
		return fengmian;
	}
			

	/**
	 * 设置：视频描述
	 */
	 
	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	/**
	 * 获取：视频描述
	 */
	public String getMiaoshu() {
		return miaoshu;
	}
			

	/**
	 * 设置：点赞数
	 */
	 
	public void setDianzanshu(Integer dianzanshu) {
		this.dianzanshu = dianzanshu;
	}

	/**
	 * 获取：点赞数
	 */
	public Integer getDianzanshu() {
		return dianzanshu;
	}
			

	/**
	 * 设置：收藏数
	 */
	 
	public void setShoucangshu(Integer shoucangshu) {
		this.shoucangshu = shoucangshu;
	}

	/**
	 * 获取：收藏数
	 */
	public Integer getShoucangshu() {
		return shoucangshu;
	}
			

	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}

	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
		
}
