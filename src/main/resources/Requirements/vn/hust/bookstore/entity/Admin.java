package vn.hust.bookstore.entity;

import java.util.List;
import java.sql.Date;

@Entity
@Getter
@Setter
@DiscriminatorValue("admin")
public class Admin extends Account {

	public List<Employee> viewEmployees() {
		return null;
	}

	public List<Order> viewOrderRevenue(Date firstDay, Date lastDay) {
		return null;
	}

	public void addEmployee(Employee employee) {

	}

	public void updateEmployeeInfo(Employee employee) {

	}

	public Long viewPayingSalary() {
		return null;
	}

}
