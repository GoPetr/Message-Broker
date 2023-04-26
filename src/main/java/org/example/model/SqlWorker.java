package org.example.model;

import org.example.dao.TimeDAO;

import java.util.List;

public class SqlWorker {
    private final TimeDAO timeDAO = new TimeDAO();

    public void save(TimeModel timeModel) {
        timeDAO.save(timeModel);
    }

    public List<TimeModel> getAll() {
        return timeDAO.findAll();
    }
}
