package com.zhaohaijie.httptask.JAV.Objects;

public class JAVImage {
	/*
	 * create table T_JAVIMAGE (
	   id INT NOT NULL auto_increment,
	   javrecordid INT default 0,
	   imgUrl VARCHAR(255) default NULL,
	   imgName VARCHAR(30) default NULL,
	   content BLOB NOT NULL,
	   PRIMARY KEY (id)
	);
	
	*/
	
	private int id;
	private int javrecordid;
	private String imgUrl;
	private String imgName;
	private byte[] content;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getJavrecordid() {
		return javrecordid;
	}
	
	public void setJavrecordid(int javrecordid) {
		this.javrecordid = javrecordid;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getImgName() {
		return imgName;
	}
	
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	public byte[] getContent() {
		return content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}

}
