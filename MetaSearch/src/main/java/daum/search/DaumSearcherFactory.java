package daum.search;

/**
 * Factory interface for DaumSearchers
 *
 * @author kyeonghee
 */
public interface DaumSearcherFactory {

    /**
     * returns specific DaumSearcher class by category.
     *
     * @param category
     * @return DaumSearcher: specific searcher object (blog, image, video, web)
     */
    public DaumSearcher getDaumSearcher(String category);
}
