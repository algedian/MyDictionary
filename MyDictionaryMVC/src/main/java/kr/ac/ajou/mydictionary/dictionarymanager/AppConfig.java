package kr.ac.ajou.mydictionary.dictionarymanager;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "kr.ac.ajou.mydictionary" })
// @Import({ kr.ac.ajou.mydictionary.userdata.UserDataConfig.class })
// kr.ac.ajou.mydictionary.dictionarydata.DictionaryDataConfig.class})
public class AppConfig {

}
