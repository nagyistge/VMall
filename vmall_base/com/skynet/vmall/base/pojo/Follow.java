package com.skynet.vmall.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_FOLLOW")
public class Follow extends IdEntity
{
	@Column
	private String swxopenid; // 关注方微信标识（新会员）

	@Column
	private String smembercno; // 关注方会员编号	
	
	@Column
	private String dwxopenid; // 被关注方微信标识（老会员）
	
	@Column
	private String dmembercno; // 被关注方会员编号

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

	public String getSmembercno()
	{
		return smembercno;
	}

	public void setSmembercno(String smembercno)
	{
		this.smembercno = smembercno;
	}

	public String getDwxopenid()
	{
		return dwxopenid;
	}

	public void setDwxopenid(String dwxopenid)
	{
		this.dwxopenid = dwxopenid;
	}

	public String getDmembercno()
	{
		return dmembercno;
	}

	public void setDmembercno(String dmembercno)
	{
		this.dmembercno = dmembercno;
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
