package com.skynet.vmall.base.pojo;

import java.math.BigDecimal;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_GOODS")
public class Goods extends IdEntity
{
	@Column
	private String classid; // 商品分类标识
	
	@Column
	private String classinternal; // 商品分类内部码
	
	@Column
	private String dealerid; // 商家（会员）标识	
	
	@Column
	private String cname; // 商品名称
	
	@Column
	private String supid; // 所属商品标识（商品分为商品、货品，货品作为商品的下级）
	
	@Column
	private String code; // 商品代码	

	@Column
	private String brand; // 品牌
	
	@Column
	private String ctype; // 类型（商品、货品）
	
	@Column
	private String spec; // 规格（白色、公斤等）（合并后规格，冗余显示）
	
	@Column
	private String speccode; // 规格 编码（合并后规格编码，用于检索查找）
	
	@Column
	private String specclass; // 规格类型（颜色、尺寸、重量等）（合并后规格分类，冗余显示）
	
	@Column
	private BigDecimal costprice; // 成本单价		

	@Column
	private BigDecimal saleprice; // 销售单价
	
	@Column
	private BigDecimal promoteprice; // 促销单价

	@Column
	private BigDecimal rebate1; // 1级返利

	@Column
	private BigDecimal rebate2; // 2级返利

	@Column
	private BigDecimal rebate3; // 3级返利

	@Column
	private BigDecimal rebate4; // 4级返利

	@Column
	private BigDecimal rebate5; // 5级返利

	@Column
	private String pic; // 商品图片（URL）

	public String getClassid()
	{
		return classid;
	}

	public void setClassid(String classid)
	{
		this.classid = classid;
	}

	public String getClassinternal()
	{
		return classinternal;
	}

	public void setClassinternal(String classinternal)
	{
		this.classinternal = classinternal;
	}

	public String getDealerid()
	{
		return dealerid;
	}

	public void setDealerid(String dealerid)
	{
		this.dealerid = dealerid;
	}

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

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public String getCtype()
	{
		return ctype;
	}

	public void setCtype(String ctype)
	{
		this.ctype = ctype;
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

	public BigDecimal getCostprice()
	{
		return costprice;
	}

	public void setCostprice(BigDecimal costprice)
	{
		this.costprice = costprice;
	}

	public BigDecimal getSaleprice()
	{
		return saleprice;
	}

	public void setSaleprice(BigDecimal saleprice)
	{
		this.saleprice = saleprice;
	}

	public BigDecimal getPromoteprice()
	{
		return promoteprice;
	}

	public void setPromoteprice(BigDecimal promoteprice)
	{
		this.promoteprice = promoteprice;
	}

	public BigDecimal getRebate1()
	{
		return rebate1;
	}

	public void setRebate1(BigDecimal rebate1)
	{
		this.rebate1 = rebate1;
	}

	public BigDecimal getRebate2()
	{
		return rebate2;
	}

	public void setRebate2(BigDecimal rebate2)
	{
		this.rebate2 = rebate2;
	}

	public BigDecimal getRebate3()
	{
		return rebate3;
	}

	public void setRebate3(BigDecimal rebate3)
	{
		this.rebate3 = rebate3;
	}

	public BigDecimal getRebate4()
	{
		return rebate4;
	}

	public void setRebate4(BigDecimal rebate4)
	{
		this.rebate4 = rebate4;
	}

	public BigDecimal getRebate5()
	{
		return rebate5;
	}

	public void setRebate5(BigDecimal rebate5)
	{
		this.rebate5 = rebate5;
	}

	public String getPic()
	{
		return pic;
	}

	public void setPic(String pic)
	{
		this.pic = pic;
	}

}
