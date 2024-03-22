package com.flight.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.management.model.Reservation;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	Reservation findByReservationId(Long reservationId);

	List<Reservation> findAllByFlightId(Long flightId);
	
	List<Reservation> findAllByDateAndFlightId(Date date,Long flightId);
	
	List<Reservation> findAllByDate(Date date);
	
    List<Reservation> findByUserId(Long userId);

    boolean existsByUserIdAndFlightIdAndSeatNumber(Long userId, Long flightId,String SeatNumber);
}
