package com.skynet.vmall.base.pojo;

import java.math.BigDecimal;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_SHOPCART")
public class ShopCart extends IdEntity
{
	@Column
	private String memberid; // 购买会员标识
	
	@Column
	private String wxopenid; // 购买会员微信标识	
	
	@Column
	private int nums; // 商品件数	

	@Column
	private BigDecimal amount; // 商品总额

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

	public int getNums()
	{
		return nums;
	}

	public void setNums(int nums)
	{
		this.nums = nums;
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
