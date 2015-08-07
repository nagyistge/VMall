package com.skynet.vmall.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_ATTACHREF")
public class AttachRef extends IdEntity
{
	@Column
	private String kid; // 业务标识

	@Column
	private String cclass; // 业务实体类型

	@Column
	private String attachid; // 附件标识

	@Column
	private String attachname; // 附件名称
	
	@Column
	private String memo; // 备注

	public String getKid()
	{
		return kid;
	}

	public void setKid(String kid)
	{
		this.kid = kid;
	}

	public String getCclass()
	{
		return cclass;
	}

	public void setCclass(String cclass)
	{
		this.cclass = cclass;
	}

	public String getAttachid()
	{
		return attachid;
	}

	public void setAttachid(String attachid)
	{
		this.attachid = attachid;
	}

	public String getAttachname()
	{
		return attachname;
	}

	public void setAttachname(String attachname)
	{
		this.attachname = attachname;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

}
