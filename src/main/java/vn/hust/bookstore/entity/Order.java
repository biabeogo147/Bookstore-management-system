package vn.hust.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "order_history")
public class Order {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> items;

    private Double totalPrice;
    private String status;
    private String address;
    private Date orderDate;
    private String paymentMethod;
    private Date paymentDate;
    private boolean paid;

    public String getCustomerName() {
        return customer != null ? customer.getFirstName() + " " + customer.getLastName() : "";
    }
}
