package org.example.dao;

import org.example.model.TimeModel;
import org.example.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TimeDAO {

    public List<TimeModel> findAll() {
        List<TimeModel> timeModels = getSession().createQuery("From TimeModel").list();

        return timeModels;
    }

    public void save(TimeModel timeModel) {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        session.save(timeModel);
        transaction.commit();
        session.close();
    }

    private Session getSession() {
        return HibernateSessionFactory.getSessionFactory().openSession();
    }

}
