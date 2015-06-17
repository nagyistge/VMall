package com.skynet.app.log.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_LOG")
public class Log extends IdEntity
{
	@Column
	private String userid; // 访问用户标识

	@Column
	private String username; // 访问用户姓名
	
	@Column
	private String loginname; // 访问用户账号
	
	@Column
	private String wxopenid; // 访问用户微信标识
	
	@Column
	private String actionname; // 日志动作名称	

	@Column
	private String uri; // 访问应用资源地址（相对）	
	
	@Column
	private String url; // 访问应用资源地址
	
	@Column
	private String sip; // 访问服务器IP
	
	@Column
	private String cip; // 访问客户端IP
	
	@Column
	private int sport; // 访问服务器端口
	
	@Column
	private Timestamp logtime; // 日志记录时间

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

	public String getLoginname()
	{
		return loginname;
	}

	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
	}

	public String getWxopenid()
	{
		return wxopenid;
	}

	public void setWxopenid(String wxopenid)
	{
		this.wxopenid = wxopenid;
	}

	public String getActionname()
	{
		return actionname;
	}

	public void setActionname(String actionname)
	{
		this.actionname = actionname;
	}

	public String getUri()
	{
		return uri;
	}

	public void setUri(String uri)
	{
		this.uri = uri;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getSip()
	{
		return sip;
	}

	public void setSip(String sip)
	{
		this.sip = sip;
	}

	public String getCip()
	{
		return cip;
	}

	public void setCip(String cip)
	{
		this.cip = cip;
	}

	public int getSport()
	{
		return sport;
	}

	public void setSport(int sport)
	{
		this.sport = sport;
	}

	public Timestamp getLogtime()
	{
		return logtime;
	}

	public void setLogtime(Timestamp logtime)
	{
		this.logtime = logtime;
	}
}
