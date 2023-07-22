package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/*  --> Bu class in amaci :
    web.xml yerine bu classi kullaniriz
    Dispatcher Servlet i olusturulmasi, baslatilmasi
    Configurasyon classlarinin yerini gosterme
 */

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootContextConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {   //http://localhost:8080/SpringMvc

        return new String[]{"/"};
    }
}
