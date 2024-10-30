package vn.hust.bookstore.entity;

import java.util.List;

@Entity
@Getter
@Setter
public class Customer extends Account {

	private String address;

	private Order[] purchaseHistory;

	private Cart cart;

	private Product[] cart;

	public List<Product> viewProduct() {
		return null;
	}

	public void addToCart(Product product) {

	}

	public void removeFromCart(Product product) {

	}

	public List<Order> viewOrderHistory() {
		return null;
	}

	public void checkOut(String payment) {

	}

}
