package org.example.util;

import org.example.model.TimeModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
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

        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .map(str -> {
                        TimeModel timeModel = new TimeModel();
                        timeModel.setTimers(LocalTime.parse(str));
                        return timeModel;
                    })
                    .collect(Collectors.toList());
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
