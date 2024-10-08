package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.JingsaixinxiEntity;
import com.entity.view.JingsaixinxiView;

import com.service.JingsaixinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 竞赛信息
 * 后端接口
 * @author 
 * @email 
 * @date 2023-05-15 14:23:37
 */
@RestController
@RequestMapping("/jingsaixinxi")
public class JingsaixinxiController {
    @Autowired
    private JingsaixinxiService jingsaixinxiService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JingsaixinxiEntity jingsaixinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("fuzeren")) {
			jingsaixinxi.setFuzerenzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<JingsaixinxiEntity> ew = new EntityWrapper<JingsaixinxiEntity>();

		PageUtils page = jingsaixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jingsaixinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JingsaixinxiEntity jingsaixinxi, 
		HttpServletRequest request){
        EntityWrapper<JingsaixinxiEntity> ew = new EntityWrapper<JingsaixinxiEntity>();

		PageUtils page = jingsaixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jingsaixinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JingsaixinxiEntity jingsaixinxi){
       	EntityWrapper<JingsaixinxiEntity> ew = new EntityWrapper<JingsaixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jingsaixinxi, "jingsaixinxi")); 
        return R.ok().put("data", jingsaixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JingsaixinxiEntity jingsaixinxi){
        EntityWrapper< JingsaixinxiEntity> ew = new EntityWrapper< JingsaixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jingsaixinxi, "jingsaixinxi")); 
		JingsaixinxiView jingsaixinxiView =  jingsaixinxiService.selectView(ew);
		return R.ok("查询竞赛信息成功").put("data", jingsaixinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JingsaixinxiEntity jingsaixinxi = jingsaixinxiService.selectById(id);
        return R.ok().put("data", jingsaixinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JingsaixinxiEntity jingsaixinxi = jingsaixinxiService.selectById(id);
        return R.ok().put("data", jingsaixinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JingsaixinxiEntity jingsaixinxi, HttpServletRequest request){
    	jingsaixinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jingsaixinxi);
        jingsaixinxiService.insert(jingsaixinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JingsaixinxiEntity jingsaixinxi, HttpServletRequest request){
    	jingsaixinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jingsaixinxi);
        jingsaixinxiService.insert(jingsaixinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JingsaixinxiEntity jingsaixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jingsaixinxi);
        jingsaixinxiService.updateById(jingsaixinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jingsaixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
