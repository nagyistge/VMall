package com.skynet.vmall.base.pojo;

import java.math.BigDecimal;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_GOODS")
public class Goods extends IdEntity
{
	// 基本信息
	@Column
	private String classid; // 商品分类标识

	@Column
	private String classinternal; // 商品分类内部码

	@Column
	private String supid; // 所属商品标识（商品分为商品、货品，货品作为商品的下级）

	@Column
	private String ctype; // 类型（商品、货品）

	@Column
	private int serial; // 排列顺序号

	// 商品信息
	@Column
	private String cname; // 商品名称
	
	@Column
	private String cno; // 商品编号（系统编号）	

	@Column
	private String dealerid; // 经销商家标识（组织机构）

	@Column
	private String dealername; // 经销商家名称

	@Column
	private String code; // 经销商商品货号（商家自定义货号）
	
	@Column
	private String supplierid; // 供货商家标识（组织机构）

	@Column
	private String suppliername; // 供货商家名称	

	@Column
	private String brand; // 品牌

	@Column
	private BigDecimal costprice; // 成本单价

	@Column
	private BigDecimal saleprice; // 销售单价（原价）

	@Column
	private BigDecimal promoteprice; // 促销单价（现价）

	@Column
	private BigDecimal allstorenum; // 总库存（所有下属货品数量总和）

	@Column
	private BigDecimal salenum; // 销售量

	@Column
	private BigDecimal basesalenum; // 基础销量

	@Column
	private int praisenum; // 点赞数

	@Column
	private int basepraisenum; // 基础点赞数

	@Column
	private int popular; // 人气（访问量）

	@Column
	private int basepopular; // 基础人气（访问量）

	@Column
	private BigDecimal weight; // 重量

	@Column
	private BigDecimal volume; // 体积

	@Column
	@ColDefine(type=ColType.VARCHAR, width=500)
	private String pic; // 商品图片（URL）

	// 佣金设置
	@Column
	private String rebatetype; // 返利方式（积分、百分比）

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

	// 库存/规格信息
	@Column
	private String spec; // 规格（白色、公斤等）（合并后规格，冗余显示）

	@Column
	private String speccode; // 规格 编码（合并后规格编码，用于检索查找）

	@Column
	private String specclass; // 规格类型（颜色、尺寸、重量等）（合并后规格分类，冗余显示）
	
	@Column
	private String defspec; // 缺省规格货品 （是、否）（产品具有多个规格的货品时，缺省显示的规格货品）

	// 物流及其它
	@Column	
	private String isfreelogistics; // 是否免物流（是、否）

	@Column
	private String freightpayer; // 运费设置（包邮、统一运费、运费模板）
	
	@Column
	private int fullnummail; // 满件包邮

	@Column
	private String hidestock; // 库存显示（是、否）

	@Column
	private int quota; // 每人限购（是、否）

	@Column
	private int buyneedpoints; // 购买商品所需积分

	@Column
	private String consumcoupon; // 消费送优惠券 （对应优惠券标识）

	@Column
	private int consumpoint; // 消费送积分
	
	@Column
	private int joinleveldiscount; // 会员等级折扣（开启、关闭）
	
	@Column
	private String state; // （新建、上架、下架);

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

	public String getSupid()
	{
		return supid;
	}

	public void setSupid(String supid)
	{
		this.supid = supid;
	}

	public String getCtype()
	{
		return ctype;
	}

	public void setCtype(String ctype)
	{
		this.ctype = ctype;
	}

	public int getSerial()
	{
		return serial;
	}

