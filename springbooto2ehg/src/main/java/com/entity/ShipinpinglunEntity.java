package com.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/** 
 * 视频评论
 */
@TableName("shipinpinglun")
public class ShipinpinglunEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	/**
	 * 视频id
	 */
	private Long shipinid;
	
	/**
	 * 评论内容
	 */
	private String pinglunneirong;
	
	/**
	 * 评论人id
	 */
	private Long pinglunrenid;
	
	/**
	 * 评论人姓名
	 */
	private String pinglunren;
	
	/**
	 * 评论时间
	 */
	private Date pingluntime;
	
	/**
	 * 父评论id
	 */
	private Long parentid;
	
	/**
	 * 回复内容
	 */
	private String huifuneirong;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShipinid() {
		return shipinid;
	}

	public void setShipinid(Long shipinid) {
		this.shipinid = shipinid;
	}

	public String getPinglunneirong() {
		return pinglunneirong;
	}

	public void setPinglunneirong(String pinglunneirong) {
		this.pinglunneirong = pinglunneirong;
	}

	public Long getPinglunrenid() {
		return pinglunrenid;
	}

	public void setPinglunrenid(Long pinglunrenid) {
		this.pinglunrenid = pinglunrenid;
	}

	public String getPinglunren() {
		return pinglunren;
	}

	public void setPinglunren(String pinglunren) {
		this.pinglunren = pinglunren;
	}

	public Date getPingluntime() {
		return pingluntime;
	}

	public void setPingluntime(Date pingluntime) {
		this.pingluntime = pingluntime;
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getHuifuneirong() {
		return huifuneirong;
	}

	public void setHuifuneirong(String huifuneirong) {
		this.huifuneirong = huifuneirong;
	}

}
