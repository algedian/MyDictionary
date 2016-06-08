package daum.searchImpl;

import daum.search.DaumSearchResult;
import daum.search.DaumSearcher;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import metasearch.common.SearchCategory;

/**
 * Searcher class for Daum web documents search. search 50 results.
 * 
 * @author kyeonghee
 */
@Stateless
@LocalBean
public class DaumWebSearcher extends DaumSearcher {

    private String output = "json"; //output type
    private String result; //number of result in one page. 10(default), 1(min), 20(max)
    private String pageno; //page number of search result. 1 to 3 are available.
    //private String advance; //advanced search. y: use, n: not use

    public DaumWebSearcher() {

    }

    @Override
    public HashMap search(String keyword) {
        System.out.println("DaumWebSearcher.search");

        url += "web?apikey=" + apiKey + "" + "&output=" + output + "" + "&pageno=" + pageno + "&result=" + result;

        daumSearchResult = new DaumSearchResult(SearchCategory.WEB.getName());

        daumSearchResult.parseJson(requestToDaum(keyword));

        HashMap map = daumSearchResult.getResultHashMap();

        System.out.println("DaumWebSearcher returns");
        return map;
    }
    
    /**
     *search web contents in daum using keyword and other string ('sort' | 'pageno')
     *
     * @param keyword
     * @param string
     * @return
     */
    public HashMap search(String keyword, String string) {
        System.out.println("DaumWebSearcher.search + string value");

        HashMap map = daumSearchResult.getResultHashMap();

        if (string.equals("1") || string.equals("2") || string.equals("3")) {
            url += "web?apikey={" + apiKey + "}&q=" + keyword + "&pageno=" + string + "&pageno=" + pageno + "&result=" + result;
        } else{
            System.err.println("wrong parameter: " + string);
        }

        daumSearchResult = new DaumSearchResult(SearchCategory.WEB.getName());
        daumSearchResult.parseJson(requestToDaum(keyword));

        System.out.println("DaumWebSearcher returns");
        return map;
    }
}
