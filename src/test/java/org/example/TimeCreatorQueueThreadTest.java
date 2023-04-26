package org.example;

import org.example.model.TimeModel;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeCreatorQueueThreadTest {

    @Test
    void testRun() throws InterruptedException {
        TimeCreatorQueueThread thread = new TimeCreatorQueueThread();
        thread.start();
        Thread.sleep(3000);

        assertFalse(thread.getTimes().isEmpty());

        for (TimeModel timeModel : thread.getTimes()) {
            assertTrue(timeModel.getTimers().isBefore(LocalTime.now().plusSeconds(1)));
            assertTrue(timeModel.getTimers().isAfter(LocalTime.now().minusSeconds(10)));
        }
    }
}