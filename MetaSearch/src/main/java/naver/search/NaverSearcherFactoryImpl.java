package naver.search;

import metasearch.common.SearchCategory;
import naver.searchImpl.NaverBlogSearcher;
import naver.searchImpl.NaverEncyclopediaSearcher;
import naver.searchImpl.NaverImageSearcher;
import naver.searchImpl.NaverNewsSearcher;
import naver.searchImpl.NaverWebSearcher;

/**
 * implementation of NaverSearcherFactory
 *
 * @author Yewon Kim
 */
public class NaverSearcherFactoryImpl implements NaverSearcherFactory {

    @Override
    public NaverSearcher getNaverSearcher(String category) {
        System.out.println("NaverSearcherFactory.getNaverSearcher");

        if (category.equals(SearchCategory.BLOG.getName())) {
            return new NaverBlogSearcher();
        } else if (category.equals(SearchCategory.IMAGE.getName())) {
            return new NaverImageSearcher();
        } else if (category.equals(SearchCategory.WEB.getName())) {
            return new NaverWebSearcher();
        } else if (category.equals(SearchCategory.NEWS.getName())) {
            return new NaverNewsSearcher();
        } else if (category.equals(SearchCategory.ENCYCLOPEDIA.getName())) {
            return new NaverEncyclopediaSearcher();
        } else {
            System.out.println("No such search category in NAVER:" + category);
            return null;
        }
    }
}
