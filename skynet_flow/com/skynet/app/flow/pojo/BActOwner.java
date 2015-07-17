package com.skynet.app.flow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_FBACTOWNER")
public class BActOwner extends IdEntity
{
	@Column
	private String bflowid; // 流程标识

	@Column
	private String bactid; // 流程标识
	
	@Column
	private String groupid; // 所有者标识

	@Column
	private String grouptype; // 所有者类型
	
	@Column
	private String groupname; // 所有者名称

	public String getBflowid()
	{
		return bflowid;
	}

	public void setBflowid(String bflowid)
	{
		this.bflowid = bflowid;
	}

	public String getBactid()
	{
		return bactid;
	}

	public void setBactid(String bactid)
	{
		this.bactid = bactid;
	}

	public String getGroupid()
	{
		return groupid;
	}

	public void setGroupid(String groupid)
	{
		this.groupid = groupid;
	}

	public String getGrouptype()
	{
		return grouptype;
	}

	public void setGrouptype(String grouptype)
	{
		this.grouptype = grouptype;
	}

	public String getGroupname()
	{
		return groupname;
	}

	public void setGroupname(String groupname)
	{
		this.groupname = groupname;
	}

}
