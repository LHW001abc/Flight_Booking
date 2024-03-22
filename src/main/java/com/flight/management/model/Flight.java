package com.flight.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "flights")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId; // unique Id

	@Column(name ="departure_city")
    private String from;

    @Column(name ="arrival_city")
    private String to;

    @Column(name ="departure_date")
//    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
