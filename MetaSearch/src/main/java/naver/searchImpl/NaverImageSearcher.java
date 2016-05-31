/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naver.searchImpl;

import java.util.HashMap;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import metasearch.common.SearchCategory;
import naver.search.NaverSearchResult;
import naver.search.NaverSearcher;

/**
 * - Searcher class for Naver Image search.
 * 
 * @author Yewon Kim
 */
@Stateless
@LocalBean
public class NaverImageSearcher extends NaverSearcher {
    
    private String sort = "sim";//선택, sim(기본값), date   정렬 옵션: sim (유사도순), date (날짜순)
    private String filter = "all";//선택, 	all (기본값), 사이즈 필터 옵션: all(전체), large(큰 사이즈), medium(중간 사이즈), small(작은 사이즈)

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
