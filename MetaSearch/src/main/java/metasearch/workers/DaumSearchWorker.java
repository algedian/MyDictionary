/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metasearch.workers;

import daum.search.DaumSearcher;
import daum.search.DaumSearcherFactory;
import daum.search.DaumSearcherFactoryImpl;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import javax.ejb.Stateless;
import metasearch.common.Vendor;

/**
 * - 다음 api 에 대한 검색작업을 담당하는 스레드 클래스. - SearchWorker thread class for Daum api,
 * extending MetaSearchWorker.
 * 
 * @author user
 */
@Stateless
public class DaumSearchWorker extends MetaSearchWorker{
    
    DaumSearcherFactory factory;
    DaumSearcher searcher;

    public DaumSearchWorker() {
        
    }

    /**
     * - contructor로, latch, keyword, category를 전달받아 지정한다.
     *
     * @param latch
     * @param keyword
     * @param category
     */
    //-
    @Override
    public void initialize(CountDownLatch latch, String keyword, String category) {
        super.initialize(latch, keyword, category);
        this.setVendor(Vendor.DAUM);
        factory = new DaumSearcherFactoryImpl();
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
        System.out.println("DaumSearchWorker.doSearch");

        searcher = factory.getDaumSearcher(category);

        HashMap map = searcher.search(keyword);

        System.out.println("DaumSearchWorker returns");

        return map;
    }
}
