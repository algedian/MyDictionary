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
 * 다른 api로의 검색을 invoke 시키고, 그 결과값들을 모아 가공하여 리턴하는 인터페이스의 구현체
 *
 * @author Yewon Kim
 */
@Stateless
public class MetaSearchFacadeImpl implements MetaSearchFacade {

    @EJB
    private MetaSearchWorkerFactory factory;

    private ArrayList<HashMap> resultList;//최종적으로 MetaSearch 쪽으로 리턴할 결과 컨테이너 hashmap
    private CountDownLatch latch;//각 thread 작업들이 모두 끝난 후 취합을 위해 latch counter가 필요함.

    public MetaSearchFacadeImpl() {
        super();
        resultList = new ArrayList<>();
    }

    @Override
    public ArrayList<HashMap> search(String keyword, String category) {
        System.out.println("MetaSearchFacadeImpl.search");

        //해당 카테고리에 대한 검색을 지원하는 vendor들 얻어온다.
        Vendor[] vendors = factory.getVendors(category);

        //검색 제공자의 수 만큼 counter를 설정한다. Search worker num == api vendors 이기 때문.
        latch = new CountDownLatch(vendors.length);

        //해당 vendor 수 만큼을 수용하는 worker array 생성.
        MetaSearchWorker[] workers = new MetaSearchWorker[vendors.length];

        int countGosts = 0;
        //각 worker들 마다..
        for (int i = 0; i < workers.length; ++i) {
            //vendor에 따른 worker 인스턴스를 얻어온다.
            workers[i] = factory.getMetaSearchWorker(vendors[i].getName(), latch, keyword, category);

            //해당 worker가 존재하고, 잘 받아졌다면...
            if (workers[i] != null) {
                System.out.println("start worker[" + i + "]");

                //각 worker들을 동작시킨다.
                workers[i].start();
            } else {
                countGosts++;//null인 worker 수 만큼 latch가 카운트다운이 안될테니 세어주자
            }
        }

        try {
            System.out.println("wait for workers");

            for (int i = 0; i < countGosts; ++i) {
                latch.countDown();//null인 worker 수 만큼 latch를 카운트다운 시킨다.
            }

            //count가 0이 될때까지 기다린다. 즉, worker 스레드들이 작업을 다 완료하기를 기다려줌.
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
