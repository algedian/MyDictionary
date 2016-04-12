package kr.ac.ajou.mydictionary.dictionarymanager;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "kr.ac.ajou.mydictionary.dictionarydata" })
@Import({ kr.ac.ajou.mydictionary.userdata.UserDataConfig.class })
// kr.ac.ajou.mydictionary.dictionarydata.DictionaryDataConfig.class})
public class AppConfig {

}
