package metasearch.workers;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import metasearch.common.SearchCategory;
import metasearch.common.Vendor;

/**
 *
 * @author Yewon Kim - Administrator
 */
public abstract class MetaSearchWorker extends Thread {

    private CountDownLatch latch;
    private Vendor vendor;
    private String keyword, category;
    private HashMap<SearchCategory, HashMap> searchResult;

    MetaSearchWorker(CountDownLatch latch, String keyword, String category) {
        this.latch = latch;
        this.keyword = keyword;
        this.category = category;
        this.searchResult = null;
    }

    @Override
    public void run() {
        System.out.println("MetaSearchWorker.run");
        doSearch(keyword, category);
        latch.countDown();
    }

    /* 이 함수를 오버라이드해서 쓸 수 있도록?*/
    public abstract void doSearch(String keyword, String category);

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public HashMap<SearchCategory, HashMap> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(HashMap<SearchCategory, HashMap> searchResult) {
        this.searchResult = searchResult;
    }
    
}
