package fr.diginamic.mongodb.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Objects;

public class Book implements IdModel<ObjectId> {
    private ObjectId id;
    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "title")
    private String title;
    @BsonProperty(value = "releaseDate")
    private LocalDate releaseDate;

    public Book() {
    }

    public Book(String name, String title, LocalDate releaseDate) {
        this.name = name;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.equals(title, book.title) &&
                Objects.equals(releaseDate, book.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, releaseDate);
    }
}
