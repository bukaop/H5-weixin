package com.jeecg.p3.commonweixin.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.commonweixin.dao.MyJwWebJwidDao;
import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;

/**
 * 描述：</b>JwWebJwidDaoImpl<br>
 * @author：pituo
 * @since：2015年12月17日 10时45分06秒 星期四 
 * @version:1.0
 */
@Repository("myJwWebJwidDao")
public class MyJwWebJwidDaoImpl extends GenericDaoDefault<MyJwWebJwid> implements MyJwWebJwidDao{

	@Override
	public Integer count(PageQuery<MyJwWebJwid> pageQuery) {
		return (Integer) super.queryOne("count", pageQuery);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MyJwWebJwid> queryPageList(PageQuery<MyJwWebJwid> pageQuery,Integer itemCount) {
		PageQueryWrapper<MyJwWebJwid> wrapper = new PageQueryWrapper<MyJwWebJwid>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<MyJwWebJwid>)super.query("queryPageList",wrapper);
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<MyJwWebJwid> queryResetTokenList(Date refDate) {
		Map<String, Object> param=Maps.newConcurrentMap();
		param.put("refDate", refDate);
		return (List<MyJwWebJwid>)super.query("queryResetTokenList",param);
	}

	@Override
	public MyJwWebJwid queryByJwid(String jwid) {
		Map<String, Object> param=Maps.newConcurrentMap();
		param.put("jwid", jwid);
		return (MyJwWebJwid) super.queryOne("queryByJwid", param);
	}

	@Override
	public void doAddSystemUserJwid(String jwid,String createBy) {
		Map<String, String> param=Maps.newConcurrentMap();
		param.put("id", UUID.randomUUID().toString().replace("-", "").toUpperCase());
		param.put("jwid", jwid);
		param.put("createBy", createBy);
		super.getSqlSession().insert("doAddSystemUserJwid", param);
	}

	@Override
	public void doDelSystemUserJwid(String jwid) {
		Map<String, String> param=Maps.newConcurrentMap();
		param.put("jwid", jwid);
		super.getSqlSession().insert("doDelSystemUserJwid", param);
		
	}

	@Override
	public MyJwWebJwid queryOneByCreateBy(String createBy) {
		Map<String, String> param=Maps.newConcurrentMap();
		param.put("createBy", createBy);
		return (MyJwWebJwid) super.queryOne("queryOneByCreateBy", param);
	}

	@Override
	public List<MyJwWebJwid> queryAll() {
		return (List<MyJwWebJwid>)super.query("queryAll");
	}

	//update-begin--Author:zhangweijian Date: 20180808 for：变更系统公众号表的公众号原始ID
	/**
	 * @功能：变更系统公众号表的公众号原始ID
	 */
	@Override
	public void updateUserJwid(String jwid, String newJwid) {
		Map<String, String> param=new HashMap<String, String>();
		param.put("jwid", jwid);
		param.put("newJwid", newJwid);
		super.update("updateJwid",param);
	}
	//update-end--Author:zhangweijian Date: 20180808 for：变更系统公众号表的公众号原始ID
}

