package metasearch.manager;

import java.util.concurrent.CountDownLatch;
import metasearch.workers.MetaSearchWorker;
import metasearch.common.Vendor;

/**
 *
 * @author Yewon Kim - Administrator
 */
public interface MetaSearchWorkerFactory {
    public MetaSearchWorker getMetaSearchWorker(String vendor, CountDownLatch latch, String keyword, String category);
    public Vendor[] getVendors(String category);
}
