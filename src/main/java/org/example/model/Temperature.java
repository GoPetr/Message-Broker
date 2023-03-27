package org.example.model;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "temperature")
public class Temperature {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperature_value")
    private Integer temperature_value;

    @Column(name = "date")
    private ZonedDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_location")
    private Location location;
}
