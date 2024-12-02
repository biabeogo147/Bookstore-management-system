package vn.hust.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("stationery")
public class Stationery extends Product {
    private String brand;
    private String type;

    @Override
    public String toString() {
        return "Stationery{" +
                "brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
