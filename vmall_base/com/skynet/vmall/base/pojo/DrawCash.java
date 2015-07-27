package com.skynet.vmall.base.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_DRAWCASH")
public class DrawCash extends IdEntity
{
	@Column
	private String cno; // 提现申请单据号
	
	@Column
	private String memberid; // 提现会员标识
	
	@Column
	private String membercno; // 会员编号
	
	@Column
	private String membercname; // 会员姓名
	
	@Column
	private String memberwxopenid; // 会员微信标识
	
	@Column
	private String memberphone; // 会员联系电话	
	
	@Column
	private String accounttype; // 提现账号类型	
	
	@Column
	private String bank; // 提现账号

	@Column
	private String openbank; // 开户银行
	
	@Column
	private String bankaccountno; // 银行账号（支付账号/银行账号/openid）
	
	@Column
	private String bankaccountcname; // 银行名称
	
	@Column
	private String bankaccountphone; // 银行账号关联电话

	@Column
	private BigDecimal amount; // 提现总金额
	
	@Column
	private String state; // 状态（申请、受理、审核、付款、结束）
	
	@Column
	private String billno; // 交付凭据号（汇款单号）
	
	@Column
	private String accepter; // 受理人用户名
	
	@Column
	private String acceptercname; // 受理人姓名
	
	@Column
	private String auditer; // 审核人用户名
	
	@Column
	private String auditercname; // 审核人姓名
	
	@Column
	private String handler; // 付款人用户名
	
	@Column
	private String handlercname; // 付款人姓名
	
	@Column
	private Timestamp applytime; // 申请时间

	@Column
	private Timestamp accepttime; // 受理时间
	
	@Column
	private Timestamp audittime; // 审核时间	

	@Column
	private Timestamp handletime; // 付款时间
	
	@Column
	private String paystate; // 支付状态
	
	@Column
	private Timestamp paytime; // 支付时间（实际付款时间）
	
	@Column
	private String payaccountno; // 支付账号（实际付款账号）
	
	@Column
	private String payaccounttype; // 支付账号类型（实际付款账号类型）
	
	@Column
	private String paybillcno; // 付款单据号（实际付款单号）

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

	public String getMemberwxopenid()
	{
		return memberwxopenid;
	}

	public void setMemberwxopenid(String memberwxopenid)
	{
		this.memberwxopenid = memberwxopenid;
	}

	public String getMemberphone()
	{
		return memberphone;
	}

	public void setMemberphone(String memberphone)
	{
		this.memberphone = memberphone;
	}

	public String getAccounttype()
	{
		return accounttype;
	}

	public void setAccounttype(String accounttype)
	{
		this.accounttype = accounttype;
	}

	public String getBank()
	{
		return bank;
	}

	public void setBank(String bank)
	{
		this.bank = bank;
	}

	public String getOpenbank()
	{
		return openbank;
	}

	public void setOpenbank(String openbank)
	{
		this.openbank = openbank;
	}

	public String getBankaccountno()
	{
		return bankaccountno;
	}

	public void setBankaccountno(String bankaccountno)
	{
		this.bankaccountno = bankaccountno;
	}

	public String getBankaccountcname()
	{
		return bankaccountcname;
	}

	public void setBankaccountcname(String bankaccountcname)
	{
		this.bankaccountcname = bankaccountcname;
	}

	public String getBankaccountphone()
	{
		return bankaccountphone;
	}

	public void setBankaccountphone(String bankaccountphone)
	{
		this.bankaccountphone = bankaccountphone;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getBillno()
	{
		return billno;
	}

	public void setBillno(String billno)
	{
		this.billno = billno;
	}

	public String getAccepter()
	{
		return accepter;
	}

	public void setAccepter(String accepter)
	{
		this.accepter = accepter;
	}

	public String getAcceptercname()
	{
		return acceptercname;
	}

	public void setAcceptercname(String acceptercname)
	{
		this.acceptercname = acceptercname;
	}

	public String getAuditer()
	{
		return auditer;
	}

	public void setAuditer(String auditer)
	{
		this.auditer = auditer;
	}

	public String getAuditercname()
	{
		return auditercname;
	}

	public void setAuditercname(String auditercname)
	{
		this.auditercname = auditercname;
	}

	public String getHandler()
	{
		return handler;
	}

	public void setHandler(String handler)
	{
		this.handler = handler;
	}

	public String getHandlercname()
	{
		return handlercname;
	}

	public void setHandlercname(String handlercname)
	{
		this.handlercname = handlercname;
	}

	public Timestamp getApplytime()
	{
		return applytime;
	}

	public void setApplytime(Timestamp applytime)
	{
		this.applytime = applytime;
	}

	public Timestamp getAccepttime()
	{
		return accepttime;
	}

	public void setAccepttime(Timestamp accepttime)
	{
		this.accepttime = accepttime;
	}

	public Timestamp getAudittime()
	{
		return audittime;
	}

	public void setAudittime(Timestamp audittime)
	{
		this.audittime = audittime;
	}

	public Timestamp getHandletime()
	{
		return handletime;
	}

	public void setHandletime(Timestamp handletime)
	{
		this.handletime = handletime;
	}

	public String getPaystate()
	{
		return paystate;
	}

	public void setPaystate(String paystate)
	{
		this.paystate = paystate;
	}

	public Timestamp getPaytime()
	{
		return paytime;
	}

	public void setPaytime(Timestamp paytime)
	{
		this.paytime = paytime;
	}

	public String getPayaccountno()
	{
		return payaccountno;
	}

	public void setPayaccountno(String payaccountno)
	{
		this.payaccountno = payaccountno;
	}

	public String getPayaccounttype()
	{
		return payaccounttype;
	}

	public void setPayaccounttype(String payaccounttype)
	{
		this.payaccounttype = payaccounttype;
	}

	public String getPaybillcno()
	{
		return paybillcno;
	}

	public void setPaybillcno(String paybillcno)
	{
		this.paybillcno = paybillcno;
	}

}
