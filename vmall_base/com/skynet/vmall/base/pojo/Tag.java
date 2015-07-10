package com.skynet.vmall.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_TAG")
public class Tag extends IdEntity
{
	@Column
	private String title; // 标签标题
	
	@Column
	private int ordernum; // 标题优先顺序
	
	@Column
	private String objclass; // 对象类别（商品分类、商品等）
	
	@Column
	private String objid; // 对象标识
	
	@Column
	private Timestamp tagtime; // 标签时间

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getOrdernum()
	{
		return ordernum;
	}

	public void setOrdernum(int ordernum)
	{
		this.ordernum = ordernum;
	}

	public String getObjclass()
	{
		return objclass;
	}

	public void setObjclass(String objclass)
	{
		this.objclass = objclass;
	}

	public String getObjid()
	{
		return objid;
	}

	public void setObjid(String objid)
	{
		this.objid = objid;
	}

	public Timestamp getTagtime()
	{
		return tagtime;
	}

	public void setTagtime(Timestamp tagtime)
	{
		this.tagtime = tagtime;
	}
	
}
