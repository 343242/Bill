package com.entity.vo;

import com.entity.CuotibanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 错题本
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 */
public class CuotibanVO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	 * 设置：学员账号
	 */
	 
	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}
	
	/**
	 * 获取：学员账号
	 */
	public String getZhanghao() {
		return zhanghao;
	}
			
	
	/**
	 * 设置：题目id
	 */
	 
	public void setTimuid(Long timuid) {
		this.timuid = timuid;
	}
	
	/**
	 * 获取：题目id
	 */
	public Long getTimuid() {
		return timuid;
	}
			
	
	/**
	 * 设置：题目内容
	 */
	 
	public void setTimuneirong(String timuneirong) {
		this.timuneirong = timuneirong;
	}
	
	/**
	 * 获取：题目内容
	 */
	public String getTimuneirong() {
		return timuneirong;
	}
			
	
	/**
	 * 设置：错误答案
	 */
	 
	public void setCuowudaan(String cuowudaan) {
		this.cuowudaan = cuowudaan;
	}
	
	/**
	 * 获取：错误答案
	 */
	public String getCuowudaan() {
		return cuowudaan;
	}
			
	
	/**
	 * 设置：正确答案
	 */
	 
	public void setZhengquedaan(String zhengquedaan) {
		this.zhengquedaan = zhengquedaan;
	}
	
	/**
	 * 获取：正确答案
	 */
	public String getZhengquedaan() {
		return zhengquedaan;
	}
			
}
