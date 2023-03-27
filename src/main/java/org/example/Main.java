package org.example;

import org.example.dao.LocationDAO;
import org.example.dao.TemperatureDAO;
import org.example.model.Location;
import org.example.model.Temperature;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Location location1 = Location
                .builder()
                .country("Russia")
                .city("Moscow")
                .temperatureList(new ArrayList<>())
                .build();

        Location location2 = Location
                .builder()
                .country("Germany")
                .city("Berlin")
                .temperatureList(new ArrayList<>())
                .build();

        Temperature temperature1 = Temperature
                .builder()
                .temperature_value(20)
                .date(ZonedDateTime.now())
                .location(location1)
                .build();


        Temperature temperature2 = Temperature
                .builder()
                .temperature_value(33)
                .date(ZonedDateTime.now())
                .location(location1)
                .build();


        Temperature temperature3 = Temperature
                .builder()
                .temperature_value(19)
                .date(ZonedDateTime.now())
                .location(location2)
                .build();

        LocationDAO locationDAO = new LocationDAO();
        TemperatureDAO temperatureDAO = new TemperatureDAO();

//        locationDAO.save(location1);
//        locationDAO.save(location2);
//
//        temperatureDAO.save(temperature1);
//        temperatureDAO.save(temperature2);
//        temperatureDAO.save(temperature3);
    }
}