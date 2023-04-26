package org.example.util;

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
}
