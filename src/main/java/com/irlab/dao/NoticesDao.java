package com.irlab.dao;

import java.util.List;

import com.irlab.entity.Notices;

/**
 * @author  fangrongfu
 * @version 1.0
 * @date    2017年10月9日上午10:18:25
 */
public interface NoticesDao {
	//将公告信息添加到公告表中
	public void addNotices(Notices notices);
	//通过公告的url来查询公告信息
	public List<Notices> selectNoticesByUrl(Notices notices);
}
