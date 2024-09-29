package vn.hust.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "account_type")
public class Account {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private String password;
    private String email;
    private String phone;
    private String fullName;
    private Long status;
    private Date timeCreated;
}
