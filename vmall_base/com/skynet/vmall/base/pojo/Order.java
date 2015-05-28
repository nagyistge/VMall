package com.skynet.vmall.base.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_ORDER")
public class Order extends IdEntity
{
	@Column
	private String distributorid; // 分销商标识	
	
	@Column
	private Timestamp ordertime; // 订单时间

	@Column
	private BigDecimal amount; // 订单总额
	
	
}
