package metasearch.manager;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import metasearch.workers.MetaSearchWorker;
import metasearch.common.Vendor;

/**
 * Interface class for MetaSearchWorkerFactory
 *
 * @author Yewon Kim
 */
public interface MetaSearchWorkerFactory {

    public MetaSearchWorker getMetaSearchWorker(String vendor, CountDownLatch latch, String keyword, String category);

    public Vendor[] getVendors(String category);
}
