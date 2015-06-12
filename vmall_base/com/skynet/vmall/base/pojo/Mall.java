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
	private String bank; // 开户银行

	@Column
	private String bankacountno; // 银行账号
	
	@Column
	private String wxcno; // 微信号	
	
	@Column
	private String wxopenid; // 微信标识
	
	@Column
	private String wxnickname; // 微信昵称
	
	@Column
	private Timestamp createtime; // 创建时间	

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

}
