package com.skynet.app.organ.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_USER")
public class User extends IdEntity
{

	@Column
	private String loginname; // 登录名

	@Column
	private String cname; // 姓名

	@Column
	private String password; // 密码

	@Column
	private String isusing; // 可用标志

	@Column
	private String ordernum; // 排序
	
	@Column
	private String weixinno; // 微信号
	
	@Column
	private String mobile; // 手机号

	public String getLoginname()
	{
		return loginname;
	}

	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getIsusing()
	{
		return isusing;
	}

	public void setIsusing(String isusing)
	{
		this.isusing = isusing;
	}

	public String getOrdernum()
	{
		return ordernum;
	}

	public void setOrdernum(String ordernum)
	{
		this.ordernum = ordernum;
	}

	public String getWeixinno()
	{
		return weixinno;
	}

	public void setWeixinno(String weixinno)
	{
		this.weixinno = weixinno;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
}
