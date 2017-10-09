package com.irlab.util;

import java.util.List;

public class ListFor {
	public String forList(List list){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < list.size(); i++){
			String s = (String) list.get(i);
			sb.append(s);
		}
		if(sb == null){
			return null;
		}
		return sb.toString();
	}
}
