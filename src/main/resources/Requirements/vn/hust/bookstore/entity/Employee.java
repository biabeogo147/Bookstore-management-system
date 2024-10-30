package vn.hust.bookstore.entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("employee")
public class Employee extends Account {

	private Long role;

	private Double salary;

	private Long workingHours;

	public void addBook(Book book) {

	}

	public void updateBook(Book book) {

	}

	public Order viewOrder(Long id) {
		return null;
	}

	public void addStock(BatchRecord batch) {

	}

}
