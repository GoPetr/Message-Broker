package org.example.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BrokerThread extends Thread {
    private final SqlThread sqlThread = new SqlThread();
    private final NoteThread noteThread = new NoteThread();
    private final TimeModel timeModel = new TimeModel();
    private final TroubleHandler troubleHandler = new TroubleHandler();

    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void run() {
        while (true) {
            timeModel.setTimers(LocalTime.now());
            Listener.checkSession();
            if (Listener.flag) {
                troubleHandler.callTroubleHandler(noteThread, sqlThread);
                sqlThread.save(timeModel);
            }
            noteThread.writingToFile(timeModel.getTimers().format(timeFormat));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<TimeModel> getAllTimesForBD() {
        return sqlThread.getAll();
    }
}
