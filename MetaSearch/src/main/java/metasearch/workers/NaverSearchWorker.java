/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metasearch.workers;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import javax.ejb.Stateless;
import metasearch.common.Vendor;
import naver.search.NaverSearcher;
import naver.search.NaverSearcherFactory;
import naver.search.NaverSearcherFactoryImpl;

/**
 * - 네이버 api 에 대한 검색작업을 담당하는 스레드 클래스. - SearchWorker thread class for Naver api,
 * extending MetaSearchWorker.
 *
 * @author Yewon Kim
 */
@Stateless
public class NaverSearchWorker extends MetaSearchWorker {

    NaverSearcherFactory factory;
    NaverSearcher searcher;

    public NaverSearchWorker() {
        
    }

    /**
     * - contructor로, latch, keyword, category를 전달받아 지정한다.
     *
     * @param latch
     * @param keyword
     * @param category
     */
    @Override
    public void initialize(CountDownLatch latch, String keyword, String category) {
        super.initialize(latch, keyword, category);
        this.setVendor(Vendor.NAVER);
        factory = new NaverSearcherFactoryImpl();
    }

    /**
     * - factory로 부터 category에 따른 searcher를 얻어온 뒤 searcher의 검색함수를 호출하여 그 결과를
     * 리턴한다.
     *
     * @param keyword
     * @param category
     * @return Hashmap: search result
     */
    @Override
    public HashMap doSearch(String keyword, String category) {
        System.out.println("NaverSearchWorker.doSearch");

        searcher = factory.getNaverSearcher(category);

        HashMap map = searcher.search(keyword);

        System.out.println("NaverSearchWorker returns");

        return map;
    }
}
