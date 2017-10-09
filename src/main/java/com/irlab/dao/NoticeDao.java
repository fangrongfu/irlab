package com.irlab.dao;

import java.util.List;

import com.irlab.entity.Notice;

/**
 * @author  fangrongfu
 * @version 1.0
 * @date    2017年10月9日上午10:18:38
 */
public interface NoticeDao {
	//添加季报公告到notice表中
	public void addNotice();
	//通过url查询notice表中的公告信息
	public List<Notice> selectNoticeByUrl(Notice notice);
}
