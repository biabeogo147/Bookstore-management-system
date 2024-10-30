package vn.hust.bookstore.entity;

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
