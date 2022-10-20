package com.santi.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * sqlSessionFactory 工具类
 * @author Administrator
 *
 */
public class BaseMybatis {
	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			String resource = "mybatis-conf.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getsqlSessionFactory() {
		return sqlSessionFactory;
	}
}
