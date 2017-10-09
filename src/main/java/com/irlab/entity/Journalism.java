package com.irlab.entity;
/** 
* @author:  fangrongfu; 
* @version: 1.0;
* @date：        2017年8月1日 下午12:37:28;
*/
public class Journalism {
	private int j_id;// 爬取的新闻的ID
	private String j_title;// 爬取的新闻发布的标题
	private String j_time;// 爬取的新闻发布的时间
	private String j_digest;// 爬取的新闻发布的时间
	private String j_content;// 爬取的新闻发布的内容
	private String j_url;// 爬取的新闻内容的链接
	public int getJ_id() {
		return j_id;
	}
	public void setJ_id(int j_id) {
		this.j_id = j_id;
	}
	public String getJ_title() {
		return j_title;
	}
	public void setJ_title(String j_title) {
		this.j_title = j_title;
	}
	public String getJ_time() {
		return j_time;
	}
	public void setJ_time(String j_time) {
		this.j_time = j_time;
	}
	public String getJ_digest() {
		return j_digest;
	}
	public void setJ_digest(String j_digest) {
		this.j_digest = j_digest;
	}
	public String getJ_content() {
		return j_content;
	}
	public void setJ_content(String j_content) {
		this.j_content = j_content;
	}
	public String getJ_url() {
		return j_url;
	}
	public void setJ_url(String j_url) {
		this.j_url = j_url;
	}
	@Override
	public String toString() {
		return "Journalism [j_id=" + j_id + ", j_title=" + j_title + ", j_time=" + j_time + ", j_digest=" + j_digest
				+ ", j_content=" + j_content + ", j_url=" + j_url + "]";
	}
}
