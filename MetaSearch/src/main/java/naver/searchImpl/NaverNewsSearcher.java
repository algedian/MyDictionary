package naver.searchImpl;

import java.util.HashMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import metasearch.common.SearchCategory;
import naver.search.NaverSearchResult;
import naver.search.NaverSearcher;

/**
 * Searcher class for Naver News search.
 *
 * @author Yewon Kim
 */
@Stateless
@LocalBean
public class NaverNewsSearcher extends NaverSearcher {

    private String sort = "sim"; //selected. sim(default), date. sort option: sim (similarity), date (order of date)

    public NaverNewsSearcher() {

    }

    @Override
    public HashMap search(String keyword) {
        System.out.println("NaverNewsSearcher.search");

        url += "news.xml?display=" + display + "&start=" + start + "&sort=" + sort;

        result = new NaverSearchResult(SearchCategory.NEWS.getName());

        result.parseXml(requestToNaver(keyword));

        return result.getResultHashMap();
    }
}
