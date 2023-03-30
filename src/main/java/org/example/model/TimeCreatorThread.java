package org.example.model;

import lombok.Getter;
import lombok.SneakyThrows;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

@Getter
public class TimeCreatorThread implements Callable<TimeModel> {
    private final TimeModel timeModel = new TimeModel();
    private final NoteThread noteThread = new NoteThread();
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    @SneakyThrows
    @Override
    public TimeModel call() {
        while (true) {
            timeModel.setTimers(LocalTime.now());
            noteThread.writingToFile(timeModel.getTimers().format(timeFormat));

            Thread.sleep(1000);
        }
    }
}
