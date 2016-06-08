package daum.search;

import daum.searchImpl.DaumBlogSearcher;
import daum.searchImpl.DaumImageSearcher;
import daum.searchImpl.DaumVideoSearcher;
import daum.searchImpl.DaumWebSearcher;
import metasearch.common.SearchCategory;

/**
 * implementation of DaumSearcherFactory
 *
 * @author kyeonghee
 */
public class DaumSearcherFactoryImpl implements DaumSearcherFactory {

    /**
     * returns specific DaumSearcher class by category.
     *
     * @param category
     * @return DaumSearcher: specific searcher object (blog, image, video, web)
     */
    @Override
    public DaumSearcher getDaumSearcher(String category) {
        System.out.println("DaumSearcherFactory.getDaumSearcher");

        if (category.equals(SearchCategory.BLOG.getName())) {
            return new DaumBlogSearcher();
        } else if (category.equals(SearchCategory.IMAGE.getName())) {
            return new DaumImageSearcher();
        } else if (category.equals(SearchCategory.WEB.getName())) {
            return new DaumWebSearcher();
        } else if (category.equals(SearchCategory.VIDEO.getName())) {
            return new DaumVideoSearcher();
        } else {
            System.out.println("No such search category in DAUM:" + category);
            return null;
        }
    }
}
