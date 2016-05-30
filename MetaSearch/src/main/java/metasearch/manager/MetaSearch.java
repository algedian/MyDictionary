package metasearch.manager;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * 실질적으로 메타서치 서비스에 대한 첫 http requet를 받는 곳.
 * @author Yewon Kim
 */
@Path("metasearch")
public class MetaSearch {

    @EJB//서비스 해줄 비지니스 로직을 가진 객체 레퍼런스를 여기에!
    MetaSearchFacade search;

    /**
     *
     * @param keyword
     * @param category
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSearchResult(@QueryParam("q") String keyword, @QueryParam("category") String category) {
        System.out.println("MetaSearch.getSearchResult");
        
        ArrayList<HashMap> map = search.search(keyword, category);
        
        System.out.println(map);
        
        return keyword;
    }
}
