package vn.hust.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "salary_history")
public class SalaryHistory {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private Date date;
    private Double salary;
    private Double hourlyWage;
    private Long workingHours;
    private Long leaveHours;
    private Long month;
    private boolean paid;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
