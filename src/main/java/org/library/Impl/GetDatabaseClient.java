package org.library.Impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.library.Model.Object;

/*
 * Test class
 */
public class GetDatabaseClient {
    public GetDatabaseClient(){}
    public MongoClient getConnectionClient(){
        /*String connectionString = "mongodb+srv://Kub-deployment-user:Password@cluster0.nnf4l.mongodb.net/?retryWrites=true&w=majority";
        try ( MongoClient mongoClient = MongoClients.create(connectionString)) {
            return mongoClient;
        }*/
        MongoClient mongoClient = DatabaseClient.getInstance();
        MongoDatabase LibraryDB = mongoClient.getDatabase("library");
        MongoCollection<Object> TestObjectCollection= LibraryDB.getCollection("TestObj", Object.class);
        Object obj=new Object("abc");
        TestObjectCollection.insertOne(obj);
        return mongoClient;
    }
}
