package fr.diginamic.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.diginamic.mongodb.client.MongoClientSingleton;
import fr.diginamic.mongodb.dao.BookDao;
import fr.diginamic.mongodb.model.Book;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        BookDao bookDao = new BookDao();

        // Insert 2 books.
        System.out.println("Created books :");
        List<Book> booksToCreate = Arrays.asList(new Book("JK Rowling", "Harry Potter 8", LocalDate.now()),
                new Book("JK Rowling", "Harry Potter 9", LocalDate.now()));
        List<Book> createdBook = new ArrayList<>();
        for (Book b : booksToCreate) {
            createdBook.add(bookDao.create(b));
            System.out.println(b);
        }

        // Find all books.
        System.out.println();
        System.out.println("List of all books :");
        List<Book> books = bookDao.findAll();
        for (Book b : books) {
            System.out.println(b);
        }

        // Remove the previously added books.
        for (Book b : createdBook) {
            bookDao.remove(b.getId());
        }

        // Find all books.
        System.out.println();
        System.out.println("List of all books :");
        books = bookDao.findAll();
        for (Book b : books) {
            System.out.println(b);
        }

        System.out.println();
        System.out.println("Book with id \"603e586bc9e60b3f3a1479c5\"");
        Book foundBook = bookDao.findById(new ObjectId("603e586bc9e60b3f3a1479c5"));
        System.out.println(foundBook);

        System.out.println();
        System.out.println("Books with title containing \"Potter 2\"");
        List<Book> foundBookByTitle = bookDao.findByTitle("Potter 2");
        for (Book b : foundBookByTitle) {
            System.out.println(b);
        }

        System.out.println();
        System.out.println("Modify, save and get book with id \"603e586bc9e60b3f3a1479c5\"");
        foundBook.setReleaseDate(LocalDate.of(2008, 7, 23));
        foundBook = bookDao.update(foundBook.getId(), foundBook);
        System.out.println(foundBook);
    }
}
