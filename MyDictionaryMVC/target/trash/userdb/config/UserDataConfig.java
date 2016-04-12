package kr.ac.ajou.mydictionary.userdb.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan("kr.ac.ajou.mydictionary.userdb.mapper")
public class UserDataConfig {
	private static final String jdbcDriver = "com.mysql.jdbc.Driver";
	private static final String dbURL = "jdbc:mysql://dijkstra.ajou.ac.kr:3306/MyDictionary";
	private static final String dbUserName = "dc";
	private static final String dbPassword = "dc";

	// private static final String mapperPackage =
	// "kr.ac.ajou.mydictionary.userdb.mapper";

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(jdbcDriver);
		dataSource.setUrl(dbURL);
		dataSource.setUsername(dbUserName);
		dataSource.setPassword(dbPassword);

		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		// sessionFactory.setTypeAliasesPackage(mapperPackage);

		return sessionFactory.getObject();
	}
}