package com.testdata;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.testdata.dao.Tracks;
import com.testdata.dao.TracksDao;

@EnableWebMvc	//usato per importare la configurazione Spring MVC da WebMvcConfigurationSupport
@Configuration
@ComponentScan(basePackages = "com.testdata.controller")	//per indicare quale package contiene il/i controller
public class WebConfig /*implements WebMvcConfigurer*/{

	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver() {
		FreeMarkerViewResolver bean = new FreeMarkerViewResolver();
		bean.setCache(true);
		bean.setPrefix("");
		bean.setSuffix(".ftl");
		return bean;
	}
	@Bean
	public FreeMarkerConfigurer configureFreeMarker() {
		FreeMarkerConfigurer config = new FreeMarkerConfigurer();
		config.setTemplateLoaderPath("/WEB-INF/view/");
		return config;
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.sqlite.JDBC");
		ds.setUsername("sa");
		ds.setPassword("sa");
		ds.setUrl("jdbc:sqlite:C:/sqllite/sqllitemusicdb.db");
		return ds;
	}
	@Bean
	public TracksDao getTracksService() {
		return new Tracks(getDataSource());
	}

}