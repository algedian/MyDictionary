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
 * - Searcher class for Naver Encyclopedia search.
 *
 * @author Yewon Kim
 */
@Stateless
@LocalBean
public class NaverEncyclopediaSearcher extends NaverSearcher {

    public NaverEncyclopediaSearcher() {

    }

    @Override
    public HashMap search(String keyword) {
        System.out.println("NaverEncyclopediaSearcher.search");

        url += "encyc.xml?display=" + display + "&start=" + start;

        result = new NaverSearchResult(SearchCategory.ENCYCLOPEDIA.getName());
        
        result.parseXml(requestToNaver(keyword));

        return result.getResultHashMap();
    }
}
