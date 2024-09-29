package vn.hust.bookstore.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("employee")
public class Employee extends Account {
    private Long role;
    private Double salary;
    private Long workingHours;
}
