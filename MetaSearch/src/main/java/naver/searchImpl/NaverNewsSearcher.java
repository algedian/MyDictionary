/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naver.searchImpl;

import java.util.HashMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import metasearch.common.SearchCategory;
import naver.search.NaverSearchResult;
import naver.search.NaverSearcher;

/**
 * - Searcher class for Naver News search.
 *
 * @author Yewon Kim
 */
@Stateless
@LocalBean
public class NaverNewsSearcher extends NaverSearcher {

    private String sort = "sim";//선택,sim(기본값), date   정렬 옵션: sim (유사도순), date (날짜순)

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
