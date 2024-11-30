package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.Customer;
import vn.hust.bookstore.entity.Product;
import vn.hust.bookstore.util.HibernateUtil;

public class CustomerService {
    public boolean addToCart(Customer customer, Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            customer = session.createQuery("from Customer c left join fetch c.cart where c.id = :id", Customer.class)
                    .setParameter("id", customer.getId())
                    .uniqueResult();

            if (customer.getCart().stream().noneMatch(p -> p.getId().equals(product.getId()))) {
                customer.getCart().add(product);
                session.update(customer);
                session.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
