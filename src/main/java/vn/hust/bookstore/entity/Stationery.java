package vn.hust.bookstore.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stationery extends Product {

    private String brand;  // Thương hiệu của sản phẩm văn phòng phẩm

    private String type;  // Loại sản phẩm văn phòng phẩm (Bút, giấy, v.v.)
}
