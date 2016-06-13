package metasearch.facade;

import java.util.concurrent.CountDownLatch;
import metasearch.workers.MetaSearchWorker;
import metasearch.common.Vendor;

/**
 * Interface class for MetaSearchWorkerFactory
 *
 * @author Yewon Kim
 */
public interface MetaSearchWorkerFactory {

    /**
     * returns specific MetaSearcher class by category and the worker is initialized. (Naver, Daum, Youtube)
     *
     * @param vendor
     * @param latch
     * @param category
     * @param keyword
     * @return MetaSearchWorker: specific search worker object
     */
    public MetaSearchWorker getMetaSearchWorker(String vendor, CountDownLatch latch, String keyword, String category);

    /**
     * provides vendors which provide search service for specific category.
     *
     * @param category
     * @return Vendor[]: array of Vendors
     */
    public Vendor[] getVendors(String category);
}
