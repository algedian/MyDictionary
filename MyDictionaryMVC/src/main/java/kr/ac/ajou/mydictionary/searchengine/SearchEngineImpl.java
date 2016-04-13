package kr.ac.ajou.mydictionary.searchengine;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.dictionarydata.DictionaryDataFacade;

import org.springframework.stereotype.Service;

@Service("searchEngine")
public class SearchEngineImpl implements SearchEngine {

	@Resource(name = "dictionaryDataBaseFacade")
	DictionaryDataFacade dictionaryDataFacade;
	
	@Override
	public void searchTest() {
		String result ="";
		
	}
}
