package naver.search;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Abstract searcher class for Naver api. Each searchers of specific categories
 * extends this class.
 *
 * @author Yewon Kim
 */
public abstract class NaverSearcher {

    protected String clientId = "LMjMuDEq1wjSCvRgP9KC";
    protected String clientSecret = "hhMIIS1Ci5";

    protected String query; //required, search query encoding to UTF-8.
    protected int display = 20; //selected, 10(default),100(maximum) set number of displayed result
    protected int start = 1; //selected ,1(default), 1000(maximum). search starting point.
    protected String url = "https://openapi.naver.com/v1/search/"; //base url about request

    protected NaverSearchResult result = null;

    /**
     * search and return the search results with keyword
     *
     * @param keyword
     * @return HashMap: search result container map
     */
    public abstract HashMap search(String keyword);

    /**
     * create and send http get request to naver. url need to be set to each
     * category before calling this function.
     *
     * @param keyword
     * @return String: xml string
     */
    protected String requestToNaver(String keyword) {
        System.out.println("NaverSearcher.requestToNaver");

        HttpClient client = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet(url + "&query=" + keyword);

        get.setHeader("Host", "openapi.naver.com");
        get.setHeader("User-Agent", "curl/7.43.0");
        get.setHeader("Accept", "*/*");
        get.setHeader("Content-Type", "application/xml");
        get.setHeader("X-Naver-Client-Id", clientId);
        get.setHeader("X-Naver-Client-Secret", clientSecret);

        HttpEntity entity;
        String xmlString = "";

        try {
            HttpResponse response = client.execute(get);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.err.println("GET> Unexpected status code: " + response.getStatusLine().getStatusCode());
                return null;
            }

            entity = response.getEntity();

            xmlString = EntityUtils.toString(entity);

        } catch (IOException | ParseException ex) {
            Logger.getLogger(NaverSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xmlString;
    }

}
