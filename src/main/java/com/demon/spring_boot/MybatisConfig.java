package com.demon.spring_boot;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages={"com.demon.spring_boot.mapper"}, sqlSessionFactoryRef="shopSqlSessionFactory")
public class MybatisConfig {
    
    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource shopDataSource;
    
    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource matchDataSource;
    
    @Bean("shopSqlSessionFactory")
    public SqlSessionFactory shopSqlSessionFactory(@Qualifier("primaryDataSource")DataSource dataSource) throws Exception{
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        return factory.getObject();
    }
    
    @Bean(name = "shopTransactionManager")
    public DataSourceTransactionManager master2TransactionManager() {
        return new DataSourceTransactionManager(shopDataSource);
    }

}
