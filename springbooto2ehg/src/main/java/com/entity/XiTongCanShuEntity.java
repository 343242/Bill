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
 * 系统参数
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@TableName("xitongcanshu")
public class XiTongCanShuEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public XiTongCanShuEntity() {
		
	}
	
	public XiTongCanShuEntity(T t) {
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
	 * 参数名称
	 */
				
	private String canshumingcheng;
	
	/**
	 * 参数键
	 */
				
	private String canshujian;
	
	/**
	 * 参数值
	 */
				
	private String canshuzhi;
	
	/**
	 * 备注
	 */
				
	private String beizhu;
	
	/**
	 * 状态
	 */
				
	private String zhuangtai;
	

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
	 * 设置：参数名称
	 */
	public void setCanshumingcheng(String canshumingcheng) {
		this.canshumingcheng = canshumingcheng;
	}
	/**
	 * 获取：参数名称
	 */
	public String getCanshumingcheng() {
		return canshumingcheng;
	}
	/**
	 * 设置：参数键
	 */
	public void setCanshujian(String canshujian) {
		this.canshujian = canshujian;
	}
	/**
	 * 获取：参数键
	 */
	public String getCanshujian() {
		return canshujian;
	}
	/**
	 * 设置：参数值
	 */
	public void setCanshuzhi(String canshuzhi) {
		this.canshuzhi = canshuzhi;
	}
	/**
	 * 获取：参数值
	 */
	public String getCanshuzhi() {
		return canshuzhi;
	}
	/**
	 * 设置：备注
	 */
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	/**
	 * 获取：备注
	 */
	public String getBeizhu() {
		return beizhu;
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