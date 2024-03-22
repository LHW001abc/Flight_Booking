package com.flight.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FlightBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightBookingSystemApplication.class, args);
	}

}
