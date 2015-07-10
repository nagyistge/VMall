package com.skynet.vmall.base.pojo;

import java.math.BigDecimal;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_EVENTITEMGOODS")
public class EventItemGoods extends IdEntity
{
	@Column
	private String eventid; // 活动标识
	
	@Column
	private String eventitemid; // 活动项目标识

	@Column
	private String goodsclassid; // 商品分类标识

	@Column
	private String goodsclassname; // 商品分类名称

	@Column
	private String goodsid; // 商品标识
	
	@Column
	private String goodsname; // 商品名称
	
	@Column
	private BigDecimal costprice; // 成本单价	
	
	@Column
	private BigDecimal saleprice; // 商品销售单价
	
	@Column
	private BigDecimal promoteprice; // 商品促销单价
	
	@Column
	private BigDecimal nums ; // 投放总量
	
	@Column
	private BigDecimal buynums ; // 个人限购数量
	
	@Column
	private BigDecimal realnums ; // 活动消费数量

	public String getEventid()
	{
		return eventid;
	}

	public void setEventid(String eventid)
	{
		this.eventid = eventid;
	}

	public String getEventitemid()
	{
		return eventitemid;
	}

	public void setEventitemid(String eventitemid)
	{
		this.eventitemid = eventitemid;
	}

	public String getGoodsclassid()
	{
		return goodsclassid;
	}

	public void setGoodsclassid(String goodsclassid)
	{
		this.goodsclassid = goodsclassid;
	}

	public String getGoodsclassname()
	{
		return goodsclassname;
	}

	public void setGoodsclassname(String goodsclassname)
	{
		this.goodsclassname = goodsclassname;
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

	public BigDecimal getCostprice()
	{
		return costprice;
	}

	public void setCostprice(BigDecimal costprice)
	{
		this.costprice = costprice;
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

	public BigDecimal getNums()
	{
		return nums;
	}

	public void setNums(BigDecimal nums)
	{
		this.nums = nums;
	}

	public BigDecimal getBuynums()
	{
		return buynums;
	}

	public void setBuynums(BigDecimal buynums)
	{
		this.buynums = buynums;
	}

	public BigDecimal getRealnums()
	{
		return realnums;
	}

	public void setRealnums(BigDecimal realnums)
	{
		this.realnums = realnums;
	}
	
}
