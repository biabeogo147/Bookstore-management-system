package vn.hust.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@DiscriminatorValue("book")
public class Book extends Product {
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private Date publicationDate;

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genre='" + genre + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
