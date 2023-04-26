package org.example;

import lombok.SneakyThrows;
import org.example.model.SqlWorker;
import org.example.model.TimeCreatorQueueThread;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        TimeCreatorQueueThread timeCreatorQueueThread = new TimeCreatorQueueThread();
        timeCreatorQueueThread.start();
        SqlWorker sqlWorker = new SqlWorker();

        while (true) {
            try {
                sqlWorker.save(timeCreatorQueueThread.getTimes().getFirst());
                timeCreatorQueueThread.getTimes().removeFirst();

            } catch (Exception e) {
                System.out.println("EBUSHKI VOROBUSHKI");
                Thread.sleep(1500);
            }
        }
    }
}
