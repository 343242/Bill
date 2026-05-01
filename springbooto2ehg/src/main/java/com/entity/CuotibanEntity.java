package com.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/** 
 * 错题本
 */
@TableName("cuotiban")
public class CuotibanEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	/**
	 * 学员账号
	 */
	private String zhanghao;
	
	/**
	 * 题目id
	 */
	private Long timuid;
	
	/**
	 * 题目内容
	 */
	private String timuneirong;
	
	/**
	 * 错误答案
	 */
	private String cuowudaan;
	
	/**
	 * 正确答案
	 */
	private String zhengquedaan;
	
	/**
	 * 添加时间
	 */
	private Date addtime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZhanghao() {
		return zhanghao;
	}

	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}

	public Long getTimuid() {
		return timuid;
	}

	public void setTimuid(Long timuid) {
		this.timuid = timuid;
	}

	public String getTimuneirong() {
		return timuneirong;
	}

	public void setTimuneirong(String timuneirong) {
		this.timuneirong = timuneirong;
	}

	public String getCuowudaan() {
		return cuowudaan;
	}

	public void setCuowudaan(String cuowudaan) {
		this.cuowudaan = cuowudaan;
	}

	public String getZhengquedaan() {
		return zhengquedaan;
	}

	public void setZhengquedaan(String zhengquedaan) {
		this.zhengquedaan = zhengquedaan;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

}
