/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metasearch.workers;

import java.util.concurrent.CountDownLatch;
import metasearch.common.Vendor;
import naver.search.NaverSearcher;
import naver.search.NaverSearcherFactory;
import naver.search.NaverSearcherFactoryImpl;

/**
 *
 * @author Yewon Kim - Administrator
 */
public class NaverSearchWorker extends MetaSearchWorker {

    NaverSearcherFactory factory;
    NaverSearcher searcher;
    
    public NaverSearchWorker(CountDownLatch latch, String keyword, String category) {
        super(latch, keyword, category);
        this.setVendor(Vendor.NAVER);
        factory = new NaverSearcherFactoryImpl();
    }

    @Override
    public void doSearch(String keyword, String category) {
        System.out.println("NaverSearchWorker.doSearch");
        
        //factory.getNaverSearcher(category);
        searcher = factory.getNaverSearcher(category);
        
        searcher.search(keyword);
        
        /*HashMap<SearchCategory, HashMap> map = new HashMap<SearchCategory, HashMap>();
        
         //각각의 검색결과를 받아온 뒤에 그 결과값을 넣어줌
         map.put(SearchCategory.BLOG, new HashMap());
        
         super.setSearchResult(map);*/
    }
}
