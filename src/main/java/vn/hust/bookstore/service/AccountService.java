package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.Account;
import vn.hust.bookstore.entity.Admin;
import vn.hust.bookstore.entity.Customer;
import vn.hust.bookstore.entity.Employee;
import vn.hust.bookstore.util.HibernateUtil;

import java.sql.Date;

public class AccountService {
    public Account getAccount(String emailOrPhone) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Account where email = :email or phone = :phone", Account.class)
                    .setParameter("email", emailOrPhone)
                    .setParameter("phone", emailOrPhone)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Account account) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long login(String emailOrPhone, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Account account = session.createQuery(
                            "from Account a where (a.email = :email or a.phone = :phone)",
                            Account.class)
                    .setParameter("email", emailOrPhone)
                    .setParameter("phone", emailOrPhone)
                    .uniqueResult();

            if (account != null && account.getPassword().equals(password)) {
                if (account instanceof Customer) {
                    return 1; // Customer
                } else if (account instanceof Employee) {
                    return 2; // Employee
                } else {
                    return 3; // Admin
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Login failed
    }

    public void addAccount(Account account) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
