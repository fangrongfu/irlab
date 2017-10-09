package com.irlab.dao;

import java.util.List;

import com.irlab.entity.Firm;

/**
 * @author  fangrongfu
 * @version 1.0
 * @date    2017年10月9日上午10:18:08
 */
public interface FirmDao {
	//查询所有的公司信息
	public List<Firm> selectAllFirm();
}
