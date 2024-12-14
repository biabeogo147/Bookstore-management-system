package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.Order;
import vn.hust.bookstore.util.HibernateUtil;

import java.util.List;

public class OrderService {
    public List<Order> getAllOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Order", Order.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateOrder(Order order) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
