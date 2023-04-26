package org.example;

import lombok.SneakyThrows;
import org.example.util.SqlService;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        SqlService sqlService = new SqlService();

        if (args.length != 0) {
            if (args[0].equals("-p")) {
                System.out.println(sqlService.getAll());
            }
        } else {
            TimeCreatorQueueThread timeCreatorQueueThread = new TimeCreatorQueueThread();
            timeCreatorQueueThread.start();

            while (true) {
                Thread.sleep(1);
                try {
                    if(!timeCreatorQueueThread.getTimes().isEmpty()) {
                        sqlService.save(timeCreatorQueueThread.getTimes().getFirst());
                        timeCreatorQueueThread.getTimes().removeFirst();
                    }
                } catch (Exception e) {
                    System.out.println("We have a problem");
                    Thread.sleep(5000);
                }
            }
        }
    }
}