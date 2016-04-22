package kr.ac.ajou.mydictionary.dictionarydata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@ComponentScan(basePackages = {"kr.ac.ajou.mydictionary.dictionarydata", "kr.ac.ajou.mydictionary.searchengine"})
@EnableMongoRepositories(basePackages = "kr.ac.ajou.mydictionary.dictionarydata")
public class DictionaryDataConfig extends AbstractMongoConfiguration {
	private static final String DB_NAME = "MyDictionary";
	private static final String DB_URL = "dijkstra.ajou.ac.kr";
	private static final String BASE_PACKAGE = "kr.ac.ajou.mydictionary.dictionarydata";

	@Override
	@Bean
	protected String getDatabaseName() {
		return DB_NAME;
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		// ServerAddress serverAddress = new ServerAddress("localhost", 27017);
		// MongoCredential credential = MongoCredential.createMongoCRCredential("storeUser", getDatabaseName(), "storePass".toCharArray());
		// MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(4).socketKeepAlive(true).build();
		// Mongo mongo = new MongoClient(serverAddress, Arrays.asList(credential), options);

		return new MongoClient(DB_URL, 27017);
	}

	@Override
	@Bean// (name = "MongoTemplate")
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), getDatabaseName());
	}

	@Override
	protected String getMappingBasePackage() {
		return BASE_PACKAGE;
	}
}
