package org.example.model;

import org.example.dao.TimeDAO;

import java.util.List;

public class SqlThread extends Thread {
    private final TimeDAO timeDAO = new TimeDAO();

    public void saveTimeToSQL(TimeModel time) {
        timeDAO.save(time);
    }

    public List<TimeModel> getAll() {
        return timeDAO.findAll();
    }
}
