package org.example.service;

import org.example.dao.TimeDAO;
import org.example.model.TimeModel;

import java.util.List;

public class SqlService {
    private final TimeDAO timeDAO = new TimeDAO();

    public void save(TimeModel timeModel) {
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
