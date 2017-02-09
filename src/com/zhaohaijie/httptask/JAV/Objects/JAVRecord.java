package com.zhaohaijie.httptask.JAV.Objects;

import java.util.Date;
import java.util.Set;

public class JAVRecord {
	/*
	 * 
	 * create table T_JAVRECORD (
		   id INT NOT NULL auto_increment,
		   language VARCHAR(2) NOT NULL,
		   avuid VARCHAR(30) NOT NULL ,
		   title VARCHAR(500) default NULL,
		   titleImgName VARCHAR(30) default NULL,
		   releaseDate DATE default "2000-01-01",
		   lengthInMinutes INT default 0,  
		   director VARCHAR(100) default NULL,
		   maker VARCHAR(100) default NULL,
		   label VARCHAR(100) default NULL,
		   userRate INT default 0,
		   PRIMARY KEY (id)
		);
	 * */
	
	private int id;
	private String language;
	private int avuid;
	private String title;
	private String titleImgName;
	private String titleImgUrl;
	private Date releaseDate;
	private int lengthInMinutes;
	private String director;
	private String maker;
	private String label;
	private int userRate;
	private String cast;
	private int wantit;
	private int watchedit;
	private int gotit;
	
	private Set<JAVGenre> genres;
	private Set<JAVImage> images;
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public int getAvuid() {
		return avuid;
	}


	public void setAvuid(int avuid) {
		this.avuid = avuid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getTitleImgName() {
		return titleImgName;
	}


	public void setTitleImgName(String titleImgName) {
		this.titleImgName = titleImgName;
	}


	public String getTitleImgUrl() {
		return titleImgUrl;
	}


	public void setTitleImgUrl(String titleImgUrl) {
		this.titleImgUrl = titleImgUrl;
	}


	public Date getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}


	public int getLengthInMinutes() {
		return lengthInMinutes;
	}


	public void setLengthInMinutes(int lengthInMinutes) {
		this.lengthInMinutes = lengthInMinutes;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getMaker() {
		return maker;
	}


	public void setMaker(String maker) {
		this.maker = maker;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public int getUserRate() {
		return userRate;
	}


	public void setUserRate(int userRate) {
		this.userRate = userRate;
	}


	public String getCast() {
		return cast;
	}


	public void setCast(String cast) {
		this.cast = cast;
	}


	public int getWantit() {
		return wantit;
	}


	public void setWantit(int wantit) {
		this.wantit = wantit;
	}


	public int getWatchedit() {
		return watchedit;
	}


	public void setWatchedit(int watchedit) {
		this.watchedit = watchedit;
	}


	public int getGotit() {
		return gotit;
	}


	public void setGotit(int gotit) {
		this.gotit = gotit;
	}


	public Set<JAVGenre> getGenres() {
		return genres;
	}


	public void setGenres(Set<JAVGenre> genres) {
		this.genres = genres;
	}


	public Set<JAVImage> getImages() {
		return images;
	}


	public void setImages(Set<JAVImage> images) {
		this.images = images;
	}


	public JAVRecord(String language, String id, String title){
		
	}
}
