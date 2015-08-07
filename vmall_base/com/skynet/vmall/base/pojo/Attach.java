package com.skynet.vmall.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_ATTACH")
public class Attach extends IdEntity
{
	@Column
	private String cclass; // 附件分类
	
	@Column	
	private String cname; // 附件名
	
	@Column
	private String curl; // 附件链接地址
	
	@Column
	private String filename; // 附件文件名	
	
	@Column
	private String fileextname; // 附件文件名	
	
	@Column
	private String createuser; // 附件创建人
	
	@Column
	private String createuserid; // 附件创建人标识

	@Column
	private Timestamp createtime; // 附件创建时间

	public String getCclass()
	{
		return cclass;
	}

	public void setCclass(String cclass)
	{
		this.cclass = cclass;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getCurl()
	{
		return curl;
	}

	public void setCurl(String curl)
	{
		this.curl = curl;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getFileextname()
	{
		return fileextname;
	}

	public void setFileextname(String fileextname)
	{
		this.fileextname = fileextname;
	}

	public String getCreateuser()
	{
		return createuser;
	}

	public void setCreateuser(String createuser)
	{
		this.createuser = createuser;
	}

	public String getCreateuserid()
	{
		return createuserid;
	}

	public void setCreateuserid(String createuserid)
	{
		this.createuserid = createuserid;
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
