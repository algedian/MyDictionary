package naver.search;

/**
 * Factory interface for NaverSearchers
 *
 * @author Yewon Kim
 */
public interface NaverSearcherFactory {
   
    /**
     * returns specific NaverSearcher class by category.
     *
     * @param category
     * @return NaverSearcher: specific searcher object
     */
    public NaverSearcher getNaverSearcher(String category);
}
