package com.skynet.vmall.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_EVENT")
public class Event extends IdEntity
{
	@Column
	private String cname; // 活动名称
	
	@Column
	private String ctype; // 活动类型（限时抢购）
	
	@Column
	private Timestamp begintime; // 开始时间
	
	@Column
	private Timestamp endtime; // 结束时间

	@Column
	private String wxmsgtype; // 微信消息类型
	
	@Column
	private String state; // 活动状态（新建、审核、发布、推广、结束）

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getCtype()
	{
		return ctype;
	}

	public void setCtype(String ctype)
	{
		this.ctype = ctype;
	}

	public Timestamp getBegintime()
	{
		return begintime;
	}

	public void setBegintime(Timestamp begintime)
	{
		this.begintime = begintime;
	}

	public Timestamp getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Timestamp endtime)
	{
		this.endtime = endtime;
	}

	public String getWxmsgtype()
	{
		return wxmsgtype;
	}

	public void setWxmsgtype(String wxmsgtype)
	{
		this.wxmsgtype = wxmsgtype;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

}
