package com.skynet.app.flow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_FBACT")
public class BAct extends IdEntity
{
	@Column
	private String bflowid; // 流程标识
	
	@Column
	private int sno; // 序号
	
	@Column
	private String cname; // 活动名称
	
	public String getBflowid()
	{
		return bflowid;
	}

	public int getSno()
	{
		return sno;
	}

	public void setSno(int sno)
	{
		this.sno = sno;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public void setBflowid(String bflowid)
	{
		this.bflowid = bflowid;
	}
}
