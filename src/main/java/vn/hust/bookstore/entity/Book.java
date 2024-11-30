package vn.hust.bookstore.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Getter
@Setter
public class Book extends Product {
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private Date publicationDate;
}
