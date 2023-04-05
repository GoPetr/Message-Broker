package org.example.model;

import java.util.List;

public class BrokerThread extends Thread {
    private final SqlThread sqlThread = new SqlThread();
    private final NoteUtil noteUtil = new NoteUtil();
    private final TroubleHandler troubleHandler = new TroubleHandler();

    @Override
    public void run() {

        while (true) {
            Listener.checkSession();

            if (!Listener.flag) {
                System.out.println("Flag is FALSE.");
            }

            if (Listener.flag) {
                troubleHandler.callTroubleHandler(noteUtil, sqlThread);
            }

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
