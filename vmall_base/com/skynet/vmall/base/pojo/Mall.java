package com.skynet.vmall.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_MALL")
public class Mall extends IdEntity
{
	@Column
	private String cname; // 名称
	
	@Column
	private String cno; // 会员编号

	@Column
	private String addr; // 地址

	@Column
	private String homepage; // 网址

	@Column
	private String phone; // 联系电话
	
	@Column
	private String qq; // QQ

	@Column
	private String email; // 电子邮件
	
	@Column
	private String country; // 国家

	@Column
	private String province; // 省份（直辖市）

	@Column
	private String city; // 地市（直辖市区）

	@Column
	private String county; // 县（直辖市街道）
	
	@Column
	private String town; // 镇（直辖市街道）	

	@Column
	private String postcode; // 邮政编码
	
	@Column
	private String accounttype; // 账号类型	

	@Column
	private String bank; // 所属银行

	@Column
	private String openbank; // 开户银行
	
	@Column
	private String bankaccountno; // 银行账号
	
	@Column
	private String bankaccountcname; // 银行名称
	
	@Column
	private String bankaccountphone; // 银行账号关联电话
	
	@Column
	private String wxcno; // 微信号	
	
	@Column
	private String wxopenid; // 微信标识
	
	@Column
	private String wxnickname; // 微信昵称
	
	@Column
	private Timestamp createtime; // 创建时间
	
	@Column
	private String wxheadimgurl; // 微信头像		

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getCno()
	{
		return cno;
	}

	public void setCno(String cno)
	{
		this.cno = cno;
	}

	public String getAddr()
	{
		return addr;
	}

	public void setAddr(String addr)
	{
		this.addr = addr;
	}

	public String getHomepage()
	{
		return homepage;
	}

	public void setHomepage(String homepage)
	{
		this.homepage = homepage;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCounty()
	{
		return county;
	}

	public void setCounty(String county)
	{
		this.county = county;
	}

	public String getTown()
	{
		return town;
	}

	public void setTown(String town)
	{
		this.town = town;
	}

	public String getPostcode()
	{
		return postcode;
	}

	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
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

	public String getWxcno()
	{
		return wxcno;
	}

	public void setWxcno(String wxcno)
	{
		this.wxcno = wxcno;
	}

	public String getWxopenid()
	{
		return wxopenid;
	}

	public void setWxopenid(String wxopenid)
	{
		this.wxopenid = wxopenid;
	}

	public String getWxnickname()
	{
		return wxnickname;
	}

	public void setWxnickname(String wxnickname)
	{
		this.wxnickname = wxnickname;
	}

	public Timestamp getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Timestamp createtime)
	{
		this.createtime = createtime;
	}

	public String getWxheadimgurl()
	{
		return wxheadimgurl;
	}

	public void setWxheadimgurl(String wxheadimgurl)
	{
		this.wxheadimgurl = wxheadimgurl;
	}

}
