/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * 다음 검색에 대한 추상클래스. 각 카테고리에 대한 Search 클래스들이 본 클래스를 상속받아 구현함.
 *  - Abstract searcher class for Daum api.
 *  - Each searchers of specific categories extends this class.
 * 
 * @author user
 */
public abstract class DaumSearcher {
    protected String apiKey = "968f9849c4eaa0ee861f2540e94390f8"; 

    protected String query;//필수,검색을 원하는 문자열로서 UTF-8로 인코딩한다.
    protected int display = 10;//선택,10(기본값),100(최대)    검색 결과 출력 건수 지정
    protected int start = 1;//선택,1(기본값), 1000(최대)   검색 시작 위치로 최대 1000까지 가능.
    protected String url = "https://apis.daum.net/search/";//요청에 대한 기본 base url
    
    protected DaumSearchResult daumSearchResult = null;

    /**
     *
     * search and return the search results with keyword
     *
     * @param keyword
     * @return HashMap: search result container map
     */
    //-
    public abstract HashMap search(String keyword);

    /**
     *
     * create and send http get request to naver. url need to be set to each
     * category before calling this function.
     *
     * @param keyword
     * @return String: xml string
     */
    //-
    protected String requestToDaum(String keyword) {
        System.out.println("DaumSearcher.requestToDaum");

        HttpClient client = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet(url + "&q=" + keyword);

        //요청 예시가 없어!?
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
