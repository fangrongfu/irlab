package com.irlab.entity;

/**
 * @author  fangrongfu
 * @version 1.0
 * @date    2017年10月9日上午9:46:19
 */
public class Firm {
	private int f_id;//上市公司的ID
	private String f_name;//上市公司的名字
	private String f_code;//上市公司的股票代码
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_code() {
		return f_code;
	}
	public void setF_code(String f_code) {
		this.f_code = f_code;
	}
	@Override
	public String toString() {
		return "Firm [f_id=" + f_id + ", f_name=" + f_name + ", f_code=" + f_code + "]";
	}
}
