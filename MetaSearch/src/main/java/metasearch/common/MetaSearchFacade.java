package metasearch.common;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import javax.ejb.Stateless;
import metasearch.enums.Vendor;

/**
 *
 * @author Yewon Kim - Administrator
 */
public interface MetaSearchFacade {
    public HashMap<Vendor, HashMap>  search(String keyword);    
}
