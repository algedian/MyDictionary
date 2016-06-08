package metasearch.manager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Inteface that invokes other search API and combines results and returns.
 *
 * @author Yewon Kim
 */
public interface MetaSearchFacade {

    public ArrayList<HashMap> search(String keyword, String category);
}
