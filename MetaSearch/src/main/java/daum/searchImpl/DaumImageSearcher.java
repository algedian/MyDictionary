package daum.searchImpl;

import daum.search.DaumSearchResult;
import daum.search.DaumSearcher;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import metasearch.common.SearchCategory;

/**
 * Searcher class for Daum Image search. search 20 results.
 *
 * @author kyeonghee
 */
@Stateless
@LocalBean
public class DaumImageSearcher extends DaumSearcher {

    private String sort = "accu"; //no default, date(order of date) | accu(order of recommendation)
    private String advance = "n"; //advanced search. y: use, n: not use

    public DaumImageSearcher() {

    }

    @Override
    public HashMap search(String keyword) {
        System.out.println("DaumImageSearcher.search");

        url += "image?apikey=" + apiKey + "&output=" + output + "&pageno=" + pageno + "&result=" + result + "&sort=" + sort + "&advance=" + advance;

        daumSearchResult = new DaumSearchResult(SearchCategory.IMAGE.getName());

        daumSearchResult.parseJson(requestToDaum(keyword));

        HashMap map = daumSearchResult.getResultHashMap();

        System.out.println("DaumImageSearcher returns");
        return map;
    }
}
