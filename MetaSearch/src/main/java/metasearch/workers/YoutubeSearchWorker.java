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

    /**
     * it is contructor that is delivered latch, keyword, category and designate
     * them.
     *
     * @param latch
     * @param keyword
     * @param category
     */
    @Override
    public void initialize(CountDownLatch latch, String keyword, String category) {
        super.initialize(latch, keyword, category);
        this.setVendor(Vendor.YOUTUBE);
    }

    /**
     * First, get certain category's searcher from factory. Then call searcher's
     * search function. Finally return the results.
     *
     * @param keyword
     * @param category
     * @return Hashmap: search result
     */
    @Override
    public HashMap doSearch(String keyword, String category) {

        System.out.println("YoutubeSearchWorker.doSearch");

        searcher = new YoutubeSearcher();

        HashMap map = searcher.search(keyword);

        System.out.println("YoutubeSearchWorker returns");

        return map;

    }

}
