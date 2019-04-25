package com.watson.dbtest;

import com.watson.dbtest.db.*;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/233:31 PM
 */

public class DBFactory {

    /**
     * 创建操作mysql数据库的对象
     *
     * @param id @Mysql 注解中的id值
     * @return
     */
    public static MySqlOperations createMysqlTest(String id) {
        return DBGenerator.getInstance().getMysqlOperation(id);
    }

    /**
     * 创建操作Oracle数据库的对象
     *
     * @param id @Oracle 注解中的id值
     * @return
     */
    public static OracleOperations createOracleTest(String id) {
        return DBGenerator.getInstance().getOracleOperation(id);
    }

    /**
     * 创建操作redis的对象
     *
     * @param id @Redis注解中的id值
     * @return
     */
    public static RedisOperations createRedisTest(String id) {
        return DBGenerator.getInstance().getRedisOperations(id);
    }

    /**
     * 创建操作mongodb的对象
     *
     * @param id @Mongo注解 中的id值
     * @return
     */
    public static MongoOperations createMongoTest(String id) {
        return DBGenerator.getInstance().getMongoOperations(id);
    }


}
