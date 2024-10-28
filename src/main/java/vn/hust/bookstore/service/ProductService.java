package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.Product;
import vn.hust.bookstore.util.HibernateUtil;

import java.util.Optional;

public class ProductService {
    public Optional<Product> addProduct(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(product);
    }

    public Optional<Product> updateProduct(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(product);
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
