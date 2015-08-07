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
	private String memberid; // 商家会员标识
	
	@Column
	private String wxopenid; // 商家会员微信标识
	
	@Column
	private String sellerid; // 厂商组织机构标识（卖家）
	
	@Column
	private String sellername; // 厂商名称（卖家）	

	@Column
	private String goodsid; // 商品标识
	
	@Column
	private String goodsname; // 商品名称	
	
	@Column
	private String goodscno; // 商品系统编号
	
	@Column
	private String goodscode; // 商品商家货号
	
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
	private int nums ; // 数量
	
	@Column
	private String state; // 订单状态（下单、收款、发货、收货、结束）

	@Column
	private BigDecimal saleprice; // 销售单价
	
	@Column
	private BigDecimal promoteprice; // 促销单价
	
	@Column
	private BigDecimal realprice; // 实际订单购买价格	
	
	@Column
	private BigDecimal amountsale; // 销售单价计算金额
	
	@Column
	private BigDecimal amountpromote; // 销售单价计算金额
	
	@Column
	private BigDecimal amountreal; // 销售单价计算金额

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
	
	@Column
	private String supwxopenid1; // 1级收取返利会员微信标识		

	@Column
	private String supwxopenid2; // 2级收取返利会员微信标识	
	
	@Column
	private String supwxopenid3; // 3级收取返利会员微信标识	
	
	@Column
	private String supwxopenid4; // 4级收取返利会员微信标识	
	
	@Column
	private String supwxopenid5; // 5级收取返利会员微信标识
	
	@Column
	private String supmemberid1; // 1级收取返利会员标识	

	@Column
	private String supmemberid2; // 2级收取返利会员标识	
	
	@Column
	private String supmemberid3; // 3级收取返利会员标识	
	
	@Column
	private String supmemberid4; // 4级收取返利会员标识	
	
	@Column
	private String supmemberid5; // 5级收取返利会员标识
	
	// 发货信息
	@Column
	private String freighttype; // 运费方式（免运费、自定义）
	
	@Column
	private BigDecimal freight; // 运费
	
	@Column
	private String logisticscomp; // 物流公司	

	@Column
	private String expressno; // 快递单号	
	
	// 收货信息
	@Column
	private String takeover; // 收货结果（同意、拒绝）	
	
	@Column
	private String takeoverreason; // 拒绝原因	

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		this.orderid = orderid;
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

	public String getSellerid()
	{
		return sellerid;
	}

	public void setSellerid(String sellerid)
	{
		this.sellerid = sellerid;
	}

	public String getSellername()
	{
		return sellername;
	}

	public void setSellername(String sellername)
	{
		this.sellername = sellername;
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

	public String getGoodscno()
	{
		return goodscno;
	}

	public void setGoodscno(String goodscno)
	{
		this.goodscno = goodscno;
	}

	public String getGoodscode()
	{
		return goodscode;
	}

	public void setGoodscode(String goodscode)
	{
		this.goodscode = goodscode;
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

	public int getNums()
	{
		return nums;
	}

	public void setNums(int nums)
	{
		this.nums = nums;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
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

	public BigDecimal getRealprice()
	{
		return realprice;
	}

	public void setRealprice(BigDecimal realprice)
	{
		this.realprice = realprice;
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

	public BigDecimal getAmountreal()
	{
		return amountreal;
	}

	public void setAmountreal(BigDecimal amountreal)
	{
		this.amountreal = amountreal;
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

	public String getSupwxopenid1()
	{
		return supwxopenid1;
	}

	public void setSupwxopenid1(String supwxopenid1)
	{
		this.supwxopenid1 = supwxopenid1;
	}

	public String getSupwxopenid2()
	{
		return supwxopenid2;
	}

	public void setSupwxopenid2(String supwxopenid2)
	{
		this.supwxopenid2 = supwxopenid2;
	}

	public String getSupwxopenid3()
	{
		return supwxopenid3;
	}

	public void setSupwxopenid3(String supwxopenid3)
	{
		this.supwxopenid3 = supwxopenid3;
	}

	public String getSupwxopenid4()
	{
		return supwxopenid4;
	}

	public void setSupwxopenid4(String supwxopenid4)
	{
		this.supwxopenid4 = supwxopenid4;
	}

	public String getSupwxopenid5()
	{
		return supwxopenid5;
	}

	public void setSupwxopenid5(String supwxopenid5)
	{
		this.supwxopenid5 = supwxopenid5;
	}

	public String getSupmemberid1()
	{
		return supmemberid1;
	}

	public void setSupmemberid1(String supmemberid1)
	{
		this.supmemberid1 = supmemberid1;
	}

	public String getSupmemberid2()
	{
		return supmemberid2;
	}

	public void setSupmemberid2(String supmemberid2)
	{
		this.supmemberid2 = supmemberid2;
	}

	public String getSupmemberid3()
	{
		return supmemberid3;
	}

	public void setSupmemberid3(String supmemberid3)
	{
		this.supmemberid3 = supmemberid3;
	}

	public String getSupmemberid4()
	{
		return supmemberid4;
	}

	public void setSupmemberid4(String supmemberid4)
	{
		this.supmemberid4 = supmemberid4;
	}

	public String getSupmemberid5()
	{
		return supmemberid5;
	}

	public void setSupmemberid5(String supmemberid5)
	{
		this.supmemberid5 = supmemberid5;
	}

	public String getFreighttype()
	{
		return freighttype;
	}

	public void setFreighttype(String freighttype)
	{
		this.freighttype = freighttype;
	}

	public BigDecimal getFreight()
	{
		return freight;
	}

	public void setFreight(BigDecimal freight)
	{
		this.freight = freight;
	}

	public String getLogisticscomp()
	{
		return logisticscomp;
	}

	public void setLogisticscomp(String logisticscomp)
	{
		this.logisticscomp = logisticscomp;
	}

	public String getExpressno()
	{
		return expressno;
	}

	public void setExpressno(String expressno)
	{
		this.expressno = expressno;
	}

	public String getTakeover()
	{
		return takeover;
	}

	public void setTakeover(String takeover)
	{
		this.takeover = takeover;
	}

	public String getTakeoverreason()
	{
		return takeoverreason;
	}

	public void setTakeoverreason(String takeoverreason)
	{
		this.takeoverreason = takeoverreason;
	}

}
