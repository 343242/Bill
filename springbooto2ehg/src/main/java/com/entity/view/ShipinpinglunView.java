package com.entity.view;

import com.entity.ShipinpinglunEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;


/**
 * 视频评论
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("shipinpinglun")
public class ShipinpinglunView  extends ShipinpinglunEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ShipinpinglunView(){
	}
	
 	public ShipinpinglunView(ShipinpinglunEntity shipinpinglunEntity){
 	try {
			BeanUtils.copyProperties(this, shipinpinglunEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
