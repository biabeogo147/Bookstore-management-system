package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.IncurredCost;
import vn.hust.bookstore.util.HibernateUtil;

import java.util.List;

public class IncurredCostService {
    public void addIncurredCost(IncurredCost incurredCost) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(incurredCost);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<IncurredCost> getAllIncurredCosts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from IncurredCost", IncurredCost.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
