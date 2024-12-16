package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.Employee;
import vn.hust.bookstore.util.HibernateUtil;

import java.util.List;

public class EmployeeService {
    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
