package daum.search;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import naver.search.NaverSearcher;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Abstract searcher class for Daum search API. Each searchers of specific
 * categories extends this class.
 *
 * @author kyeonghee
 */
public abstract class DaumSearcher {

    protected String apiKey = "968f9849c4eaa0ee861f2540e94390f8";

    protected String query; //required, search query encoding to UTF-8.
    //protected int display = 10; //selected, 10(default),100(maximum) set number of displayed result //maybe not in daum api
    //protected int start = 1; //selected ,1(default), 1000(maximum). search starting point. //maybe not in daum api

    protected String url = "https://apis.daum.net/search/"; //base url about request

    protected DaumSearchResult daumSearchResult = null;

    /**
     * search and return the search results about keyword
     *
     * @param keyword
     * @return HashMap: search result container map
     */
    public abstract HashMap search(String keyword);

    /**
     * create and send http get request to daum. url need to be set to each
     * category before calling this function.
     *
     * @param keyword
     * @return String: json string
     */
    protected String requestToDaum(String keyword) {
        System.out.println("DaumSearcher.requestToDaum");

        HttpClient client = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet(url + "&q=" + keyword);

        get.setHeader("Host", "apis.daum.net");
        //get.setHeader("User-Agent", "curl/7.43.0");
        get.setHeader("Accept", "*/*");
        get.setHeader("Content-Type", "application/json");

        HttpEntity entity;
        String jsonString = "";

        try {
            HttpResponse response = client.execute(get);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.err.println("GET> Unexpected status code: " + response.getStatusLine().getStatusCode());
                return null;
            }

            entity = response.getEntity();

            jsonString = EntityUtils.toString(entity);

        } catch (IOException | ParseException ex) {
            Logger.getLogger(NaverSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonString;
    }
}
