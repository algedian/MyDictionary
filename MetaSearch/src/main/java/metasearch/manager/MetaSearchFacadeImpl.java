package metasearch.manager;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import metasearch.workers.MetaSearchWorker;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metasearch.common.Vendor;

/**
 *
 * @author Yewon Kim - Administrator
 */
@Stateless
public class MetaSearchFacadeImpl implements MetaSearchFacade {

    private HashMap<Vendor, HashMap> metaSearchResult;
    private final CountDownLatch latch;

    @EJB
    private MetaSearchWorkerFactory factory;

    public MetaSearchFacadeImpl() {
        super();
        this.latch = new CountDownLatch(Vendor.size);
        metaSearchResult = new HashMap<Vendor, HashMap>();
    }

    @Override
    public HashMap<Vendor, HashMap> search(String keyword, String category) {
        System.out.println("MetaSearchFacadeImpl.search");

        Vendor[] vendors = factory.getVendors(category);//Vendor.values();
        MetaSearchWorker[] workers = new MetaSearchWorker[vendors.length];

        for (int i = 0; i < workers.length; ++i) {
            workers[i] = factory.getMetaSearchWorker(vendors[i].getName(), latch, keyword, category);
            if (workers[i] != null) {
                System.out.println("start worker[" + i + "]");
                workers[i].start();
            }
        }

        try {
            //count가 0이 될때까지 기다린다. 즉, worker 스레드들이 작업을 다 완료하기를 기다려줌.
            System.out.println("wait for workers");
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(MetaSearchFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*for (int i = 0; i < workers.length; ++i) {
         this.metaSearchResult.put(workers[i].getVendor(), workers[i].getSearchResult());
         }*/
        return metaSearchResult;
    }

}
