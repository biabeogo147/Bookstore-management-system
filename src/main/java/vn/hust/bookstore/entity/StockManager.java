package vn.hust.bookstore.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("stock_manager")
public class StockManager extends Employee {
    private static Double coefficientsSalary = 0.8;
}
