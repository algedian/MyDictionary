package metasearch.common;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Configuration class
 *
 * @author Yewon Kim
 */
@javax.ws.rs.ApplicationPath("service")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(metasearch.manager.MetaSearch.class);
    }

}
