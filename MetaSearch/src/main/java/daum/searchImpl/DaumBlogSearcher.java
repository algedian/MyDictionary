package daum.searchImpl;

import daum.search.DaumSearchResult;
import daum.search.DaumSearcher;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import metasearch.common.SearchCategory;

/**
 * Searcher class for Daum Blog search. search 50 results.
 *
 * @author kyeonghee
 */
@Stateless
@LocalBean
public class DaumBlogSearcher extends DaumSearcher {

    private String sort = "date"; //date(latest) | accu(accuracy)
    private String output = "json"; //output type
    private String result = "20"; //number of result in one page. 10(default), 1(min), 20(max)
    private String pageno = "1"; //page number of search result. 1 to 3 are available.
    //private String advance = "n"; //advanced search. y: use, n: not use

    public DaumBlogSearcher() {

    }

    /**
     * search blog contents in daum using keyword
     *
     * @param keyword
     * @return
     */
    @Override
    public HashMap search(String keyword) { //sort는 default(date)
        System.out.println("DaumBlogSearcher.search");

        url += "blog?apikey=" + apiKey + "" + "&output=" + output + "" + "&pageno=" + pageno + "&result=" + result;

        daumSearchResult = new DaumSearchResult(SearchCategory.BLOG.getName());

        daumSearchResult.parseJson(requestToDaum(keyword));

        HashMap map = daumSearchResult.getResultHashMap();

        System.out.println("DaumBlogSearcher returns");
        return map;
    }

    /**
     *search blog contents in daum using keyword and other string ('sort' | 'pageno')
     *
     * @param keyword
     * @param string
     * @return
     */
    public HashMap search(String keyword, String string) {
        System.out.println("DaumBlogSearcher.search + string value");

        HashMap map = daumSearchResult.getResultHashMap();

        if (string.equalsIgnoreCase("date") || string.equalsIgnoreCase("accu")) {
            url += "blog?apikey={" + apiKey + "}&q=" + keyword + "&sort=" + string + "&pageno=" + pageno + "&result=" + result;
        } else if (string.equals("1") || string.equals("2") || string.equals("3")) {
            url += "blog?apikey={" + apiKey + "}&q=" + keyword + "&pageno=" + string + "&pageno=" + pageno + "&result=" + result;
        } else{
            System.err.println("wrong parameter: " + string);
        }

        daumSearchResult = new DaumSearchResult(SearchCategory.BLOG.getName());
        daumSearchResult.parseJson(requestToDaum(keyword));

        System.out.println("DaumBlogSearcher returns");
        return map;
    }
}
