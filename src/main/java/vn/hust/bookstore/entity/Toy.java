package vn.hust.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("toy")
public class Toy extends Product {
    private String brand;
    private String ageGroup;

    @Override
    public String toString() {
        return "Toy{" +
                "brand='" + brand + '\'' +
                ", ageGroup='" + ageGroup + '\'' +
                '}';
    }
}
