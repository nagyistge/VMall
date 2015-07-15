package com.skynet.vmall.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_RUNFLOWLOG")
public class RunFlowLog extends IdEntity
{
	@Column
	private String runflowkey; // 流程实例标识
	
	@Column
	private String sname; // 开始活动名称
	
	@Column
	private String dname; // 结束活动名称
	
	@Column
	private Timestamp createtime; // 时间
	
	@Column
	private String suserid; // 开始用户标识
	
	@Column
	private String susername; // 开始用户姓名

	@Column
	private String duserid; // 结束用户标识
	
	@Column
	private String dusername; // 结束用户姓名

	@Column
	private String ctype; // 类型（转发、退回）

	public String getRunflowkey()
	{
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey)
	{
		this.runflowkey = runflowkey;
	}

	public String getSname()
	{
		return sname;
	}

	public void setSname(String sname)
	{
		this.sname = sname;
	}

	public String getDname()
	{
		return dname;
	}

	public void setDname(String dname)
	{
		this.dname = dname;
	}

	public Timestamp getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Timestamp createtime)
	{
		this.createtime = createtime;
	}

	public String getSuserid()
	{
		return suserid;
	}

	public void setSuserid(String suserid)
	{
		this.suserid = suserid;
	}

	public String getSusername()
	{
		return susername;
	}

	public void setSusername(String susername)
	{
		this.susername = susername;
	}

	public String getDuserid()
	{
		return duserid;
	}

	public void setDuserid(String duserid)
	{
		this.duserid = duserid;
	}

	public String getDusername()
	{
		return dusername;
	}

	public void setDusername(String dusername)
	{
		this.dusername = dusername;
	}

	public String getCtype()
	{
		return ctype;
	}

	public void setCtype(String ctype)
	{
		this.ctype = ctype;
	}

}
