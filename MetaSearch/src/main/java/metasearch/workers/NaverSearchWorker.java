package metasearch.workers;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import javax.ejb.Stateless;
import metasearch.common.Vendor;
import naver.search.NaverSearcher;
import naver.search.NaverSearcherFactory;
import naver.search.NaverSearcherFactoryImpl;

/**
 * SearchWorker thread class for Naver search API that extends MetaSearchWorker.
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
     * it is contructor that is delivered latch, keyword, category and designate them.
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
     * First, get certain category's searcher from factory. 
     * Then call searcher's search function. Finally return the results.
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
