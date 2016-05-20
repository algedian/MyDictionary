package metasearch.common;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import metasearch.workers.MetaSearchWorker;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metasearch.enums.Vendor;
import metasearch.factory.MetaSearchWorkerFactory;

/**
 *
 * @author Yewon Kim - Administrator
 */
@Stateless
public class MetaSearchFacadeImpl implements MetaSearchFacade {

    private HashMap<Vendor, HashMap> metaSearchResult;
    private CountDownLatch latch = new CountDownLatch(Vendor.size);

    @EJB
    private MetaSearchWorkerFactory factory;

    public MetaSearchFacadeImpl() {
        super();
        metaSearchResult = new HashMap<Vendor, HashMap>();
    }

    @Override
    public HashMap<Vendor, HashMap> search(String keyword) {

        Vendor[] vendors = Vendor.values();
        MetaSearchWorker[] workers = new MetaSearchWorker[Vendor.size];
        
        for (int i = 0; i < workers.length; ++i) {
             workers[i] = factory.getMetaSearchWorker(vendors[i], latch);
            //workers[i].start();
        }

        try {
            //count가 0이 될때까지 기다린다. 즉, worker 스레드들이 작업을 다 완료하기를 기다려줌.
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(MetaSearchFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < workers.length; ++i) {
            this.metaSearchResult.put(workers[i].getVendor(), workers[i].getSearchResult());
        }

        return metaSearchResult;
    }

}
