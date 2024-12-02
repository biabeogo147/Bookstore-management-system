package vn.hust.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "product_type")
public abstract class Product {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Long quantity;

    public String getDescription() {
        return description + ' ' + this.toString();
    }
}
