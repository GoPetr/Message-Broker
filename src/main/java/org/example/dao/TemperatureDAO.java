package org.example.dao;

import org.example.model.Location;
import org.example.model.Temperature;
import org.example.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TemperatureDAO {
    public List<Temperature> findAll() {
        List<Temperature> temperatures = getSession().createQuery("From Temperature ").list();
        return temperatures;
    }

    public Temperature findById(long id) {
        return getSession().get(Temperature.class, id);
    }

    public void save(Temperature temperature) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(temperature);
        transaction.commit();
        session.close();
    }

    Session getSession() {
        return HibernateSessionFactory.getSessionFactory().openSession();
    }
}