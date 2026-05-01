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
 * 系统日志
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@TableName("rizhi")
public class RiZhiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public RiZhiEntity() {
		
	}
	
	public RiZhiEntity(T t) {
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
	 * 操作用户
	 */
				
	private String caozuoyonghu;
	
	/**
	 * 操作类型
	 */
				
	private String caozuoleixing;
	
	/**
	 * 操作内容
	 */
				
	private String caozuoneirong;
	
	/**
	 * 操作时间
	 */
			
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat  
	private Date caozuoshijian;
	
	/**
	 * IP地址
	 */
				
	private String ipdizhi;
	
	/**
	 * 浏览器
	 */
				
	private String liulanqi;
	

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
	 * 设置：操作用户
	 */
	public void setCaozuoyonghu(String caozuoyonghu) {
		this.caozuoyonghu = caozuoyonghu;
	}
	/**
	 * 获取：操作用户
	 */
	public String getCaozuoyonghu() {
		return caozuoyonghu;
	}
	/**
	 * 设置：操作类型
	 */
	public void setCaozuoleixing(String caozuoleixing) {
		this.caozuoleixing = caozuoleixing;
	}
	/**
	 * 获取：操作类型
	 */
	public String getCaozuoleixing() {
		return caozuoleixing;
	}
	/**
	 * 设置：操作内容
	 */
	public void setCaozuoneirong(String caozuoneirong) {
		this.caozuoneirong = caozuoneirong;
	}
	/**
	 * 获取：操作内容
	 */
	public String getCaozuoneirong() {
		return caozuoneirong;
	}
	/**
	 * 设置：操作时间
	 */
	public void setCaozuoshijian(Date caozuoshijian) {
		this.caozuoshijian = caozuoshijian;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getCaozuoshijian() {
		return caozuoshijian;
	}
	/**
	 * 设置：IP地址
	 */
	public void setIpdizhi(String ipdizhi) {
		this.ipdizhi = ipdizhi;
	}
	/**
	 * 获取：IP地址
	 */
	public String getIpdizhi() {
		return ipdizhi;
	}
	/**
	 * 设置：浏览器
	 */
	public void setLiulanqi(String liulanqi) {
		this.liulanqi = liulanqi;
	}
	/**
	 * 获取：浏览器
	 */
	public String getLiulanqi() {
		return liulanqi;
	}

}