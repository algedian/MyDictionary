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

    private String sort = "sim";//선택,sim(기본값), date   정렬 옵션: sim (유사도순), date (날짜순)

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
