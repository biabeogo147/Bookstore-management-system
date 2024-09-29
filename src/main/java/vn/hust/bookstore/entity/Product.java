package vn.hust.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;  // Mã định danh sản phẩm

    private String name;  // Tên sản phẩm

    private Double price;  // Giá sản phẩm

    private String description;  // Mô tả ngắn về sản phẩm

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;  // Đơn hàng mà sản phẩm này thuộc về
}
