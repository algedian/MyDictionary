package daum.searchImpl;

import daum.search.DaumSearchResult;
import daum.search.DaumSearcher;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import metasearch.common.SearchCategory;

/**
 * Searcher class for Daum web documents search. search 20 results.
 *
 * @author kyeonghee
 */
@Stateless
@LocalBean
public class DaumWebSearcher extends DaumSearcher {

    private String advance = "n"; //advanced search. y: use, n: not use

    public DaumWebSearcher() {

    }

    @Override
    public HashMap search(String keyword) {
        System.out.println("DaumWebSearcher.search");

        url += "web?apikey=" + apiKey + "&output=" + output + "" + "&pageno=" + pageno + "&result=" + result + "&advance=" + advance;

        daumSearchResult = new DaumSearchResult(SearchCategory.WEB.getName());

        daumSearchResult.parseJson(requestToDaum(keyword));

        HashMap map = daumSearchResult.getResultHashMap();

        System.out.println("DaumWebSearcher returns");
        return map;
    }
}
