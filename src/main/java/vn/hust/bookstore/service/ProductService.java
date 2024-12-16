package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.Book;
import vn.hust.bookstore.entity.Product;
import vn.hust.bookstore.entity.Stationery;
import vn.hust.bookstore.entity.Toy;
import vn.hust.bookstore.util.HibernateUtil;

import java.util.List;
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
            Optional<Product> getProduct = getProduct(product.getName());
            if (getProduct.isPresent()) {
                Product existingProduct = getProduct.get();
                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setQuantity(product.getQuantity());
                existingProduct.setDescription(product.getDescription());

                switch (existingProduct) {
                    case Book book -> {
                        book.setAuthor(((Book) product).getAuthor());
                        book.setPublisher(((Book) product).getPublisher());
                        book.setGenre(((Book) product).getGenre());
                        book.setPublicationDate(((Book) product).getPublicationDate());
                    }
                    case Stationery stationery -> {
                        stationery.setBrand(((Stationery) product).getBrand());
                        stationery.setType(((Stationery) product).getType());
                    }
                    case Toy toy -> {
                        toy.setBrand(((Toy) product).getBrand());
                        toy.setAgeGroup(((Toy) product).getAgeGroup());
                    }
                    default -> {
                    }
                }

                session.update(existingProduct);
                session.getTransaction().commit();
            }
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
