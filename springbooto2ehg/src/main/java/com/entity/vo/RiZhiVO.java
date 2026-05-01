package com.entity.vo;

import com.entity.RiZhiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 */
public class RiZhiVO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
		 				/**
	 * 操作人
	 */
	private String caozuoren;
	/**
	 * 操作内容
	 */
	private String caozuoneirong;
	/**
	 * 操作时间
	 */
	private Date caozuoshijian;
	/**
	 * 操作IP
	 */
	private String caozuozheip;
	/**
	 * 操作地点
	 */
	private String caozuodidian;
	/**
	 * 操作结果
	 */
	private String caozuojieguo;
				
	
	/**
	 * 设置：操作人
	 */
	 
	public void setCaozuoren(String caozuoren) {
		this.caozuoren = caozuoren;
	}
	
	/**
	 * 获取：操作人
	 */
	public String getCaozuoren() {
		return caozuoren;
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
	 * 设置：操作IP
	 */
	 
	public void setCaozuozheip(String caozuozheip) {
		this.caozuozheip = caozuozheip;
	}
	
	/**
	 * 获取：操作IP
	 */
	public String getCaozuozheip() {
		return caozuozheip;
	}
			
	
	/**
	 * 设置：操作地点
	 */
	 
	public void setCaozuodidian(String caozuodidian) {
		this.caozuodidian = caozuodidian;
	}
	
	/**
	 * 获取：操作地点
	 */
	public String getCaozuodidian() {
		return caozuodidian;
	}
			
	
	/**
	 * 设置：操作结果
	 */
	 
	public void setCaozuojieguo(String caozuojieguo) {
		this.caozuojieguo = caozuojieguo;
	}
	
	/**
	 * 获取：操作结果
	 */
	public String getCaozuojieguo() {
		return caozuojieguo;
	}
			
}
