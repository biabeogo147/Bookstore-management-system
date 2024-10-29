package vn.hust.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;   // Mã định danh đơn hàng

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> items;

    private Double totalPrice;  // Tổng giá trị đơn hàng

    private String status;  // Trạng thái của đơn hàng (Pending, Processing, Delivered, etc.)

    private Date orderDate;  // Ngày đặt hàng

    private String paymentMethod;  // Phương thức thanh toán (Credit Card, Cash, etc.)

    private Date paymentDate;  // Ngày thanh toán được thực hiện
}
