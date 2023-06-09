package org.example.util;

import org.example.model.TimeModel;

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

public class NoteUtil {
    private final String FILE_NAME = System.getProperty("user.dir") + "\\src\\main\\resources\\NoteSQL.txt";
    private final Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\NoteSQL.txt");

    public void writingToFile(String text) {
        try {
            createFile();
            Files.write(path, text.getBytes(), StandardOpenOption.APPEND);
            Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TimeModel> readingFromFile() {
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
        try {
            new File(FILE_NAME).createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
