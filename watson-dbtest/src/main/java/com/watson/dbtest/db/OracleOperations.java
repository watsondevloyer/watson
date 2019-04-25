package com.watson.dbtest.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/2312:49 PM
 */
public class OracleOperations extends JdbcTemplate {

    public OracleOperations(String username,String password,String url){

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setInitialSize(3);
        dataSource.setMaxIdle(3);
        setDataSource(dataSource);

    }
}
