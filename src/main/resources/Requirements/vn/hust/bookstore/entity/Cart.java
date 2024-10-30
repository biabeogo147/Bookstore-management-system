package vn.hust.bookstore.entity;

@Entity
@Getter
@Setter
public class Cart {

	@Id
	@GeneratedValue(generator="increment")
	private Long id;

	private Product[] items;

}
