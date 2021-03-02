package fr.diginamic.mongodb.client;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoClientSingleton {

    private static MongoClient mongoClient;

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
            MongoClientSettings clientSettings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                    .codecRegistry(codecRegistry)
                    .build();

            mongoClient = MongoClients.create(clientSettings);
        }
        return mongoClient;
    }

    public static void releaseMongoClient() {
        mongoClient.close();
        mongoClient = null;
    }
}
