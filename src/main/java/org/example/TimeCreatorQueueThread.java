package org.example;

import lombok.Getter;
import lombok.SneakyThrows;
import org.example.model.TimeModel;

import java.time.LocalTime;
import java.util.ArrayDeque;

@Getter
public class TimeCreatorQueueThread extends Thread {
   private ArrayDeque<TimeModel> times = new ArrayDeque<>();

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            times.add(TimeModel.builder().timers(LocalTime.now()).build());
            System.out.println(times.size() + "     " + times);
            Thread.sleep(1000);
        }
    }
}
