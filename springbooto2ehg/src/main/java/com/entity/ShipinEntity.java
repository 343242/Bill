package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 视频学习
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@TableName("shipin")
public class ShipinEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ShipinEntity() {
		
	}
	
	public ShipinEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
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
	 * 视频文件
	 */
				
	private String shipinwenjian;
	
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
	

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
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
	 * 设置：视频文件
	 */
	public void setShipinwenjian(String shipinwenjian) {
		this.shipinwenjian = shipinwenjian;
	}
	/**
	 * 获取：视频文件
	 */
	public String getShipinwenjian() {
		return shipinwenjian;
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