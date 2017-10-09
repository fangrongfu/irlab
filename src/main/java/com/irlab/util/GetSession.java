package com.irlab.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author  fangrongfu
 * @version 1.0
 * @date    2017年10月9日下午12:31:51
 */
public class GetSession {
	public SqlSession getSqlSession() {
		// mybatis配置文件
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			// 得到配置文件流
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 创建会话工厂，传入mybatis的配置文件
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
		// 通过工厂得到SqlSession
		SqlSession session = ssf.openSession();
		return session;
	}
}
