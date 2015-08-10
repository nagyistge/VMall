package com.skynet.vmall.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_GOODSCLASSSPECVAL")
public class GoodsClassSpecValue extends IdEntity
{
	@Column
	private String goodsclassid; // 商品分类标识
	
	@Column
	private String goodsclassspecid; // 商品分类规格标识	
	
	@Column
	private String specclass; // 规格类型（颜色、尺寸、重量等）	
	
	@Column
	private int sno; // 序号
	
	@Column
	private String cvalue; // 规格内容

	public String getGoodsclassid()
	{
		return goodsclassid;
	}

	public void setGoodsclassid(String goodsclassid)
	{
		this.goodsclassid = goodsclassid;
	}

	public String getGoodsclassspecid()
	{
		return goodsclassspecid;
	}

	public void setGoodsclassspecid(String goodsclassspecid)
	{
		this.goodsclassspecid = goodsclassspecid;
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
