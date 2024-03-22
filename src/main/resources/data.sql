CREATE TABLE users (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      first_name VARCHAR(255),
                      last_name VARCHAR(255),
                      dob TIMESTAMP,
                      email VARCHAR(255),
                      phone VARCHAR(255),
                      address VARCHAR(255),
                      role VARCHAR(10) NOT NULL
);

CREATE TABLE flights (
                         flight_id INT AUTO_INCREMENT PRIMARY KEY,
                         departure_city VARCHAR(50) NOT NULL,
                         arrival_city VARCHAR(255) NOT NULL,
                         departure_date TIMESTAMP NOT NULL
);



CREATE TABLE Reservations (
                         reservation_id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id INT NOT NULL,
                         user_name VARCHAR(25) NOT NULL,
                         flight_id INT NOT NULL,
                         departure_city VARCHAR(50) NOT NULL,
                         arrival_city VARCHAR(255) NOT NULL,
                         departure_date TIMESTAMP NOT NULL,
                         seat_number VARCHAR(3)
);

CREATE TABLE Seats (
                         seat_id INT AUTO_INCREMENT PRIMARY KEY,
                         flight_id INT NOT NULL,
                         seat_number VARCHAR(3) NOT NULL,
                         is_occupied BOOLEAN NOT NULL

);

INSERT INTO users (username, password, first_name, last_name, dob, email, phone, address, role)
VALUES ('admin', '1234', 'John', 'Smith', '1999-01-01', 'john.smith@flight.com', '000-000-0000', '100 Fake Ave', 'admin');
INSERT INTO users (username, password, first_name, last_name, dob, email, phone, address, role)
VALUES ('guest1', '4321', 'Ada', 'Green', '1990-12-12', 'ada.green@flight.com', '000-000-0001', '999 Nowhere Street', 'user');

INSERT INTO flights (departure_city, arrival_city, departure_date)
VALUES ('Seattle', 'Chicago', '2023-04-01');
INSERT INTO flights (departure_city, arrival_city, departure_date)
VALUES ('Seattle', 'Chicago', '2023-05-01');
INSERT INTO flights (departure_city, arrival_city, departure_date)
VALUES ('Seattle', 'Chicago', '2023-06-01');

INSERT INTO reservations (user_id, user_name, flight_id, departure_city, arrival_city, departure_date, seat_number)
VALUES (2, "guest1", 1, 'Seattle', 'Chicago', '2023-04-01', '1A');
