package com.skynet.vmall.base.pojo;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("T_APP_GOODSCHANGE")
public class GoodsChange extends Goods
{
	@Column
	private Timestamp changetime; // 变更时间
	
	@Column
	private String changer; // 变更人账号
	
	@Column
	private String changercname; // 变更人姓名

	public Timestamp getChangetime()
	{
		return changetime;
	}

	public void setChangetime(Timestamp changetime)
	{
		this.changetime = changetime;
	}

	public String getChanger()
	{
		return changer;
	}

	public void setChanger(String changer)
	{
		this.changer = changer;
	}

	public String getChangercname()
	{
		return changercname;
	}

	public void setChangercname(String changercname)
	{
		this.changercname = changercname;
	}
	
}
