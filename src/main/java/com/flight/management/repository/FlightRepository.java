package com.flight.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.management.model.Flight;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	List<Flight> findByDate(Date date);
	
    Flight findByFromAndToAndDate(String from, String to, Date date);

    boolean existsByFromAndToAndDate(String from, String to, Date date);

}
