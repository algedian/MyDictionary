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
 * - 네이버 검색에 대한 추상클래스. 각 카테고리에 대한 Search 클래스들이 본 클래스를 상속받아 구현함.
 *  - Abstract searcher class for Naver api.
 *  - Each searchers of specific categories extends this class.
 *
 * @author Yewon Kim
 */
public abstract class NaverSearcher {

    protected String clientId = "LMjMuDEq1wjSCvRgP9KC";
    protected String clientSecret = "hhMIIS1Ci5";

    protected String query;//필수,검색을 원하는 문자열로서 UTF-8로 인코딩한다.
    protected int display = 10;//선택,10(기본값),100(최대)    검색 결과 출력 건수 지정
    protected int start = 1;//선택,1(기본값), 1000(최대)   검색 시작 위치로 최대 1000까지 가능.
    protected String url = "https://openapi.naver.com/v1/search/";//요청에 대한 기본 base url
    
    protected NaverSearchResult result = null;

    /**
     *
     * search and return the search results with keyword
     *
     * @param keyword
     * @return HashMap: search result container map
     */
    public abstract HashMap search(String keyword);

    /**
     *
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
