package com.skynet.app.dictionary.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_SYS_DICTIONARYCLASS")
public class DictionaryClass  extends IdEntity
{
	@Column
	private String supid; // 上级分类标识

	@Column
	private String dname; // 分类名称

	@Column
	private String dkey; // 字典分类key值

	@Column
	private String islast; // 是否根節點

	@Column
	private String supname; // 上级分类名称
}
