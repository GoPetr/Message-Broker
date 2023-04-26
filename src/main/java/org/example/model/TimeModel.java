package org.example.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "time")
public class TimeModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "time_value")
    private LocalTime timers;

    @Override
    public String toString() {
        return timers.toString();
    }
}

