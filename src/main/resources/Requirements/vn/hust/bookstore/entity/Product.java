package vn.hust.bookstore.entity;

@Entity
@Getter
@Setter
@Inheritance(strategy=InheritanceType.JOINED)
public class Product {

	@Id
	@GeneratedValue(generator="increment")
	private Long id;

	private String name;

	private Double price;

	private String description;

	private Long quantity;

}
