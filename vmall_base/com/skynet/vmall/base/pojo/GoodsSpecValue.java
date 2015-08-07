package com.skynet.vmall.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_GOODSSPECVAL")
public class GoodsSpecValue extends IdEntity
{
	@Column
	private String goodsid; // 商品分类标识

	@Column
	private String goodsspecid; // 商品分类规格标识
	
	@Column
	private String specclass; // 规格类型（颜色、尺寸、重量等）	

	@Column
	private int sno; // 序号

	@Column
	private String cvalue; // 规格内容

	public String getGoodsid()
	{
		return goodsid;
	}

	public void setGoodsid(String goodsid)
	{
		this.goodsid = goodsid;
	}

	public String getGoodsspecid()
	{
		return goodsspecid;
	}

	public void setGoodsspecid(String goodsspecid)
	{
		this.goodsspecid = goodsspecid;
	}

	public String getSpecclass()
	{
		return specclass;
	}

	public void setSpecclass(String specclass)
	{
		this.specclass = specclass;
	}

	public int getSno()
	{
		return sno;
	}

	public void setSno(int sno)
	{
		this.sno = sno;
	}

	public String getCvalue()
	{
		return cvalue;
	}

	public void setCvalue(String cvalue)
	{
		this.cvalue = cvalue;
	}

}
