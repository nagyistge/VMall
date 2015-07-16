package com.skynet.app.flow.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_FBFLOW")
public class BFlow extends IdEntity
{
	@Column
	private String cname; // 流程名称（订单流程、提现流程等）

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}
}
