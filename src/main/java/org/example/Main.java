package org.example;

import org.example.service.BrokerThread;
import org.example.service.TimeCreatorThread;
import org.example.util.HibernateSessionFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 0) {
            if (args[0].equals("-p")) {
                BrokerThread brokerThread = new BrokerThread();
                System.out.println(brokerThread.getAllTimesForBD());
            }
        } else {
            TimeCreatorThread timeCreatorThread = new TimeCreatorThread();
            ExecutorService service = Executors.newCachedThreadPool();
            service.submit(timeCreatorThread);

            while (true) {
                BrokerThread brokerThread = new BrokerThread();
                brokerThread.setName("Broker Thread");
                brokerThread.start();
                brokerThread.join();
                System.out.println("No Database connection.");
                HibernateSessionFactory.sessionFactory = null;
            }
        }
    }
}
