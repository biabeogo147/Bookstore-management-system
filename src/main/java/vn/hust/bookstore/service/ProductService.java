package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.Product;
import vn.hust.bookstore.util.HibernateUtil;

import java.util.List;
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

    public Optional<Product> getProduct(String name) {
        Product product = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            product = session.createQuery("from Product where name = :name", Product.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(product);
    }

    public List<Product> getAllProducts() {
        List<Product> products = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            products = session.createQuery("from Product", Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
