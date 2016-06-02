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
 * - Searcher class for Daum Image search.
 * 
 * @author user
 */
@Stateless
@LocalBean
public class DaumImageSearcher extends DaumSearcher{
    private String sort = ""; //기본값 없음. date(날짜순) | accu(추천순)
    private String output = "json";
    //private String result; //한 페이지에 출력될 결과수
    //private String pageno; //검색 결과 페이지 번호
    //private String advance; //상세 검색 기능 사용 여부
        
    public DaumImageSearcher(){
        
    }
    
    @Override
    public HashMap search(String keyword) {
        System.out.println("DaumImageSearcher.search");
        
        url += "image?apikey=" + apiKey + "" + "&output=" + output + "";
        
        daumSearchResult = new DaumSearchResult(SearchCategory.IMAGE.getName());
        
        daumSearchResult.parseJson(requestToDaum(keyword));
                
        HashMap map = daumSearchResult.getResultHashMap();
        
        System.out.println("DaumImageSearcher returns");
        return map;  
    }
    
}
