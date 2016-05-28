package metasearch.manager;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import javax.ejb.Stateless;
import metasearch.common.Vendor;

/**
 *
 * @author Yewon Kim - Administrator
 */
public interface MetaSearchFacade {
    public HashMap<Vendor, HashMap>  search(String keyword, String category);    
}
