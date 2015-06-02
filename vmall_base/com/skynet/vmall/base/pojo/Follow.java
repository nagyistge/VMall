package com.skynet.vmall.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_FOLLOW")
public class Follow extends IdEntity
{
	@Column
	private String swxopenid; // 关注方微信标识
	
	@Column
	private String dwxopenid; // 被关注方微信标识

	@Column
	private Timestamp followtime; // 关注时间

	public String getSwxopenid()
	{
		return swxopenid;
	}

	public void setSwxopenid(String swxopenid)
	{
		this.swxopenid = swxopenid;
	}

	public String getDwxopenid()
	{
		return dwxopenid;
	}

	public void setDwxopenid(String dwxopenid)
	{
		this.dwxopenid = dwxopenid;
	}

	public Timestamp getFollowtime()
	{
		return followtime;
	}

	public void setFollowtime(Timestamp followtime)
	{
		this.followtime = followtime;
	}

}
