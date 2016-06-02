/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daum.searchImpl;

import daum.search.DaumSearchResult;
import daum.search.DaumSearcher;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import metasearch.common.SearchCategory;

/**
 * - Searcher class for Daum Blog search.
 *
 * @author user
 */
@Stateless
@LocalBean
public class DaumBlogSearcher extends DaumSearcher{
    private String sort = "date"; //date(최신순) | accu(정확도순)
    private String output = "json";
    private String result = "10"; //한 페이지에 출력될 결과수
    private String pageno = "1"; //검색 결과 페이지 번호
    private String advance = "n"; //상세 검색 기능 사용 여부
    
    public DaumBlogSearcher(){
        
    }

    @Override
    public HashMap search(String keyword) { //sort는 기본값(date)으로
        System.out.println("DaumBlogSearcher.search");
        
        url += "blog?apikey=" + apiKey + "" + "&output=" + output + "";// + "&pageno=" + pageno + "&result=" + result;
        
        daumSearchResult = new DaumSearchResult(SearchCategory.BLOG.getName());
        
        daumSearchResult.parseJson(requestToDaum(keyword));
                
        HashMap map = daumSearchResult.getResultHashMap();
        
        System.out.println("DaumBlogSearcher returns");
        return map;        
    }
    
    public HashMap search(String keyword, String sort) { //sort를 반영한 함수는 없어도 되나?
        System.out.println("DaumBlogSearcher.search + sort value");
        
        url += "blog?apikey={" + apiKey + "}&q=" + keyword + "&sort=" + sort;
        //result나 pageno는 뭐지
        
        daumSearchResult = new DaumSearchResult(SearchCategory.BLOG.getName());
        
        daumSearchResult.parseJson(requestToDaum(keyword));
                
        HashMap map = daumSearchResult.getResultHashMap();
        
        System.out.println("DaumBlogSearcher returns");
        return map;              
    }
    
    
}
