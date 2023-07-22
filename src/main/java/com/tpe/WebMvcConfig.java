package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//  burasi configurasyon classi olacak   -> @Configuration


@Configuration
@ComponentScan("com.tpe")       //defaultta da hangi dizinde ise altindaki tum packagelari tarar
@EnableWebMvc   //  Web Mvcyi aktif et
public class WebMvcConfig implements WebMvcConfigurer {

    //view name e karsilik gelen jsp dosyasinin cozumlenmesini saglayan yapi-bilesen: view resolver
    // view name olarak sadece hi gelmis olacak, ,jsp eklenerek dizinde araniyor
    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);  //JavaStandartTagLibrary: JSP dosyalri icinde HTML taglerini kullanarak
        // daha az kod  yazmamizi saglar
        resolver.setPrefix("/WEB-INF/views/");   //view dosyalari nerede(dizin)
        resolver.setSuffix(".jsp");   // view name geldiginde .jsp uzantisini ekleyerek web-inf altinda ara demis olduk
        // webapp view dosyalarinin bulundugu yer, otomatik oraya baktigi icin yazmadik
        return resolver;
    }

    /*
    Kullanicidan static bir request geldiginde Web Server tarafindan dogrudan  response verilir, ozel yazilima gerek yok.
    css,image gibi statik olan kaynaklarin dispatcher server tarafindan karsilanmasina gerek yok.
    WebMvcConfigurer interface'inin addResourceHandlers methodu ile otomatik olarak bunu saglayabiliriz
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { //sadece statik olanlar icin bunu yapiyoruz.
       registry.addResourceHandler("/resources/**").//bu pathteki dosylari dispatchera gondermeden statik sun
        addResourceLocations("/resources/")
               .setCachePeriod(0);   // statik olan verileri cache aliyor, period suresi verilebiliyor
    }
}