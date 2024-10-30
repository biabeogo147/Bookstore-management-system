package vn.hust.bookstore.entity;

import java.sql.Date;

@Entity
@Getter
@Setter
public class BatchRecord {

	@Id
	@GeneratedValue(generator="increment")
	private Long id;

	private Long inQuantity;

	private Date inDate;

	private Double inPrice;

	private Product product;

}
