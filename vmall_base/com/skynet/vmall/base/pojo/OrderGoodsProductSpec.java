package com.skynet.vmall.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_ORDERGOODSPDSPEC")
public class OrderGoodsProductSpec extends IdEntity
{
	@Column
	private String orderid; // 订单标识
	
	@Column
	private String ordergoodsid; // 订单商品标识
	
	@Column
	private String goodsid; // 商品标识
	
	@Column
	private String productid; // 货品标识（商品不同规格为货品）	
	
	@Column
	private int sno; // 序号	
	
	@Column
	private String spec; // 规格（白色、公斤等）
	
	@Column
	private String speccode; // 规格编码（4位）	
	
	@Column
	private String specclass; // 规格类型（颜色、尺寸、重量等）

	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(String orderid)
	{
		this.orderid = orderid;
	}

	public String getOrdergoodsid()
	{
		return ordergoodsid;
	}

	public void setOrdergoodsid(String ordergoodsid)
	{
		this.ordergoodsid = ordergoodsid;
	}

	public String getGoodsid()
	{
		return goodsid;
	}

	public void setGoodsid(String goodsid)
	{
		this.goodsid = goodsid;
	}

	public String getProductid()
	{
		return productid;
	}

	public void setProductid(String productid)
	{
		this.productid = productid;
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

	public String getSpeccode()
	{
		return speccode;
	}

	public void setSpeccode(String speccode)
	{
		this.speccode = speccode;
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
