package org.example.model;

import org.example.dao.TimeDAO;

import java.util.List;

public class SqlThread {
    private final TimeDAO timeDAO = new TimeDAO();

    public void save(TimeModel timeModel) {
        System.out.println("THIS IS SQL");
        timeDAO.save(timeModel);
    }

    public List<TimeModel> getAll() {
        return timeDAO.findAll();
    }

    public TimeModel findById(Long id) {
        return timeDAO.findById(id);
    }

    public Long getMaxId() {
        return timeDAO.getMaxId();
    }

}
