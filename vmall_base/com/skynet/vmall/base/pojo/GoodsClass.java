package com.skynet.vmall.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_GOODSCLASS")
public class GoodsClass extends IdEntity
{
	// 基本信息
	@Column
	private String cname; // 分类名称	
	
	@Column
	private String supid; // 上级分类标识
	
	@Column
	private String internal; // 内部码

	@Column
	private String dealerid; // 商家（会员）标识
	
	// 样式信息
	@Column
	private String pic; // 分类图标地址

	@Column
	private String floorclass; // 首页分类样式
	
	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getSupid()
	{
		return supid;
	}

	public void setSupid(String supid)
	{
		this.supid = supid;
	}

	public String getInternal()
	{
		return internal;
	}

	public void setInternal(String internal)
	{
		this.internal = internal;
	}

	public String getDealerid()
	{
		return dealerid;
	}

	public void setDealerid(String dealerid)
	{
		this.dealerid = dealerid;
	}

	public String getPic()
	{
		return pic;
	}

	public void setPic(String pic)
	{
		this.pic = pic;
	}

	public String getFloorclass()
	{
		return floorclass;
	}

	public void setFloorclass(String floorclass)
	{
		this.floorclass = floorclass;
	}

}
