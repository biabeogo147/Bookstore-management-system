package vn.hust.bookstore.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vn.hust.bookstore.entity.Customer;
import vn.hust.bookstore.util.HibernateUtil;

public class CustomerService {

    public final SessionFactory sessionFactory;

    public CustomerService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addCustomer(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
