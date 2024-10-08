package com.entity.view;

import com.entity.JieguozhanshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 结果展示
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2023-05-15 14:23:37
 */
@TableName("jieguozhanshi")
public class JieguozhanshiView  extends JieguozhanshiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JieguozhanshiView(){
	}
 
 	public JieguozhanshiView(JieguozhanshiEntity jieguozhanshiEntity){
 	try {
			BeanUtils.copyProperties(this, jieguozhanshiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
