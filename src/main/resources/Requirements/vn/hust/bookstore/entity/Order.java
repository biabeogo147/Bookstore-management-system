package vn.hust.bookstore.entity;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(generator="increment")
	private Long id;

	private Double totalPrice;

	private String status;

	private String paymentMethod;

	private Date orderDate;

	private Date paymentDate;

	private Product[] items;

	private Customer customer;

}
