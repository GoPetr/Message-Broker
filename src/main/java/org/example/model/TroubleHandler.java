package org.example.model;

import java.util.List;

public class TroubleHandler {

    public synchronized void callTroubleHandler(NoteUtil noteUtil, SqlThread sqlThread) {

        List<TimeModel> readingFromFile = noteUtil.readingFromFile();

        //if DB is null
        if (sqlThread.getMaxId() == null) {
            for (int j = 0; j < readingFromFile.size() - 1; j++) {
                sqlThread.save(readingFromFile.get(j));
                return;
            }
        }

        Long maxIdFromTable = sqlThread.getMaxId();

        //last record in DB
        TimeModel model = sqlThread.findById(maxIdFromTable);

        //last record in DB and txt file
        boolean flag = false;
        for (int i = 0; i < readingFromFile.size() - 1; i++) {
            if (model.getTimers().equals(readingFromFile.get(i).getTimers())) {
                i++;
                flag = true;
                //insert records to DB
                for (; i < readingFromFile.size(); i++) {
                    sqlThread.save(readingFromFile.get(i));
                }
            }
        }

        //if no matches in the DB, then write down the entire file
        if (!flag) {
            for (int j = 0; j < readingFromFile.size() - 1; j++) {
                sqlThread.save(readingFromFile.get(j));
            }
        }
    }
}