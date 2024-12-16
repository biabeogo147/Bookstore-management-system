package vn.hust.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class Employee extends Account {
    private Double hourlyWage;
    private Long workingHours;
    private Long leaveHours;
}
