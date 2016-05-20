package metasearch.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Yewon Kim - Administrator
 */
@Path("metasearch")
public class MetaSearch {

    @EJB//서비스 해줄 비지니스 로직을 가진 객체 레퍼런스를 여기에!
    MetaSearchFacade search;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TestBean> getSearchResult(@QueryParam("q") String keyword) {

        //ConcurrentHashMap<Vendor, HashMap>  result = search.search(keyword);
        List<TestBean> list = new ArrayList<TestBean>();
        list.add(new TestBean());
        
        //return Response.ok().build();
        return list;
    }
}
