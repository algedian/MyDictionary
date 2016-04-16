package kr.ac.ajou.mydictionary.userdata;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@ComponentScan("kr.ac.ajou.mydictionary.userdata")
@MapperScan("kr.ac.ajou.mydictionary.userdata")
public class UserDataConfig {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://dijkstra.ajou.ac.kr:3306/MyDictionary";
	private static final String DB_USER_NAME = "dc";
	private static final String DB_PASSWORD = "dc";

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(JDBC_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USER_NAME);
		dataSource.setPassword(DB_PASSWORD);

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

		return sessionFactory.getObject();
	}

	// @Bean(destroyMethod="")
	// public SqlSessionTemplate sqlSessionTemplate() throws Exception{
	// SqlSessionTemplate sqlSessionTemplate = new
	// SqlSessionTemplate(sqlSessionFactory());
	// return sqlSessionTemplate;
	// }

	// @Bean
	// public static PropertySourcesPlaceholderConfigurer
	// propertySourcesPlaceholderConfigurer() {
	// return new PropertySourcesPlaceholderConfigurer();
	// }
	/*
	 * @SuppressWarnings("resource")
	 * 
	 * @Bean public UserDataMapper userDataMapper() throws Exception { return
	 * new
	 * SqlSessionTemplate(sqlSessionFactory()).getMapper(UserDataMapper.class);
	 * }
	 */
}