package com.skynet.vmall.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("T_APP_MEMBER")
public class Member extends Dealer
{

	@Column
	private String sex; // 性别

	@Column
	private String mobile; // 手机号
	
	@Column
	private String supid; // 上级标识
	
	@Column
	private String internal; // 内部码
	
	@Column
	private int level; // 级别

	@Column
	private String ctype; // 类型 (商城、总经销商、分销商、会员)
	
	@Column
	private int score; // 累计积分

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

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

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public String getCtype()
	{
		return ctype;
	}

	public void setCtype(String ctype)
	{
		this.ctype = ctype;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}
	
}
