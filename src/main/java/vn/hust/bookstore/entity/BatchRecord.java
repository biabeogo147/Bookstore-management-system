package vn.hust.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class BatchRecord {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    private Double inPrice;
    private Long inQuantity;
    private Date inDate;
}
