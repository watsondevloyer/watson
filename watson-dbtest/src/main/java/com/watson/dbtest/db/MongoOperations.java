package com.watson.dbtest.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.List;


/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/235:40 PM
 */

public class MongoOperations extends MongoClient {

    public MongoOperations(String host, int port) {
        super(host, port);
    }

    public MongoOperations(ServerAddress addr, List<MongoCredential> credentialsList) {
        super(addr, credentialsList);
    }
}
