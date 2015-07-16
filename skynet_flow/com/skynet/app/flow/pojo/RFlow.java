package com.skynet.app.flow.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_SYS_FRFLOW")
public class RFlow
{
    @Name
    protected String runflowkey;
    
	@Column
	private String bflowid; // 流程定义标识

	@Column
	private String dataid; // 业务记录标识	

	@Column
	private Timestamp createtime; // 创建时间
	
	@Column
	private String userid; // 创建用户标识
	
	@Column
	private String username; // 创建用户姓名
	
	@Column
	private String state; // 流程状态

	public String getRunflowkey()
	{
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey)
	{
		this.runflowkey = runflowkey;
	}

	public String getBflowid()
	{
		return bflowid;
	}

	public void setBflowid(String bflowid)
	{
		this.bflowid = bflowid;
	}

	public String getDataid()
	{
		return dataid;
	}

	public void setDataid(String dataid)
	{
		this.dataid = dataid;
	}

	public Timestamp getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Timestamp createtime)
	{
		this.createtime = createtime;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
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
