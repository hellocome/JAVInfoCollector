package com.zhaohaijie.httptask.JAV.Objects;

public class JAVGenre {
	/*
	 * create table T_JAVRECORD_GENRE (
		   javgenreid INT NOT NULL auto_increment,
		   javrecordid INT default 0,
		   PRIMARY KEY (javgenreid,javrecordid)
		);
		
		create table T_JAVGENRE (
		   id INT NOT NULL auto_increment, 
		   genreName VARCHAR(100) NOT NULL,
		   PRIMARY KEY (id)
		);
	 */
	
	private int id;
	private String genreName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
}
