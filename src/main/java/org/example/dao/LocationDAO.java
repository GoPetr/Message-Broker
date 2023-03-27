package org.example.dao;

import org.example.model.Location;
import org.example.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LocationDAO {

    public List<Location> findAll() {
        List<Location> locations = getSession().createQuery("From Location").list();
        return locations;
    }

    public Location findById(long id) {
        return getSession().get(Location.class, id);
    }

    public void save(Location location) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(location);
        transaction.commit();
        session.close();
    }

    private Session getSession() {
        return HibernateSessionFactory.getSessionFactory().openSession();
    }
}
