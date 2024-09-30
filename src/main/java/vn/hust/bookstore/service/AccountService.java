package vn.hust.bookstore.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vn.hust.bookstore.entity.Account;
import vn.hust.bookstore.entity.Customer;
import vn.hust.bookstore.util.HibernateUtil;

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

    public boolean login(String emailOrPhone, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Account account = session.createQuery("from Account where email = :email or phone = :phone", Account.class)
                    .setParameter("email", emailOrPhone)
                    .setParameter("phone", emailOrPhone)
                    .uniqueResult();
            if (account != null && account.getPassword().equals(password)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void signup(String email, String phone, String password, String cfPassword) {
        if (!password.equals(cfPassword)) {
            throw new IllegalArgumentException("Password and confirm password do not match");
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Customer account = new Customer();
            account.setEmail(email);
            account.setPhone(phone);
            account.setPassword(password);
            session.beginTransaction();
            session.persist(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
