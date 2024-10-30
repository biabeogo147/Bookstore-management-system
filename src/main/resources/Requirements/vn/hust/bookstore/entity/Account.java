package vn.hust.bookstore.entity;

import java.sql.Date;

@Entity
@Getter
@Setter
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="account_type")
public class Account {

	@Id
	@GeneratedValue(generator="increment")
	private Long id;

	private String phone;

	private String password;

	private String email;

	private String lastName;

	private String firstName;

	private boolean isMale;

	private Long status;

	private Date timeCreated;

	private Date birthday;

}