package com.skynet.vmall.base.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_ORDERGOODSREBATE")
public class OrderGoodsRebate extends IdEntity
{
	@Column
	private String orderid; // 返利订单标识
	
	@Column
	private String ordercno; // 返利订单编号
	
	@Column
	private String ordergoodsid; // 订单明细商品标识
	
	@Column
	private String goodsid; // 订单商品标识	
	
	@Column
	private String ordergoodsname; // 订单明细商品名称
	
	@Column
	private String supmemberid; // 收取返利会员标识
	
	@Column
	private String supmembercname; // 发放返利会员姓名	
	
	@Column
	private String supwxopenid; // 收取返利会员微信标识
	
	@Column
	private String submemberid; // 发放返利会员微信标识
	
	@Column
	private String subwxopenid; // 发放返利会员微信标识
	
	@Column
	private String submembercname; // 发放返利会员姓名
	
	@Column
	private int level; // 返利级别
	
	@Column
	private BigDecimal rebate ; // 单品返利积分
	
	@Column
	private int nums ; // 本次商品数量
	
	@Column
	private BigDecimal score; // 本次返利积分（元）
	
	@Column
	private Timestamp rebatetime; // 返利发生时间（订单时间）
	
	@Column
	private String drawcashid; // 提现申请标识

	@Column
	private String drawcashcno; // 提现申请单号
	
	@Column
	private Timestamp drawapplytime; // 提现申请时间
	
	@Column
	private String state; // 提现申请单状态	

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		this.orderid = orderid;
	}

	public String getOrdercno()
	{
		return ordercno;
	}

	public void setOrdercno(String ordercno)
	{
		this.ordercno = ordercno;
	}

	public String getOrdergoodsid()
	{
		return ordergoodsid;
	}

	public void setOrdergoodsid(String ordergoodsid)
	{
		this.ordergoodsid = ordergoodsid;
	}

	public String getGoodsid()
	{
		return goodsid;
	}

	public void setGoodsid(String goodsid)
	{
		this.goodsid = goodsid;
	}

	public String getOrdergoodsname()
	{
		return ordergoodsname;
	}

	public void setOrdergoodsname(String ordergoodsname)
	{
		this.ordergoodsname = ordergoodsname;
	}

	public String getSupmemberid()
	{
		return supmemberid;
	}

	public void setSupmemberid(String supmemberid)
	{
		this.supmemberid = supmemberid;
	}

	public String getSupmembercname()
	{
		return supmembercname;
	}

	public void setSupmembercname(String supmembercname)
	{
		this.supmembercname = supmembercname;
	}

	public String getSupwxopenid()
	{
		return supwxopenid;
	}

	public void setSupwxopenid(String supwxopenid)
	{
		this.supwxopenid = supwxopenid;
	}

	public String getSubmemberid()
	{
		return submemberid;
	}

	public void setSubmemberid(String submemberid)
	{
		this.submemberid = submemberid;
	}

	public String getSubwxopenid()
	{
		return subwxopenid;
	}

	public void setSubwxopenid(String subwxopenid)
	{
		this.subwxopenid = subwxopenid;
	}

	public String getSubmembercname()
	{
		return submembercname;
	}

	public void setSubmembercname(String submembercname)
	{
		this.submembercname = submembercname;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public BigDecimal getRebate()
	{
		return rebate;
	}

	public void setRebate(BigDecimal rebate)
	{
		this.rebate = rebate;
	}

	public int getNums()
	{
		return nums;
	}

	public void setNums(int nums)
	{
		this.nums = nums;
	}

	public BigDecimal getScore()
	{
		return score;
	}

	public void setScore(BigDecimal score)
	{
		this.score = score;
	}

	public Timestamp getRebatetime()
	{
		return rebatetime;
	}

	public void setRebatetime(Timestamp rebatetime)
	{
		this.rebatetime = rebatetime;
	}

	public String getDrawcashid()
	{
		return drawcashid;
	}

	public void setDrawcashid(String drawcashid)
	{
		this.drawcashid = drawcashid;
	}

	public String getDrawcashcno()
	{
		return drawcashcno;
	}

	public void setDrawcashcno(String drawcashcno)
	{
		this.drawcashcno = drawcashcno;
	}

	public Timestamp getDrawapplytime()
	{
		return drawapplytime;
	}

	public void setDrawapplytime(Timestamp drawapplytime)
	{
		this.drawapplytime = drawapplytime;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

}
