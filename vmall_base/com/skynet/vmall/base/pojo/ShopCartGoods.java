package com.skynet.vmall.base.pojo;

import java.math.BigDecimal;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_SHOPCARTGOODS")
public class ShopCartGoods extends IdEntity
{
	@Column
	private String shopcartid; // 购物车标识
	
	@Column
	private String memberid; // 销售方会员标识
	
	@Column
	private String wxopenid; // 销售方会员微信标识		

	@Column
	private String goodsid; // 商品标识
	
	@Column
	private String goodsname; // 商品名称
	
	@Column
	private int nums ; // 数量
	
	@Column
	private BigDecimal saleprice; // 销售单价
	
	@Column
	private BigDecimal promoteprice; // 促销单价
	
	@Column
	private BigDecimal amountsale; // 销售单价计算金额
	
	@Column
	private BigDecimal amountpromote; // 销售单价计算金额

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

	public String getShopcartid()
	{
		return shopcartid;
	}

	public void setShopcartid(String shopcartid)
	{
		this.shopcartid = shopcartid;
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

	public String getGoodsid()
	{
		return goodsid;
	}

	public void setGoodsid(String goodsid)
	{
		this.goodsid = goodsid;
	}

	public String getGoodsname()
	{
		return goodsname;
	}

	public void setGoodsname(String goodsname)
	{
		this.goodsname = goodsname;
	}

	public int getNums()
	{
		return nums;
	}

	public void setNums(int nums)
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

	public BigDecimal getPromoteprice()
	{
		return promoteprice;
	}

	public void setPromoteprice(BigDecimal promoteprice)
	{
		this.promoteprice = promoteprice;
	}

	public BigDecimal getAmountsale()
	{
		return amountsale;
	}

	public void setAmountsale(BigDecimal amountsale)
	{
		this.amountsale = amountsale;
	}

	public BigDecimal getAmountpromote()
	{
		return amountpromote;
	}

	public void setAmountpromote(BigDecimal amountpromote)
	{
		this.amountpromote = amountpromote;
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
