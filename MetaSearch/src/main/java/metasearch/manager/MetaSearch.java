package metasearch.manager;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Class that receives about 'MetaSearch service' first request actually.
 *
 * @author Yewon Kim
 */
@Path("metasearch")
public class MetaSearch {

    @EJB//Object reference that has business logic about providing service
    MetaSearchFacade search;

    /**
     * Returns JSON string that is result about keyword and category
     * The string is encoded by UTF-8.
     *
     * @param keyword
     * @param category
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf8")
    public String getSearchResult(@QueryParam("q") String keyword, @QueryParam("category") String category) {
        System.out.println("MetaSearch.getSearchResult");

        ArrayList<HashMap> result = search.search(keyword, category);

        System.out.println(result);

        String json = new Gson().toJson(result);

        return json;
    }
}
