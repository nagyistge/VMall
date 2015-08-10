package com.skynet.vmall.base.pojo;

import java.math.BigDecimal;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_GOODSPRICE")
public class GoodsPrice extends IdEntity
{
	// 基本信息
	@Column
	private String goodsid; // 商品标识
	
	@Column
	private String membertype; // 会员类型	
	
	@Column
	private String eventid; // 参与活动标识
	
	@Column
	private String eventname; // 参与活动名称
	
	@Column
	private String eventitemid; // 参与活动项目标识
	
	@Column
	private String eventitemname; // 参与活动项目标题
	
	@Column
	private String eventitemgoodsid; // 参与活动项目商品标识	
	
	@Column
	private String isdefault; // 是否缺省价格（是、否）

	@Column
	private BigDecimal costprice; // 成本单价

	@Column
	private BigDecimal saleprice; // 销售单价（原价）

	@Column
	private BigDecimal promoteprice; // 促销单价（现价）

	// 佣金设置
	@Column
	private String rebatetype; // 返利方式（积分、百分比）

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

	public String getGoodsid()
	{
		return goodsid;
	}

	public void setGoodsid(String goodsid)
	{
		this.goodsid = goodsid;
	}

	public String getMembertype()
	{
		return membertype;
	}

	public void setMembertype(String membertype)
	{
		this.membertype = membertype;
	}

	public String getEventid()
	{
		return eventid;
	}

	public void setEventid(String eventid)
	{
		this.eventid = eventid;
	}

	public String getEventname()
	{
		return eventname;
	}

	public void setEventname(String eventname)
	{
		this.eventname = eventname;
	}

	public String getEventitemid()
	{
		return eventitemid;
	}

	public void setEventitemid(String eventitemid)
	{
		this.eventitemid = eventitemid;
	}

	public String getEventitemname()
	{
		return eventitemname;
	}

	public void setEventitemname(String eventitemname)
	{
		this.eventitemname = eventitemname;
	}

	public String getEventitemgoodsid()
	{
		return eventitemgoodsid;
	}

	public void setEventitemgoodsid(String eventitemgoodsid)
	{
		this.eventitemgoodsid = eventitemgoodsid;
	}

	public String getIsdefault()
	{
		return isdefault;
	}

	public void setIsdefault(String isdefault)
	{
		this.isdefault = isdefault;
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

	public String getRebatetype()
	{
		return rebatetype;
	}

	public void setRebatetype(String rebatetype)
	{
		this.rebatetype = rebatetype;
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
