package vn.hust.bookstore.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
public class Book extends Product {

    private String title;  // Tên sách

    private String author;  // Tác giả của cuốn sách

    private String publisher;  // Nhà xuất bản của cuốn sách

    private String genre;  // Thể loại của sách

    private Date publicationDate;  // Ngày xuất bản của sách
}
