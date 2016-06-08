package naver.search;

/**
 * Factory interface for NaverSearchers
 *
 * @author Yewon Kim
 */
public interface NaverSearcherFactory {

    public NaverSearcher getNaverSearcher(String category);
}
