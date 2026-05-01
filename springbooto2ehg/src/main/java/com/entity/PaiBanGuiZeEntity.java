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
 * 排班规则
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-04-20 00:17:36
 */
@TableName("paibanguize")
public class PaiBanGuiZeEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public PaiBanGuiZeEntity() {
		
	}
	
	public PaiBanGuiZeEntity(T t) {
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