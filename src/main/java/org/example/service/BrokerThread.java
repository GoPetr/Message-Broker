package org.example.service;

import org.example.model.TimeModel;
import org.example.util.HibernateSessionFactory;
import org.example.util.TroubleHandler;
import org.example.util.Listener;
import org.example.util.NoteUtil;

import java.util.List;
import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class BrokerThread implements Callable<TimeModel> {
    private final SqlService sqlService = new SqlService();
    private final NoteUtil noteUtil = new NoteUtil();
    private final TroubleHandler troubleHandler = new TroubleHandler();


    public TimeModel call() throws Exception {

        while (true) {
            try{
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
            }}
            catch (Exception e){
                try {
                    HibernateSessionFactory.sessionFactory = null;
                    sleep(3000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }

    public List<TimeModel> getAllTimesForBD() {
        return sqlService.getAll();
    }


}
