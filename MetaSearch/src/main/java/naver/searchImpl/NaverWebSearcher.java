package naver.searchImpl;

import java.util.HashMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import metasearch.common.SearchCategory;
import naver.search.NaverSearchResult;
import naver.search.NaverSearcher;

/**
 * Searcher class for Naver Web search.
 *
 * @author Yewon Kim
 */
@Stateless
@LocalBean
public class NaverWebSearcher extends NaverSearcher {

    public NaverWebSearcher() {

    }

    @Override
    public HashMap search(String keyword) {
        System.out.println("NaverWebSearcher.search");

        url += "webkr.xml?display=" + display + "&start=" + start;

        result = new NaverSearchResult(SearchCategory.WEB.getName());

        result.parseXml(requestToNaver(keyword));

        return result.getResultHashMap();
    }
}
