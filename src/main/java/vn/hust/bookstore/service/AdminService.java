package vn.hust.bookstore.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vn.hust.bookstore.entity.Admin;
import vn.hust.bookstore.util.HibernateUtil;

public class AdminService {

    public final SessionFactory sessionFactory;

    public AdminService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addAdmin(Admin admin) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(admin);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
