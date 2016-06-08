package metasearch.workers;

import daum.search.DaumSearcher;
import daum.search.DaumSearcherFactory;
import daum.search.DaumSearcherFactoryImpl;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import javax.ejb.Stateless;
import metasearch.common.Vendor;

/**
 * SearchWorker thread class for Daum search API that extends MetaSearchWorker.
 *
 * @author kyeonghee
 */
@Stateless
public class DaumSearchWorker extends MetaSearchWorker {

    DaumSearcherFactory factory;
    DaumSearcher searcher;

    public DaumSearchWorker() {

    }

    /**
     * it is contructor that is delivered latch, keyword, category and designate them.
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
     * First, get certain category's searcher from factory. 
     * Then call searcher's search function. Finally return the results.
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
