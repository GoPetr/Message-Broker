package org.example.model;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BrokerThread extends Thread {
    private final SqlThread sqlThread = new SqlThread();
    private final NoteThread noteThread = new NoteThread();
    private final TroubleHandler troubleHandler = new TroubleHandler();
    private final TimeCreatorThread timeCreatorThread = new TimeCreatorThread();

    @Override
    public void run() {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(timeCreatorThread);

        while (true) {
            Listener.checkSession();

            if (!Listener.flag) {
                System.out.println("No DB Connection.");
            }

            if (Listener.flag) {
                troubleHandler.callTroubleHandler(noteThread, sqlThread);
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
