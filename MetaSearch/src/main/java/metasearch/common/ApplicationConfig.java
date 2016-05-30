/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metasearch.common;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * 설정 클래스입니다만..?
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
