package metasearch.factory;

import java.util.concurrent.CountDownLatch;
import metasearch.workers.MetaSearchWorker;
import metasearch.enums.Vendor;

/**
 *
 * @author Yewon Kim - Administrator
 */
public interface MetaSearchWorkerFactory {
    public MetaSearchWorker getMetaSearchWorker(Vendor vendor, CountDownLatch latch);
}
