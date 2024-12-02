package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.Customer;
import vn.hust.bookstore.entity.Order;
import vn.hust.bookstore.entity.Product;
import vn.hust.bookstore.util.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    public boolean addToCart(Customer customer, Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            customer = session.createQuery("from Customer c left join fetch c.cart where c.id = :id", Customer.class)
                    .setParameter("id", customer.getId())
                    .uniqueResult();

            if (customer.getCart().stream().noneMatch(p -> p.getId().equals(product.getId()))) {
                customer.getCart().add(session.get(Product.class, product.getId()));
                session.merge(customer);
                session.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addOrder(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            customer = session.createQuery("from Customer c left join fetch c.cart where c.id = :id", Customer.class)
                    .setParameter("id", customer.getId())
                    .uniqueResult();

            Order order = new Order();
            order.setCustomer(customer);
            order.setItems(new ArrayList<>(new HashSet<>(customer.getCart())));
            order.setTotalPrice(customer.getCart().stream().mapToDouble(Product::getPrice).sum());
            //order.setStatus("Pending");
            //order.setPaymentMethod("Cash");
            //order.setPaymentDate(new java.sql.Date(System.currentTimeMillis()));
            order.setOrderDate(new java.sql.Date(System.currentTimeMillis()));
            session.persist(order);

            customer.getPurchaseHistory().add(order);
            customer.getCart().clear();
            session.merge(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomer(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateCustomer(Customer customer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearCart(Customer customer) {
        customer.getCart().clear();
        updateCustomer(customer);
    }
}
