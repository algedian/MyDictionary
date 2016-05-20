/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naver.blog;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import naver._general.NaverIngretedSearchResult;
import naver._general.NaverSearch;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class NaverBlogSearch extends NaverSearch {
    
    private String sort;//선택,sim(기본값), date   정렬 옵션: sim (유사도순), date (날짜순)
    
    @Override
    public NaverIngretedSearchResult search(String keyword) {
        //검색 결과 받아오고 가공하고 리턴!
        return null;
    }
}
