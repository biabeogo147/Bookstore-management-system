package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.Product;
import vn.hust.bookstore.util.HibernateUtil;

import java.util.Optional;

public class ProductService {
    public void addProduct(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Product> getProduct(Long id) {
        Product product = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            product = session.get(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(product);
    }

}
