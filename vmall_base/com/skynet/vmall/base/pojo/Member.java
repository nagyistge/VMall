package com.skynet.vmall.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("T_APP_MEMBER")
public class Member extends Dealer
{
	@Column
	private String mobile; // 手机号
	
	@Column
	private String supid; // 上级标识
	
	@Column
	private String internal; // 内部码

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getSupid()
	{
		return supid;
	}

	public void setSupid(String supid)
	{
		this.supid = supid;
	}

	public String getInternal()
	{
		return internal;
	}

	public void setInternal(String internal)
	{
		this.internal = internal;
	}
	
}
