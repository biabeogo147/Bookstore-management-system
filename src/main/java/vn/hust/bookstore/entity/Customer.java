package vn.hust.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("customer")
public class Customer extends Account {
    private String address;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> purchaseHistory;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> cart;
}
