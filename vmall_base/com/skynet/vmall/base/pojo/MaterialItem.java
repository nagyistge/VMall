package com.skynet.vmall.base.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.skynet.framework.pojo.IdEntity;

@Table("T_APP_MATERIALITEM")
public class MaterialItem extends IdEntity
{
	@Column
	private String materialid; // 图文素材标识
	
	@Column
	private String title; // 消息图文素材标题
	
	@Column
	private String author; // 作者
	
	@Column
	private String pic; // 封面图片
	
	@Column
	private String description; // 摘要
	
	@Column
	private String content; // 正文
	
	@Column
	private String linkurl; // 链接地址
	
	@Column
	private String linkname; // 链接名称

	public String getMaterialid()
	{
		return materialid;
	}

	public void setMaterialid(String materialid)
	{
		this.materialid = materialid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getPic()
	{
		return pic;
	}

	public void setPic(String pic)
	{
		this.pic = pic;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getLinkurl()
	{
		return linkurl;
	}

	public void setLinkurl(String linkurl)
	{
		this.linkurl = linkurl;
	}

	public String getLinkname()
	{
		return linkname;
	}

	public void setLinkname(String linkname)
	{
		this.linkname = linkname;
	}

}