	public void setSerial(int serial)
	{
		this.serial = serial;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getCno()
	{
		return cno;
	}

	public void setCno(String cno)
	{
		this.cno = cno;
	}

	public String getDealerid()
	{
		return dealerid;
	}

	public void setDealerid(String dealerid)
	{
		this.dealerid = dealerid;
	}

	public String getDealername()
	{
		return dealername;
	}

	public void setDealername(String dealername)
	{
		this.dealername = dealername;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getSupplierid()
	{
		return supplierid;
	}

	public void setSupplierid(String supplierid)
	{
		this.supplierid = supplierid;
	}

	public String getSuppliername()
	{
		return suppliername;
	}

	public void setSuppliername(String suppliername)
	{
		this.suppliername = suppliername;
	}

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
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

	public BigDecimal getAllstorenum()
	{
		return allstorenum;
	}

	public void setAllstorenum(BigDecimal allstorenum)
	{
		this.allstorenum = allstorenum;
	}

	public BigDecimal getSalenum()
	{
		return salenum;
	}

	public void setSalenum(BigDecimal salenum)
	{
		this.salenum = salenum;
	}

	public BigDecimal getBasesalenum()
	{
		return basesalenum;
	}

	public void setBasesalenum(BigDecimal basesalenum)
	{
		this.basesalenum = basesalenum;
	}

	public int getPraisenum()
	{
		return praisenum;
	}

	public void setPraisenum(int praisenum)
	{
		this.praisenum = praisenum;
	}

	public int getBasepraisenum()
	{
		return basepraisenum;
	}

	public void setBasepraisenum(int basepraisenum)
	{
		this.basepraisenum = basepraisenum;
	}

	public int getPopular()
	{
		return popular;
	}

	public void setPopular(int popular)
	{
		this.popular = popular;
	}

	public int getBasepopular()
	{
		return basepopular;
	}

	public void setBasepopular(int basepopular)
	{
		this.basepopular = basepopular;
	}

	public BigDecimal getWeight()
	{
		return weight;
	}

	public void setWeight(BigDecimal weight)
	{
		this.weight = weight;
	}

	public BigDecimal getVolume()
	{
		return volume;
	}

	public void setVolume(BigDecimal volume)
	{
		this.volume = volume;
	}

	public String getPic()
	{
		return pic;
	}

	public void setPic(String pic)
	{
		this.pic = pic;
	}

	public String getRebatetype()
	{
		return rebatetype;
	}

	public void setRebatetype(String rebatetype)
	{
		this.rebatetype = rebatetype;
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

	public String getDefspec()
	{
		return defspec;
	}

	public void setDefspec(String defspec)
	{
		this.defspec = defspec;
	}

	public String getIsfreelogistics()
	{
		return isfreelogistics;
	}

	public void setIsfreelogistics(String isfreelogistics)
	{
		this.isfreelogistics = isfreelogistics;
	}

	public String getFreightpayer()
	{
		return freightpayer;
	}

	public void setFreightpayer(String freightpayer)
	{
		this.freightpayer = freightpayer;
	}

	public int getFullnummail()
	{
		return fullnummail;
	}

	public void setFullnummail(int fullnummail)
	{
		this.fullnummail = fullnummail;
	}

	public String getHidestock()
	{
		return hidestock;
	}

	public void setHidestock(String hidestock)
	{
		this.hidestock = hidestock;
	}

	public int getQuota()
	{
		return quota;
	}

	public void setQuota(int quota)
	{
		this.quota = quota;
	}

	public int getBuyneedpoints()
	{
		return buyneedpoints;
	}

	public void setBuyneedpoints(int buyneedpoints)
	{
		this.buyneedpoints = buyneedpoints;
	}

	public String getConsumcoupon()
	{
		return consumcoupon;
	}

	public void setConsumcoupon(String consumcoupon)
	{
		this.consumcoupon = consumcoupon;
	}

	public int getConsumpoint()
	{
		return consumpoint;
	}

	public void setConsumpoint(int consumpoint)
	{
		this.consumpoint = consumpoint;
	}

	public int getJoinleveldiscount()
	{
		return joinleveldiscount;
	}

	public void setJoinleveldiscount(int joinleveldiscount)
	{
		this.joinleveldiscount = joinleveldiscount;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}
}
