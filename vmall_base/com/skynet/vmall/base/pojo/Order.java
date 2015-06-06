package com.skynet.vmall.base.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_ORDER")
public class Order extends IdEntity
{
	@Column
	private String cno; // 订单编号

	@Column
	private String memberid; // 购买方会员标识
	
	@Column
	private String wxopenid; // 购买方会员微信标识	
	
	@Column	
	private Timestamp ordertime; // 订单时间

	@Column
	private BigDecimal amount; // 订单总额

	public String getCno()
	{
		return cno;
	}

	public void setCno(String cno)
	{
		this.cno = cno;
	}

	public String getMemberid()
	{
		return memberid;
	}

	public void setMemberid(String memberid)
	{
		this.memberid = memberid;
	}

	public String getWxopenid()
	{
		return wxopenid;
	}

	public void setWxopenid(String wxopenid)
	{
		this.wxopenid = wxopenid;
	}

	public Timestamp getOrdertime()
	{
		return ordertime;
	}

	public void setOrdertime(Timestamp ordertime)
	{
		this.ordertime = ordertime;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}
	
}
