package org.example;

import org.example.model.TimeModel;
import org.example.service.BrokerThread;
import org.example.service.TimeCreatorThread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 0) {
            if (args[0].equals("-p")) {
                BrokerThread brokerThread = new BrokerThread();
                System.out.println(brokerThread.getAllTimesForBD());
            }
        } else {
            BrokerThread brokerThread = new BrokerThread();
            TimeCreatorThread timeCreatorThread = new TimeCreatorThread();
            ExecutorService service = Executors.newCachedThreadPool();

            List<Future<TimeModel>> futures = service.invokeAll(List.of(brokerThread, timeCreatorThread));
            futures.stream().map(cf -> {
                try {
                    return cf.get();
                } catch (Exception e) {
                    return null;
                }
            }).collect(Collectors.toList());
        }
    }
}
