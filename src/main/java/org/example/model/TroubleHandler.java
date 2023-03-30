package org.example.model;

import java.util.List;

public class TroubleHandler {

    public synchronized void callTroubleHandler(NoteThread noteThread, SqlThread sqlThread) {

        List<TimeModel> readingFromFile = noteThread.readingFromFile();

        //когда БД пустая
        if (sqlThread.getMaxId() == null) {
            for (int j = 0; j < readingFromFile.size() - 1; j++) {
                sqlThread.save(readingFromFile.get(j));
                return;
            }
        }

        Long maxIdFromTable = sqlThread.getMaxId();
        System.out.println("reading from file = " + noteThread.readingFromFile());

        //последняя точка в БД
        System.out.println("find by id: " + sqlThread.findById(maxIdFromTable));
        TimeModel model = sqlThread.findById(maxIdFromTable);

        //последняя точка записи в БД и txt файле.
        boolean flag = false;
        for (int i = 0; i < readingFromFile.size() - 1; i++) {
            if (model.getTimers().equals(readingFromFile.get(i).getTimers())) {
                i++;
                flag = true;
                //запись в БД значений которые отсутствуют
                for (; i < readingFromFile.size(); i++) {
                    sqlThread.save(readingFromFile.get(i));
                }
            }
        }

        //Когда в БД нет совпадений, то запиши весь блокнот
        if (!flag) {
            for (int j = 0; j < readingFromFile.size() - 1; j++) {
                sqlThread.save(readingFromFile.get(j));
            }
        }
    }
}