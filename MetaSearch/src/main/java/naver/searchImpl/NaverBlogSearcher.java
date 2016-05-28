/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naver.searchImpl;

import java.io.IOException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import naver.search.NaverSearcher;
import naver.search.NaverSearchResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class NaverBlogSearcher extends NaverSearcher {

    private String sort = "sim";//선택,sim(기본값), date   정렬 옵션: sim (유사도순), date (날짜순)

    public NaverBlogSearcher() {
        url += "display=" + display + "&start=" + start + "&sort=" + sort;
    }

    @Override
    public NaverSearchResult search(String keyword) {

        System.out.println("NaverBlogSearcher.search");

        HttpClient client = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet(url + "&query=" + keyword);

        get.setHeader("Host", "openapi.naver.com");
        get.setHeader("User-Agent", "curl/7.43.0");
        get.setHeader("Accept", "*/*");
        get.setHeader("Content-Type", "application/xml");
        get.setHeader("X-Naver-Client-Id", clientId);
        get.setHeader("X-Naver-Client-Secret", clientSecret);

        HttpEntity entity;

        try {
            HttpResponse response = client.execute(get);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.err.println("GET> Unexpected status code: " + response.getStatusLine().getStatusCode());
                return null;
            }

            entity = response.getEntity();

            String data = EntityUtils.toString(entity);

            System.out.println(data);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
