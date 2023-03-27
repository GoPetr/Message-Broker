package org.example.model;

import java.util.List;

public class TroubleHandler {

    public void callTroubleHandler(NoteThread noteThread, SqlThread sqlThread) {

        List<TimeModel> allFromBD = sqlThread.getAll();
        List<TimeModel> readingFromFile = noteThread.readingFromFile();

        //последняя точка в БД
        TimeModel model = allFromBD.get(allFromBD.size() - 1);

        //последняя точка записи в БД и txt файле.
        for (int i = 0; i < readingFromFile.size() - 1; i++) {
            if (model.getTimers().equals(readingFromFile.get(i).getTimers())) {
                //запись в БД значений которые отсутствуют
                for (; i++ < readingFromFile.size() - 1; i++) {
                    sqlThread.saveTimeToSQL(readingFromFile.get(i));
                }
            }
        }

        System.out.println(allFromBD.get(allFromBD.size() - 1));
        System.out.println();
        System.out.println(readingFromFile.get(readingFromFile.size() - 1));
    }
}
