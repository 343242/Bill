package com.entity.vo;

import com.entity.XiTongCanShuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * 系统参数
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 */
public class XiTongCanShuVO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
