package metasearch.manager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Inteface that invokes other search API and combines results and returns.
 *
 * @author Yewon Kim
 */
public interface MetaSearchFacade {

    /**
     * It returns about search result that 'MetaSearchWorker' using keyword, category
     * 
     * @param keyword
     * @param category
     * @return ArrayList<HashMap> - success: search result / fail: null ArrayList
     */
    public ArrayList<HashMap> search(String keyword, String category);
}
