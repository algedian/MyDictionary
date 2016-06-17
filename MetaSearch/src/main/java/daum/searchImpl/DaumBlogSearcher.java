package daum.searchImpl;

import daum.search.DaumSearchResult;
import daum.search.DaumSearcher;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import metasearch.common.SearchCategory;

/**
 * Searcher class for Daum Blog search. search 20 results.
 *
 * @author kyeonghee
 */
@Stateless
@LocalBean
public class DaumBlogSearcher extends DaumSearcher {

    private String sort = "accu"; //date(latest): default | accu(accuracy)
    private String advance = "n"; //advanced search. y: use, n: not use

    public DaumBlogSearcher() {

    }

    @Override
    public HashMap search(String keyword) {
        System.out.println("DaumBlogSearcher.search");

        url += "blog?apikey=" + apiKey + "&output=" + output + "&pageno=" + pageno + "&result=" + result + "&sort=" + sort + "&advance=" + advance;
        //System.out.println("url: " + url);

        daumSearchResult = new DaumSearchResult(SearchCategory.BLOG.getName());

        daumSearchResult.parseJson(requestToDaum(keyword));

        HashMap map = daumSearchResult.getResultHashMap();

        System.out.println("DaumBlogSearcher returns");
        return map;
    }
}
