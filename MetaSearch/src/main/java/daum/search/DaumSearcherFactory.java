package daum.search;

/**
 * Factory interface for DaumSearchers
 *
 * @author kyeonghee
 */
public interface DaumSearcherFactory {

    public DaumSearcher getDaumSearcher(String category);
}
