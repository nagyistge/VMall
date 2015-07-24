package com.skynet.app.organ.pojo;

import java.sql.Timestamp;

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
	private String sex; // 性别	

	@Column
	private String password; // 密码

	@Column
	private String isusing; // 可用标志

	@Column
	private String ordernum; // 排序
	
	@Column
	private String wxcno; // 微信号
	
	@Column
	private String wxopenid; // 微信标识
	
	@Column
	private String wxnickname; // 微信昵称	
	
	@Column
	private String mobile; // 手机号
	
	@Column
	private Timestamp createtime; // 创建时间	

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

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
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

	public String getWxcno()
	{
		return wxcno;
	}

	public void setWxcno(String wxcno)
	{
		this.wxcno = wxcno;
	}

	public String getWxopenid()
	{
		return wxopenid;
	}

	public void setWxopenid(String wxopenid)
	{
		this.wxopenid = wxopenid;
	}

	public String getWxnickname()
	{
		return wxnickname;
	}

	public void setWxnickname(String wxnickname)
	{
		this.wxnickname = wxnickname;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public Timestamp getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Timestamp createtime)
	{
		this.createtime = createtime;
	}

}
