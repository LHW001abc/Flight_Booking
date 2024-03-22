package com.flight.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.management.model.Seat;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByFlightId(Long flightId);

    Seat findByFlightIdAndSeatNumber(Long flightId, String seatNumber);

	void deleteBySeatNumber(String seatNumber);

	List<Seat> findAllByFlightId(Long flightId);

}
