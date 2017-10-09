package com.irlab.dao;

import java.util.List;

import com.irlab.entity.Journalism;

public interface JournalismDao {
	//将新闻信息添加到新闻表中
	public void addJournalism(Journalism journalism);
	//通过新闻得url来查询新闻信息
	public List<Journalism> selectJournalismByUrl(Journalism journalism);
}
