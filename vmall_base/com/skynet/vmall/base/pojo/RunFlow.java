package com.skynet.vmall.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_RUNFLOW")
public class RunFlow extends IdEntity
{
	@Column
	private String flowname; // 流程名称（订单流程、提现流程等）
	
	@Column
	private String dataid; // 业务记录标识	

	@Column
	private Timestamp createtime; // 创建时间
	
	@Column
	private String userid; // 创建用户标识
	
	@Column
	private String username; // 创建用户姓名

	public String getFlowname()
	{
		return flowname;
	}

	public void setFlowname(String flowname)
	{
		this.flowname = flowname;
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

}
