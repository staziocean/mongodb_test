package fr.diginamic.mongodb.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.diginamic.mongodb.client.MongoClientSingleton;
import fr.diginamic.mongodb.model.IdModel;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public abstract class GenericDao<T extends IdModel, ID> implements CrudDao<T, ID> {

    private static final String DATABASE_NAME = "library";
    private final String collectionName;
    private final Class<T> javaType;

    private MongoClient client;

    public GenericDao(String collectionName, Class<T> javaType) {
        this.collectionName = collectionName;
        this.javaType = javaType;
    }

    @Override
    public T create(T t) {
        MongoCollection<T> collection = getCollection();

        collection.insertOne(t);

        return t;
    }

    @Override
    public T update(ID id, T t) {
        MongoCollection<T> collection = getCollection();

        collection.findOneAndReplace(eq(id), t);

        return findById(id);
    }

    @Override
    public T findById(ID id) {
        MongoCollection<T> collection = getCollection();

        return collection.find(eq(id)).first();
    }

    @Override
    public List<T> findAll() {
        MongoCollection<T> collection = getCollection();

        return collection.find().into(new ArrayList<>());
    }

    @Override
    public void remove(ID id) {
        MongoCollection<T> collection = getCollection();

        collection.findOneAndDelete(eq(id));
    }

    protected MongoCollection<T> getCollection() {
        client = MongoClientSingleton.getMongoClient();
        MongoDatabase db = client.getDatabase(DATABASE_NAME);
        return db.getCollection(collectionName, javaType);
    }
}
