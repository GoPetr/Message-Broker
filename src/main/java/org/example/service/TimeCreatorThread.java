package org.example.service;

import lombok.SneakyThrows;
import org.example.model.TimeModel;
import org.example.util.NoteUtil;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

public class TimeCreatorThread implements Callable<TimeModel> {
    private final TimeModel timeModel = new TimeModel();
    private final NoteUtil noteUtil = new NoteUtil();
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    @SneakyThrows
    @Override
    public TimeModel call() {
        while (true) {
            timeModel.setTimers(LocalTime.now());
            noteUtil.writingToFile(timeModel.getTimers().format(timeFormat));
            Thread.sleep(1000);
        }
    }
}
