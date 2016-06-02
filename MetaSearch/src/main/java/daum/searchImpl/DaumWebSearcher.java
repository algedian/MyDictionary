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
 *
 * @author user
 */
@Stateless
@LocalBean
public class DaumWebSearcher extends DaumSearcher{
    private String output = "json";
    //private String result; //한 페이지에 출력될 결과수
    //private String pageno; //검색 결과 페이지 번호
    //private String advance; //상세 검색 기능 사용 여부
    
    public DaumWebSearcher(){
    
    }
    
    @Override
    public HashMap search(String keyword) {
        System.out.println("DaumWebSearcher.search");
        
        url += "web?apikey=" + apiKey + "" + "&output=" + output + "";
        
        daumSearchResult = new DaumSearchResult(SearchCategory.WEB.getName());
        
        daumSearchResult.parseJson(requestToDaum(keyword));
                
        HashMap map = daumSearchResult.getResultHashMap();
        
        System.out.println("DaumWebSearcher returns");
        return map; 
    }    
}
