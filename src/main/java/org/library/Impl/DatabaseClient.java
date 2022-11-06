package org.library.Impl;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DatabaseClient {
    private static MongoClient client;
    final private static ConnectionString CONNECTION_STRING = new ConnectionString(ConstantValues.CONNECTION_STRING);
    final private static CodecRegistry POJO_CODEC_REGISTRY = fromProviders(PojoCodecProvider.builder().automatic(true).build());
    final private static CodecRegistry CODEC_REGISTRY = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            POJO_CODEC_REGISTRY);
    final private static MongoClientSettings CLIENT_SETTINGS = MongoClientSettings.builder()
            .applyConnectionString(CONNECTION_STRING)
            .codecRegistry(CODEC_REGISTRY)
            .build();

    private DatabaseClient(){}

    public static MongoClient getInstance(){
        if(client==null){
            client=MongoClients.create(CLIENT_SETTINGS);
            return client;
        }
        return client;
    }
}
