package com.skynet.vmall.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_MALL")
public class Mall extends IdEntity
{

	@Column
	private String cname; // 名称

	@Column
	private String addr; // 地址

	@Column
	private String homepage; // 网址

	@Column
	private String phone; // 联系电话

	@Column
	private String state; // 省份

	@Column
	private String city; // 地市

	@Column
	private String county; // 县区

	@Column
	private String postcode; // 邮政编码

	@Column
	private String bank; // 开户银行

	@Column
	private String bankacountno; // 银行账号
	
	@Column
	private String weixinno; // 微信号

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
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

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
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

	public String getPostcode()
	{
		return postcode;
	}

	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}

	public String getBank()
	{
		return bank;
	}

	public void setBank(String bank)
	{
		this.bank = bank;
	}

	public String getBankacountno()
	{
		return bankacountno;
	}

	public void setBankacountno(String bankacountno)
	{
		this.bankacountno = bankacountno;
	}

	public String getWeixinno()
	{
		return weixinno;
	}

	public void setWeixinno(String weixinno)
	{
		this.weixinno = weixinno;
	}

}
