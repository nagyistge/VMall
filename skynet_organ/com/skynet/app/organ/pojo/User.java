package com.skynet.app.organ.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_USER")
public class User extends IdEntity
{

	@Column
	private String loginname; // 登录名

	@Column
	private String cname; // 姓名

	@Column
	private String password; // 密码

	@Column
	private String isusing; // 可用标志

	@Column
	private String ordernum; // 排序
	
	@Column
	private String weixinno; // 微信号
	
	@Column
	private String mobile; // 手机号

}
