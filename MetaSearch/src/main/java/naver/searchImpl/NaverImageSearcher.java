package naver.searchImpl;

import java.util.HashMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import metasearch.common.SearchCategory;
import naver.search.NaverSearchResult;
import naver.search.NaverSearcher;

/**
 * Searcher class for Naver Image search.
 *
 * @author Yewon Kim
 */
@Stateless
@LocalBean
public class NaverImageSearcher extends NaverSearcher {

    private String sort = "sim"; //selected. sim(default), date. sort option: sim (similarity), date (order of date)
    private String filter = "all"; //selected. all(default). size filter option: all, large, medium, small 

    public NaverImageSearcher() {

    }

    @Override
    public HashMap search(String keyword) {
        System.out.println("NaverImageSearcher.search");

        url += "image.xml?display=" + display + "&start=" + start + "&sort=" + sort + "&filter=" + filter;

        result = new NaverSearchResult(SearchCategory.IMAGE.getName());

        result.parseXml(requestToNaver(keyword));

        return result.getResultHashMap();
    }
}
