package daum.searchImpl;

import daum.search.DaumSearchResult;
import daum.search.DaumSearcher;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import metasearch.common.SearchCategory;

/**
 * Searcher class for Daum video search. search 20 results.
 *
 * @author kyeonghee
 */
@Stateless
@LocalBean
public class DaumVideoSearcher extends DaumSearcher {

    private String sort = "accuracy"; //accuracy(accuracy) : default | recency(latest)

    public DaumVideoSearcher() {

    }

    @Override
    public HashMap search(String keyword) {
        System.out.println("DaumVideoSearcher.search");

        url += "vclip?apikey=" + apiKey + "&output=" + output + "&pageno=" + pageno + "&result=" + result + "&sort=" + sort;

        daumSearchResult = new DaumSearchResult(SearchCategory.VIDEO.getName());

        daumSearchResult.parseJson(requestToDaum(keyword));

        HashMap map = daumSearchResult.getResultHashMap();

        System.out.println("DaumVideoSearcher returns");
        return map;
    }
}
