package fr.diginamic.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.diginamic.mongodb.client.MongoClientSingleton;
import fr.diginamic.mongodb.model.Book;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.Document;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Book book = new Book("Harry Potter 3", LocalDate.now());

        MongoClient mongoClient = MongoClientSingleton.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("library");
        MongoCollection<Book> collection = database.getCollection("book", Book.class);

        collection.insertOne(book);

        for (Book b : collection.find()) {
            System.out.println(b);
        }

    }
}
