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
	private String batchno; // 订单批次号（单次生成多个订单时批次）

	@Column
	private String memberid; // 购买方会员标识

	@Column
	private String wxopenid; // 购买方会员微信标识

	@Column
	private String membercno; // 购买方会员编号

	@Column
	private String membercname; // 购买方会员姓名
	
	@Column
	private String sellerid; // 厂商组织机构标识（卖家）
	
	@Column
	private String sellername; // 厂商名称（卖家）

	@Column
	private String phone; // 购买方联系电话

	@Column
	private Timestamp ordertime; // 订单时间

	@Column
	private BigDecimal amountsale; // 订单销售单价总额

	@Column
	private BigDecimal amountpromote; // 订单促销单价总额（现价总额）

	@Column
	private BigDecimal amount; // 订单总额（一般为促销单价总额）

	// 支付信息
	@Column
	private String paymode; // 支付方式

	@Column
	private String paybank; // 支付卡开户银行

	@Column
	private String paybankaccountno; // 支付卡银行账号

	// 收货信息
	@Column
	private String takercname; // 收货人（姓名）

	@Column
	private String takermobile; // 收货人联系（手机）电话

	@Column
	private String takeprovince; // 收货省份（直辖市）

	@Column
	private String takecity; // 收货地市（直辖市区）

	@Column
	private String takecounty; // 收货县（直辖市街道）

	@Column
	private String taketown; // 收货镇（直辖市街道）

	@Column
	private String takepostcode; // 收货邮政编码

	@Column
	private String takeaddress; // 收货地址

	@Column
	private String state; // 收货订单状态（下单、收款、发货、收货、结束）

	// 订单支付信息
	@Column
	private String paystate; // 订单收款支付状态（未支付、已支付）

	@Column
	private Timestamp paynotifytime; // 订单收款支付通知时间

	@Column
	private String thirdpaytradeno; // 第三方支付平台交易标识号

	@Column
	private String wxpayorderno;

	@Column
	private String wxpaydeviceinfo;

	@Column
	private String wxpaytradetype;

	@Column
	private String wxpaybanktype;

	@Column
	private BigDecimal wxpaytotalfee; // 订单实际支付总额

	@Column
	private String wxpayissubscribe;

	@Column
	private String wxpaytimeend;

	@Column
	private String wxpayopenid;

	public String getCno()
	{
		return cno;
	}

	public void setCno(String cno)
	{
		this.cno = cno;
	}

	public String getBatchno()
	{
		return batchno;
	}

	public void setBatchno(String batchno)
	{
		this.batchno = batchno;
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

	public String getMembercno()
	{
		return membercno;
	}

	public void setMembercno(String membercno)
	{
		this.membercno = membercno;
	}

	public String getMembercname()
	{
		return membercname;
	}

	public void setMembercname(String membercname)
	{
		this.membercname = membercname;
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

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public Timestamp getOrdertime()
	{
		return ordertime;
	}

	public void setOrdertime(Timestamp ordertime)
	{
		this.ordertime = ordertime;
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

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public String getPaymode()
	{
		return paymode;
	}

	public void setPaymode(String paymode)
	{
		this.paymode = paymode;
	}

	public String getPaybank()
	{
		return paybank;
	}

	public void setPaybank(String paybank)
	{
		this.paybank = paybank;
	}

	public String getPaybankaccountno()
	{
		return paybankaccountno;
	}

	public void setPaybankaccountno(String paybankaccountno)
	{
		this.paybankaccountno = paybankaccountno;
	}

	public String getTakercname()
	{
		return takercname;
	}

	public void setTakercname(String takercname)
	{
		this.takercname = takercname;
	}

	public String getTakermobile()
	{
		return takermobile;
	}

	public void setTakermobile(String takermobile)
	{
		this.takermobile = takermobile;
	}

	public String getTakeprovince()
	{
		return takeprovince;
	}

	public void setTakeprovince(String takeprovince)
	{
		this.takeprovince = takeprovince;
	}

	public String getTakecity()
	{
		return takecity;
	}

	public void setTakecity(String takecity)
	{
		this.takecity = takecity;
	}

	public String getTakecounty()
	{
		return takecounty;
	}

	public void setTakecounty(String takecounty)
	{
		this.takecounty = takecounty;
	}

	public String getTaketown()
	{
		return taketown;
	}

	public void setTaketown(String taketown)
	{
		this.taketown = taketown;
	}

	public String getTakepostcode()
	{
		return takepostcode;
	}

	public void setTakepostcode(String takepostcode)
	{
		this.takepostcode = takepostcode;
	}

	public String getTakeaddress()
	{
		return takeaddress;
	}

	public void setTakeaddress(String takeaddress)
	{
		this.takeaddress = takeaddress;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getPaystate()
	{
		return paystate;
	}

	public void setPaystate(String paystate)
	{
		this.paystate = paystate;
	}

	public Timestamp getPaynotifytime()
	{
		return paynotifytime;
	}

	public void setPaynotifytime(Timestamp paynotifytime)
	{
		this.paynotifytime = paynotifytime;
	}

	public String getThirdpaytradeno()
	{
		return thirdpaytradeno;
	}

	public void setThirdpaytradeno(String thirdpaytradeno)
	{
		this.thirdpaytradeno = thirdpaytradeno;
	}

	public String getWxpayorderno()
	{
		return wxpayorderno;
	}

	public void setWxpayorderno(String wxpayorderno)
	{
		this.wxpayorderno = wxpayorderno;
	}

	public String getWxpaydeviceinfo()
	{
		return wxpaydeviceinfo;
	}

	public void setWxpaydeviceinfo(String wxpaydeviceinfo)
	{
		this.wxpaydeviceinfo = wxpaydeviceinfo;
	}

	public String getWxpaytradetype()
	{
		return wxpaytradetype;
	}

	public void setWxpaytradetype(String wxpaytradetype)
	{
		this.wxpaytradetype = wxpaytradetype;
	}

	public String getWxpaybanktype()
	{
		return wxpaybanktype;
	}

	public void setWxpaybanktype(String wxpaybanktype)
	{
		this.wxpaybanktype = wxpaybanktype;
	}

	public BigDecimal getWxpaytotalfee()
	{
		return wxpaytotalfee;
	}

	public void setWxpaytotalfee(BigDecimal wxpaytotalfee)
	{
		this.wxpaytotalfee = wxpaytotalfee;
	}

	public String getWxpayissubscribe()
	{
		return wxpayissubscribe;
	}

	public void setWxpayissubscribe(String wxpayissubscribe)
	{
		this.wxpayissubscribe = wxpayissubscribe;
	}

	public String getWxpaytimeend()
	{
		return wxpaytimeend;
	}

	public void setWxpaytimeend(String wxpaytimeend)
	{
		this.wxpaytimeend = wxpaytimeend;
	}

	public String getWxpayopenid()
	{
		return wxpayopenid;
	}

	public void setWxpayopenid(String wxpayopenid)
	{
		this.wxpayopenid = wxpayopenid;
	}

}
