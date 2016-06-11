package metasearch.workers;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import javax.ejb.Stateless;

import metasearch.common.Vendor;
import youtube.search.YoutubeSearcher;

/**
 * Youtube search worker class
 *
 * @author Aziz
 */
@Stateless
public class YoutubeSearchWorker extends MetaSearchWorker {

    YoutubeSearcher searcher;

    @Override
    public void initialize(CountDownLatch latch, String keyword, String category) {
        super.initialize(latch, keyword, category);
        this.setVendor(Vendor.YOUTUBE);
    }

    public HashMap doSearch(String keyword, String category) {

        System.out.println("YoutubeSearchWorker.doSearch");

        searcher = new YoutubeSearcher();

        HashMap map = searcher.search(keyword);

        System.out.println("YoutubeSearchWorker returns");

        return map;

    }

}