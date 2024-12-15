package vn.hust.bookstore.service;

import org.hibernate.Session;
import vn.hust.bookstore.entity.BatchRecord;
import vn.hust.bookstore.util.HibernateUtil;

public class BatchRecordService {
    public void addBatchRecord(BatchRecord batchRecord) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(batchRecord);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
