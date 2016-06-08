package naver.searchImpl;

import java.util.HashMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import metasearch.common.SearchCategory;
import naver.search.NaverSearcher;
import naver.search.NaverSearchResult;

/**
 * - Searcher class for Naver Blog search.
 *
 * @author Yewon Kim
 */
@Stateless
@LocalBean
public class NaverBlogSearcher extends NaverSearcher {

    private String sort = "sim"; //selected. sim(default), date. sort option: sim (similarity), date (order of date)

    public NaverBlogSearcher() {

    }

    @Override
    public HashMap search(String keyword) {
        System.out.println("NaverBlogSearcher.search");

        url += "blog.xml?display=" + display + "&start=" + start + "&sort=" + sort;

        result = new NaverSearchResult(SearchCategory.BLOG.getName());

        result.parseXml(requestToNaver(keyword));

        HashMap map = result.getResultHashMap();

        System.out.println("NaverBlogSearcher returns");
        return map;
    }

}
