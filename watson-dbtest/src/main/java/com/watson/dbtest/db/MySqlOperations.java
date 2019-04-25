package com.watson.dbtest.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/2311:34 AM
 */
public class MySqlOperations extends JdbcTemplate  {

    public MySqlOperations(String username,String password,String url){

        setDataSource(initDataSource(username,password,url,"com.mysql.jdbc.Driver"));

    }

    public MySqlOperations(String username,String password,String url,String driver){
        setDataSource(initDataSource(username,password,url,driver));
    }


    private BasicDataSource initDataSource(String username,String password,String url,String driver){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setInitialSize(3);
        dataSource.setMaxIdle(3);
        return dataSource;
    }

}
