package org.example.model;

import java.util.List;

public class TroubleHandler {

    public void callTroubleHandler(NoteThread noteThread, SqlThread sqlThread) {

        if (sqlThread.getMaxId() == null) {
            return;
        }
        Long maxIdFromTable = sqlThread.getMaxId();
        System.out.println("reading from file = " + noteThread.readingFromFile());
        List<TimeModel> readingFromFile = noteThread.readingFromFile();

        //последняя точка в БД
        System.out.println("find by id: " + sqlThread.findById(maxIdFromTable));
        TimeModel model = sqlThread.findById(maxIdFromTable);

        //последняя точка записи в БД и txt файле.
        for (int i = 0; i < readingFromFile.size() - 1; i++) {
            if (model.getTimers().equals(readingFromFile.get(i).getTimers())) {
                i++;
                //запись в БД значений которые отсутствуют
                for (; i < readingFromFile.size(); i++) {
                    sqlThread.save(readingFromFile.get(i));
                }
            }
        }
    }
}
