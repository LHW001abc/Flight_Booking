package com.flight.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.flight.management.model.Flight;
import com.flight.management.model.Reservation;
import com.flight.management.model.Seat;
import com.flight.management.repository.FlightRepository;
import com.flight.management.repository.ReservationRepository;
import com.flight.management.repository.SeatRepository;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FlightRepository flightRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
 
    @Autowired
    private SeatRepository seatRepository;
    

    @GetMapping("/dashboard_admin")
    public String dashboardAdmin() {
        return "dashboard_admin";
    }
    
    @GetMapping("/flight_schedules")
    public String viewFlightSchedules(Model model) {
        List<Flight> flightSchedules = flightRepository.findAll();
        model.addAttribute("flightSchedules", flightSchedules);
        return "flight_schedules_admin";
    }

    @GetMapping("/view_bookings")
    public String viewAllBookings(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        model.addAttribute("reservationobj",new Reservation());
        return "all_bookings_admin";
    }
    
    @PostMapping("/view_bookings")
    public String viewAllBookingsBasedOnDateAndFlightId(@ModelAttribute("reservationobj") Reservation reservation,
    		Model model) {
        List<Reservation> reservations;
        if(reservation.getDate()!=null && reservation.getFlightId()!=null) {
        	reservations=reservationRepository.findAllByDateAndFlightId(reservation.getDate(),reservation.getFlightId());
        }
        else if(reservation.getDate()==null && reservation.getFlightId()!=null) {
        	reservations=reservationRepository.findAllByFlightId(reservation.getFlightId());
        }
        else if(reservation.getDate()!=null && reservation.getFlightId()==null) {
        	reservations=reservationRepository.findAllByDate(reservation.getDate());
        }
        else {
        	reservations = reservationRepository.findAll();
        }
        model.addAttribute("reservations", reservations);
        return "all_bookings_admin";
    }
    
    @GetMapping("/add_flight")
    public String addFlightSchedule(@ModelAttribute("flight") Flight flight, Model model) {
        // check whether flight already exist
        model.addAttribute("flightExist", false);
        model.addAttribute("addFlightSuccess", false);
        model.addAttribute("flight", flight);
        return "add_flight_admin";
    }

    @PostMapping ("/save_added_flight")
    public String saveAddedFlightSchedule(@ModelAttribute("flight") Flight flight, Model model) {
        // check whether flight already exist
        model.addAttribute("flightExist", false);
        model.addAttribute("addFlightSuccess", false);
        if (!flightRepository.existsByFromAndToAndDate(flight.getFrom(), flight.getTo(), flight.getDate())) {
            flightRepository.save(flight);
            model.addAttribute("addFlightSuccess", true);
        } else {
            model.addAttribute("flightExist", true);
        }
        model.addAttribute("flight", flight);
        return "add_flight_admin";
    }

    @GetMapping("/delete_flight")
    public String deleteFlight(@ModelAttribute("flight") Flight flight, Model model) {
        model.addAttribute("flightExist", true);
        model.addAttribute("deleteFlightSuccess", false);
        model.addAttribute("flight", flight);
        return "delete_flight_admin";
    }

    @PostMapping("/save_delete_flight")
    public String saveDeletedFlight(@ModelAttribute("flight") Flight flight, Model model) {
        // check whether flight already exist
        model.addAttribute("flightExist", true);
        model.addAttribute("deleteFlightSuccess", false);
        Flight savedFlight = flightRepository.findByFromAndToAndDate(flight.getFrom(), flight.getTo(), flight.getDate());
        if (savedFlight != null) {
        	List<Reservation> reservations=reservationRepository.findAllByFlightId(savedFlight.getFlightId());
            List<Seat> seats = seatRepository.findAllByFlightId(savedFlight.getFlightId());
            flightRepository.delete(savedFlight);
            for(int i=0;i<seats.size();i++) {
            	reservationRepository.delete(reservations.get(i));
            	seatRepository.delete(seats.get(i));
            }
            model.addAttribute("deleteFlightSuccess", true);
        } else {
            model.addAttribute("flightExist", false);
        }
        model.addAttribute("flight", flight);
        return "delete_flight_admin";
    }

}
