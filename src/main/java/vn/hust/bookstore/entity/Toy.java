package vn.hust.bookstore.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toy extends Product {

    private String brand;  // Thương hiệu đồ chơi

    private String ageGroup;  // Nhóm tuổi mà đồ chơi này phù hợp
}
