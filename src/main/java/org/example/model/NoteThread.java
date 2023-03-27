package org.example.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NoteThread extends Thread {
    private final String FILE_NAME = "C:\\Users\\GoPetr\\Documents\\Java Projects\\Message Broker\\src\\main\\resources\\NoteSQL.txt";
    private final Path path = Paths.get("C:\\Users\\GoPetr\\Documents\\Java Projects\\Message Broker\\src\\main\\resources\\NoteSQL.txt");

    @Override
    public void run() {
        createFile();
    }

    public void writingToFile(String text) {
        try {
            createFile();
            Files.write(path, text.getBytes(), StandardOpenOption.APPEND);
            Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TimeModel> readingFromFile() { //todo ОБЯЗАТЕЛЬНО УЗНАТЬ КАК ЭТО ДЕЛАТЬ СТРИМАМИ
        createFile();
        List<String> result;
        List<TimeModel> returnList = new ArrayList<>();
        try (Stream<String> lines = Files.lines(path)) {
            result = lines.collect(Collectors.toList());
            for (String str : result) {
                TimeModel timeModel = new TimeModel();
                timeModel.setTimers(LocalTime.parse(str));
                returnList.add(timeModel);
            }
            return returnList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void createFile() {
        File newFile = new File(FILE_NAME);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
