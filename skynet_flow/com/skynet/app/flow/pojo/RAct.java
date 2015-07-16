package com.skynet.app.flow.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_SYS_FRACT")
public class RAct
{
    @Name
    protected String runactkey;
    
	@Column
    protected String runflowkey;
    
	@Column
	private String bflowid; // 流程定义标识
	
	@Column
	private String bactid; // 活动定义标识	

	@Column
	private String dataid; // 业务记录标识	

	@Column
	private Timestamp createtime; // 创建时间
	
	@Column
	private String userid; // 创建用户标识
	
	@Column
	private String username; // 创建用户姓名

	public String getRunactkey()
	{
		return runactkey;
	}

	public void setRunactkey(String runactkey)
	{
		this.runactkey = runactkey;
	}

	public String getRunflowkey()
	{
		return runflowkey;
	}

	public void setRunflowkey(String runflowkey)
	{
		this.runflowkey = runflowkey;
	}

	public String getBflowid()
	{
		return bflowid;
	}

	public void setBflowid(String bflowid)
	{
		this.bflowid = bflowid;
	}

	public String getBactid()
	{
		return bactid;
	}

	public void setBactid(String bactid)
	{
		this.bactid = bactid;
	}

	public String getDataid()
	{
		return dataid;
	}

	public void setDataid(String dataid)
	{
		this.dataid = dataid;
	}

	public Timestamp getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Timestamp createtime)
	{
		this.createtime = createtime;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
	
	

}
