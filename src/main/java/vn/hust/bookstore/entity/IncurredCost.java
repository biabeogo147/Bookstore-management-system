package vn.hust.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class IncurredCost {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private String description;
    private Double cost;
    private Date date;
}
