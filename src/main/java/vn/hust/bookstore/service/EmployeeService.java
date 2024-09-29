package vn.hust.bookstore.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vn.hust.bookstore.entity.Employee;
import vn.hust.bookstore.util.HibernateUtil;

public class EmployeeService {

    public final SessionFactory sessionFactory;

    public EmployeeService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
