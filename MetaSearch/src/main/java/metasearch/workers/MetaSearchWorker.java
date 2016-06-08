package metasearch.workers;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import metasearch.common.SearchCategory;
import metasearch.common.Vendor;

/**
 * An abstract thread class that is responsible for searhing works. 
 * Each search workers of specific api vendor extends this class.
 *
 * @author Yewon Kim
 */
public abstract class MetaSearchWorker extends Thread {

    private CountDownLatch latch;
    private Vendor vendor;
    private String keyword;
    private String category;
    private HashMap<String, HashMap> searchResult;

    public MetaSearchWorker() {

    }

    public void initialize(CountDownLatch latch, String keyword, String category) {
        this.latch = latch;
        this.keyword = keyword;
        this.category = category;
        this.searchResult = new HashMap<>();
    }

    //abstract method
    public abstract HashMap doSearch(String keyword, String category);

    @Override
    public void run() {
        System.out.println("MetaSearchWorker.run");

        HashMap map = doSearch(keyword, category);

        searchResult.put(vendor.getName(), map);

        //Latch counter is decreased, when each thread finishes its job. 
        latch.countDown();

        System.out.println("MetaSearchWorker work finished");
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public HashMap<String, HashMap> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(HashMap<String, HashMap> searchResult) {
        this.searchResult = searchResult;
    }

}
