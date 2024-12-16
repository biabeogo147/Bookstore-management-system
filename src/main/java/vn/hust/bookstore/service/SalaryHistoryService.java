package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.SalaryHistory;
import vn.hust.bookstore.util.HibernateUtil;

import java.util.List;

public class SalaryHistoryService {
    public void addSalaryHistory(SalaryHistory salaryHistory) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(salaryHistory);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSalaryHistory(SalaryHistory salaryHistory) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(salaryHistory);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SalaryHistory> getAllSalaryHistories() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from SalaryHistory", SalaryHistory.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
