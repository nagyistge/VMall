package com.skynet.vmall.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_GOODSCLASSSPEC")
public class GoodsClassSpec extends IdEntity
{
	@Column
	private String goodsclassid; // 商品分类标识
	
	@Column
	private int sno; // 序号
	
	@Column
	private String spec; // 规格（白色、公斤等）
	
	@Column
	private String specclass; // 规格类型（颜色、尺寸、重量等）

	public String getGoodsclassid()
	{
		return goodsclassid;
	}

	public void setGoodsclassid(String goodsclassid)
	{
		this.goodsclassid = goodsclassid;
	}

	public int getSno()
	{
		return sno;
	}

	public void setSno(int sno)
	{
		this.sno = sno;
	}

	public String getSpec()
	{
		return spec;
	}

	public void setSpec(String spec)
	{
		this.spec = spec;
	}

	public String getSpecclass()
	{
		return specclass;
	}

	public void setSpecclass(String specclass)
	{
		this.specclass = specclass;
	}
	
}
