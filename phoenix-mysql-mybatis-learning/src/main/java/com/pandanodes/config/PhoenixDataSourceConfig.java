package com.pandanodes.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.pandanodes.dao.phoenix.**",
        sqlSessionTemplateRef = "phoenixSqlSessionTemplate")
public class PhoenixDataSourceConfig {


    public static String PHOENIX_LOCATION_PATTERN ="classpath:/mapper/phoenix/*.xml";

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.phoenix")
    public DataSource phoenixDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory phoenixSqlSessionFactory(@Qualifier("phoenixDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PHOENIX_LOCATION_PATTERN));
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager phoenixTransactionManager(@Qualifier("phoenixDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate phoenixSqlSessionTemplate(@Qualifier("phoenixSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

