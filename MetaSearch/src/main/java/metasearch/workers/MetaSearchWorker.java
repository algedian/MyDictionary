package metasearch.workers;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import metasearch.enums.SearchCategory;
import metasearch.enums.Vendor;

/**
 *
 * @author Yewon Kim - Administrator
 */
public abstract class MetaSearchWorker extends Thread {

    private CountDownLatch latch;
    private Vendor vendor;
    private String keyword;
    private HashMap<SearchCategory, HashMap> searchResult;

    MetaSearchWorker(CountDownLatch latch, String keyword) {
        this.latch = latch;
        this.keyword = keyword;
        this.searchResult = null;
    }

    public void run() {
        doSearch(keyword);
        latch.countDown();
    }

    /* 이 함수를 오버라이드해서 쓸 수 있도록?*/
    public abstract void doSearch(String keyword);

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
