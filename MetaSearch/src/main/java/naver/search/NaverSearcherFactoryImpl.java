/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naver.search;

import javax.ejb.Stateless;
import metasearch.common.SearchCategory;
import naver.searchImpl.NaverBlogSearcher;

/**
 *
 * @author Yewon Kim - Administrator
 */
public class NaverSearcherFactoryImpl implements NaverSearcherFactory {

    @Override
    public NaverSearcher getNaverSearcher(String category) {
        System.out.println("NaverSearcherFactory.getNaverSearcher");
        if (category.equals(SearchCategory.BLOG.getName())) {
            return new NaverBlogSearcher();
        } else if (category.equals(SearchCategory.IMAGE.getName())) {
            return null;
        } else if (category.equals(SearchCategory.WEB.getName())) {
            return null;
        } else if (category.equals(SearchCategory.NEWS.getName())) {
            return null;
        } else if (category.equals(SearchCategory.ENCYCLOPEDIA.getName())) {
            return null;
        } else {
            System.out.println("No such search category in NAVER:" + category);
            return null;
        }
    }
}
