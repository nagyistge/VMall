package com.skynet.vmall.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_EVENTITEMMEMBER")
public class EventItemMember extends IdEntity
{
	@Column
	private String eventid; // 活动标识
	
	@Column
	private String eventitemid; // 活动项目标识
	
	@Column
	private String memberctype; // 活动会员类型
	
	@Column
	private String memberid; // 活动会员标识

	public String getEventid()
	{
		return eventid;
	}

	public void setEventid(String eventid)
	{
		this.eventid = eventid;
	}

	public String getEventitemid()
	{
		return eventitemid;
	}

	public void setEventitemid(String eventitemid)
	{
		this.eventitemid = eventitemid;
	}

	public String getMemberctype()
	{
		return memberctype;
	}

	public void setMemberctype(String memberctype)
	{
		this.memberctype = memberctype;
	}

	public String getMemberid()
	{
		return memberid;
	}

	public void setMemberid(String memberid)
	{
		this.memberid = memberid;
	}
	
	
		
}
