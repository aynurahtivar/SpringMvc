package com.tpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")  //properties dosyasinin kaynagi
public class RootContextConfig {

    @Autowired
    private Environment environment;
    //properties source icindeki belirttigimiz dosyadan propertiesleri okur


    //Hibernate de DB ile iletisim icin Session objesine ihtiyac duyar.
    //  Session objesini buraya LocalSessionFactoryBean ile aliyoruz


    @Bean   //olusturulan objeyi diger classlarda da kullanabiliriz
    public LocalSessionFactoryBean sessionFactory() {   //
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setHibernateProperties(hibernateProperties());        //hibernate icin ozellikler set edilecek
        //(hibernate.config.xml) icerisindeki config ayarlarini tanimladigimiz yer, ddlauto, showsql vs.
        sessionFactory.setDataSource(dataSource()); //JDBC icin gerekli bilgiler
        //  Driver class,url,username,password
        sessionFactory.setPackagesToScan(new String[]{"com.tpe.domain"});
        // Hibernatedeki Entity classlarin taranmasini icin package adi veriyoruz

        return sessionFactory;
    }

    private Properties hibernateProperties() {  //icinde hibernatein propertilerini bulunduran properties objesi dondurecek
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }


    private DataSource dataSource() {    //JDBC properties donduren obje
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;

    }


}