package org.example.dao;

import org.example.model.Listener;
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

    public TimeModel findById(Long id) {
        return getSession().get(TimeModel.class, id);


    }

    public Long getMaxId() { //todo как то косячно
        List<Long> query = getSession().createQuery("select max(id) from TimeModel").list();
        return query.get(0);

    }

    public void save(TimeModel timeModel) {
        if (getSession() == null) {
            return;
        }
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(timeModel);
        transaction.commit();
        session.close();
    }

    private Session getSession() {
        Listener.checkSession();
        //    return null;
        //  }
        return HibernateSessionFactory.getSessionFactory().openSession();
    }
}
