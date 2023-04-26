package org.example.util;


import org.example.service.SqlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TroubleHandlerTest {

    @BeforeEach
    void setup() {
        NoteUtil noteUtil = new NoteUtil();
        SqlService sqlService = new SqlService();
    }

    @Test
    void callTroubleHandler() {

    }
}