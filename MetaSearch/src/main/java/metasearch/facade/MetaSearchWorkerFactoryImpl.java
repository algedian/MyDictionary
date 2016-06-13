package metasearch.manager;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import javax.ejb.Stateless;
import metasearch.common.SearchCategory;
import metasearch.workers.MetaSearchWorker;
import metasearch.workers.NaverSearchWorker;
import metasearch.common.Vendor;
import metasearch.workers.DaumSearchWorker;
import metasearch.workers.YoutubeSearchWorker;

/**
 * Implementation of MetaSearchWorkerFactory
 *
 * @author Yewon Kim
 */
@Stateless
public class MetaSearchWorkerFactoryImpl implements MetaSearchWorkerFactory {

    @Override
    public MetaSearchWorker getMetaSearchWorker(String vendor, CountDownLatch latch, String keyword, String category) {
        System.out.println("MetaSearchWorkerFactoryImpl.getMetaSearchWorker");

        MetaSearchWorker worker = null;

        if (vendor.equals(Vendor.DAUM.getName())) {
            System.out.println("--returns DAUM");
            worker = new DaumSearchWorker();
            worker.initialize(latch, keyword, category);
        } else if (vendor.equals(Vendor.NAVER.getName())) {
            System.out.println("--returns NAVER");
            worker = new NaverSearchWorker();
            worker.initialize(latch, keyword, category);
        } else if (vendor.equals(Vendor.YOUTUBE.getName())) {
            System.out.println("--returns YOUTUBE");
            worker = new YoutubeSearchWorker();
            worker.initialize(latch, keyword, category);
        }
        return worker;
    }

    @Override
    public Vendor[] getVendors(String category) {
        System.out.println("MetaSearchWorkerFactoryImpl.getVendors");

        ArrayList<Vendor> vendors = new ArrayList<Vendor>();

        if (category.equals(SearchCategory.BLOG.getName())
                || category.equals(SearchCategory.IMAGE.getName())
                || category.equals(SearchCategory.WEB.getName())) {

            vendors.add(Vendor.DAUM);
            vendors.add(Vendor.NAVER);

        } else if (category.equals(SearchCategory.VIDEO.getName())) {

            vendors.add(Vendor.DAUM);
            vendors.add(Vendor.YOUTUBE);

        } else if (category.equals(SearchCategory.NEWS.getName())
                || category.equals(SearchCategory.ENCYCLOPEDIA.getName())) {

            vendors.add(Vendor.NAVER);
        } else {
            System.out.println("No such search category:" + category);
            return null;
        }

        Vendor[] vendorArr = new Vendor[vendors.size()];

        for (int i = 0; i < vendors.size(); ++i) {
            vendorArr[i] = vendors.get(i);
        }

        return vendorArr;
    }
}
