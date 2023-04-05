package org.example.util;

import org.example.model.TimeModel;
import org.example.service.SqlService;

import java.util.List;

public class TroubleHandler {

    public void callTroubleHandler(NoteUtil noteUtil, SqlService sqlService) {

        List<TimeModel> readingFromFile = noteUtil.readingFromFile();

        //if DB is null
        if (sqlService.getMaxId() == null) {
            for (int j = 0; j < readingFromFile.size() - 1; j++) {
                sqlService.save(readingFromFile.get(j));
                return;
            }
        }

        Long maxIdFromTable = sqlService.getMaxId();

        //last record in DB
        TimeModel model = sqlService.findById(maxIdFromTable);

        //last record in DB and txt file
        boolean flag = false;
        for (int i = 0; i < readingFromFile.size() - 1; i++) {
            if (model.getTimers().equals(readingFromFile.get(i).getTimers())) {
                i++;
                flag = true;
                //insert records to DB
                for (; i < readingFromFile.size(); i++) {
                    sqlService.save(readingFromFile.get(i));
                }
            }
        }

        //if no matches in the DB, then write down the entire file
        if (!flag) {
            for (int j = 0; j < readingFromFile.size() - 1; j++) {
                sqlService.save(readingFromFile.get(j));
            }
        }
    }
}