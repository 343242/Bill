package com.entity.vo;

import com.entity.ShipinpinglunEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 视频评论
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 */
public class ShipinpinglunVO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
		 				/**
	 * 视频id
	 */
	private Long shipinid;
	/**
	 * 评论内容
	 */
	private String pinglunneirong;
	/**
	 * 评论人
	 */
	private String pinglunren;
	/**
	 * 评论时间
	 */
	private Date pingluntime;
	/**
	 * 回复内容
	 */
	private String huifuneirong;
				
	
	/**
	 * 设置：视频id
	 */
	 
	public void setShipinid(Long shipinid) {
		this.shipinid = shipinid;
	}
	
	/**
	 * 获取：视频id
	 */
	public Long getShipinid() {
		return shipinid;
	}
			
	
	/**
	 * 设置：评论内容
	 */
	 
	public void setPinglunneirong(String pinglunneirong) {
		this.pinglunneirong = pinglunneirong;
	}
	
	/**
	 * 获取：评论内容
	 */
	public String getPinglunneirong() {
		return pinglunneirong;
	}
			
	
	/**
	 * 设置：评论人
	 */
	 
	public void setPinglunren(String pinglunren) {
		this.pinglunren = pinglunren;
	}
	
	/**
	 * 获取：评论人
	 */
	public String getPinglunren() {
		return pinglunren;
	}
			
	
	/**
	 * 设置：评论时间
	 */
	 
	public void setPingluntime(Date pingluntime) {
		this.pingluntime = pingluntime;
	}
	
	/**
	 * 获取：评论时间
	 */
	public Date getPingluntime() {
		return pingluntime;
	}
			
	
	/**
	 * 设置：回复内容
	 */
	 
	public void setHuifuneirong(String huifuneirong) {
		this.huifuneirong = huifuneirong;
	}
	
	/**
	 * 获取：回复内容
	 */
	public String getHuifuneirong() {
		return huifuneirong;
	}
			
}
