package metasearch.manager;

import java.util.ArrayList;
import java.util.HashMap;
import metasearch.workers.MetaSearchWorker;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import metasearch.common.Vendor;

/**
 * Implementation about MetaSearchFacade
 *
 * @author Yewon Kim
 */
@Stateless
public class MetaSearchFacadeImpl implements MetaSearchFacade {

    @EJB
    private MetaSearchWorkerFactory factory;

    private ArrayList<HashMap> resultList; //최종적으로 MetaSearch 쪽으로 리턴할 결과 컨테이너 hashmap
    private CountDownLatch latch; //It is need to have a latch counter that collect all thread's finished job

    public MetaSearchFacadeImpl() {
        super();
        resultList = new ArrayList<>();
    }

    @Override
    public ArrayList<HashMap> search(String keyword, String category) {
        System.out.println("MetaSearchFacadeImpl.search");

        resultList.clear();

        //get vendor that supports certain category.
        Vendor[] vendors = factory.getVendors(category);

        //set the number of counter as much as the number of vendors. because of Search worker num == api vendors num.
        latch = new CountDownLatch(vendors.length);

        //create worker array that accepts workers(# or certain vendor) 
        MetaSearchWorker[] workers = new MetaSearchWorker[vendors.length];

        int countGosts = 0;
        //each worker..
        for (int i = 0; i < workers.length; ++i) {
            //get worker instance about vendor
            workers[i] = factory.getMetaSearchWorker(vendors[i].getName(), latch, keyword, category);

            //if there exists the worker and successes to get instance...
            if (workers[i] != null) {
                System.out.println("start worker[" + i + "]");

                //start each workers.
                workers[i].start();
            } else {
                countGosts++;//if not, count latch counter as much as the number of null workers
            }
        }

        try {
            System.out.println("wait for workers");

            for (int i = 0; i < countGosts; ++i) {
                latch.countDown();//countdown the latch counter as much as the number of null workers. 
            }

            //wait until counter become 0. That is, wait until worker threads complete their job.
            latch.await();

            System.out.println("worker finished");

        } catch (InterruptedException ex) {
            Logger.getLogger(MetaSearchFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < workers.length; ++i) {
            if (workers[i] != null) {
                this.resultList.add(workers[i].getSearchResult());
            }
        }

        System.out.println("returns resultList");
        return resultList;
    }
}
