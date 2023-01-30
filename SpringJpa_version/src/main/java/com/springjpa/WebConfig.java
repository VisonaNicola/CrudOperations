package com.springjpa;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.springjpa.dao.Albums;
import com.springjpa.dao.AlbumsDao;
import com.springjpa.dao.Genres;
import com.springjpa.dao.GenresDao;
import com.springjpa.dao.Tracks;
import com.springjpa.dao.TracksDao;

@EnableWebMvc	//usato per importare la configurazione Spring MVC da WebMvcConfigurationSupport
@Configuration
@ComponentScan(basePackages = "com.springjpa.controller")	//per indicare quale package contiene il/i controller
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.springjpa.repository", entityManagerFactoryRef="emf", transactionManagerRef = "tmf")
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
	
	@Bean(name="emf")
	public LocalContainerEntityManagerFactoryBean getEntityManager() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setGenerateDdl(false);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(getDataSource());
		factory.setJpaVendorAdapter(adapter);
		factory.setPackagesToScan(getClass().getPackage().getName());
		return factory;
	}
	
	//questo nome è necessario per usare le repository
	@Bean(name="tmf")	//per gestione transazioni 
	public PlatformTransactionManager getTransactionManager() {
		JpaTransactionManager jtm = new JpaTransactionManager(getEntityManager().getObject());
		
		return jtm;
	}
	
	@Bean
	public TracksDao getTracksService() {
		return new Tracks();
	}
	@Bean
	public GenresDao getGernesService() {
		return new Genres();
	}
	@Bean
	public AlbumsDao getAlbumsService() {
		return new Albums();
	}

}