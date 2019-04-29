package com.watson.dbtest;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.watson.dbtest.db.MongoOperations;
import com.watson.dbtest.db.MySqlOperations;
import com.watson.dbtest.db.RedisOperations;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * DBFactory Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 23, 2019</pre>
 */
@Slf4j
public class DBFactoryTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: createMysqlTest(String id)
     */


    /**
     * Method: createOracleTest(String id)
     */
    @Test
    public void testCreateOracleTest() throws Exception {
    }

    @Test
    public void testCreateMysqlTest() throws Exception {
        String sql="select projectName from tb_project where id=4";
        MySqlOperations mangosteenDB = DBFactory.createMysqlTest("mangosteenDB");
        Map<String, Object> stringObjectMap = mangosteenDB.queryForMap(sql);
        for(Map.Entry<String,Object> map:stringObjectMap.entrySet()){
            System.out.println(map.getKey());
            System.out.println(map.getValue());
        }

    }
    /**
     * Method: createRedisTest(String id)
     */
    @Test
    public void testCreateRedisTest() throws Exception {

        RedisOperations redisTest = DBFactory.createRedisTest("90Redis");
        redisTest.set(1,"test","testCreateRedisTest");
        System.out.println(redisTest.get(1,"test"));
    }

    /**
     * Method: createMongoTest(String id)
     */
    @Test
    public void testCreateMongoTest() throws Exception {
        MongoOperations mongodb = DBFactory.createMongoTest("mongodb");
        MongoDatabase payinterface = mongodb.getDatabase("yapi");
        MongoCollection<Document> collection = payinterface.getCollection("group");
        long count = collection.count();
        System.out.println(count);
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> iterator = documents.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }


}
