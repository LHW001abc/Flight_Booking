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
@Table(name = "reservations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "user_name")
    private String userName;

	@Column(name = "flight_id")
    private Long flightId;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name ="departure_city")
    private String from;

    @Column(name ="arrival_city")
    private String to;

    @Column(name ="departure_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;


}
