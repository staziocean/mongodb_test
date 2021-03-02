package fr.diginamic.mongodb.dao;

import com.mongodb.client.MongoCollection;
import fr.diginamic.mongodb.model.Book;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class BookDao extends GenericDao<Book, ObjectId> {

    public BookDao() {
        super("book", Book.class);
    }

    public List<Book> findByTitle(String title) {
        MongoCollection<Book> collection = getCollection();

        return collection.find(regex("title", ".*" + title + ".*")).into(new ArrayList<>());
    }
}
