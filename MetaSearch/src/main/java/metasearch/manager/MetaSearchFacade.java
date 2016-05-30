package metasearch.manager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 다른 api로의 검색을 invoke 시키고, 그 결과값들을 모아 가공하여 리턴하는 인터페이스
 * 
 * @author Yewon Kim
 */
public interface MetaSearchFacade {
    public ArrayList<HashMap>  search(String keyword, String category);    
}
