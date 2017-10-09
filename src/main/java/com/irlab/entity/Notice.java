package com.irlab.entity;

/**
 * @author  fangrongfu
 * @version 1.0
 * @date    2017年10月9日上午9:47:57
 */
public class Notice {
	private int n_id;// 公司公告表的ID
	private String n_name;//  公司的名称
	private String n_code;//  公司的股票代码
	private String n_title;//  公司公告的标题
	private String n_time;//  公司公告的时间
	private String n_content;//  公司公告的内容
	private String n_url;//  公司公告PDF下载的url
	private String n_event;//  公司公告的事件类型
	public int getN_id() {
		return n_id;
	}
	public void setN_id(int n_id) {
		this.n_id = n_id;
	}
	public String getN_name() {
		return n_name;
	}
	public void setN_name(String n_name) {
		this.n_name = n_name;
	}
	public String getN_code() {
		return n_code;
	}
	public void setN_code(String n_code) {
		this.n_code = n_code;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_time() {
		return n_time;
	}
	public void setN_time(String n_time) {
		this.n_time = n_time;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public String getN_url() {
		return n_url;
	}
	public void setN_url(String n_url) {
		this.n_url = n_url;
	}
	public String getN_event() {
		return n_event;
	}
	public void setN_event(String n_event) {
		this.n_event = n_event;
	}
	@Override
	public String toString() {
		return "Notice [n_id=" + n_id + ", n_name=" + n_name + ", n_code=" + n_code + ", n_title=" + n_title
				+ ", n_time=" + n_time + ", n_content=" + n_content + ", n_url=" + n_url + ", n_event=" + n_event + "]";
	}
}
