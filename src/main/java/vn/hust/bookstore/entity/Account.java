package vn.hust.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private String phone;
    private String password;
    private String email;
    private String lastName;
    private String firstName;
    private boolean isMale;
    private Date birthday;
    private Long status;
    private Date timeCreated;
}
