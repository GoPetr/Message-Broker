package org.example.service;

import org.example.model.TimeModel;
import org.example.util.TroubleHandler;
import org.example.util.Listener;
import org.example.util.NoteUtil;

import java.util.List;

public class BrokerThread extends Thread {
    private final SqlService sqlService = new SqlService();
    private final NoteUtil noteUtil = new NoteUtil();
    private final TroubleHandler troubleHandler = new TroubleHandler();

    @Override
    public void run() {

        while (true) {
            Listener.checkSession();

            if (!Listener.flag) {
                System.out.println("No Database connection");
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } else {
                troubleHandler.callTroubleHandler(noteUtil, sqlService);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<TimeModel> getAllTimesForBD() {
        return sqlService.getAll();
    }
}
