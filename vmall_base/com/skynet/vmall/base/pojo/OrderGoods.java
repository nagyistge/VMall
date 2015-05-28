package com.skynet.vmall.base.pojo;

import java.math.BigDecimal;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_ORDERGOODS")
public class OrderGoods extends IdEntity
{
	@Column
	private String orderid; // 订单标识

	@Column
	private String goodsid; // 商品标识
	
	@Column
	private Number nums ; // 数量

	@Column
	private BigDecimal saleprice; // 销售单价

	@Column
	private BigDecimal rebate1; // 1级返利

	@Column
	private BigDecimal rebate2; // 2级返利

	@Column
	private BigDecimal rebate3; // 3级返利

	@Column
	private BigDecimal rebate4; // 4级返利

	@Column
	private BigDecimal rebate5; // 5级返利

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		this.orderid = orderid;
	}

	public String getGoodsid()
	{
		return goodsid;
	}

	public void setGoodsid(String goodsid)
	{
		this.goodsid = goodsid;
	}

	public Number getNums()
	{
		return nums;
	}

	public void setNums(Number nums)
	{
		this.nums = nums;
	}

	public BigDecimal getSaleprice()
	{
		return saleprice;
	}

	public void setSaleprice(BigDecimal saleprice)
	{
		this.saleprice = saleprice;
	}

	public BigDecimal getRebate1()
	{
		return rebate1;
	}

	public void setRebate1(BigDecimal rebate1)
	{
		this.rebate1 = rebate1;
	}

	public BigDecimal getRebate2()
	{
		return rebate2;
	}

	public void setRebate2(BigDecimal rebate2)
	{
		this.rebate2 = rebate2;
	}

	public BigDecimal getRebate3()
	{
		return rebate3;
	}

	public void setRebate3(BigDecimal rebate3)
	{
		this.rebate3 = rebate3;
	}

	public BigDecimal getRebate4()
	{
		return rebate4;
	}

	public void setRebate4(BigDecimal rebate4)
	{
		this.rebate4 = rebate4;
	}

	public BigDecimal getRebate5()
	{
		return rebate5;
	}

	public void setRebate5(BigDecimal rebate5)
	{
		this.rebate5 = rebate5;
	}
	
}
